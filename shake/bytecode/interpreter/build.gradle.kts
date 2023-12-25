import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

group = projectGroup("bytecode")
version = resolveVersion()
description = "Bytecode interpreter for Shake"
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
        testImplementation(project(":util:testlib"))
        testImplementation(project(":shake:bytecode:utils"))
    }
}

useKotest()
