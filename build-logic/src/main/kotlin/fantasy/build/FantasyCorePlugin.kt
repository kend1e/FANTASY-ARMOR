package fantasy.build

import fantasy.definitions.armor
import fantasy.gradle.PlatformExtension
import fantasy.gradle.ResourcesExtension

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class FantasyCorePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.build.shared")
        extensions.configure<PlatformExtension> { javaVersion.set(17) }
        extensions.configure<ResourcesExtension> {
            generate("generateArmorDefaults") {
                contents.set(defaultResources(armor))
                outputDirectory.set(layout.buildDirectory.dir("generated/resources/defaults"))
            }
        }
    }
}
