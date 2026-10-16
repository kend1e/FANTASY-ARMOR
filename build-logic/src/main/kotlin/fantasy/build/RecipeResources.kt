package fantasy.build

internal fun recipeResources(armor: List<ArmorDefinition>, profile: ResourceProfile): Map<String, String> = buildMap {
    val directory = "data/$RESOURCE_NAMESPACE/${profile.recipeDirectory}"
    for (set in armor) {
        for (piece in Piece.entries) {
            val id = "${set.id}_${piece.id}"
            put("$directory/$id.json", smithingRecipe(set, piece, profile))
        }
    }
    put("$directory/moon_crystal.json", moonCrystalRecipe(profile))
}

private fun smithingRecipe(set: ArmorDefinition, piece: Piece, profile: ResourceProfile): String {
    val base = "minecraft:netherite_${piece.id}"
    val result = "$RESOURCE_NAMESPACE:${set.id}_${piece.id}"
    return when (profile) {
        ResourceProfile.LEGACY -> json(SmithingRecipe(
            ItemIngredient(set.template), ItemIngredient(base), ItemIngredient(set.addition), LegacyResult(result),
        ))
        ResourceProfile.COMPONENTS -> json(SmithingRecipe(
            ItemIngredient(set.template), ItemIngredient(base), ItemIngredient(set.addition), ComponentResult(result),
        ))
        ResourceProfile.ITEM_DEFINITIONS -> json(SmithingRecipe(set.template, base, set.addition, result))
    }
}

private fun moonCrystalRecipe(profile: ResourceProfile): String {
    val pattern = listOf("###", "#X#", "###")
    val ingredients = mapOf("#" to "minecraft:glow_ink_sac", "X" to "minecraft:amethyst_shard")
    val result = "$RESOURCE_NAMESPACE:moon_crystal"
    return when (profile) {
        ResourceProfile.LEGACY -> json(ShapedRecipe(
            pattern, ingredients.mapValues { ItemIngredient(it.value) }, LegacyResult(result),
        ))
        ResourceProfile.COMPONENTS -> json(ShapedRecipe(
            pattern, ingredients.mapValues { ItemIngredient(it.value) }, ComponentResult(result),
        ))
        ResourceProfile.ITEM_DEFINITIONS -> json(ShapedRecipe(pattern, ingredients, ComponentResult(result)))
    }
}
