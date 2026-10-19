import fantasy.build.Target
import org.slf4j.event.Level

plugins {
    id("fantasy.neoforge")
    `java-library`
    alias(libs.plugins.moddev)
    idea
}

val platform = extensions.getByType<Target>()
val modId = providers.gradleProperty("mod_id").get()

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
val localRuntime by configurations.creating
configurations.runtimeClasspath { extendsFrom(localRuntime) }

dependencies {
    implementation("software.bernie.geckolib:geckolib-neoforge-${platform.minecraft}:${platform.geckolib}")
}

neoForge.ideSyncTask(tasks.named("generateModMetadata"))

idea.module {
    isDownloadSources = true
    isDownloadJavadoc = true
}
