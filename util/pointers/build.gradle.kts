import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utility for using pointers in Kotlin"

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}