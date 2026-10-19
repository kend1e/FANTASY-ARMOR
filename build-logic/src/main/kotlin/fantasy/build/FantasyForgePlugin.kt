package fantasy.build

import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.Sync
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class FantasyForgePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.platform")
        pluginManager.apply("maven-publish")
        val platform = extensions.getByType<Target>()
        val modId = providers.gradleProperty("mod_id").get()
        val modAuthors = providers.gradleProperty("mod_authors").get()

        repositories {
            maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
                name = "GeckoLib"
                content {
                    includeGroupByRegex("software\\.bernie.*")
                    includeGroup("com.eliotlash.mclib")
                }
            }
        }

        val metadata = mapOf(
            "minecraft_version" to platform.minecraft,
            "minecraft_version_range" to platform.minecraftRange,
            "forge_version" to platform.loaderVersion,
            "forge_version_range" to platform.loaderDependencyRange,
            "loader_version_range" to platform.loaderRange,
            "geckolib_version_range" to platform.geckolibRange,
            "mod_id" to modId,
            "mod_name" to providers.gradleProperty("mod_name").get(),
            "mod_license" to providers.gradleProperty("mod_license").get(),
            "mod_version" to platform.modVersion,
            "mod_authors" to modAuthors,
            "mod_description" to providers.gradleProperty("mod_description").get(),
        )
        val generateModMetadata = tasks.register<Sync>("generateModMetadata") {
            inputs.properties(metadata)
            from("src/main/templates")
            into(layout.buildDirectory.dir("generated/resources/metadata"))
            expand(metadata)
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            resources.srcDir(generateModMetadata)
        }
        tasks.named("generateResources") { dependsOn(generateModMetadata) }
    }
}
