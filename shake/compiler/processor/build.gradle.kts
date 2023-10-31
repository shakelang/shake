import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("compiler.processor")
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":util:common-io"))
        implementation(project(":util:primitives"))
        implementation(project(":shake:compiler:lexer"))
        implementation(project(":shake:compiler:parser"))

        testImplementation(kotlin("test"))
    }
    jvm {
        dependencies {
            implementation("org.reflections:reflections:0.9.12")
        }
    }
}
