import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
}

group = projectGroup("shake.compiler")
version = resolveVersion()
public = true
description = "Utilities for parsing stuff with kotlin"

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:common-io"))
        testImplementation(project(":util:testlib:lexer"))
    }
}

useKotest()
