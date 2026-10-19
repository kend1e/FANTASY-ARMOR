package fantasy.build

import com.google.gson.annotations.SerializedName

// Only the resource structures emitted by this mod, not a general Minecraft schema.
internal data class ItemIngredient(val item: String)
internal data class ItemFieldResult(val item: String)
internal data class IdFieldResult(val id: String)

internal data class SmithingRecipe<I, R>(
    val template: I,
    val base: I,
    val addition: I,
    val result: R,
) {
    val type = "minecraft:smithing_transform"
    val category = "equipment"
}

internal data class ShapelessRecipe<I, R>(
    val ingredients: List<I>,
    val result: R,
) {
    val type = "minecraft:crafting_shapeless"
}

internal data class ShapedRecipe<I, R>(
    val pattern: List<String>,
    val key: Map<String, I>,
    val result: R,
) {
    val type = "minecraft:crafting_shaped"
}

internal data class TagResource(val values: List<String>, val replace: Boolean = false)

internal data class ItemModelResource(
    val textures: Map<String, String>,
    val overrides: List<ModelOverride>? = null,
    val parent: String = "item/generated",
)

internal data class ModelOverride(val predicate: Map<String, Double>, val model: String)
internal data class ItemDefinition(val model: ItemModelNode)

internal sealed interface ItemModelNode

internal data class ModelReference(
    val model: String,
    val tints: List<Tint>? = null,
) : ItemModelNode {
    val type = "minecraft:model"
}

internal data class DyedCondition(
    @SerializedName("on_true") val onTrue: ItemModelNode,
    @SerializedName("on_false") val onFalse: ItemModelNode,
) : ItemModelNode {
    val type = "minecraft:condition"
    val property = "minecraft:has_component"
    val component = "minecraft:dyed_color"
}

internal sealed interface Tint
internal data class ConstantTint(val value: Int) : Tint {
    val type = "minecraft:constant"
}
internal data class DyeTint(@SerializedName("default") val defaultColor: Int = -1) : Tint {
    val type = "minecraft:dye"
}
