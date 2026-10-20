package fantasy.build

import org.gradle.api.Plugin
import org.gradle.api.Project

class FantasyNeoForgePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply("fantasy.platform")
        project.pluginManager.apply("fantasy.build.neoforge")
    }
}
