import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utilities for parsing command line arguments and building console applications"
public = true

val projectName = name

useKotest()

kotlin {
    dependencies {
        testImplementation(project(":util:testlib"))
    }
}
