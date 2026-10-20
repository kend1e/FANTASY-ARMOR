pluginManagement {
    includeBuild("../../fantasy-build")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "fantasy-armor-build-logic"

dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("../gradle/libs.versions.toml")) } }
}
