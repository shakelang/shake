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
description = "Utility library for primitive types in KMP (Access single bits, work with bytes, etc...)"
public = true

val projectName = name

kotlin {
    dependencies {
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
