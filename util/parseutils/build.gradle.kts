import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"
public = true

val projectName = name

kotlin {
    dependencies {
        kotlin("stdlib-common")
        implementation(project(":util:colorlib"))
        implementation(project(":util:common-io"))
        implementation(project(":util:environment"))
        testImplementation(kotlin("test"))
        testImplementation(project(":util:testlib"))
    }
}
