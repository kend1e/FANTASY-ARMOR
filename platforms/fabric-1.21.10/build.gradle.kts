import org.gradle.api.tasks.bundling.AbstractArchiveTask
import fantasy.build.FabricSettings
import fantasy.build.Target
import fantasy.build.registerReleaseExport

plugins {
    id("fantasy.fabric")
    alias(libs.plugins.fabricLoom)
}

val platform = extensions.getByType<Target>()
val fabric = extensions.getByType<FabricSettings>()

dependencies {
    minecraft("com.mojang:minecraft:${platform.minecraft}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${platform.loaderVersion}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric.fabricApi}")
    modImplementation("software.bernie.geckolib:geckolib-fabric-${platform.minecraft}:${platform.geckolib}")
    implementation(libs.mclib)
    modApi("me.shedaniel.cloth:cloth-config-fabric:${fabric.clothConfig}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
}

registerReleaseExport(tasks.named<AbstractArchiveTask>("remapJar").flatMap { it.archiveFile })
