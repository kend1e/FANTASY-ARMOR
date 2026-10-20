import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport

plugins {
    id("fantasy.fabric")
    id("fabric-loom")
}

dependencies { implementation(libs.mclib) }

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
