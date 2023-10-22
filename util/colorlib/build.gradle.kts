import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("com.github.node-gradle.node") version "3.1.1"
    id("maven-publish")
}

group = "io.github.shakelang.util.colorlib"
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

repositories {
    mavenCentral()
}

node {
    // Whether to download and install a specific Node.js version or not
    // If false, it will use the globally installed Node.js
    // If true, it will download node using above parameters
    // Note that npm is bundled with Node.js
    download.set(false)

    // Base URL for fetching node distributions
    // Only used if download is true
    // Change it if you want to use a mirror
    // Or set to null if you want to add the repository on your own.
    distBaseUrl.set("https://nodejs.org/dist")

    // The npm command executed by the npmInstall task
    // By default it is install, but it can be changed to ci
    npmInstallCommand.set("ci")

    // The directory where Node.js is unpacked (when download is true)
    workDir.set(file("${project.rootDir}/.gradle/nodejs"))

    // The directory where npm is installed (when a specific version is defined)
    npmWorkDir.set(file("${project.rootDir}/.gradle/npm"))

    // The directory where yarn is installed (when a Yarn task is used)
    yarnWorkDir.set(file("${project.rootDir}/.gradle/yarn"))

    // The Node.js project directory location
    // This is where the package.json file and node_modules directory are located
    // By default it is at the root of the current project
    nodeProjectDir.set(file("${project.rootDir}/docs"))

    // Whether the plugin automatically should add the proxy configuration to npm and yarn commands
    // according the proxy configuration defined for Gradle
    // Disable this option if you want to configure the proxy for npm or yarn on your own
    // (in the .npmrc file for instance)
    // nodeProxySettings.set(ProxySettings.SMART)
}

kotlin {

    js(IR) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useFirefoxHeadless()
                }
            }
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.9.10")
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