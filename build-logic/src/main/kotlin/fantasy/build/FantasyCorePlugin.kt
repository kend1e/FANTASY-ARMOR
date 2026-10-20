package fantasy.build

import fantasy.definitions.armor
import fantasy.gradle.content.armor.defaultResources
import fantasy.gradle.PlatformExtension
import fantasy.gradle.generateModConstants
import fantasy.definitions.mod
import fantasy.definitions.targets
import fantasy.gradle.ResourcesExtension

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class FantasyCorePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.build.shared")
        extensions.configure<PlatformExtension> { javaVersion.set(targets.minOf { it.java }) }
        generateModConstants("${mod.group}.shared", mapOf("MOD_ID" to mod.id))
        extensions.configure<ResourcesExtension> {
            generate("generateArmorDefaults") {
                contents.set(defaultResources(armor))
                outputDirectory.set(layout.buildDirectory.dir("generated/resources/defaults"))
            }
        }
    }
}
