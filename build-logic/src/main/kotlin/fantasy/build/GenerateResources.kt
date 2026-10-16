package fantasy.build

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.provider.MapProperty
import org.gradle.api.tasks.*
import javax.inject.Inject

@CacheableTask
abstract class GenerateResources : DefaultTask() {
    @get:Input abstract val contents: MapProperty<String, String>
    @get:OutputDirectory abstract val outputDirectory: DirectoryProperty
    @get:Inject abstract val fileSystem: FileSystemOperations

    @TaskAction fun generate() {
        val root = outputDirectory.get().asFile.toPath()
        val files = contents.get()
        files.keys.forEach { name ->
            require(!java.nio.file.Path.of(name).isAbsolute && root.resolve(name).normalize().startsWith(root)) {
                "Resource path escapes output directory: $name"
            }
        }
        fileSystem.delete { delete(root) }
        for ((name, text) in files.toSortedMap()) {
            val file = root.resolve(name).toFile()
            file.parentFile.mkdirs()
            file.writeText(text, Charsets.UTF_8)
        }
    }
}

