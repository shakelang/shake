import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("compiler.lexer")
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        testImplementation(kotlin("test"))
    }
}
