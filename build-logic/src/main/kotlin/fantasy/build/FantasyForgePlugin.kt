package fantasy.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class FantasyForgePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.platform")
        pluginManager.apply("maven-publish")
        val platform = extensions.getByType<Target>()

        repositories {
            maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
                name = "GeckoLib"
                content {
                    includeGroupByRegex("software\\.bernie.*")
                    includeGroup("com.eliotlash.mclib")
                }
            }
        }

        require(platform.settings is ForgeSettings) { "Expected Forge settings for $path" }
        val metadata = extensions.getByType<ModMetadata>().templateValues(platform) + mapOf(
            "forge_version" to platform.loaderVersion,
            "forge_version_range" to platform.loaderDependencyRange,
        )
        registerMetadataTemplates(metadata)

    }
}
