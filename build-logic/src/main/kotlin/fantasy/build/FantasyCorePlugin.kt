package fantasy.build

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
        val defaults = tasks.register<GenerateResources>("generateArmorDefaults") {
            contents.set(defaultResources())
            outputDirectory.set(layout.buildDirectory.dir("generated/resources/defaults"))
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            resources.srcDir(defaults.flatMap { it.outputDirectory })
        }
    }
}
