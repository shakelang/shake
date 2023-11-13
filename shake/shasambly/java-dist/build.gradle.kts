import com.shakelang.shake.util.changelog.resolveVersion
import conventions.projectGroup
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    application
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

group = projectGroup("shasambly.java-dist")
version = resolveVersion()
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
                implementation(project(":util:primitives"))
                implementation(project(":util:common-io"))
                implementation(project(":util:parseutils"))
                implementation(project(":shake:shasambly:shasambly"))
                implementation(project(":shake:shasambly:shastools"))
                implementation(project(":shake:shasambly:shasp"))
            }
        }
        val test by getting {
            dependencies {
            }
        }
    }

    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_16)
    }
}

application {
    applicationName = "ShasTools"
}
var classPath: FileCollection? = null
val startScripts = tasks.named<CreateStartScripts>("startScripts") {
    applicationName = "shasx"
    outputDir = file("build/scripts")
    mainClassName = "com.shakelang.shake.shasambly.ShasXKt"
    classPath = classpath
    dependsOn(startScriptShasB, startScriptShasC, startScriptShasG, startScriptShasP)
}
val startScriptShasB by tasks.register<CreateStartScripts>("startScriptShasB") {
    applicationName = "shasb"
    outputDir = file("build/scripts")
    mainClassName = "com.shakelang.shake.shasambly.ShasBeautifyKt"
    classpath = classPath
}
val startScriptShasC by tasks.register<CreateStartScripts>("startScriptShasC") {
    applicationName = "shasc"
    outputDir = file("build/scripts")
    mainClassName = "com.shakelang.shake.shasambly.ShasCompileKt"
    classpath = classPath
}
val startScriptShasG by tasks.register<CreateStartScripts>("startScriptShasG") {
    applicationName = "shasg"
    outputDir = file("build/scripts")
    mainClassName = "com.shakelang.shake.shasambly.ShasGenerateKt"
    classpath = classPath
}
val startScriptShasP by tasks.register<CreateStartScripts>("startScriptShasP") {
    applicationName = "shasp"
    outputDir = file("build/scripts")
    mainClassName = "com.shakelang.shake.shasambly.ShasPKt"
    classpath = classPath
}

val projectName = name
tasks.named<Jar>("jar") {
    archiveBaseName.set("shake-$projectName")
}
