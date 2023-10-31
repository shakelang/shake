package io.github.shakelang.shake.conventions.mpp

plugins {
//    id("com.github.hierynomus.license")
}

// configure jar builds to include license file
tasks.withType<Jar> {
    from(rootProject.file("LICENSE"))
    if (project.file("NOTICE").exists()) from(rootProject.file("NOTICE"))
    if (project.file("changelog.md").exists()) from(rootProject.file("changelog.md"))
}