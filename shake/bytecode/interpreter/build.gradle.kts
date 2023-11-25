import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

group = projectGroup("compiler.jsgenerator")
version = resolveVersion()
description = "js-generator"
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

        kotest()
    }
}
