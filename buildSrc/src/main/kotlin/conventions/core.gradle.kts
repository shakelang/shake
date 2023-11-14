package conventions

import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

val jvmTarget = JVM_TARGET

// Tell other plugins that this is a multiplatform project
project.ext.set("isMultiplatform", true)

plugins {
    kotlin("multiplatform")
//    id("org.junit.platform:junit-platform-reporting:1.10.0")
    id("org.jetbrains.kotlinx.kover")
    id("com.github.node-gradle.node")
}

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    withSourcesJar(publish = true)
//    dependencies {
//        implementation("dev.icerock.moko:resources:0.23.0")
//        testImplementation("dev.icerock.moko:resources-test:0.23.0")
//    }
    jvm { }
    js(IR) {
        browser {
            testTask(Action {
                useKarma {
                    useChromeHeadless()
                    useFirefoxHeadless()
                }
            })
        }
    }
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

//multiplatformResources {
//    multiplatformResourcesPackage = "com.shakelang.shake.$packageName.resources"
//    multiplatformResourcesClassName = "${packageCapitalized}Resources"
//    iosBaseLocalizationRegion = "en" // optional, default "en"
//    multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
//}

kotlin {
//    dependencies {
//        implementation("dev.icerock.moko:resources:0.23.0")
//        testImplementation("dev.icerock.moko:resources-test:0.23.0")
//    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = jvmTarget
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }

//        val main by compilations.getting
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
                        customField(
                            "browser",
                            mapOf("fs" to false, "path" to false, "os" to false, "readline" to false)
                        )
                    }
                    kotlinOptions {
                        moduleKind = "commonjs"
                        sourceMap = true
                        sourceMapEmbedSources = "always"
                    }

                }
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
//
//tasks.named("compileKotlinJs").configure {
//    dependsOn("generateMRcommonMain")
//}
//
//tasks.named("compileKotlinJvm").configure {
//    dependsOn("generateMRcommonMain")
//}
//
//tasks.named("compileKotlinMetadata").configure {
//    dependsOn("generateMRcommonMain")
//}
//
//tasks.named("compileTestKotlinJs").configure {
//    dependsOn("generateMRcommonMain")
//}
//
//tasks.named("compileTestKotlinJvm").configure {
//    dependsOn("generateMRcommonMain")
//}
//
//tasks.named("jsProcessResources").configure {
//    dependsOn("generateMRcommonMain")
//}
//
//tasks.named("jvmProcessResources").configure {
//    dependsOn("generateMRcommonMain")
//}

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
