package fantasy.build

import fantasy.gradle.Loader
import fantasy.gradle.content.armor.*

import com.google.gson.Gson

data class TestProfile(
    val id: String,
    val modifiers: Boolean,
    val durability: Boolean,
    val effects: Boolean,
    val helmetArmor: Double,
    val setEffects: List<Effect>,
) {
    fun files(loader: Loader, testServerConfigDirectory: String): Map<String, String> {
        if (loader == Loader.FABRIC) return mapOf(
            "config/fantasy_armor-common.json" to Gson().toJson(mapOf(
                "applyModifiers" to modifiers, "enableDurability" to durability, "applyArmorEffects" to effects,
            )),
            "config/fantasy_armor-effects.json" to Gson().toJson(mapOf("effects" to mapOf("eclipse_soldier" to setEffects))),
            "config/fantasy_armor-attributes.json" to Gson().toJson(mapOf(
                "sets" to mapOf("eclipse_soldier" to mapOf("helmet" to mapOf("armor" to helmetArmor))),
            )),
        )
        val settings = """
            ["General Settings"]
            applyModifiers = $modifiers
            enableDurability = $durability
            applyArmorEffects = $effects
        """.trimIndent()
        val attributes = """
            ["Armor Attributes".eclipse_soldier.helmet]
            armor = $helmetArmor
        """.trimIndent()
        val effectsConfig = """
            ["Armor Effects".eclipse_soldier]
            effects = ${Gson().toJson(setEffects.map { "${it.id},${it.duration},${it.amplifier}" })}
        """.trimIndent()
        val directories = if (loader == Loader.NEOFORGE) listOf(testServerConfigDirectory) else listOf("defaultconfigs", testServerConfigDirectory)
        return directories.flatMap { directory -> listOf(
            "$directory/fantasy_armor-server.toml" to settings,
            "$directory/fantasy_armor-armor_attributes.toml" to attributes,
            "$directory/fantasy_armor-armor_effects.toml" to effectsConfig,
        ) }.toMap()
    }
}

fun testProfile(id: String, armor: List<ArmorDefinition>): TestProfile {
    val set = armor.single { it.id == "eclipse_soldier" }
    return when (id) {
        "defaults" -> TestProfile(id, true, false, true, set.pieces.getValue(Piece.HELMET).armor, set.effects)
        "configured" -> TestProfile(id, false, true, false, 11.0,
            set.effects.map { if (it.id == "minecraft:strength") it.copy(duration = 367, amplifier = 2) else it })
        else -> error("Unknown testProfile '$id'; expected defaults or configured")
    }
}
