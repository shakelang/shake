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
description = "Utility for working with binary data structures"
public = true

val projectName = name

kotlin {
    dependencies {
        implementation(project(":util:primitives"))
        implementation(project(":util:common-io"))
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
