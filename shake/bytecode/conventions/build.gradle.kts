import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

group = projectGroup("shake.bytecode")
version = resolveVersion()
description = "Bytecode conventions for Shake"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        implementation(project(":util:pointers"))
        implementation(project(":util:primitives"))
        implementation(project(":util:common-io"))
        implementation(project(":shake:compiler:descriptor"))
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
