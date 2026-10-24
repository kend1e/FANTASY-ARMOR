plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.minecraftforge.net/")
}

dependencies {
    implementation(libs.fantasy.build)
    implementation(libs.fantasy.fabric)
    implementation(libs.fantasy.forge)
    implementation(libs.fantasy.neoforge)
    implementation(libs.gson)
}

kotlin {
    jvmToolchain(25)
    sourceSets.main {
        kotlin.srcDir("../definitions")
    }
}

gradlePlugin {
    plugins {
        register("settings") {
            id = "fantasy.settings"
            implementationClass = "fantasy.build.FantasySettingsPlugin"
        }
        register("platform") {
            id = "fantasy.platform"
            implementationClass = "fantasy.build.FantasyPlatformPlugin"
        }
        register("core") {
            id = "fantasy.core"
            implementationClass = "fantasy.build.FantasyCorePlugin"
        }
    }
}
