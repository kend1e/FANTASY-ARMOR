import fantasy.gradle.GeckoLib
import fantasy.build.configureArmorTests
import fantasy.build.verifyArmorTestReport
import fantasy.gradle.Target
import fantasy.build.registerReleaseExport
import fantasy.gradle.ModMetadata

plugins {
    id("fantasy.forge")
    java
    idea
    eclipse
    alias(libs.plugins.forgeGradle)
}

val platform = extensions.getByType<Target>()
val metadata = extensions.getByType<ModMetadata>()
val modId = metadata.id

minecraft {
    mappings("official", platform.minecraft)
    runs {
        configureEach {
            workingDir.set(layout.projectDirectory.dir("run"))
            args("--mixin.config=fantasy_armor.mixins.json")
            systemProperty("eventbus.api.strictRuntimeChecks", "true")
        }
        register("client")
        register("server") { args("--nogui") }
        register("gameTestServer")
        register("data") {
            workingDir.set(layout.projectDirectory.dir("run-data"))
            args("--mod", modId, "--all", "--output",
                layout.buildDirectory.dir("generated/resources/datagen").get(),
                "--existing", layout.projectDirectory.dir("src/main/resources"))
        }
    }
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
    mavenCentral()

}

dependencies {
    // FG 7's detached launch configuration needs explicit variant attributes.
    // Configure before passing to FG: its Action overload casts to a Groovy closure owner.
    val forge = create("net.minecraftforge:forge:${platform.minecraft}-${platform.loaderVersion}") as ExternalModuleDependency
    forge.attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(Attribute.of("net.minecraftforge.mappings.channel", String::class.java), "official")
        attribute(Attribute.of("net.minecraftforge.mappings.version", String::class.java), platform.minecraft)
    }
    implementation(minecraft.dependency(project.name, forge))
    annotationProcessor("net.minecraftforge:eventbus-validator:7.0.5")
    annotationProcessor("org.spongepowered:mixin:0.8.7:processor")
    implementation(platform.dependency<GeckoLib>().notation(platform))
}

tasks.jar {
    manifest.attributes("MixinConfigs" to "fantasy_armor.mixins.json")
}

registerReleaseExport(tasks.jar.flatMap { it.archiveFile })

val tests = configureArmorTests()
val testReport = verifyArmorTestReport("runTestGameTestServer")
minecraft.runs.named("gameTestServer") {
    with(tests) {
        systemProperty("forge.logging.console.level", "info")
        systemProperty("forge.enabledGameTestNamespaces", "fantasy_armor_tests")
        systemProperty("fantasy_armor.test.report", testReport.get().asFile.absolutePath)
        workingDir.set(layout.buildDirectory.dir("run/test"))
    }
}
