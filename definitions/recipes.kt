package fantasy.definitions

import fantasy.build.RESOURCE_NAMESPACE
import fantasy.build.ShapedRecipeDefinition

val craftingRecipes = listOf(
    ShapedRecipeDefinition(
        id = "moon_crystal",
        pattern = listOf("###", "#X#", "###"),
        ingredients = mapOf("#" to "minecraft:glow_ink_sac", "X" to "minecraft:amethyst_shard"),
        result = "$RESOURCE_NAMESPACE:moon_crystal",
    ),
)
