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
        put("$assets/items/moon_crystal.json", json(ItemDefinition(modelReference("moon_crystal"))))
    }
}

private fun baseItemModel(id: String, itemDefinitions: Boolean) = ItemModelResource(
    textures = mapOf("layer0" to "$RESOURCE_NAMESPACE:item/$id"),
    overrides = if (itemDefinitions) null else listOf(ModelOverride(
        predicate = mapOf("$RESOURCE_NAMESPACE:dyed" to 1.0),
        model = "$RESOURCE_NAMESPACE:item/${id}_dyed",
    )),
)

private fun dyedItemModel(id: String) = ItemModelResource(
    textures = mapOf(
        "layer0" to "$RESOURCE_NAMESPACE:item/$id",
        "layer1" to "$RESOURCE_NAMESPACE:item/${id}_overlay",
    ),
)

private fun modelReference(id: String) = ModelReference("$RESOURCE_NAMESPACE:item/$id")

private fun dyedItemDefinition(id: String) = ItemDefinition(
    DyedCondition(
        onTrue = ModelReference(
            model = "$RESOURCE_NAMESPACE:item/${id}_dyed",
            tints = listOf(ConstantTint(-1), DyeTint()),
        ),
        onFalse = modelReference(id),
    ),
)
