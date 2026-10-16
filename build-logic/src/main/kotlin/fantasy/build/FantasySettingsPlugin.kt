package fantasy.build

import fantasy.definitions.targets
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class FantasySettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        settings.rootProject.name = "fantasy-armor"
        settings.include(":core")
        require(targets.map { it.name }.distinct().size == targets.size) { "Duplicate build target" }
        targets.forEach { settings.include(it.path) }
    }
}
