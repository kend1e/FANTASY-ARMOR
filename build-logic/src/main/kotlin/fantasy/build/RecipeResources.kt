package fantasy.build

internal fun recipeResources(armor: List<ArmorDefinition>, profile: ResourceProfile): Map<String, String> = buildMap {
    val directory = "data/$RESOURCE_NAMESPACE/${profile.recipeDirectory}"
    for (set in armor) {
        for (piece in Piece.entries) {
            val id = "${set.id}_${piece.id}"
            put("$directory/$id.json", json(smithingRecipe(set, piece, profile)))
        }
    }
    put("$directory/moon_crystal.json", json(moonCrystalRecipe(profile)))
}

private fun ingredient(id: String, profile: ResourceProfile): Any =
    if (profile == ResourceProfile.ITEM_DEFINITIONS) id else mapOf("item" to id)

private fun smithingRecipe(set: ArmorDefinition, piece: Piece, profile: ResourceProfile): Map<String, Any> {
    val result = "$RESOURCE_NAMESPACE:${set.id}_${piece.id}"
    return mapOf(
        "type" to "minecraft:smithing_transform",
        "category" to "equipment",
        "template" to ingredient(set.template, profile),
        "base" to ingredient("minecraft:netherite_${piece.id}", profile),
        "addition" to ingredient(set.addition, profile),
        "result" to if (profile == ResourceProfile.ITEM_DEFINITIONS) result else mapOf(profile.resultKey to result),
    )
}

private fun moonCrystalRecipe(profile: ResourceProfile): Map<String, Any> = mapOf(
    "type" to "minecraft:crafting_shaped",
    "pattern" to listOf("###", "#X#", "###"),
    "key" to mapOf(
        "#" to ingredient("minecraft:glow_ink_sac", profile),
        "X" to ingredient("minecraft:amethyst_shard", profile),
    ),
    "result" to mapOf(profile.resultKey to "$RESOURCE_NAMESPACE:moon_crystal"),
)
