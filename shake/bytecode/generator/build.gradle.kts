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
    id("conventions.publishing")
}

kotlin {
    dependencies {
        implementation(project(":util:pointers"))
        implementation(project(":util:primitives"))
        implementation(project(":util:common-io"))
        implementation(project(":shake:bytecode:conventions"))
        implementation(project(":shake:bytecode:utils"))
        implementation(project(":shake:compiler:processor"))
        testImplementation(project(":util:testlib"))
        testImplementation(project(":util:shason"))
        testImplementation(project(":util:embed:api"))
        testImplementation(project(":shake:compiler:shakelib"))
        testImplementation(project(":shake:bytecode:interpreter"))
        testImplementation(project(":shake:bytecode:tools"))
    }
}

useKotest()
