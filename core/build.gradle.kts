plugins {
    id("fantasy.core")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.gson)
}
