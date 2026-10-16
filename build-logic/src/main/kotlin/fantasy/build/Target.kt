package fantasy.build

enum class Loader(val id: String) { FORGE("forge"), NEOFORGE("neoforge"), FABRIC("fabric") }
enum class ResourceProfile(val recipeDirectory: String, val resultKey: String) {
    LEGACY("recipes", "item"), COMPONENTS("recipe", "id"), ITEM_DEFINITIONS("recipe", "id")
}

data class Target(
    val loader: Loader,
    val minecraft: String,
    val java: Int,
    val modVersion: String,
    val loaderVersion: String,
    val geckolib: String,
    val resources: ResourceProfile,
    val minecraftRange: String = "[$minecraft]",
    val loaderRange: String = "[${loaderVersion.substringBefore('.')},)",
    val mappingChannel: String = "official",
    val mappingVersion: String = minecraft,
    val fabricApi: String = "",
    val clothConfig: String = "",
    val parchment: String = "",
) {
    val name: String get() = "${loader.id}-$minecraft"
    val path: String get() = ":platforms:$name"
    val legacyForge: Boolean get() = loader == Loader.FORGE && resources == ResourceProfile.LEGACY
}
