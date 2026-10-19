package fantasy.build

import fantasy.definitions.targets
import fantasy.definitions.armor
import fantasy.definitions.languages
import fantasy.definitions.craftingRecipes
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
        val metadata = ModMetadata.read(providers)
        require(metadata.id == RESOURCE_NAMESPACE) { "mod_id must match resource namespace $RESOURCE_NAMESPACE" }
        group = metadata.group
        extensions.add(ModMetadata::class.java, "modMetadata", metadata)
        extensions.add(Target::class.java, "platformTarget", target)
        extensions.configure<BasePluginExtension> {
            archivesName.set(metadata.id)
        }
        val coreSourcesDependencies = configurations.dependencyScope("coreSourcesDependencies")
        val coreSources = configurations.resolvable("coreSources") {
            extendsFrom(coreSourcesDependencies.get())
            isTransitive = false
        }
        val coreResourcesDependencies = configurations.dependencyScope("coreResourcesDependencies")
        val coreResources = configurations.resolvable("coreResources") {
            extendsFrom(coreResourcesDependencies.get())
            isTransitive = false
        }
        dependencies.add(coreSourcesDependencies.name, dependencies.project(mapOf("path" to ":core", "configuration" to "sharedSources")))
        dependencies.add(coreResourcesDependencies.name, dependencies.project(mapOf("path" to ":core", "configuration" to "sharedResources")))
        extensions.getByType<SourceSetContainer>().named("main") {
            java.srcDir(coreSources)
            resources.srcDir(coreResources)
        }
        configureJava(target)
        configureResources(target)
        tasks.register("generateResources") {
            group = "build"
            description = "Prepares shared resources, content, defaults, and mod metadata."
            dependsOn(coreResources, tasks.named("prepareSharedResources"), tasks.named("generateContentResources"))
        }
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
            from(resourcesRoot) { exclude("assets/$RESOURCE_NAMESPACE/geo/**") }
            from(resourcesRoot.dir("assets/$RESOURCE_NAMESPACE/geo")) {
                val modelDirectory = "assets/$RESOURCE_NAMESPACE/${target.geometryDirectory}"
                into(modelDirectory)
            }
            into(layout.buildDirectory.dir("generated/resources/shared"))
            duplicatesStrategy = DuplicatesStrategy.FAIL
        }
        val content = tasks.register<GenerateResources>("generateContentResources") {
            contents.set(contentResources(armor, languages, craftingRecipes, target.resources))
            outputDirectory.set(layout.buildDirectory.dir("generated/resources/content"))
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            java.srcDir(rootProject.layout.projectDirectory.dir("minecraft/${target.minecraft}/src/main/java"))
            java.srcDir(rootProject.layout.projectDirectory.dir("loaders/${target.loader.id}/src/main/java"))
            resources.srcDir(shared)
            resources.srcDir(content.flatMap { it.outputDirectory })
        }
        tasks.named<Copy>("processResources") { duplicatesStrategy = DuplicatesStrategy.FAIL }
        // Also covers loader run preparation tasks created after this plugin.
        tasks.matching { it.name == "runClient" || it.name == "runServer" }.configureEach {
            dependsOn(tasks.named("classes"))
        }
    }
}
