import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.common-io")
version = "0.1.2"
description = "Utility for working with colors in console applications (Kotlin Multiplatform)"

val projectName = name

kotlin {
    dependencies {
        implementation(project(":util:primitives"))
        testImplementation(kotlin("test"))
    }
}
