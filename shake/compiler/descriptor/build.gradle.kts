import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

group = projectGroup("shake.compiler")
version = resolveVersion()
description = "Compiler descriptor conventions"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        implementation(project(":util:pointers"))
        implementation(project(":util:primitives"))
        implementation(project(":util:common-io"))
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
