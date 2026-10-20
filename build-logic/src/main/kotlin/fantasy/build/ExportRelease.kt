package fantasy.build

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

/** Each platform owns one file, never the shared dist directory. */
@DisableCachingByDefault(because = "Copying a local artifact is cheaper than caching it")
abstract class ExportRelease : DefaultTask() {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val artifact: RegularFileProperty

    @get:OutputFile
    abstract val destination: RegularFileProperty

    @TaskAction
    fun export() {
        artifact.get().asFile.copyTo(destination.get().asFile, overwrite = true)
    }
}
