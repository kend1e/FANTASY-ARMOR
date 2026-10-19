import fantasy.build.Target

plugins {
    id("fantasy.fabric")
    alias(libs.plugins.fabricLoom)
}

val platform = extensions.getByType<Target>()

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
}
