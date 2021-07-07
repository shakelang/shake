group = "com.github.shakelang.shake"
version = "0.1.0"
description = "interpreter"
java.sourceCompatibility = JavaVersion.VERSION_1_8

apply(plugin = "java-library")

plugins {
    kotlin("multiplatform") version "1.5.10"
    id("org.jetbrains.dokka")
    id("com.github.shakelang.shake.java-conventions")
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
        val main by compilations.getting
        val jvmFatJar by compilations.creating {
            defaultSourceSet {
                dependencies {
                    implementation(main.compileDependencyFiles + main.output.classesDirs)
                }
            }



            // Create a test task to run the tests produced by this compilation:
            tasks.register<Jar>("jvmFatJar") {
                dependsOn("jvmJar")
                val classpath = compileDependencyFiles + runtimeDependencyFiles
                // Run only the tests from this compilation's outputs:
                val testClassesDirs = output.classesDirs
                manifest {
                    attributes (mapOf(
                        "Implementation-Title" to "Gradle Jar File Example",
                        "Implementation-Version" to archiveVersion,
                        "Main-Class" to "com.github.shakelang.shake.cli.ShakeCli"
                    ))
                }

                archiveAppendix.set("jvm-executable")
                //println(configurations.asMap.filter { it.key.toLowerCase().contains("jvm") }.keys)
                //println(configurations.asMap.mapValues { it.value.map { it2 -> it2.path } })
                from (
                    classpath.map { if(it.isDirectory) it else zipTree(it) },
                ) {
                    exclude("META-INF/DEPENDENCIES")
                    exclude("META-INF/DEPENDENCIES.txt")
                    exclude("META-INF/LICENSE")
                    exclude("META-INF/LICENSE.txt")
                    exclude("META-INF/license.txt")
                    exclude("META-INF/NOTICE")
                    exclude("META-INF/NOTICE.txt")
                    exclude("META-INF/notice.txt")
                    exclude("META-INF/INDEX.LIST")
                    exclude("META-INF/versions")
                    exclude("META-INF/versions/9/module-info.class")
                }
            }
        }
    }

    js(LEGACY) {
        nodejs {
        }
        browser {
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
                implementation(project(":util"))
                implementation(project(":lexer"))
                implementation(project(":parser"))
                implementation(project(":interpreter"))
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
        val jsTest by getting
        // val nativeMain by getting
        // val nativeTest by getting
    }
}

tasks.jar {
    dependsOn("jvmFatJar")
}

tasks.test {
    useJUnitPlatform()

    testLogging.showExceptions = true
    maxHeapSize = "1G"
    // ignoreFailures = true
    filter {
        includeTestsMatching("com.github.shakelang.shake.*")
    }
}
