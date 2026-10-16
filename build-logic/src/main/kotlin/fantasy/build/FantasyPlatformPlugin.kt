package fantasy.build

import fantasy.definitions.targets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.Sync
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*

class FantasyPlatformPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val target = targets.single { it.path == path }
        pluginManager.apply("java")
        version = target.modVersion
        val properties = mapOf(
            "minecraft_version" to target.minecraft, "minecraft_version_range" to target.minecraftRange,
            "mod_version" to target.modVersion, "geckolib_version" to target.geckolib,
            "forge_version" to target.loaderVersion, "forge_version_range" to target.loaderRange,
            "neo_version" to target.loaderVersion, "loader_version" to target.loaderVersion,
            "loader_version_range" to target.loaderRange, "mapping_channel" to target.mappingChannel,
            "mapping_version" to target.mappingVersion, "fabric_version" to target.fabricApi,
            "fabric_api_version" to target.fabricApi, "cloth_config_version" to target.clothConfig,
            "parchment_minecraft_version" to target.minecraft, "parchment_mappings_version" to target.parchment,
            "maven_group" to property("mod_group_id"), "archives_base_name" to property("mod_id"),
        )
        properties.forEach { (name, value) -> extensions.extraProperties.set(name, value) }
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(target.java))
        }
        tasks.withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            options.release.set(target.java)
        }
        tasks.withType<AbstractArchiveTask>().configureEach {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
        }

        val resourcesRoot = rootProject.layout.projectDirectory.dir("resources")
        val shared = tasks.register<Sync>("prepareSharedResources") {
            from(resourcesRoot) { exclude("assets/fantasy_armor/geo/**") }
            from(resourcesRoot.dir("assets/fantasy_armor/geo")) {
                into(if (target.resources == ResourceProfile.ITEM_DEFINITIONS) "assets/fantasy_armor/geckolib/models" else "assets/fantasy_armor/geo")
            }
            into(layout.buildDirectory.dir("generated/resources/shared"))
            duplicatesStrategy = DuplicatesStrategy.FAIL
        }
        val content = tasks.register<GenerateResources>("generateContentResources") {
            contents.set(contentResources(target.resources))
            outputDirectory.set(layout.buildDirectory.dir("generated/resources/content"))
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            java.srcDir(rootProject.layout.projectDirectory.dir("minecraft/${target.minecraft}/src/main/java"))
            resources.srcDir(shared)
            resources.srcDir(content.flatMap { it.outputDirectory })
        }
        tasks.named<Copy>("processResources") { duplicatesStrategy = DuplicatesStrategy.FAIL }
        // Also covers loader run preparation tasks created after this plugin.
        tasks.matching { it.name == "runClient" || it.name == "runServer" }.configureEach {
            dependsOn(tasks.named("classes"))
        }

        val releaseVersion = target.modVersion.removeSuffix("-${target.minecraft}")
        val releaseName = "fantasy-armor-$releaseVersion-${target.minecraft}-${target.loader.id}.jar"
        val destination = rootProject.layout.projectDirectory.dir("dist")
        val invalidate = tasks.register<Delete>("invalidateRelease") {
            delete(destination.file(releaseName))
        }
        // A failed recompilation must not leave a stale artifact for this target in dist.
        tasks.named("compileJava") { dependsOn(invalidate) }
        tasks.named("processResources") { dependsOn(invalidate) }
        val producer = when {
            target.loader == Loader.FABRIC -> "remapJar"
            target.legacyForge -> "renameJar"
            else -> "jar"
        }
        val export = tasks.register<Copy>("exportRelease") {
            group = "build"
            description = "Copies the installable ${target.name} artifact into root dist."
            if (target.legacyForge) {
                // RenameJar exposes one @OutputFile; it is not an AbstractArchiveTask.
                from(tasks.named(producer))
            } else {
                from(tasks.named<AbstractArchiveTask>(producer).flatMap { it.archiveFile })
            }
            into(destination)
            rename { releaseName }
        }
        tasks.named("assemble") { dependsOn(export) }
    }
}
