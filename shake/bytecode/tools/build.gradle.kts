import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

group = projectGroup("shake.bytecode")
version = resolveVersion()
description = "Tools for working with bytecode"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

kotlin {
    dependencies {
        implementation(project(":util:pointers"))
        implementation(project(":util:primitives"))
        implementation(project(":util:common-io"))
        implementation(project(":shake:bytecode:conventions"))
        implementation(project(":shake:bytecode:utils"))
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
