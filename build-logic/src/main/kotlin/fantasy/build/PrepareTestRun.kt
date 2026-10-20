package fantasy.build

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.tasks.*
import org.gradle.work.DisableCachingByDefault

@DisableCachingByDefault(because = "Minecraft rewrites configuration files during each run")
abstract class PrepareTestRun : DefaultTask() {
    @get:Input abstract val contents: MapProperty<String, String>
    @get:Internal abstract val runDirectory: DirectoryProperty

    init { outputs.upToDateWhen { false } }

    @TaskAction fun prepare() {
        val root = runDirectory.get().asFile.toPath()
        for ((relative, contents) in contents.get()) {
            val destination = root.resolve(relative).normalize()
            require(destination.startsWith(root)) { "Test config path escapes run directory: $relative" }
            destination.toFile().apply { parentFile.mkdirs(); writeText(contents, Charsets.UTF_8) }
        }
    }
}
