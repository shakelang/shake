import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.parseutils")
version = "0.1.2"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

kotlin {
    dependencies {
        kotlin("stdlib-common")
        implementation(project(":util:colorlib"))
        implementation(project(":util:common-io"))
        implementation(project(":util:environment"))
        testImplementation(kotlin("test"))
        testImplementation(project(":util:testlib"))
    }
}
