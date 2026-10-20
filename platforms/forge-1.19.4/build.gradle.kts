import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport
import fantasy.gradle.ModMetadata

plugins {
    id("fantasy.forge")
    id("net.minecraftforge.gradle")
}

val metadata = extensions.getByType<ModMetadata>()
val modId = metadata.id

dependencies { implementation(libs.mclib) }

publishing {
    publications {
        register<MavenPublication>("mavenJava") { artifact(tasks.named("renameJar")) }
    }
    repositories { maven { url = uri(layout.projectDirectory.dir("mcmodsrepo")) } }
}


val tests = configureArmorTests()
val testReport = verifyArmorTestReport("runTestGameTestServer")
minecraft.runs.named("gameTestServer") {
    with(tests) {
        systemProperty("forge.logging.console.level", "info")
        mods.register(modId) { source(sourceSets.main.get()) }
        mods.register("fantasy_armor_tests") { source(tests) }
        systemProperty("forge.enabledGameTestNamespaces", "fantasy_armor_tests")
        systemProperty("fantasy_armor.test.report", testReport.get().asFile.absolutePath)
        workingDir.set(layout.buildDirectory.dir("run/test"))
    }
}
