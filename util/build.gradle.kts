import org.jetbrains.kotlin.gradle.targets.js.npm.packageJson

group = "com.github.nsc.de.shake"
version = "0.1.0"
description = "lexer"
java.sourceCompatibility = JavaVersion.VERSION_1_8

apply(plugin = "java-library")

plugins {
    kotlin("multiplatform") version "1.5.10"
    id("org.jetbrains.dokka")
    id("com.github.nsc.de.shake.java-conventions")
    java
    `maven-publish`
}

val srcDirs = arrayOf("src/main/kotlin")
val testDirs = arrayOf("src/test/kotlin")


sourceSets {
    main {
        java.srcDirs(*srcDirs)
    }
    test {
        java.srcDirs(*testDirs)
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20180130")
    implementation("org.reflections:reflections:0.9.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

tasks.register<Jar>("sourceJar") {
    this.archiveFileName.set("${project.name}-${project.version}-sources.jar")
    srcDirs.forEach {
        from(it) {
            include("**")
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
            compilations["main"].packageJson {
                customField("browser", mapOf( "fs" to false, "path" to false, "os" to false))
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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        // val nativeMain by getting
        // val nativeTest by getting
    }
}

tasks.build {
    dependsOn("sourceJar")
}

tasks.test {
    useJUnitPlatform()

    testLogging.showExceptions = true
    maxHeapSize = "1G"
    // ignoreFailures = true
    filter {
        includeTestsMatching("com.github.nsc.de.shake.*")
    }
}