package fantasy.build

enum class Piece { HELMET, CHESTPLATE, LEGGINGS, BOOTS;
    val id: String get() = name.lowercase(java.util.Locale.ROOT)
}

data class Attributes(
    val armor: Double,
    val armorToughness: Double,
    val knockbackResistance: Double,
    val movementSpeed: Double = 0.0,
    val maxHealth: Double = 0.0,
    val attackDamage: Double = 0.0,
    val attackSpeed: Double = 0.0,
    val luck: Double = 0.0,
    val durability: Double,
) {
    fun json(): Map<String, Double> = linkedMapOf(
        "armor" to armor, "armorToughness" to armorToughness,
        "knockbackResistance" to knockbackResistance, "movementSpeed" to movementSpeed,
        "maxHealth" to maxHealth, "attackDamage" to attackDamage,
        "attackSpeed" to attackSpeed, "luck" to luck, "durability" to durability,
    )
}

data class Effect(val id: String, val duration: Int, val amplifier: Int) {
    init {
        require(id.matches(Regex("[a-z0-9_.-]+:[a-z0-9_./-]+"))) { "Invalid effect ID: $id" }
        require(duration == -1 || duration > 0) { "Invalid duration for $id" }
        require(amplifier in 0..255) { "Invalid amplifier for $id" }
    }
    fun json(): Map<String, Any> = linkedMapOf("id" to id, "duration" to duration, "amplifier" to amplifier)
}

data class ArmorDefinition(
    val id: String,
    val pieces: Map<Piece, Attributes>,
    val effects: List<Effect>,
    val template: String,
    val addition: String,
)

class ArmorBuilder(private val id: String) {
    private val pieces = linkedMapOf<Piece, Attributes>()
    private val effects = mutableListOf<Effect>()
    var template: String = ""
    var addition: String = "fantasy_armor:moon_crystal"

    fun piece(piece: Piece, attributes: Attributes) {
        require(pieces.putIfAbsent(piece, attributes) == null) { "Duplicate $piece in $id" }
        require(attributes.json().values.all { it.isFinite() }) { "Non-finite attribute in $id" }
    }
    fun effect(id: String, duration: Int = 259, amplifier: Int = 0) {
        effects += Effect(id, duration, amplifier)
    }
    fun build(): ArmorDefinition {
        require(id.matches(Regex("[a-z0-9_]+"))) { "Invalid armor ID: $id" }
        require(pieces.keys == Piece.entries.toSet()) { "All four pieces required for $id" }
        for (ingredient in listOf(template, addition)) {
            require(ingredient.matches(Regex("[a-z0-9_.-]+:[a-z0-9_./-]+"))) { "Invalid ingredient in $id: $ingredient" }
        }
        return ArmorDefinition(id, pieces.toMap(), effects.toList(), template, addition)
    }
}

class ArmorCatalog {
    private val definitions = linkedMapOf<String, ArmorDefinition>()
    fun armor(id: String, configure: ArmorBuilder.() -> Unit) {
        require(id !in definitions) { "Duplicate armor ID: $id" }
        definitions[id] = ArmorBuilder(id).apply(configure).build()
    }
    fun build(): List<ArmorDefinition> = definitions.values.toList()
}

fun armorCatalog(configure: ArmorCatalog.() -> Unit): List<ArmorDefinition> = ArmorCatalog().apply(configure).build()

class Translations {
    private val languages = linkedMapOf<String, Map<String, String>>()
    fun language(locale: String, configure: Language.() -> Unit) {
        require(locale.matches(Regex("[a-z]{2,3}_[a-z0-9_]+"))) { "Invalid locale: $locale" }
        require(locale !in languages) { "Duplicate locale: $locale" }
        languages[locale] = Language().apply(configure).entries.toMap()
    }
    fun build(): Map<String, Map<String, String>> = languages.toMap()
}

class Language {
    internal val entries = linkedMapOf<String, String>()
    fun entry(key: String, value: String) {
        require(entries.putIfAbsent(key, value) == null) { "Duplicate translation key: $key" }
    }
}

fun translations(configure: Translations.() -> Unit): Map<String, Map<String, String>> =
    Translations().apply(configure).build()
