group = "io.github.shakelang.shake"
version = "0.1.0"
description = "js-generator"
java.sourceCompatibility = JavaVersion.VERSION_1_8

apply(plugin = "java-library")

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    id("io.github.shakelang.shake.java-conventions")
    java
    `maven-publish`
}

repositories {
    mavenLocal()
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
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(LEGACY) {
        nodejs {
        }
        browser {
            compilations {
                "main" {
                    packageJson {
                        customField("browser", mapOf( "fs" to false, "path" to false, "os" to false))
                    }
                    kotlinOptions {
                        moduleKind = "commonjs"
                        sourceMap = true
                        sourceMapEmbedSources = "always"
                    }
                }
            }
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    /*
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
    */


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":util:parseutils"))
                implementation(project(":shake:compiler:lexer"))
                implementation(project(":shake:compiler:parser"))
                implementation(project(":shake:compiler:processor"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.reflections:reflections:0.9.12")
            }
        }
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        // val nativeMain by getting
        // val nativeTest by getting
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging.showExceptions = true
    maxHeapSize = "1G"
    // ignoreFailures = true
    filter {
        includeTestsMatching("io.github.shakelang.shake.*")
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