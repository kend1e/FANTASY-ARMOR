import fantasy.build.Target
import fantasy.build.registerReleaseExport
import fantasy.build.ModMetadata

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
val modAuthors = metadata.authors

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
    // Match the version shipped by Minecraft despite newer transitive requests.
    implementation("net.sf.jopt-simple:jopt-simple:5.0.4") {
        version { strictly("5.0.4") }
    }
    implementation("software.bernie.geckolib:geckolib-forge-${platform.minecraft}:${platform.geckolib}")
    implementation(libs.mclib)
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

sourceSets.main {
    resources.srcDir(layout.buildDirectory.dir("generated/resources/datagen"))
}

registerReleaseExport(tasks.jar.flatMap { it.archiveFile })
