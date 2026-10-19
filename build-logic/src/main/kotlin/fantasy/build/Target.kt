package fantasy.build

enum class Loader(val id: String) { FORGE("forge"), NEOFORGE("neoforge"), FABRIC("fabric") }
enum class ResourceProfile(val recipeDirectory: String) {
    LEGACY("recipes"), COMPONENTS("recipe"), ITEM_DEFINITIONS("recipe")
}

data class Target(
    val loader: Loader,
    val minecraft: String,
    val java: Int,
    val modVersion: String,
    val loaderVersion: String,
    val geckolib: String,
    val resources: ResourceProfile,
    val minecraftRange: String = if (loader == Loader.FABRIC) "~$minecraft" else "[$minecraft]",
    val loaderRange: String = if (loader == Loader.FABRIC) ">=$loaderVersion" else "[${loaderVersion.substringBefore('.')},)",
    val fabricApi: String = "",
    val clothConfig: String = "",
    val srgRemapping: Boolean = false,
    val geckolibRange: String = ">=$geckolib",
    val fabricApiRange: String = "*",
    val clothConfigRange: String = "*",
) {
    val name: String get() = "${loader.id}-$minecraft"
    val path: String get() = ":platforms:$name"
    init {
        require(!srgRemapping || loader == Loader.FORGE) { "SRG remapping requires Forge: $name" }
        require(loader != Loader.FABRIC || (fabricApi.isNotBlank() && clothConfig.isNotBlank())) {
            "Fabric API and Cloth Config versions are required for $name"
        }
    }
}
