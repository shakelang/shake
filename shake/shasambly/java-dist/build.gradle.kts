plugins {
    application
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("maven-publish")
}

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Java distribution commands for Shasambly"

repositories {
    mavenCentral()
}

tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("docs/html"))

    doFirst {
        dokkaSourceSets.create("common") {
            sourceRoots.setFrom("src/commonMain")
        }
        dokkaSourceSets.create("jvm") {
            sourceRoots.setFrom("src/jvmMain")
        }
        dokkaSourceSets.create("js") {
            sourceRoots.setFrom("src/jsMain")
        }
    }
}

tasks.dokkaGfm.configure {
    outputDirectory.set(buildDir.resolve("docs/markdown"))

    doFirst {
        dokkaSourceSets.create("common") {
            sourceRoots.setFrom("src/commonMain")
        }
        dokkaSourceSets.create("jvm") {
            sourceRoots.setFrom("src/jvmMain")
        }
        dokkaSourceSets.create("js") {
            sourceRoots.setFrom("src/jsMain")
        }
    }
}

kotlin {
    sourceSets {
        val main by getting {
            dependencies {
                implementation(project(":util:parseutils"))
                implementation(project(":shake:shasambly:shasambly"))
            }
        }
        val test by getting {
            dependencies {
                implementation(project(":util:parseutils"))
                implementation(project(":shake:shasambly:shasambly"))
            }
        }
    }
}

application {
    applicationName = "ShasTools"
}
var classPath: FileCollection? = null
val startScripts = tasks.named<CreateStartScripts>("startScripts") {
    applicationName = "shasx"
    outputDir = file("build/scripts")
    mainClassName = "io.github.shakelang.shake.shasambly.Shasx"
    classPath = classpath
    finalizedBy(startScriptShasB, startScriptShasC, startScriptShasG)
}
val startScriptShasB by tasks.register<CreateStartScripts>("startScriptShasB") {
    applicationName = "shasb"
    outputDir = file("build/scripts")
    mainClassName = "io.github.shakelang.shake.shasambly.ShasBeautify"
    classpath = classPath
}
val startScriptShasC by tasks.register<CreateStartScripts>("startScriptShasC") {
    applicationName = "shasc"
    outputDir = file("build/scripts")
    mainClassName = "io.github.shakelang.shake.shasambly.ShasCompile"
    classpath = classPath
}
val startScriptShasG by tasks.register<CreateStartScripts>("startScriptShasG") {
    applicationName = "shasg"
    outputDir = file("build/scripts")
    mainClassName = "io.github.shakelang.shake.shasambly.ShasGenerate"
    classpath = classPath
}