package fantasy.build

@ContentDsl
class Translations {
    private val languages = linkedMapOf<String, Map<String, String>>()
    fun language(locale: String, configure: Language.() -> Unit) {
        require(locale.matches(Regex("[a-z]{2,3}_[a-z0-9_]+"))) { "Invalid locale: $locale" }
        require(locale !in languages) { "Duplicate locale: $locale" }
        languages[locale] = Language().apply(configure).entries.toMap()
    }
    fun build(): Map<String, Map<String, String>> = languages.toMap()
}

@ContentDsl
class Language {
    internal val entries = linkedMapOf<String, String>()

    private var names: ArmorNames? = null

    fun armorNames(helmet: String, chestplate: String, leggings: String, boots: String) {
        require(names == null) { "Armor name templates already defined" }
        names = ArmorNames(helmet, chestplate, leggings, boots)
    }

    fun armor(id: String, name: String? = null, configure: ArmorTranslation.() -> Unit) {
        require(id.matches(Regex("[a-z0-9_]+"))) { "Invalid armor ID: $id" }
        val templates = requireNotNull(names) { "Declare armorNames before armor translations" }
        val translation = ArmorTranslation().apply(configure)
        for (piece in Piece.entries) {
            val key = "item.$RESOURCE_NAMESPACE.${id}_${piece.id}"
            val itemName = translation.name(piece) ?: templates.forPiece(piece).replace(
                "{name}", requireNotNull(name) { "Missing name for $id/${piece.id}" },
            )
            entry(key, itemName)
            val description = translation.description(piece) ?: translation.description
            if (description != null) entry("$key.tooltip", description)
        }
    }

    fun entry(key: String, value: String) {
        require(entries.putIfAbsent(key, value) == null) { "Duplicate translation key: $key" }
    }
}

fun translations(configure: Translations.() -> Unit): Map<String, Map<String, String>> =
    Translations().apply(configure).build()

private data class ArmorNames(
    val helmet: String,
    val chestplate: String,
    val leggings: String,
    val boots: String,
) {
    init {
        require(listOf(helmet, chestplate, leggings, boots).all { "{name}" in it }) {
            "Each armor name template must contain {name}"
        }
    }

    fun forPiece(piece: Piece): String = when (piece) {
        Piece.HELMET -> helmet
        Piece.CHESTPLATE -> chestplate
        Piece.LEGGINGS -> leggings
        Piece.BOOTS -> boots
    }
}

@ContentDsl
class ArmorTranslation internal constructor() {
    var helmet: String? = null
    var chestplate: String? = null
    var leggings: String? = null
    var boots: String? = null
    var description: String? = null
    var helmetDescription: String? = null
    var chestplateDescription: String? = null
    var leggingsDescription: String? = null
    var bootsDescription: String? = null

    internal fun name(piece: Piece): String? = when (piece) {
        Piece.HELMET -> helmet
        Piece.CHESTPLATE -> chestplate
        Piece.LEGGINGS -> leggings
        Piece.BOOTS -> boots
    }

    internal fun description(piece: Piece): String? = when (piece) {
        Piece.HELMET -> helmetDescription
        Piece.CHESTPLATE -> chestplateDescription
        Piece.LEGGINGS -> leggingsDescription
        Piece.BOOTS -> bootsDescription
    }
}
