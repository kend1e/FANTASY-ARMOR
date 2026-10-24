package fantasy.build

import fantasy.definitions.armor
import fantasy.definitions.materials
import fantasy.gradle.resources.json
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
            generate("generateMaterialCatalog") {
                contents.set(mapOf("material_catalog.json" to json(materials.map { it.id })))
                outputDirectory.set(layout.buildDirectory.dir("generated/resources/materials"))
            }
            generate("generateArmorDefaults") {
                contents.set(defaultResources(armor))
                outputDirectory.set(layout.buildDirectory.dir("generated/resources/defaults"))
            }
        }
    }
}
