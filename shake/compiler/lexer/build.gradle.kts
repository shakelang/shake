import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("shake.compiler")
version = resolveVersion()
public = true
description = "Utilities for parsing stuff with kotlin"

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:common-io"))
        kotest()
    }
}
