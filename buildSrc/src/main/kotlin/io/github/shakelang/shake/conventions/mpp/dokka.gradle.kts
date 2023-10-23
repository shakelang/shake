package io.github.shakelang.shake.conventions.mpp

import org.jetbrains.dokka.gradle.DokkaTask

val rootProjectDir = project.rootProject.rootDir
val projectName = project.name

apply(plugin = "org.jetbrains.dokka")

tasks.named<DokkaTask>("dokkaHtml").configure {
    outputDirectory.set(buildDir.resolve("docs/html"))
}

tasks.named<DokkaTask>("dokkaGfm").configure {
    outputDirectory.set(buildDir.resolve("docs/markdown"))
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