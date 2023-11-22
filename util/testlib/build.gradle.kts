import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

val projectName = name

kotlin {
    dependencies {
        kotest()
    }
}
