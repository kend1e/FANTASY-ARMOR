plugins {
    base
    idea
}

val platformProjects = project(":platforms").subprojects

tasks.named("assemble") {
    dependsOn(platformProjects.map { "${it.path}:assemble" })
}

tasks.register("generateAll") {
    group = "build"
    description = "Prepares all resources without writing into source directories."
    dependsOn(":core:generateArmorDefaults")
    dependsOn(platformProjects.flatMap {
        listOf("${it.path}:prepareSharedResources", "${it.path}:generateContentResources")
    })
}

tasks.named<Delete>("clean") {
    delete(layout.projectDirectory.dir("dist"))
    dependsOn(":core:clean")
    dependsOn(platformProjects.map { "${it.path}:clean" })
}
