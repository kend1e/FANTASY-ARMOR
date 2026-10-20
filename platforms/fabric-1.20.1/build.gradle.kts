import fantasy.gradle.GeckoLib
import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import fantasy.gradle.FabricApi
import fantasy.gradle.ClothConfig
import fantasy.gradle.Target
import fantasy.build.registerReleaseExport

plugins {
    id("fantasy.fabric")
    alias(libs.plugins.fabricLoom)
}

val platform = extensions.getByType<Target>()

fabricApi {
    configureDataGeneration {
        addToResources.set(false)
        outputDirectory.set(layout.buildDirectory.file("generated/resources/datagen"))
    }
}

// Loom 1.13.6 otherwise resolves directories eagerly and loses task dependencies.
sourceSets.main {
    resources.srcDir(layout.buildDirectory.dir("generated/resources/datagen"))
}

dependencies {
    minecraft("com.mojang:minecraft:${platform.minecraft}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${platform.loaderVersion}")
    modImplementation(platform.dependency<FabricApi>().notation)
    modImplementation(platform.dependency<GeckoLib>().notation(platform))
    implementation(libs.mclib)
    modApi(platform.dependency<ClothConfig>().notation) {
        exclude(group = "net.fabricmc.fabric-api")
    }
}

registerReleaseExport(tasks.named<AbstractArchiveTask>("remapJar").flatMap { it.archiveFile })

val tests = configureArmorTests()
fabricApi.configureTests {
    createSourceSet.set(false)
    enableClientGameTests.set(false)
}
loom.mods {
    register("fantasy_armor") { sourceSet(sourceSets.main.get()) }
    register("fantasy_armor_tests") { sourceSet(tests) }
}
val testReport = verifyArmorTestReport("runGameTest")
loom.runs.named("gameTest") {
    source(tests)
    runDir("build/run/test")
    property("fabric-api.gametest.report-file", testReport.get().asFile.absolutePath)
}
