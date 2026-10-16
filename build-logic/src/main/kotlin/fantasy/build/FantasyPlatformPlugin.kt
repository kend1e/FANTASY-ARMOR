package fantasy.build

import fantasy.definitions.targets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Copy
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
        group = providers.gradleProperty("mod_group_id").get()
        extensions.add(Target::class.java, "platformTarget", target)
        extensions.configure<BasePluginExtension> {
            archivesName.set(providers.gradleProperty("mod_id"))
        }
        configureJava(target)
        configureResources(target)
        configureRelease(target)
    }

    private fun Project.configureJava(target: Target) {
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
    }

    private fun Project.configureResources(target: Target) {
        val resourcesRoot = rootProject.layout.projectDirectory.dir("resources")
        val shared = tasks.register<Sync>("prepareSharedResources") {
            from(resourcesRoot) { exclude("assets/fantasy_armor/geo/**") }
            from(resourcesRoot.dir("assets/fantasy_armor/geo")) {
                val modelDirectory = when (target.resources) {
                    ResourceProfile.ITEM_DEFINITIONS -> "assets/fantasy_armor/geckolib/models"
                    else -> "assets/fantasy_armor/geo"
                }
                into(modelDirectory)
            }
            into(layout.buildDirectory.dir("generated/resources/shared"))
            duplicatesStrategy = DuplicatesStrategy.FAIL
        }
        val content = tasks.register<GenerateResources>("generateContentResources") {
            contents.set(contentResources(target.resources, target.loader))
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
    }

    private fun Project.configureRelease(target: Target) {
        val releaseVersion = target.modVersion.removeSuffix("-${target.minecraft}")
        val releaseName = "fantasy-armor-$releaseVersion-${target.minecraft}-${target.loader.id}.jar"
        val releaseFile = rootProject.layout.projectDirectory.file("dist/$releaseName")
        val producer = when {
            target.loader == Loader.FABRIC -> "remapJar"
            target.legacyForge -> "renameJar"
            else -> "jar"
        }
        val export = tasks.register<ExportRelease>("exportRelease") {
            group = "build"
            description = "Copies the installable ${target.name} artifact into root dist."
            if (target.legacyForge) {
                // RenameJar exposes one @OutputFile; it is not an AbstractArchiveTask.
                artifact.set(layout.file(tasks.named(producer).map { it.outputs.files.singleFile }))
            } else {
                artifact.set(tasks.named<AbstractArchiveTask>(producer).flatMap { it.archiveFile })
            }
            destination.set(releaseFile)
        }
        tasks.named("assemble") { dependsOn(export) }
    }
}
