
package fantasy.build

enum class Loader(val id: String) {
    FORGE("forge"),
    NEOFORGE("neoforge"),
    FABRIC("fabric");

    fun minimumVersion(version: String): String = when (this) {
        FABRIC -> ">=$version"
        else -> "[$version,)"
    }

    fun languageVersion(version: String): String = when (this) {
        FORGE -> minimumVersion(version.substringBefore('.'))
        NEOFORGE -> minimumVersion("1")
        FABRIC -> minimumVersion(version)
    }
}

enum class ResourceProfile(val recipeDirectory: String) {
    LEGACY("recipes"),
    COMPONENTS("recipe"),
    ITEM_DEFINITIONS("recipe")
}

data class Target(
    val loader: Loader,
    val minecraft: String,
    val java: Int,
    val modVersion: String,
    val loaderVersion: String,
    val geckolib: String,
    val resources: ResourceProfile,
    val fabricApi: String = "",
    val clothConfig: String = "",
    val srgRemapping: Boolean = false
) {
    val name get() = "${loader.id}-$minecraft"
    val path get() = ":platforms:$name"

    val minecraftRange get() = when (loader) {
        Loader.FABRIC -> "=>$minecraft"
        else -> "[$minecraft,)"
    }

    val loaderRange get() = loader.languageVersion(loaderVersion)
    val loaderDependencyRange get() = loader.minimumVersion(loaderVersion)
    val geckolibRange get() = loader.minimumVersion(geckolib)
    val fabricApiRange get() = loader.minimumVersion(fabricApi)
    val clothConfigRange get() = loader.minimumVersion(clothConfig)

    init {
        require(!srgRemapping || loader == Loader.FORGE) {
            "SRG remapping requires Forge: $name"
        }

        require(loader != Loader.FABRIC ||
                (fabricApi.isNotBlank() && clothConfig.isNotBlank())) {
            "Fabric API and Cloth Config versions are required for $name"
        }
    }
}
