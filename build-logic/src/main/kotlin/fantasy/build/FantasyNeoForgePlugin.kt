package fantasy.build

import org.gradle.api.tasks.Sync
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class FantasyNeoForgePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.platform")
        pluginManager.apply("maven-publish")
        val platform = extensions.getByType<Target>()
        val modId = providers.gradleProperty("mod_id").get()

        repositories {
            maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
                name = "GeckoLib"
                content { includeGroup("software.bernie.geckolib") }
            }
        }

        val metadata = mapOf(
            "minecraft_version" to platform.minecraft,
            "minecraft_version_range" to platform.minecraftRange,
            "neo_version" to platform.loaderVersion,
            "neo_version_range" to platform.loaderDependencyRange,
            "loader_version_range" to platform.loaderRange,
            "geckolib_version_range" to platform.geckolibRange,
            "mod_id" to modId,
            "mod_name" to providers.gradleProperty("mod_name").get(),
            "mod_license" to providers.gradleProperty("mod_license").get(),
            "mod_version" to platform.modVersion,
            "mod_authors" to providers.gradleProperty("mod_authors").get(),
            "mod_description" to providers.gradleProperty("mod_description").get(),
        )
        val generateModMetadata by tasks.registering(Sync::class) {
            inputs.properties(metadata)
            expand(metadata)
            from("src/main/templates")
            into(layout.buildDirectory.dir("generated/resources/metadata"))
        }
        extensions.getByType<SourceSetContainer>().named("main") { resources.srcDir(generateModMetadata) }
        tasks.named("generateResources") { dependsOn(generateModMetadata) }

        extensions.configure<PublishingExtension> {
            publications {
                register<MavenPublication>("mavenJava") { from(components["java"]) }
            }
            repositories { maven { url = uri(layout.projectDirectory.dir("repo")) } }
        }
    }
}
