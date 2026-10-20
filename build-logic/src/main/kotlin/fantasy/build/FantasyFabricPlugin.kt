package fantasy.build

import fantasy.gradle.Target
import fantasy.gradle.FabricSettings

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class FantasyFabricPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("fantasy.platform")
        pluginManager.apply("maven-publish")
        repositories {
            maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
                name = "GeckoLib"
                content {
                    includeGroupByRegex("software\\.bernie.*")
                    includeGroup("com.eliotlash.mclib")
                }
            }
            maven("https://maven.shedaniel.me/")
            maven("https://maven.terraformersmc.com/releases/")
        }

        val target = extensions.getByType<Target>()
        require(target.settings is FabricSettings) { "Expected Fabric settings for $path" }

        extensions.configure<JavaPluginExtension> { withSourcesJar() }

        val archiveName = extensions.getByType<BasePluginExtension>().archivesName
        tasks.named<Jar>("jar") {
            val licenseName = "LICENSE.md_${archiveName.get()}"
            inputs.property("archivesName", archiveName)
            from(rootProject.layout.projectDirectory.file("LICENSE.md")) {
                rename { licenseName }
            }
        }

        extensions.configure<PublishingExtension> {
            publications {
                create<MavenPublication>("mavenJava") {
                    artifactId = archiveName.get()
                    from(components["java"])
                }
            }
        }
    }
}
