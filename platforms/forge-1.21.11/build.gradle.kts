plugins {
    id("fantasy.platform")
    id("net.minecraftforge.gradle")
}

minecraft.runs.configureEach {
    args("--mixin.config=fantasy_armor.mixins.json")
    systemProperty("eventbus.api.strictRuntimeChecks", "true")
}

dependencies {
    annotationProcessor("net.minecraftforge:eventbus-validator:7.0.5")
    annotationProcessor("org.spongepowered:mixin:0.8.7:processor")
}

tasks.jar { manifest.attributes("MixinConfigs" to "fantasy_armor.mixins.json") }
