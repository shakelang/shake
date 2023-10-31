import conventions.dependencies

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = "io.github.shakelang.util.testlib"
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

kotlin {
    dependencies {
        implementation(kotlin("test"))
    }
}
