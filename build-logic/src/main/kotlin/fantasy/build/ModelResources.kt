package fantasy.build

internal fun modelResources(armor: List<ArmorDefinition>, profile: ResourceProfile): Map<String, String> = buildMap {
    val assets = "assets/$RESOURCE_NAMESPACE"
    val itemDefinitions = profile == ResourceProfile.ITEM_DEFINITIONS
    for (set in armor) {
        for (piece in Piece.entries) {
            val id = "${set.id}_${piece.id}"
            put("$assets/models/item/$id.json", json(baseItemModel(id, itemDefinitions)))
            put("$assets/models/item/${id}_dyed.json", json(dyedItemModel(id)))
            if (itemDefinitions) {
                put("$assets/items/$id.json", json(dyedItemDefinition(id)))
            }
        }
    }
    if (itemDefinitions) {
        put("$assets/items/moon_crystal.json", json(mapOf("model" to modelReference("moon_crystal"))))
    }
}

private fun baseItemModel(id: String, itemDefinitions: Boolean): Map<String, Any> = buildMap {
    put("parent", "item/generated")
    put("textures", mapOf("layer0" to "$RESOURCE_NAMESPACE:item/$id"))
    if (!itemDefinitions) {
        put("overrides", listOf(mapOf(
            "predicate" to mapOf("$RESOURCE_NAMESPACE:dyed" to 1.0),
            "model" to "$RESOURCE_NAMESPACE:item/${id}_dyed",
        )))
    }
}

private fun dyedItemModel(id: String): Map<String, Any> = mapOf(
    "parent" to "item/generated",
    "textures" to mapOf(
        "layer0" to "$RESOURCE_NAMESPACE:item/$id",
        "layer1" to "$RESOURCE_NAMESPACE:item/${id}_overlay",
    ),
)

private fun modelReference(id: String): Map<String, Any> = mapOf(
    "type" to "minecraft:model",
    "model" to "$RESOURCE_NAMESPACE:item/$id",
)

private fun dyedItemDefinition(id: String): Map<String, Any> {
    val dyedModel = modelReference("${id}_dyed") + mapOf(
        "tints" to listOf(
            mapOf("type" to "minecraft:constant", "value" to -1),
            mapOf("type" to "minecraft:dye", "default" to -1),
        ),
    )
    val condition = mapOf(
        "type" to "minecraft:condition",
        "property" to "minecraft:has_component",
        "component" to "minecraft:dyed_color",
        "on_true" to dyedModel,
        "on_false" to modelReference(id),
    )
    return mapOf("model" to condition)
}
