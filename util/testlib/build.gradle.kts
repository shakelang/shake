import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("io.github.shakelang.shake.conventions.mpp.publishing")
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
