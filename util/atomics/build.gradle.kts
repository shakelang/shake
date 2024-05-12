import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utility library for atomic operations (automatically update values)"
// public = true

val projectName = name

kotlin {
    dependencies {
        testImplementation(project(":util:testlib"))
        implementation(project(":util:primitives"))
    }
}

useKotest()
