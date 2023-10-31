import conventions.dependencies
import conventions.projectGroup

group = projectGroup("util.shason")
version = "0.1.0"
description = "A json parser implemented in kotlin (mpp)"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        testImplementation(kotlin("test"))
    }
}
