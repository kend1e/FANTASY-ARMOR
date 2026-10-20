import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport
import fantasy.build.Target
import fantasy.build.registerReleaseExport
import fantasy.build.ModMetadata
import org.slf4j.event.Level

plugins {
    id("fantasy.neoforge")
    `java-library`
    alias(libs.plugins.moddev)
    idea
}

val platform = extensions.getByType<Target>()
val metadata = extensions.getByType<ModMetadata>()
val modId = metadata.id

neoForge {
    version = platform.loaderVersion
    runs {
        register("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        register("data") {
            data()
            programArguments.addAll(
                "--mod", modId, "--all", "--output",
                layout.buildDirectory.dir("generated/resources/datagen").get().asFile.absolutePath,
                "--existing", file("src/main/resources").absolutePath,
            )
        }
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = Level.DEBUG
        }
    }
    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main {
    resources.srcDir(layout.buildDirectory.dir("generated/resources/datagen"))
}

tasks.processResources { duplicatesStrategy = DuplicatesStrategy.FAIL }

dependencies {
    implementation("software.bernie.geckolib:geckolib-neoforge-${platform.minecraft}:${platform.geckolib}")
}

neoForge.ideSyncTask(tasks.named("generateModMetadata"))

idea.module {
    isDownloadSources = true
    isDownloadJavadoc = true
}

registerReleaseExport(tasks.jar.flatMap { it.archiveFile })

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
