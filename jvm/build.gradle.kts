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
                implementation(project(":cli"))
            }
        }
        val test by getting {
            dependencies {
            }
        }
    }
}

application {
    applicationName = "shake"
    mainClass.set("io.github.shakelang.shake.cli.ShakeCli")
}
var classPath: FileCollection? = null