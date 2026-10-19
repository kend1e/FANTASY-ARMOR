package fantasy.build

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class FantasyFabricPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.platform")
        pluginManager.apply("maven-publish")
        repositories {
            maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
                name = "GeckoLib"
                content {
                    includeGroupByRegex("software\\.bernie.*")
                    includeGroup("com.eliotlash.mclib")
                }
            }
            maven("https://maven.shedaniel.me/")
            maven("https://maven.terraformersmc.com/releases/")
        }

        val target = extensions.getByType<Target>()
        val metadata = mapOf(
            "id" to providers.gradleProperty("mod_id").get(),
            "version" to target.modVersion,
            "name" to providers.gradleProperty("mod_name").get(),
            "description" to providers.gradleProperty("mod_description").get().replace("\\n", "\n"),
            "authors" to providers.gradleProperty("mod_authors").get().split(",").map(String::trim),
            "contact" to mapOf(
                "homepage" to providers.gradleProperty("mod_homepage").get(),
                "sources" to providers.gradleProperty("mod_sources").get(),
            ),
            "license" to providers.gradleProperty("mod_license").get(),
            "icon" to providers.gradleProperty("mod_icon").get(),
            "environment" to providers.gradleProperty("mod_environment").get(),
            "depends" to mapOf(
                "fabricloader" to target.loaderRange,
                "minecraft" to target.minecraftRange,
                "java" to ">=${target.java}",
                "fabric-api" to target.fabricApiRange,
                "geckolib" to target.geckolibRange,
                "cloth-config2" to target.clothConfigRange,
            ),
        )
        val template = providers.fileContents(layout.projectDirectory.file("src/main/templates/fabric.mod.json")).asText
        val generateModMetadata = tasks.register<GenerateResources>("generateModMetadata") {
            contents.set(template.map { text ->
                val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
                val json = JsonParser.parseString(text).asJsonObject
                metadata.forEach { (name, value) ->
                    require(!json.has(name)) { "$name must be configured in targets.kt or gradle.properties, not fabric.mod.json" }
                    json.add(name, gson.toJsonTree(value))
                }
                mapOf("fabric.mod.json" to gson.toJson(json) + "\n")
            })
            outputDirectory.set(layout.buildDirectory.dir("generated/resources/metadata"))
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            resources.srcDir(generateModMetadata.flatMap { it.outputDirectory })
        }
        tasks.named("generateResources") { dependsOn(generateModMetadata) }

        extensions.configure<JavaPluginExtension> { withSourcesJar() }

        val archiveName = extensions.getByType<BasePluginExtension>().archivesName
        tasks.named<Jar>("jar") {
            val licenseName = "LICENSE.md_${archiveName.get()}"
            inputs.property("archivesName", archiveName)
            from(rootProject.layout.projectDirectory.file("LICENSE.md")) {
                rename { licenseName }
            }
        }

        extensions.configure<PublishingExtension> {
            publications {
                create<MavenPublication>("mavenJava") {
                    artifactId = archiveName.get()
                    from(components["java"])
                }
            }
        }
    }
}
