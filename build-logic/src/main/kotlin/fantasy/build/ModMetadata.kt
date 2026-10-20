package fantasy.build

import fantasy.gradle.ResourcesExtension
import org.gradle.api.Project
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.*

data class ModMetadata(
    val id: String,
    val name: String,
    val group: String,
    val license: String,
    val authors: String,
    val description: String,
    val homepage: String,
    val sources: String,
    val icon: String,
    val environment: String,
) {
    internal fun templateValues(target: Target): Map<String, String> = mapOf(
        "minecraft_version" to target.minecraft,
        "minecraft_version_range" to target.minecraftRange,
        "loader_version_range" to target.loaderRange,
        "geckolib_version_range" to target.geckolibRange,
        "mod_id" to id,
        "mod_name" to name,
        "mod_license" to license,
        "mod_version" to target.modVersion,
        "mod_authors" to authors,
        "mod_description" to description,
    )

    internal fun fabricValues(target: Target, fabric: FabricSettings): Map<String, Any> = mapOf(
        "id" to id,
        "version" to target.modVersion,
        "name" to name,
        "description" to description.replace("\\n", "\n"),
        "authors" to authors.split(",").map(String::trim),
        "contact" to mapOf("homepage" to homepage, "sources" to sources),
        "license" to license,
        "icon" to icon,
        "environment" to environment,
        "depends" to mapOf(
            "fabricloader" to target.loaderRange,
            "minecraft" to target.minecraftRange,
            "java" to ">=${target.java}",
            "fabric-api" to fabric.fabricApiRange,
            "geckolib" to target.geckolibRange,
            "cloth-config2" to fabric.clothConfigRange,
        ),
    )

    companion object {
        internal fun read(providers: ProviderFactory): ModMetadata {
            fun property(name: String) = providers.gradleProperty("mod_$name").get()
            return ModMetadata(
                property("id"), property("name"), property("group_id"), property("license"),
                property("authors"), property("description"), property("homepage"),
                property("sources"), property("icon"), property("environment"),
            )
        }
    }
}

internal fun Project.registerMetadataTemplates(values: Map<String, String>) {
    val metadata = tasks.register<Sync>("generateModMetadata") {
        inputs.properties(values)
        from("src/main/templates")
        into(layout.buildDirectory.dir("generated/resources/metadata"))
        expand(values)
    }
    extensions.configure<ResourcesExtension> { sources.from(metadata) }
}
