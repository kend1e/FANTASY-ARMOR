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
    dependsOn(platformProjects.map { "${it.path}:generateResources" })
}

tasks.named<Delete>("clean") {
    delete(layout.projectDirectory.dir("dist"))
    dependsOn(":core:clean")
    dependsOn(platformProjects.map { "${it.path}:clean" })
}
