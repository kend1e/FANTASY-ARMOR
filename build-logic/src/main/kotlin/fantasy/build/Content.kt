package fantasy.build

@DslMarker
annotation class ContentDsl

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
    init {
        require(listOf(armor, armorToughness, knockbackResistance, movementSpeed,
            maxHealth, attackDamage, attackSpeed, luck, durability).all { it.isFinite() }) {
            "Armor attributes must be finite"
        }
        require(armor >= 0 && armorToughness >= 0 && durability >= 0) {
            "Protection and durability must not be negative"
        }
    }
}

data class Effect(val id: String, val duration: Int, val amplifier: Int) {
    init {
        require(id.matches(Regex("[a-z0-9_.-]+:[a-z0-9_./-]+"))) { "Invalid effect ID: $id" }
        require(duration == -1 || duration > 0) { "Invalid duration for $id" }
        require(amplifier in 0..255) { "Invalid amplifier for $id" }
    }
}

data class ArmorDefinition(
    val id: String,
    val pieces: Map<Piece, Attributes>,
    val effects: List<Effect>,
    val upgradeIngredient: String,
    val addition: String,
)

/** Null means inherit; zero is an explicit override. */
@ContentDsl
class AttributeOverrides internal constructor() {
    var armor: Double? = null
    var armorToughness: Double? = null
    var knockbackResistance: Double? = null
    var movementSpeed: Double? = null
    var maxHealth: Double? = null
    var attackDamage: Double? = null
    var attackSpeed: Double? = null
    var luck: Double? = null
    var durability: Double? = null

    internal fun applyTo(base: Attributes): Attributes = base.copy(
        armor = armor ?: base.armor,
        armorToughness = armorToughness ?: base.armorToughness,
        knockbackResistance = knockbackResistance ?: base.knockbackResistance,
        movementSpeed = movementSpeed ?: base.movementSpeed,
        maxHealth = maxHealth ?: base.maxHealth,
        attackDamage = attackDamage ?: base.attackDamage,
        attackSpeed = attackSpeed ?: base.attackSpeed,
        luck = luck ?: base.luck,
        durability = durability ?: base.durability,
    )
}

@ContentDsl
class ArmorBuilder internal constructor(
    private val id: String,
    private val basePieces: Map<Piece, Attributes>,
) {
    private var defaults: AttributeOverrides? = null
    private val pieces = linkedMapOf<Piece, AttributeOverrides>()
    private val effects = mutableListOf<Effect>()
    var upgradeIngredient: String = ""
    var addition: String = "fantasy_armor:moon_crystal"

    fun defaults(configure: AttributeOverrides.() -> Unit) {
        require(defaults == null) { "Duplicate defaults in $id" }
        defaults = AttributeOverrides().apply(configure)
    }

    fun piece(piece: Piece, configure: AttributeOverrides.() -> Unit) {
        require(piece !in pieces) { "Duplicate $piece in $id" }
        pieces[piece] = AttributeOverrides().apply(configure)
    }

    fun helmet(configure: AttributeOverrides.() -> Unit) = piece(Piece.HELMET, configure)
    fun chestplate(configure: AttributeOverrides.() -> Unit) = piece(Piece.CHESTPLATE, configure)
    fun leggings(configure: AttributeOverrides.() -> Unit) = piece(Piece.LEGGINGS, configure)
    fun boots(configure: AttributeOverrides.() -> Unit) = piece(Piece.BOOTS, configure)

    fun effect(id: String, duration: Int = 259, amplifier: Int = 0) {
        effects += Effect(id, duration, amplifier)
    }

    internal fun build(): ArmorDefinition {
        require(id.matches(Regex("[a-z0-9_]+"))) { "Invalid armor ID: $id" }
        for (ingredient in listOf(upgradeIngredient, addition)) {
            require(ingredient.matches(Regex("[a-z0-9_.-]+:[a-z0-9_./-]+"))) {
                "Invalid ingredient in $id: $ingredient"
            }
        }
        val resolved = Piece.entries.associateWith { piece ->
            val base = basePieces.getValue(piece)
            val common = defaults?.applyTo(base) ?: base
            pieces[piece]?.applyTo(common) ?: common
        }
        return ArmorDefinition(id, resolved, effects.toList(), upgradeIngredient, addition)
    }
}

@ContentDsl
class ArmorCatalog internal constructor(private val defaults: Map<Piece, Attributes>) {
    private val definitions = linkedMapOf<String, ArmorDefinition>()

    fun armor(id: String, configure: ArmorBuilder.() -> Unit) {
        require(id !in definitions) { "Duplicate armor ID: $id" }
        definitions[id] = ArmorBuilder(id, defaults).apply(configure).build()
    }

    internal fun build(): List<ArmorDefinition> = definitions.values.toList()
}

fun armorCatalog(
    defaults: Map<Piece, Attributes>,
    configure: ArmorCatalog.() -> Unit,
): List<ArmorDefinition> {
    require(defaults.keys == Piece.entries.toSet()) { "Defaults must define all four armor pieces" }
    return ArmorCatalog(defaults.toMap()).apply(configure).build()
}
