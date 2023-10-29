package io.github.shakelang.shake.conventions.mpp

plugins {
//    id("com.github.hierynomus.license")
}

// configure jar builds to include license file
tasks.withType<Jar> {
    from(rootProject.file("LICENSE"))
}