import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport
import fantasy.gradle.ModMetadata
import org.slf4j.event.Level

plugins {
    id("fantasy.neoforge")
    id("net.neoforged.moddev")
}

val modId = extensions.getByType<ModMetadata>().id

val tests = configureArmorTests()
val testReport = verifyArmorTestReport("runGameTestServer")
neoForge {
    mods.register("fantasy_armor_tests") { sourceSet(tests) }
    runs.named("gameTestServer") {
        logLevel = Level.INFO
        sourceSet.set(tests)
        gameDirectory.set(layout.buildDirectory.dir("run/test"))
        systemProperty("neoforge.enabledGameTestNamespaces", "fantasy_armor_tests")
        systemProperty("fantasy_armor.test.report", testReport.get().asFile.absolutePath)
    }
    runs.matching { it.name != "gameTestServer" }.configureEach {
        loadedMods.set(setOf(mods.getByName(modId)))
    }
}
