package fantasy.build

import com.google.gson.GsonBuilder
import fantasy.definitions.armorTests
import fantasy.definitions.armor
import fantasy.definitions.testVersions
import fantasy.definitions.targets
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*
import javax.xml.parsers.DocumentBuilderFactory

fun Project.configureArmorTests(): SourceSet {
    validateTestCatalog(armorTests, targets)
    val target = extensions.getByType<Target>()
    val version = testVersions[target.minecraft] ?: error("No test sources declared for ${target.name}")
    val registration = requireNotNull(version.registrations[target.loader]) { "No test registration declared for ${target.name}" }
    val cases = armorTests.filter { it.appliesTo(target) }
    val profile = testProfile(providers.gradleProperty("testProfile").getOrElse("defaults"), armor)
    val sourceSets = extensions.getByType<SourceSetContainer>()
    val main = sourceSets.getByName("main")
    val tests = sourceSets.getByName("test")
    val sourceRoot = rootProject.layout.projectDirectory
    tests.java.srcDir(sourceRoot.dir("tests/common/java"))
    tests.java.srcDir(sourceRoot.dir(if (target.loader == Loader.FORGE && registration != TestRegistration.FORGE_FUNCTIONS)
        "tests/mock-player/java" else "tests/server-player/java"))
    version.sources.forEach { tests.java.srcDir(sourceRoot.dir("tests/minecraft/$it/java")) }
    tests.java.srcDir(sourceRoot.dir("loaders/${target.loader.id}/src/test/java"))
    configurations.getByName(tests.implementationConfigurationName)
        .extendsFrom(configurations.getByName(main.implementationConfigurationName))
    configurations.getByName(tests.runtimeOnlyConfigurationName)
        .extendsFrom(configurations.getByName(main.runtimeOnlyConfigurationName))
    tests.compileClasspath += main.output + main.compileClasspath
    tests.runtimeClasspath += main.output + main.runtimeClasspath
    val generatedSources = tasks.register<GenerateFiles>("generateTestSources") {
        contents.set(buildMap {
            put("net/kenddie/fantasyarmor/test/ArmorTests.java", registration.entrypoint(cases))
            if (!registration.fabric) {
                val loaderPackage = if (target.loader == Loader.NEOFORGE) "net.neoforged.fml.common.Mod" else "net.minecraftforge.fml.common.Mod"
                put("net/kenddie/fantasyarmor/test/TestMod.java", """
                    package net.kenddie.fantasyarmor.test;
                    @$loaderPackage("fantasy_armor_tests")
                    public final class TestMod {
                        public TestMod(${if (registration == TestRegistration.FORGE_FUNCTIONS) "net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext context" else ""}) throws javax.xml.parsers.ParserConfigurationException {
                            TestReports.install();
                            ${if (registration == TestRegistration.FORGE_FUNCTIONS) "ArmorTests.register(context.getModBusGroup());" else ""}
                        }
                    }
                """.trimIndent())
            }
        })
        outputDirectory.set(layout.buildDirectory.dir("generated/sources/test"))
    }
    val metadata = tasks.register<GenerateFiles>("generateTestMetadata") {
        contents.set(buildMap {
            if (registration.fabric) {
                put("fabric.mod.json", GsonBuilder().setPrettyPrinting().create().toJson(mapOf(
                    "schemaVersion" to 1, "id" to "fantasy_armor_tests", "version" to "1.0", "environment" to "*",
                    "entrypoints" to mapOf("fabric-gametest" to listOf("net.kenddie.fantasyarmor.test.ArmorTests")),
                    "depends" to mapOf("fantasy_armor" to "*"),
                )))
            } else {
                val file = if (target.loader == Loader.NEOFORGE) "neoforge.mods.toml" else "mods.toml"
                put("META-INF/$file", """
                    modLoader="javafml"
                    loaderVersion="${target.loaderRange}"
                    license="DSMSLv2"
                    [[mods]]
                    modId="fantasy_armor_tests"
                    version="1.0"
                    displayName="Fantasy Armor Tests"
                """.trimIndent())
            }
            put("armor_test_profile.json", GsonBuilder().create().toJson(profile))
            put("armor_test_recipes.json", GsonBuilder().create().toJson(armor.map { mapOf("id" to it.id, "template" to it.upgradeIngredient, "addition" to it.addition) }))
            if (registration == TestRegistration.FORGE_FUNCTIONS) {
                cases.forEach { case ->
                    put("data/fantasy_armor_tests/test_instance/${case.id}.json", GsonBuilder().create().toJson(mapOf(
                        "type" to "minecraft:function", "function" to "fantasy_armor_tests:${case.id}",
                        "environment" to "minecraft:default", "structure" to "forge:empty3x3x3", "max_ticks" to 100,
                    )))
                }
            }
            put("pack.mcmeta", GsonBuilder().create().toJson(mapOf("pack" to buildMap<String, Any> {
                put("pack_format", version.packFormat)
                put("description", "Fantasy Armor test scenes")
                if (version.packFormat >= 82) {
                    put("min_format", version.packFormat)
                    put("max_format", version.packFormat)
                }
            })))
        })
        outputDirectory.set(layout.buildDirectory.dir("generated/resources/test/metadata"))
    }
    val structures = tasks.register<org.gradle.api.tasks.Sync>("prepareTestStructures") {
        from(sourceRoot.file("tests/resources/empty.nbt")) { into("data/fantasy_armor_tests/${version.structures}") }
        into(layout.buildDirectory.dir("generated/resources/test/scenes"))
    }
    tests.java.srcDir(generatedSources.flatMap { it.outputDirectory })
    tests.resources.srcDir(metadata.flatMap { it.outputDirectory })
    tests.resources.srcDir(structures)
    tasks.named<Test>("test") {
        exclude("net/kenddie/fantasyarmor/test/**")
        // Discovery is checked in the Minecraft report.
        failOnNoDiscoveredTests = false
        description = "Runs server tests for ${target.name}."
    }
    return tests
}

fun Project.verifyArmorTestReport(taskName: String): Provider<RegularFile> {
    val target = extensions.getByType<Target>()
    val version = testVersions[target.minecraft] ?: error("No test sources declared for ${target.name}")
    val registration = requireNotNull(version.registrations[target.loader]) { "No test registration declared for ${target.name}" }
    val expected = armorTests.filter { it.appliesTo(target) }.map { registration.reportName(it.id) }.toSet() + registration.additionalReportNames
    val profile = testProfile(providers.gradleProperty("testProfile").getOrElse("defaults"), armor)
    val prepare = tasks.register<PrepareTestRun>("prepareTestRun") {
        val files = profile.files(target.loader, version.serverConfigDirectory)
        filesToRemove.set(files.keys)
        contents.set(if (profile.id == "defaults") emptyMap() else files)
        runDirectory.set(layout.buildDirectory.dir("run/test"))
    }
    val report = layout.buildDirectory.file("reports/test/${profile.id}/results.xml")
    tasks.matching { it.name == taskName }.configureEach {
        outputs.file(report)
        outputs.upToDateWhen { false }
        dependsOn(prepare, tasks.named("classes"), tasks.named("testClasses"))
        doFirst {
            val file = report.get().asFile
            file.parentFile.mkdirs()
            if (file.exists() && !file.delete()) throw GradleException("Cannot clear previous test report: $file")
        }
        doLast {
            val file = report.get().asFile
            if (!file.isFile) throw GradleException("Tests did not produce their report: $file")
            val factory = DocumentBuilderFactory.newInstance()
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
            val document = factory.newDocumentBuilder().parse(file)
            val cases = document.getElementsByTagName("testcase")
            val actual = (0 until cases.length).map { (cases.item(it) as org.w3c.dom.Element).getAttribute("name") }
            val failed = document.getElementsByTagName("failure").length + document.getElementsByTagName("error").length
            val skipped = document.getElementsByTagName("skipped").length
            if (actual.toSet() != expected || actual.size != expected.size || failed != 0 || skipped != 0) {
                throw GradleException("Expected successful tests $expected; got $actual, failed $failed, skipped $skipped. See $file")
            }
        }
    }
    tasks.named("test") { dependsOn(taskName) }
    return report
}
