import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("io.github.shakelang.shake.conventions.mpp.publishing")
}

group = "io.github.shakelang.util.parseutils"
version = "0.1.1"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

kotlin {
    dependencies {
        kotlin("stdlib-common")
        implementation(project(":util:colorlib"))
        implementation(project(":util:common-io"))
        testImplementation(kotlin("test"))
        testImplementation(project(":util:testlib"))
    }
}
