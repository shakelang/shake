import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.logger")
version = "0.1.0"
description = "Logger utility for Kotlin Multiplatform."

val projectName = name

kotlin {
    dependencies {
        implementation(project(":util:environment"))
        implementation(project(":util:colorlib"))
        testImplementation(kotlin("test"))
    }

    js {
        dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.9.1")
        }
    }
}