import io.github.shakelang.shake.conventions.mpp.dependencies

group = "io.github.shakelang.util.shason"
version = "0.1.0"
description = "A json parser implemented in kotlin (mpp)"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        testImplementation(kotlin("test"))
    }
}

val projectName = name
tasks.named<Jar>("jvmJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("jsJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("metadataJar") {
    archiveBaseName.set("shake-$projectName")
}