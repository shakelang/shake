import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

group = projectGroup("shake.compiler")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

kotlin {
    dependencies {
        implementation(project(":util:pointers"))
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":util:common-io"))
        implementation(project(":util:primitives"))
        implementation(project(":util:logger"))
        implementation(project(":shake:compiler:lexer"))
        implementation(project(":shake:compiler:parser"))
        implementation(project(":shake:compiler:descriptor"))
        testImplementation(project(":util:testlib"))
        testImplementation(project(":util:embed:api"))
        testImplementation(project(":shake:compiler:shakelib"))
    }
}

useKotest()
