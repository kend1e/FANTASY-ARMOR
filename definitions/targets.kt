package fantasy.definitions

import fantasy.gradle.buildTargets

val targets = buildTargets(releaseVersion = "1.3") {
    forge("1.19.4") {
        dataGeneration = true
        geckolib()
    }
    fabric("1.19.4") {
        fabricApi()
        geckolib()
        clothConfig()
    }
    forge("1.20.1") {
        geckolib()
    }
    fabric("1.20.1") {
        dataGeneration = true
        fabricApi()
        geckolib()
        clothConfig()
    }
    forge("1.21.1") {
        dataGeneration = true
        geckolib()
    }
    neoforge("1.21.1") {
        dataGeneration = true
        geckolib()
    }
    fabric("1.21.1") {
        fabricApi()
        geckolib()
        clothConfig()
    }
    forge("1.21.10") {
        geckolib()
    }
    neoforge("1.21.10") {
        geckolib()
    }
    fabric("1.21.10") {
        fabricApi()
        geckolib()
        clothConfig()
    }
    forge("1.21.11") {
        dataGeneration = true
        geckolib()
    }
    neoforge("1.21.11") {
        geckolib()
    }
    fabric("1.21.11") {
        fabricApi()
        geckolib()
        clothConfig()
    }
}
