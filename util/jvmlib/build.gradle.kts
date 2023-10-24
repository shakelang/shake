import io.github.shakelang.shake.conventions.mpp.dependencies

group = "io.github.shakelang.jvmlib"
version = "0.1.0"
description = "A library for jvm stuff in java"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    java
}

val javaCompilations = arrayOf("6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")

// Create java compilations source sets
sourceSets {
    javaCompilations.forEach {
        val javaCompilation = create("java$it") {
            java.srcDir("src/resourceSources/java")
            resources.srcDir("src/resourceSources/resources")
            compileClasspath += sourceSets["main"].compileClasspath
        }
    }
}

// Create java compilations tasks
javaCompilations.forEach {
    tasks.register("compileJava$it", JavaCompile::class.java) {
        group = "test-resource-build"
        source = sourceSets["java$it"].java
        classpath = sourceSets["java$it"].compileClasspath
        destinationDir = file("build/classes/java$it")
    }
}

tasks.register("compileAllJava") {
    group = "test-resource-build"
    javaCompilations.forEach {
        dependsOn("compileJava$it")
    }
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        testImplementation(kotlin("test"))
    }
}