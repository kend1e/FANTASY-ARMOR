package fantasy.build

import com.google.gson.JsonParser

import org.gradle.api.tasks.SourceSetContainer
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
        val fabric = target.settings
        require(fabric is FabricSettings) { "Expected Fabric settings for $path" }
        extensions.add(FabricSettings::class.java, "fabricSettings", fabric)
        val metadata = extensions.getByType<ModMetadata>().fabricValues(target, fabric)
        val template = providers.fileContents(layout.projectDirectory.file("src/main/templates/fabric.mod.json")).asText
        val generateModMetadata = tasks.register<GenerateFiles>("generateModMetadata") {
            contents.set(template.map { text ->
                val document = JsonParser.parseString(text).asJsonObject
                metadata.forEach { (name, value) ->
                    require(!document.has(name)) { "$name must be configured in targets.kt or gradle.properties, not fabric.mod.json" }
                    document.add(name, resourceGson.toJsonTree(value))
                }
                mapOf("fabric.mod.json" to json(document))
            })
            outputDirectory.set(layout.buildDirectory.dir("generated/resources/metadata"))
        }
        extensions.getByType<SourceSetContainer>().named("main") {
            resources.srcDir(generateModMetadata.flatMap { it.outputDirectory })
        }
        tasks.named("generateResources") { dependsOn(generateModMetadata) }

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
