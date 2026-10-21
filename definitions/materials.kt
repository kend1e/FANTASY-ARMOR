package fantasy.definitions

import fantasy.gradle.content.MaterialDefinition
import fantasy.gradle.content.ShapedRecipe

val moonCrystal = MaterialDefinition("moon_crystal", ShapedRecipe(
    pattern = listOf("###", "#X#", "###"),
    ingredients = mapOf('#' to "minecraft:glow_ink_sac", 'X' to "minecraft:amethyst_shard"),
))

val materials = listOf(moonCrystal)
