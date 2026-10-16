plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(localGroovy())
}

kotlin {
    jvmToolchain(21)
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
