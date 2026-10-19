package fantasy.definitions

import fantasy.build.Loader.*
import fantasy.build.ResourceProfile.*
import fantasy.build.Target

private val latestModVersion = "1.3"

// Explicitly supported combinations; adding a version does not imply all loaders support it.
val targets = listOf(
    Target(
        loader = FORGE,
        minecraft = "1.19.4",
        java = 17,
        modVersion = "$latestModVersion-1.19.4",
        loaderVersion = "45.3.9",
        geckolib = "4.2",
        resources = LEGACY,
        srgRemapping = true
    ),
    Target(
        loader = FORGE,
        minecraft = "1.20.1",
        java = 17,
        modVersion = "$latestModVersion-1.20.1",
        loaderVersion = "47.3.12",
        geckolib = "4.4.7",
        resources = LEGACY,
        srgRemapping = true
    ),
    Target(
        loader = FABRIC,
        minecraft = "1.20.1",
        java = 17,
        modVersion = "$latestModVersion-1.20.1",
        loaderVersion = "0.16.7",
        geckolib = "4.4.7",
        resources = LEGACY,
        fabricApi = "0.92.2+1.20.1",
        clothConfig = "11.1.136"
    ),
    Target(
        loader = FORGE,
        minecraft = "1.21.1",
        java = 21,
        modVersion = "$latestModVersion-1.21.1",
        loaderVersion = "52.1.3",
        geckolib = "4.7.7",
        resources = COMPONENTS
    ),
    Target(
        loader = NEOFORGE,
        minecraft = "1.21.1",
        java = 21,
        modVersion = "$latestModVersion-1.21.1",
        loaderVersion = "21.1.206",
        geckolib = "4.7.7",
        resources = COMPONENTS,
    ),
    Target(
        loader = FABRIC,
        minecraft = "1.21.1",
        java = 21,
        modVersion = "$latestModVersion-1.21.1",
        loaderVersion = "0.18.2",
        geckolib = "4.8.2",
        resources = COMPONENTS,
        fabricApi = "0.116.7+1.21.1",
        clothConfig = "15.0.140"
    ),
    Target(
        loader = FABRIC,
        minecraft = "1.21.10",
        java = 21,
        modVersion = "$latestModVersion-1.21.10",
        loaderVersion = "0.18.4",
        geckolib = "5.3-alpha-3",
        resources = ITEM_DEFINITIONS,
        fabricApi = "0.138.4+1.21.10",
        clothConfig = "20.0.149"
    ),
    Target(
        loader = FORGE,
        minecraft = "1.21.11",
        java = 21,
        modVersion = "$latestModVersion-1.21.11",
        loaderVersion = "61.2.0",
        geckolib = "5.4.5",
        resources = ITEM_DEFINITIONS
    ),
)
