import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

group = projectGroup("util")
version = resolveVersion()
description = "A json parser implemented in kotlin (mpp)"
public = true

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        implementation(project(":util:common-io"))
        implementation(project(":util:parseutils"))
        testImplementation(project(":util:testlib:lexer"))
    }
}

useKotest()
