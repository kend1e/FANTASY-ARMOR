package fantasy.definitions

import fantasy.build.FabricSettings
import fantasy.build.ForgeSettings
import fantasy.build.NeoForgeSettings
import fantasy.build.Target

private const val latestModVersion = "1.3"

// Explicitly supported combinations; adding a version does not imply all loaders support it.
val targets = listOf(
    Target(
        settings = ForgeSettings("45.3.9"),
        minecraftVersion = minecraftVersions["1.19.4"],
        releaseVersion = latestModVersion,
        geckolib = "4.2",
    ),
    Target(
        settings = ForgeSettings("47.3.12"),
        minecraftVersion = minecraftVersions["1.20.1"],
        releaseVersion = latestModVersion,
        geckolib = "4.4.7",
    ),
    Target(
        settings = FabricSettings("0.16.7", fabricApi = "0.92.2+1.20.1", clothConfig = "11.1.136"),
        minecraftVersion = minecraftVersions["1.20.1"],
        releaseVersion = latestModVersion,
        geckolib = "4.4.7",
    ),
    Target(
        settings = ForgeSettings("52.1.3"),
        minecraftVersion = minecraftVersions["1.21.1"],
        releaseVersion = latestModVersion,
        geckolib = "4.7.7",
    ),
    Target(
        settings = NeoForgeSettings("21.1.206"),
        minecraftVersion = minecraftVersions["1.21.1"],
        releaseVersion = latestModVersion,
        geckolib = "4.7.7",
    ),
    Target(
        settings = FabricSettings("0.18.2", fabricApi = "0.116.7+1.21.1", clothConfig = "15.0.140"),
        minecraftVersion = minecraftVersions["1.21.1"],
        releaseVersion = latestModVersion,
        geckolib = "4.8.2",
    ),
    Target(
        settings = FabricSettings("0.18.4", fabricApi = "0.138.4+1.21.10", clothConfig = "20.0.149"),
        minecraftVersion = minecraftVersions["1.21.10"],
        releaseVersion = latestModVersion,
        geckolib = "5.3-alpha-3",
    ),
    Target(
        settings = ForgeSettings("61.2.0"),
        minecraftVersion = minecraftVersions["1.21.11"],
        releaseVersion = latestModVersion,
        geckolib = "5.4.5",
    ),
)
