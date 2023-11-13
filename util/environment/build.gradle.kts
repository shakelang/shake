import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.environment")
version = resolveVersion()
description = "Utility for working with colors in console applications (Kotlin Multiplatform)"

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}
