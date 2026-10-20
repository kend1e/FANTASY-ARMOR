package fantasy.definitions

import fantasy.gradle.buildTargets
import fantasy.gradle.ForgeNames

val targets = buildTargets(releaseVersion = "1.3") {
    forge(minecraftVersions["1.19.4"].runtime, "45.3.9") {
        names = ForgeNames.SRG
        dataGeneration = true
        geckolib("4.2")
    }
    forge(minecraftVersions["1.20.1"].runtime, "47.3.12") {
        names = ForgeNames.SRG
        geckolib("4.4.7")
    }
    fabric(minecraftVersions["1.20.1"].runtime, "0.16.7") {
        dataGeneration = true
        fabricApi("0.92.2+1.20.1")
        geckolib("4.4.7")
        clothConfig("11.1.136")
    }
    forge(minecraftVersions["1.21.1"].runtime, "52.1.3") {
        dataGeneration = true
        geckolib("4.7.7")
    }
    neoforge(minecraftVersions["1.21.1"].runtime, "21.1.206") {
        dataGeneration = true
        geckolib("4.7.7")
    }
    fabric(minecraftVersions["1.21.1"].runtime, "0.18.2") {
        fabricApi("0.116.7+1.21.1")
        geckolib("4.8.2")
        clothConfig("15.0.140")
    }
    fabric(minecraftVersions["1.21.10"].runtime, "0.18.4") {
        fabricApi("0.138.4+1.21.10")
        geckolib("5.3-alpha-3")
        clothConfig("20.0.149")
    }
    forge(minecraftVersions["1.21.11"].runtime, "61.2.0") {
        dataGeneration = true
        geckolib("5.4.5")
    }
}
