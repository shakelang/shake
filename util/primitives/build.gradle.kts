import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utility for working with colors in console applications (Kotlin Multiplatform)"
public = true

val projectName = name

kotlin {
    dependencies {
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
