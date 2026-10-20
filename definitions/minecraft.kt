package fantasy.definitions

import fantasy.build.*
import fantasy.build.ArmorTagFormat.*
import fantasy.build.ItemModelFormat.*
import fantasy.build.ResultEncoding.*

private val stringIngredientRecipes = recipesWithItemIds {
    directory = "recipe"
    craftingResult = ID_FIELD
    smithingTransform(result = ITEM_ID)
}

// Exact supported versions; no inferred compatibility with unlisted releases.
val minecraftVersions = minecraftCatalog {
    version("1.19.4") {
        java = 17
        recipes = recipesWithItemObjects {
            directory = "recipes"
            craftingResult = ITEM_FIELD
            craftingTable()
        }
        itemModels = PREDICATE_OVERRIDES
        armorTags = NONE
    }
    version("1.20.1") {
        java = 17
        recipes = recipesWithItemObjects {
            directory = "recipes"
            craftingResult = ITEM_FIELD
            smithingTransform(result = ITEM_FIELD)
        }
        itemModels = PREDICATE_OVERRIDES
        armorTags = NONE
    }
    version("1.21.1") {
        java = 21
        recipes = recipesWithItemObjects {
            directory = "recipe"
            craftingResult = ID_FIELD
            smithingTransform(result = ID_FIELD)
        }
        itemModels = PREDICATE_OVERRIDES
        armorTags = DYEABLE_AND_ENCHANTABLE
    }
    version("1.21.10") {
        java = 21
        recipes = stringIngredientRecipes
        itemModels = ITEM_DEFINITIONS
        armorTags = DYEABLE_AND_ENCHANTABLE
    }
    version("1.21.11") {
        java = 21
        recipes = stringIngredientRecipes
        itemModels = ITEM_DEFINITIONS
        armorTags = DYEABLE_AND_ENCHANTABLE
    }
}
