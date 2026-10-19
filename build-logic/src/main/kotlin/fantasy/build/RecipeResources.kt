package fantasy.build

internal fun recipeResources(armor: List<ArmorDefinition>, format: RecipeFormat): Map<String, String> = buildMap {
    val directory = "data/$RESOURCE_NAMESPACE/${format.directory}"
    for (set in armor) {
        for (piece in Piece.entries) {
            val id = "${set.id}_${piece.id}"
            put("$directory/$id.json", armorRecipe(set, piece, format))
        }
    }
    put("$directory/moon_crystal.json", moonCrystalRecipe(format))
}

private fun armorRecipe(set: ArmorDefinition, piece: Piece, format: RecipeFormat): String {
    val base = "minecraft:netherite_${piece.id}"
    val result = "$RESOURCE_NAMESPACE:${set.id}_${piece.id}"
    return when (val method = format.armor) {
        ArmorRecipeMethod.CraftingTable -> json(ShapedRecipe(
            listOf("TBA"),
            mapOf("T" to format.ingredients.encode(set.upgradeIngredient), "B" to format.ingredients.encode(base),
                "A" to format.ingredients.encode(set.addition)),
            format.craftingResult.encode(result),
        ))
        is ArmorRecipeMethod.SmithingTransform -> json(SmithingRecipe(
            format.ingredients.encode(set.upgradeIngredient), format.ingredients.encode(base),
            format.ingredients.encode(set.addition), method.result.encode(result),
        ))
    }
}

private fun moonCrystalRecipe(format: RecipeFormat): String {
    val ingredients = mapOf("#" to "minecraft:glow_ink_sac", "X" to "minecraft:amethyst_shard")
    return json(ShapedRecipe(
        listOf("###", "#X#", "###"),
        ingredients.mapValues { format.ingredients.encode(it.value) },
        format.craftingResult.encode("$RESOURCE_NAMESPACE:moon_crystal"),
    ))
}

// The JSON boundary deliberately supports both strings and small object DTOs.
private fun IngredientEncoding.encode(id: String): Any = when (this) {
    IngredientEncoding.ITEM_OBJECT -> ItemIngredient(id)
    IngredientEncoding.ITEM_ID -> id
}

private fun ResultEncoding.encode(id: String): Any = when (this) {
    ResultEncoding.ITEM_FIELD -> ItemFieldResult(id)
    ResultEncoding.ID_FIELD -> IdFieldResult(id)
    ResultEncoding.ITEM_ID -> id
}
