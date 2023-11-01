import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("testlib")
version = "0.1.1"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

kotlin {
    dependencies {
        implementation(kotlin("test"))
    }
}
