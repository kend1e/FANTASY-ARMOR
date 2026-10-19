package fantasy.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class FantasyNeoForgePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.platform")
        pluginManager.apply("maven-publish")
        val platform = extensions.getByType<Target>()

        repositories {
            maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
                name = "GeckoLib"
                content { includeGroup("software.bernie.geckolib") }
            }
        }

        require(platform.settings is NeoForgeSettings) { "Expected NeoForge settings for $path" }
        val metadata = extensions.getByType<ModMetadata>().templateValues(platform) + mapOf(
            "neo_version" to platform.loaderVersion,
            "neo_version_range" to platform.loaderDependencyRange,
        )
        registerMetadataTemplates(metadata)


        extensions.configure<PublishingExtension> {
            publications {
                register<MavenPublication>("mavenJava") { from(components["java"]) }
            }
            repositories { maven { url = uri(layout.projectDirectory.dir("repo")) } }
        }
    }
}
