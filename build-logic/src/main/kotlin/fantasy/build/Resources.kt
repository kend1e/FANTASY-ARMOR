package fantasy.build

import com.google.gson.GsonBuilder
import fantasy.definitions.armor
import fantasy.definitions.languages

internal const val RESOURCE_NAMESPACE = "fantasy_armor"

private val resourceGson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

internal fun json(value: Any): String = resourceGson.toJson(value) + "\n"

fun defaultResources(): Map<String, String> = mapOf(
    "armor_defaults.json" to json(armor.associate { set ->
        set.id to set.pieces.mapKeys { it.key.id }
    }),
    "effect_defaults.json" to json(armor.associate { set -> set.id to set.effects }),
)

fun contentResources(profile: ResourceProfile, loader: Loader): Map<String, String> = buildMap {
    val groups = listOf(
        translationResources(languages, loader),
        recipeResources(armor, profile),
        modelResources(armor, profile),
        tagResources(armor, profile),
    )
    for (resources in groups) {
        for ((path, contents) in resources) {
            require(putIfAbsent(path, contents) == null) { "Duplicate generated resource: $path" }
        }
    }
}
