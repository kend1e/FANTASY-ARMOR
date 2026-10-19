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

sealed interface LoaderSettings {
    val loader: Loader
    val version: String
}

data class ForgeSettings(override val version: String) : LoaderSettings {
    override val loader get() = Loader.FORGE
}

data class NeoForgeSettings(override val version: String) : LoaderSettings {
    override val loader get() = Loader.NEOFORGE
}

data class FabricSettings(
    override val version: String,
    val fabricApi: String,
    val clothConfig: String,
) : LoaderSettings {
    override val loader get() = Loader.FABRIC
    val fabricApiRange get() = loader.minimumVersion(fabricApi)
    val clothConfigRange get() = loader.minimumVersion(clothConfig)

    init {
        require(listOf(version, fabricApi, clothConfig).all { it.isNotBlank() }) {
            "Fabric loader, API and Cloth Config versions must not be blank"
        }
    }
}

data class Target(
    val settings: LoaderSettings,
    val minecraftVersion: MinecraftVersion,
    val releaseVersion: String,
    val geckolib: String,
) {
    val loader get() = settings.loader
    val loaderVersion get() = settings.version
    val modVersion get() = "$releaseVersion-$minecraft"
    val releaseFileName get() = "fantasy-armor-$releaseVersion-$minecraft-${loader.id}.jar"
    val minecraft get() = minecraftVersion.id
    val java get() = minecraftVersion.java
    val resources get() = minecraftVersion.resources
    val geometryDirectory get() = when (geckolib.substringBefore('.')) {
        "4" -> "geo"
        "5" -> "geckolib/models"
        else -> error("Unsupported GeckoLib resource layout: $geckolib")
    }

    val name get() = "${loader.id}-$minecraft"
    val path get() = ":platforms:$name"

    val minecraftRange get() = when (loader) {
        Loader.FABRIC -> ">=$minecraft"
        else -> "[$minecraft,)"
    }

    val loaderRange get() = loader.languageVersion(loaderVersion)
    val loaderDependencyRange get() = loader.minimumVersion(loaderVersion)
    val geckolibRange get() = loader.minimumVersion(geckolib)
}
