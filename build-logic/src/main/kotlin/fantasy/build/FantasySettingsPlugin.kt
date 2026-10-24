package fantasy.build

import fantasy.definitions.targets
import fantasy.definitions.mod
import fantasy.gradle.includeModTargets
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class FantasySettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        settings.rootProject.name = mod.releaseName
        settings.include(":core")
        settings.includeModTargets(targets)
    }
}
