import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

group = projectGroup("shake")
version = resolveVersion()
description = "Shakespeare is a tool for generating shake source files."
public = false

kotlin {
    dependencies {
        implementation(project(":util:primitives"))
        implementation(project(":util:logger"))
        implementation(project(":util:common-io"))
        implementation(project(":util:parseutils"))
        testImplementation(project(":util:testlib"))
        testImplementation(project(":shake:compiler:shakelib"))
    }
}

useKotest()
