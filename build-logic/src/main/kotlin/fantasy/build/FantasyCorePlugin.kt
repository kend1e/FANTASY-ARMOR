package fantasy.build

import fantasy.definitions.armor

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*

class FantasyCorePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("java-library")
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(17))
        }
        val sharedSources = configurations.consumable("sharedSources")
        val sharedResources = configurations.consumable("sharedResources")
        artifacts.add(sharedSources.name, layout.projectDirectory.dir("src/main/java"))
        artifacts.add(sharedResources.name, tasks.named("processResources")) {
            type = "directory"
        }
        val defaults = tasks.register<GenerateResources>("generateArmorDefaults") {
            contents.set(defaultResources(armor))
            outputDirectory.set(layout.buildDirectory.dir("generated/resources/defaults"))
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            resources.srcDir(defaults.flatMap { it.outputDirectory })
        }
    }
}
