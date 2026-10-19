package fantasy.definitions

import fantasy.build.Loader.*
import fantasy.build.ResourceProfile.*
import fantasy.build.Target

// Explicitly supported combinations; adding a version does not imply all loaders support it.
val targets = listOf(
    Target(FORGE, "1.19.4", 17, "1.2.3-1.19.4", "45.3.9", "4.2", LEGACY,
        minecraftRange = "[1.19.4,1.20)", srgRemapping = true),
    Target(FORGE, "1.20.1", 17, "1.2.4-1.20.1", "47.3.12", "4.4.7", LEGACY,
        minecraftRange = "[1.20.1,1.21)", srgRemapping = true),
    Target(FABRIC, "1.20.1", 17, "1.2.4-1.20.1", "0.16.7", "4.4.7", LEGACY,
        fabricApi = "0.92.2+1.20.1", clothConfig = "11.1.136"),
    Target(FORGE, "1.21.1", 21, "1.2.4-1.21.1", "52.1.3", "4.7.7", COMPONENTS,
        minecraftRange = "[1.21.1,1.22)"),
    Target(NEOFORGE, "1.21.1", 21, "1.2.4-1.21.1", "21.1.206", "4.7.7", COMPONENTS,
        loaderRange = "[1,)"),
    Target(FABRIC, "1.21.1", 21, "1.2.4-1.21.1", "0.18.2", "4.8.2", COMPONENTS,
        fabricApi = "0.116.7+1.21.1", clothConfig = "15.0.140"),
    Target(FABRIC, "1.21.10", 21, "1.2.4-1.21.10", "0.18.4", "5.3-alpha-3", ITEM_DEFINITIONS,
        fabricApi = "0.138.4+1.21.10", clothConfig = "20.0.149", loaderRange = ">=0.18.2"),
    Target(FORGE, "1.21.11", 21, "1.2.5-1.21.11", "61.2.0", "5.4.5", ITEM_DEFINITIONS,
        minecraftRange = "[1.21.11,1.22)"),
)
