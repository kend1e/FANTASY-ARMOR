package fantasy.definitions

import fantasy.build.TestCase
import fantasy.build.Loader
import fantasy.build.TestVersion
import fantasy.build.TestRegistration.*

val armorTests = listOf(
    TestCase("recipes", "RecipeTests"),
    TestCase("shapeless", "ShapelessRecipeTests", minecraft = setOf("1.19.4")),
    TestCase("dyeing", "DyeingTests"),
    TestCase("attributes", "AttributeTests"),
    TestCase("sets", "SetTests"),
    TestCase("effects", "EffectTests"),
    TestCase("configs", "ConfigTests"),
    TestCase("config_load", "ConfigLoadTests"),
    TestCase("config_restart_policy", "RestartPolicyTests", loaders = setOf(Loader.NEOFORGE)),
    TestCase("ticks", "TickTests"),
)

val testVersions = mapOf(
    "1.19.4" to TestVersion(
        sources = listOf("nbt", "crafting-container", "1.19.4"),
        packFormat = 12,
        structures = "structures",
        registrations = mapOf(Loader.FORGE to FORGE_CONTAINER),
    ),
    "1.20.1" to TestVersion(
        sources = listOf("nbt", "crafting-container", "1.20.1"),
        packFormat = 15,
        structures = "structures",
        registrations = mapOf(Loader.FORGE to FORGE_CONTAINER, Loader.FABRIC to FABRIC_VANILLA),
    ),
    "1.21.1" to TestVersion(
        sources = listOf("1.21.1"),
        packFormat = 48,
        structures = "structure",
        registrations = mapOf(Loader.FORGE to FORGE_COMPONENTS, Loader.FABRIC to FABRIC_VANILLA, Loader.NEOFORGE to NEOFORGE),
    ),
    "1.21.10" to TestVersion(
        sources = listOf("components", "crafting-input", "1.21.10"),
        packFormat = 88,
        structures = "structure",
        registrations = mapOf(Loader.FABRIC to FABRIC_REGISTRY),
    ),
    "1.21.11" to TestVersion(
        sources = listOf("components", "crafting-input", "1.21.11"),
        packFormat = 94,
        structures = "structure",
        registrations = mapOf(Loader.FORGE to FORGE_FUNCTIONS),
        serverConfigDirectory = "gametestserver/gametestworld/serverconfig",
    ),
)
