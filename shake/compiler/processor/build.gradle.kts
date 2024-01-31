import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup
import conventions.useKotest
import dev.icerock.gradle.MRVisibility

plugins {
    id("conventions.all")
    id("conventions.publishing")
    id("dev.icerock.mobile.multiplatform-resources")
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
        testImplementation(project(":util:testlib"))
        testImplementation(project(":shake:compiler:shakelib"))

        testImplementation("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform
        testImplementation("dev.icerock.moko:resources-test:0.23.0")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.shakelang.shake.processor.testsrc" // required
    multiplatformResourcesClassName = "SharedRes" // optional, default MR
    multiplatformResourcesVisibility = MRVisibility.Internal // optional, default Public
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesSourceSet = "commonTest" // optional, default "commonMain"
}

useKotest()
