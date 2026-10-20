package fantasy.definitions

import fantasy.gradle.resources.ShapedRecipeDefinition

val craftingRecipes = listOf(
    ShapedRecipeDefinition(
        id = "moon_crystal",
        pattern = listOf("###", "#X#", "###"),
        ingredients = mapOf("#" to "minecraft:glow_ink_sac", "X" to "minecraft:amethyst_shard"),
        result = "${mod.id}:moon_crystal",
    ),
)
