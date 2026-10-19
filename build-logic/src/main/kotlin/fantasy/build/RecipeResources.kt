package fantasy.build

internal fun recipeResources(armor: List<ArmorDefinition>, craftingRecipes: List<ShapedRecipeDefinition>, format: RecipeFormat): Map<String, String> = buildMap {
    val directory = "data/$RESOURCE_NAMESPACE/${format.directory}"
    for (set in armor) {
        for (piece in Piece.entries) {
            val id = "${set.id}_${piece.id}"
            put("$directory/$id.json", armorRecipe(set, piece, format))
        }
    }
    for (recipe in craftingRecipes) {
        val path = "$directory/${recipe.id}.json"
        require(putIfAbsent(path, shapedRecipe(recipe, format)) == null) { "Duplicate recipe: $path" }
    }
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

private fun shapedRecipe(recipe: ShapedRecipeDefinition, format: RecipeFormat): String = json(ShapedRecipe(
    recipe.pattern,
    recipe.ingredients.mapValues { format.ingredients.encode(it.value) },
    format.craftingResult.encode(recipe.result),
))

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
