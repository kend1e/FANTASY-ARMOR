package fantasy.build

import fantasy.gradle.ExportRelease
import fantasy.gradle.Target
import fantasy.gradle.ModMetadata

import org.gradle.api.Project
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.*

fun Project.registerReleaseExport(installableArtifact: Provider<RegularFile>) {
    val target = extensions.getByType<Target>()
    val metadata = extensions.getByType<ModMetadata>()
    val releaseFile = rootProject.layout.projectDirectory.file("dist/${target.releaseFileName(metadata)}")
    val export = tasks.register<ExportRelease>("exportRelease") {
        group = "build"
        description = "Copies the installable ${target.name} artifact into root dist."
        artifact.set(installableArtifact)
        destination.set(releaseFile)
    }
    tasks.named("assemble") { dependsOn(export) }
}
