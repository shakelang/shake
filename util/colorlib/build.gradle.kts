import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
}

group = "io.github.shakelang.util.colorlib"
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}


tasks.register("documentationBase") {
    dependsOn(
        "copyDokkaHtml", // execute yarn install
        "yarn" // generate dokka docs & copy them
    )
}

tasks.register("documentation") {
    dependsOn(
        "documentationBase", // documentation task base
        "yarnBuild" // start yarn build script
    )
}

tasks.register("prepareDocumentationDevelopment") {
    dependsOn(
        "documentationBase", // documentation task base
    )
}

tasks.register("deployDocumentation") {
    dependsOn(
        "documentationBase", // documentation task base
        "yarnDeploy" // start yarn build script
    )
}

listOf(
    "docusaurus", "start", "build", "swizzle", "deploy", "clear", "serve", "write-translations", "write-heading-ids", "typecheck").forEach {
    tasks.register<com.github.gradle.node.yarn.task.YarnTask>("yarn${it.capitalize()}") {
        this.yarnCommand.set(listOf(it))
        this.workingDir.set(file("${project.rootDir}/docs"))
    }
}