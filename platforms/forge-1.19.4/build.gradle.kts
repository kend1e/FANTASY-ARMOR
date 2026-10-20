import fantasy.gradle.GeckoLib
import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport
import net.minecraftforge.renamer.gradle.RenameJar
import fantasy.gradle.Target
import fantasy.build.registerReleaseExport
import fantasy.gradle.ModMetadata

plugins {
    id("fantasy.forge")
    java
    idea
    eclipse
    alias(libs.plugins.forgeGradle)
    alias(libs.plugins.renamer)
}

val platform = extensions.getByType<Target>()
val metadata = extensions.getByType<ModMetadata>()
val modId = metadata.id
val modAuthors = metadata.authors

minecraft {
    mappings("official", platform.minecraft)
    runs {
        configureEach {
            workingDir.set(layout.projectDirectory.dir("run"))
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", modId)
        }
        register("client")
        register("server") { args("--nogui") }
        register("gameTestServer")
        register("data") {
            workingDir.set(layout.projectDirectory.dir("run-data"))
            args("--mod", modId, "--all", "--output",
                layout.buildDirectory.dir("generated/resources/datagen").get(),
                "--existing", layout.projectDirectory.dir("src/main/resources"))
        }
    }
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
    mavenCentral()

}

dependencies {
    // FG 7's detached launch configuration needs explicit variant attributes.
    // Configure before passing to FG: its Action overload casts to a Groovy closure owner.
    val forge = create("net.minecraftforge:forge:${platform.minecraft}-${platform.loaderVersion}") as ExternalModuleDependency
    forge.attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(Attribute.of("net.minecraftforge.mappings.channel", String::class.java), "official")
        attribute(Attribute.of("net.minecraftforge.mappings.version", String::class.java), platform.minecraft)
    }
    implementation(minecraft.dependency(project.name, forge))
    implementation(renamer.dependency(platform.dependency<GeckoLib>().notation(platform)))
    implementation(libs.mclib)
}

tasks.jar {
    manifest.attributes(
        "Specification-Title" to modId,
        "Specification-Vendor" to modAuthors,
        "Specification-Version" to "1",
        "Implementation-Title" to project.name,
        "Implementation-Version" to archiveVersion,
        "Implementation-Vendor" to modAuthors,
    )
}

sourceSets.main {
    resources.srcDir(layout.buildDirectory.dir("generated/resources/datagen"))
}

// Legacy Forge releases use SRG names, while development uses official names.
renamer.mappings.from(minecraft.getDependency(project.name).toSrgFile)
renamer.classes(tasks.jar) { archiveClassifier.set("srg") }

val devRefmapMappings = renamer.convert(
    "devRefmapMappings", minecraft.getDependency(project.name).toSrgFile, "srg"
) { reverse.set(true) }
minecraft.runs.configureEach {
    systemProperty("mixin.env.remapRefMap", "true")
    systemProperty("mixin.env.refMapRemappingFile",
        devRefmapMappings.flatMap { it.output }.map { it.asFile.absolutePath })
}
tasks.matching { it.name == "runClient" || it.name == "runServer" }.configureEach {
    dependsOn(devRefmapMappings)
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") { artifact(tasks.named("renameJar")) }
    }
    repositories { maven { url = uri(layout.projectDirectory.dir("mcmodsrepo")) } }
}

registerReleaseExport(tasks.named<RenameJar>("renameJar").flatMap { it.output })

val tests = configureArmorTests()
val testReport = verifyArmorTestReport("runTestGameTestServer")
minecraft.runs.named("gameTestServer") {
    with(tests) {
        systemProperty("forge.logging.console.level", "info")
        mods.register(modId) { source(sourceSets.main.get()) }
        mods.register("fantasy_armor_tests") { source(tests) }
        systemProperty("forge.enabledGameTestNamespaces", "fantasy_armor_tests")
        systemProperty("fantasy_armor.test.report", testReport.get().asFile.absolutePath)
        workingDir.set(layout.buildDirectory.dir("run/test"))
    }
}
tasks.matching { it.name == "runTestGameTestServer" }.configureEach {
    dependsOn(devRefmapMappings)
}
