package fantasy.build

import fantasy.gradle.MinecraftRuntime

@DslMarker
annotation class MinecraftDsl

enum class IngredientEncoding { ITEM_OBJECT, ITEM_ID }
enum class ResultEncoding { ITEM_FIELD, ID_FIELD, ITEM_ID }
enum class ItemModelFormat { PREDICATE_OVERRIDES, ITEM_DEFINITIONS }
enum class ArmorTagFormat { NONE, DYEABLE_AND_ENCHANTABLE }

sealed interface ArmorRecipeMethod {
    object CraftingTable : ArmorRecipeMethod
    data class SmithingTransform(val result: ResultEncoding) : ArmorRecipeMethod
}

data class RecipeFormat(
    val directory: String,
    val ingredients: IngredientEncoding,
    val craftingResult: ResultEncoding,
    val armor: ArmorRecipeMethod,
) {
    init {
        require(directory == "recipes" || directory == "recipe") { "Unsupported recipe directory: $directory" }
        require(craftingResult != ResultEncoding.ITEM_ID) { "Crafting requires an object result" }
    }
}

data class MinecraftResources(
    val recipes: RecipeFormat,
    val itemModels: ItemModelFormat,
    val armorTags: ArmorTagFormat,
)

data class MinecraftVersion(val runtime: MinecraftRuntime, val resources: MinecraftResources)

class MinecraftCatalog internal constructor(versions: Map<String, MinecraftVersion>) {
    private val versions = versions.toMap()
    operator fun get(id: String): MinecraftVersion = versions[id]
        ?: error("Minecraft version $id must be declared in definitions/minecraft.kt")
}

@MinecraftDsl
class MinecraftCatalogBuilder internal constructor() {
    private val versions = linkedMapOf<String, MinecraftVersion>()

    fun version(id: String, configure: MinecraftVersionBuilder.() -> Unit) {
        require(id !in versions) { "Duplicate Minecraft version: $id" }
        versions[id] = MinecraftVersionBuilder(id).apply(configure).build()
    }

    internal fun build() = MinecraftCatalog(versions)
}

@MinecraftDsl
class MinecraftVersionBuilder internal constructor(private val id: String) {
    var java: Int? = null
    var recipes: RecipeFormat? = null
    var itemModels: ItemModelFormat? = null
    var armorTags: ArmorTagFormat? = null

    internal fun build() = MinecraftVersion(
        MinecraftRuntime(id, requireNotNull(java) { "$id: java is required" }),
        MinecraftResources(
            requireNotNull(recipes) { "$id: recipes are required" },
            requireNotNull(itemModels) { "$id: itemModels are required" },
            requireNotNull(armorTags) { "$id: armorTags are required" },
        ),
    )
}

@MinecraftDsl
class RecipeFormatBuilder internal constructor(private val ingredients: IngredientEncoding) {
    var directory: String? = null
    var craftingResult: ResultEncoding? = null
    private var armor: ArmorRecipeMethod? = null

    fun craftingTable() = setArmor(ArmorRecipeMethod.CraftingTable)
    fun smithingTransform(result: ResultEncoding) = setArmor(ArmorRecipeMethod.SmithingTransform(result))

    private fun setArmor(method: ArmorRecipeMethod) {
        require(armor == null) { "Armor recipe method is already configured" }
        armor = method
    }

    internal fun build() = RecipeFormat(
        requireNotNull(directory) { "Recipe directory is required" },
        ingredients,
        requireNotNull(craftingResult) { "Crafting result encoding is required" },
        requireNotNull(armor) { "Armor recipe method is required" },
    )
}

fun minecraftCatalog(configure: MinecraftCatalogBuilder.() -> Unit): MinecraftCatalog =
    MinecraftCatalogBuilder().apply(configure).build()

fun recipesWithItemObjects(configure: RecipeFormatBuilder.() -> Unit): RecipeFormat =
    RecipeFormatBuilder(IngredientEncoding.ITEM_OBJECT).apply(configure).build()

fun recipesWithItemIds(configure: RecipeFormatBuilder.() -> Unit): RecipeFormat =
    RecipeFormatBuilder(IngredientEncoding.ITEM_ID).apply(configure).build()
