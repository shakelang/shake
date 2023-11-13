import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.markdown")
version = resolveVersion()
description = "Utility package for working with markdown (Kotlin Multiplatform)"

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}
