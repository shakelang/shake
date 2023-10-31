package conventions

import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named
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
    into(file("$rootProjectDir/build/docs/html/$projectName/"))
}

tasks.register<Copy>("copyDokkaGfm") {
    group = "documentation"
    dependsOn("dokkaGfm")
    from(file("build/docs/markdown"))
    into(file("$rootProjectDir/build/docs/markdown/$projectName/"))
}