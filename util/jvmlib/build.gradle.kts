import io.github.shakelang.shake.conventions.mpp.dependencies

group = "io.github.shakelang.jvmlib"
version = "0.1.0"
description = "A library for jvm stuff in java"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        testImplementation(kotlin("test"))
    }
}