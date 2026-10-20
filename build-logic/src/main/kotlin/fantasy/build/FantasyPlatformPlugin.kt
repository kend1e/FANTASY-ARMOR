package fantasy.build

import fantasy.definitions.targets
import fantasy.definitions.armor
import fantasy.definitions.languages
import fantasy.definitions.craftingRecipes
import fantasy.gradle.PlatformExtension
import fantasy.gradle.ResourcesExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.*

class FantasyPlatformPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val target = targets.single { it.path == path }
        pluginManager.apply("fantasy.build.platform")
        pluginManager.apply("fantasy.build.resources")
        version = target.modVersion
        val metadata = ModMetadata.read(providers)
        require(metadata.id == RESOURCE_NAMESPACE) { "mod_id must match resource namespace $RESOURCE_NAMESPACE" }
        group = metadata.group
        extensions.add(ModMetadata::class.java, "modMetadata", metadata)
        extensions.add(Target::class.java, "platformTarget", target)
        extensions.configure<PlatformExtension> {
            javaVersion.set(target.java)
            archiveName.set(metadata.id)
            sources.from(rootProject.layout.projectDirectory.dir("minecraft/${target.minecraft}/src/main/java"))
            sources.from(rootProject.layout.projectDirectory.dir("loaders/${target.loader.id}/src/main/java"))
        }
        dependencies.add("sharedSourcesDependencies", dependencies.project(mapOf("path" to ":core", "configuration" to "sharedSources")))
        dependencies.add("sharedResourcesDependencies", dependencies.project(mapOf("path" to ":core", "configuration" to "sharedResources")))
        configureResources(target)
        tasks.named("generateResources") {
            dependsOn(configurations.named("sharedResourceFiles"))
        }
    }

    private fun Project.configureResources(target: Target) {
        val resourcesRoot = rootProject.layout.projectDirectory.dir("resources")
        val shared = tasks.register<Sync>("prepareSharedResources") {
            from(resourcesRoot) { exclude("assets/$RESOURCE_NAMESPACE/geo/**") }
            from(resourcesRoot.dir("assets/$RESOURCE_NAMESPACE/geo")) {
                val modelDirectory = "assets/$RESOURCE_NAMESPACE/${target.geometryDirectory}"
                into(modelDirectory)
            }
            into(layout.buildDirectory.dir("generated/resources/shared"))
            duplicatesStrategy = DuplicatesStrategy.FAIL
        }
        extensions.configure<ResourcesExtension> {
            sources.from(shared)
            generate("generateContentResources") {
                contents.set(contentResources(armor, languages, craftingRecipes, target.resources))
                outputDirectory.set(layout.buildDirectory.dir("generated/resources/content"))
            }
        }
        // Also covers loader run preparation tasks created after this plugin.
        tasks.matching { it.name == "runClient" || it.name == "runServer" }.configureEach {
            dependsOn(tasks.named("classes"))
        }
    }
}
