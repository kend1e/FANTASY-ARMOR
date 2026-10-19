import fantasy.build.Target

plugins {
    id("fantasy.fabric")
    alias(libs.plugins.fabricLoom)
}

val platform = extensions.getByType<Target>()

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
