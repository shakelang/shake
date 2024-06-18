import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

group = projectGroup("shake.bytecode")
version = resolveVersion()
description = "Generator for Shake Bytecode"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        implementation(project(":shake:bytecode:conventions"))
        implementation(project(":shake:bytecode:utils"))
        implementation(project(":shake:compiler:processor"))
        implementation(project(":util:common-io"))
        implementation(project(":util:logger"))
        implementation(project(":util:parseutils"))
        implementation(project(":util:pointers"))
        implementation(project(":util:primitives"))
        testImplementation(project(":shake:bytecode:interpreter"))
        testImplementation(project(":shake:bytecode:tools"))
        testImplementation(project(":shake:compiler:shakelib"))
        testImplementation(project(":shake:compiler:shaketest"))
        testImplementation(project(":util:embed:api"))
        testImplementation(project(":util:shason"))
        testImplementation(project(":util:testlib"))
    }
}

useKotest()
