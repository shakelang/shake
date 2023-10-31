import conventions.dependencies
plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = "io.github.shakelang.util.colorlib"
version = "0.1.1"
description = "Utility for working with colors in console applications (Kotlin Multiplatform)"

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}