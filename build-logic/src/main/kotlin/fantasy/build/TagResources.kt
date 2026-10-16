package fantasy.build

internal fun tagResources(armor: List<ArmorDefinition>, profile: ResourceProfile): Map<String, String> = buildMap {
    if (profile == ResourceProfile.LEGACY) return@buildMap

    put("data/minecraft/tags/item/dyeable.json", tag(armor.flatMap { set ->
        Piece.entries.map { "$RESOURCE_NAMESPACE:${set.id}_${it.id}" }
    }))
    val enchantableTags = mapOf(
        Piece.HELMET to "head_armor",
        Piece.CHESTPLATE to "chest_armor",
        Piece.LEGGINGS to "leg_armor",
        Piece.BOOTS to "foot_armor",
    )
    for ((piece, name) in enchantableTags) {
        put("data/minecraft/tags/item/enchantable/$name.json", tag(armor.map {
            "$RESOURCE_NAMESPACE:${it.id}_${piece.id}"
        }))
    }
}

private fun tag(values: List<String>): String = json(TagResource(values))
