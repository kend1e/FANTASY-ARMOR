import fantasy.build.Target

plugins {
    id("fantasy.platform")
    alias(libs.plugins.fabricLoom)
    `maven-publish`
}

val platform = extensions.getByType<Target>()
val coreSourceSets = project(":core").extensions.getByType<SourceSetContainer>()

repositories {
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
        name = "GeckoLib"
        content {
            includeGroupByRegex("software\\.bernie.*")
            includeGroup("com.eliotlash.mclib")
        }
    }
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

fabricApi {
    configureDataGeneration {
        addToResources.set(false)
        outputDirectory.set(layout.buildDirectory.file("generated/resources/datagen"))
    }
}

// Loom 1.13.6 otherwise resolves directories eagerly and loses task dependencies.
sourceSets.main {
    resources.srcDir(layout.buildDirectory.dir("generated/resources/datagen"))
}

dependencies {
    minecraft("com.mojang:minecraft:${platform.minecraft}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${platform.loaderVersion}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${platform.fabricApi}")
    modImplementation("software.bernie.geckolib:geckolib-fabric-${platform.minecraft}:${platform.geckolib}")
    implementation(libs.mclib)
    modApi("me.shedaniel.cloth:cloth-config-fabric:${platform.clothConfig}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    implementation(project(":core"))
}

val resourceVersion = project.version.toString()
tasks.processResources {
    inputs.property("version", resourceVersion)
    inputs.property("preserveJsonEscapes", true)
    filesMatching("fabric.mod.json") {
        expand(mapOf("version" to resourceVersion)) { escapeBackslash = true }
    }
    duplicatesStrategy = DuplicatesStrategy.FAIL
}

java { withSourcesJar() }

val archiveName = base.archivesName
tasks.jar {
    from(coreSourceSets.named("main").map { it.output })
    inputs.property("archivesName", archiveName)
    from("LICENSE") {
        rename { "${it}_${archiveName.get()}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = archiveName.get()
            from(components["java"])
        }
    }
}
