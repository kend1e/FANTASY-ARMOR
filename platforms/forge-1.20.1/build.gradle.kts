import fantasy.build.Target

plugins {
    id("fantasy.platform")
    java
    idea
    eclipse
    `maven-publish`
    alias(libs.plugins.forgeGradle)
    alias(libs.plugins.renamer)
}

val platform = extensions.getByType<Target>()
val modId = providers.gradleProperty("mod_id").get()
val modAuthors = providers.gradleProperty("mod_authors").get()

minecraft {
    mappings("official", platform.minecraft)
    runs {
        configureEach {
            workingDir.set(layout.projectDirectory.dir("run"))
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", modId)
        }
        register("client")
        register("server") { args("--nogui") }
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
    implementation(renamer.dependency("software.bernie.geckolib:geckolib-forge-${platform.minecraft}:${platform.geckolib}"))
    implementation(libs.mclib)
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
    val replacements = metadata
    inputs.properties(replacements)
    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) { expand(replacements) }
    duplicatesStrategy = DuplicatesStrategy.FAIL
}

tasks.jar {
    manifest.attributes(
        "Specification-Title" to modId,
        "Specification-Vendor" to modAuthors,
        "Specification-Version" to "1",
        "Implementation-Title" to project.name,
        "Implementation-Version" to archiveVersion,
        "Implementation-Vendor" to modAuthors,
    )
}

// Shared sources must belong to the mod in both dev runs and release jars.
sourceSets.main {
    java.srcDir(rootProject.file("core/src/main/java"))
    resources.srcDir(project(":core").tasks.named("processResources"))
}

// Legacy Forge releases use SRG names, while development uses official names.
renamer.mappings.from(minecraft.getDependency(project.name).toSrgFile)
renamer.classes(tasks.jar) { archiveClassifier.set("srg") }
tasks.assemble { dependsOn(tasks.named("renameJar")) }

val devRefmapMappings = renamer.convert(
    "devRefmapMappings", minecraft.getDependency(project.name).toSrgFile, "srg"
) { reverse.set(true) }
minecraft.runs.configureEach {
    systemProperty("mixin.env.remapRefMap", "true")
    systemProperty("mixin.env.refMapRemappingFile",
        devRefmapMappings.flatMap { it.output }.map { it.asFile.absolutePath })
}
tasks.matching { it.name == "runClient" || it.name == "runServer" }.configureEach {
    dependsOn(devRefmapMappings)
}

idea.module {
    isDownloadSources = true
    isDownloadJavadoc = true
}
