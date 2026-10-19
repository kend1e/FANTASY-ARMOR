package fantasy.build

import com.google.gson.GsonBuilder

const val RESOURCE_NAMESPACE = "fantasy_armor"

internal val resourceGson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

internal fun json(value: Any): String = resourceGson.toJson(value) + "\n"

fun defaultResources(armor: List<ArmorDefinition>): Map<String, String> = mapOf(
    "armor_defaults.json" to json(armor.associate { set ->
        set.id to set.pieces.mapKeys { it.key.id }
    }),
    "effect_defaults.json" to json(armor.associate { set -> set.id to set.effects }),
)

fun contentResources(
    armor: List<ArmorDefinition>,
    languages: TranslationCatalog,
    craftingRecipes: List<ShapedRecipeDefinition>,
    formats: MinecraftResources,
): Map<String, String> = buildMap {
    languages.validate(armor)
    val groups = listOf(
        translationResources(languages.entries),
        recipeResources(armor, craftingRecipes, formats.recipes),
        modelResources(armor, formats.itemModels),
        tagResources(armor, formats.armorTags),
    )
    for (resources in groups) {
        for ((path, contents) in resources) {
            require(putIfAbsent(path, contents) == null) { "Duplicate generated resource: $path" }
        }
    }
}
