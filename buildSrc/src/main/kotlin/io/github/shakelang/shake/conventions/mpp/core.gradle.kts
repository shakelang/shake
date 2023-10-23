package io.github.shakelang.shake.conventions.mpp

import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

val jvmTarget = JVM_TARGET

plugins {
    kotlin("multiplatform")
//    id("org.junit.platform:junit-platform-reporting:1.10.0")
    id("org.jetbrains.kotlinx.kover")
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = jvmTarget
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }

        val main by compilations.getting
    }

    js(IR) {
        nodejs {
        }
        browser {
            dependencies {
                implementation(npm("path-browserify", "1.0.1"))
            }

            compilations {
                "main" {
                    packageJson {
                        customField("browser", mapOf( "fs" to false, "path" to false, "os" to false, "readline" to false))
                    }
                    kotlinOptions {
                        moduleKind = "commonjs"
                        sourceMap = true
                        sourceMapEmbedSources = "always"
                    }

                }
            }
            commonWebpackConfig {
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
}

tasks.named("jvmTest") {
//    extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
//        isDisabled = false
//        binaryReportFile.set(file("$buildDir/reports/kover/result.bin"))
//        includes = listOf("*")
//    }
}

val projectName = name

tasks.named<Jar>("jvmJar") {
    archiveBaseName.set("shake-$projectName")
}

tasks.named<Jar>("jsJar") {
    archiveBaseName.set("shake-$projectName")
}

//tasks.named<Jar>("metadataJar") {
//    archiveBaseName.set("shake-$projectName")
//}

tasks.named<KotlinJvmTest>("jvmTest") {
    ignoreFailures = true
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinTest::class).configureEach {
    reports.junitXml.required.set(true)
}