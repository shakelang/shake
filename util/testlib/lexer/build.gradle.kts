import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.KOTEST_VERSION
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.testlib")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"
public = true

val projectName = name

kotlin {
    dependencies {
        implementation("io.kotest:kotest-framework-engine:$KOTEST_VERSION")
        implementation("io.kotest:kotest-assertions-core:$KOTEST_VERSION")
        implementation("io.kotest:kotest-property:$KOTEST_VERSION")
        implementation(project(":util:common-io"))
        implementation(project(":util:parseutils"))
    }
}
useKotest()
