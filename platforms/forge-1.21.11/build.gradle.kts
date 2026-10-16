import fantasy.build.Target

plugins {
    id("fantasy.platform")
    java
    idea
    eclipse
    `maven-publish`
    alias(libs.plugins.forgeGradle)
}

val platform = extensions.getByType<Target>()
val modId = providers.gradleProperty("mod_id").get()
val modAuthors = providers.gradleProperty("mod_authors").get()

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
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
        name = "GeckoLib"
        content {
            includeGroupByRegex("software\\.bernie.*")
            includeGroup("com.eliotlash.mclib")
        }
    }
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
    implementation("software.bernie.geckolib:geckolib-forge-${platform.minecraft}:${platform.geckolib}")
    compileOnly(project(":core"))
}

val metadata = mapOf(
    "minecraft_version" to platform.minecraft,
    "minecraft_version_range" to platform.minecraftRange,
    "forge_version" to platform.loaderVersion,
    "forge_version_range" to platform.loaderRange,
    "loader_version_range" to platform.loaderRange,
    "mod_id" to modId,
    "mod_name" to providers.gradleProperty("mod_name").get(),
    "mod_license" to providers.gradleProperty("mod_license").get(),
    "mod_version" to platform.modVersion,
    "mod_authors" to modAuthors,
    "mod_description" to providers.gradleProperty("mod_description").get(),
)
tasks.processResources {
    inputs.properties(metadata)
    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) { expand(metadata) }
    duplicatesStrategy = DuplicatesStrategy.FAIL
}

tasks.jar {
    manifest.attributes("MixinConfigs" to "fantasy_armor.mixins.json")
}

// Shared sources must belong to the mod in both dev runs and release jars.
sourceSets.main {
    java.srcDir(rootProject.file("core/src/main/java"))
    resources.srcDir(project(":core").tasks.named("processResources"))
}
