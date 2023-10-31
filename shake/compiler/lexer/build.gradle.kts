import conventions.dependencies

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        testImplementation(kotlin("test"))
    }
}
