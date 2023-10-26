import io.github.shakelang.shake.conventions.mpp.dependencies
plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("io.github.shakelang.shake.conventions.mpp.publishing")
}

group = "io.github.shakelang.util.common-io"
version = "0.1.0"
description = "Utility for working with colors in console applications (Kotlin Multiplatform)"

val projectName = name

kotlin {
    dependencies {
        implementation(project(":util:primitives"))
        testImplementation(kotlin("test"))
    }
}