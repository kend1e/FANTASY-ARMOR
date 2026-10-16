import fantasy.build.Target
import org.slf4j.event.Level

plugins {
    id("fantasy.platform")
    `java-library`
    `maven-publish`
    alias(libs.plugins.moddev)
    idea
}

val platform = extensions.getByType<Target>()
val modId = providers.gradleProperty("mod_id").get()
val coreSourceSets = project(":core").extensions.getByType<SourceSetContainer>()

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
            sourceSet(coreSourceSets.named("main").get())
        }
    }
}

sourceSets.main {
    resources.srcDir(layout.buildDirectory.dir("generated/resources/datagen"))
}

tasks.processResources { duplicatesStrategy = DuplicatesStrategy.FAIL }
val localRuntime by configurations.creating
configurations.runtimeClasspath { extendsFrom(localRuntime) }

repositories {
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
        name = "GeckoLib"
        content { includeGroup("software.bernie.geckolib") }
    }
}

dependencies {
    implementation("software.bernie.geckolib:geckolib-neoforge-${platform.minecraft}:${platform.geckolib}")
    implementation(project(":core"))
}

val metadata = mapOf(
    "minecraft_version" to platform.minecraft,
    "minecraft_version_range" to platform.minecraftRange,
    "neo_version" to platform.loaderVersion,
    "loader_version_range" to platform.loaderRange,
    "mod_id" to modId,
    "mod_name" to providers.gradleProperty("mod_name").get(),
    "mod_license" to providers.gradleProperty("mod_license").get(),
    "mod_version" to platform.modVersion,
    "mod_authors" to providers.gradleProperty("mod_authors").get(),
    "mod_description" to providers.gradleProperty("mod_description").get(),
)
val generateModMetadata by tasks.registering(ProcessResources::class) {
    inputs.properties(metadata)
    expand(metadata)
    from("src/main/templates")
    into(layout.buildDirectory.dir("generated/resources/metadata"))
}
sourceSets.main { resources.srcDir(generateModMetadata) }
neoForge.ideSyncTask(generateModMetadata)

publishing {
    publications {
        register<MavenPublication>("mavenJava") { from(components["java"]) }
    }
    repositories { maven { url = uri(layout.projectDirectory.dir("repo")) } }
}

tasks.jar { from(coreSourceSets.named("main").map { it.output }) }

idea.module {
    isDownloadSources = true
    isDownloadJavadoc = true
}
