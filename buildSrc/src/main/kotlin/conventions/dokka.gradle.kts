package conventions

import org.jetbrains.dokka.gradle.DokkaTask

val rootProjectDir = project.rootProject.rootDir
val projectName = project.name

apply(plugin = "org.jetbrains.dokka")

tasks.named<DokkaTask>("dokkaHtml").configure {
    outputDirectory.set(layout.buildDirectory.dir("docs/html"))
}

tasks.named<DokkaTask>("dokkaGfm").configure {
    outputDirectory.set(layout.buildDirectory.dir("docs/markdown"))
}

tasks.register("createDokkaInDocs") {
    dependsOn("copyDokkaHtml")
}

tasks.register<Copy>("copyDokkaHtml") {
    group = "documentation"
    dependsOn("dokkaHtml")
    from(file("build/docs/html"))
    into(file("$rootProjectDir/build/docs/html/${project.path.replace(":", "/")}/"))
}

tasks.register<Copy>("copyDokkaGfm") {
    group = "documentation"
    dependsOn("dokkaGfm")
    from(file("build/docs/markdown"))
    into(file("$rootProjectDir/build/docs/markdown/${project.path.replace(":", "/")}/"))
}