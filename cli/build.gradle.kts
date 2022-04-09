group = "io.github.shakelang.shake"
version = "0.1.0"
description = "interpreter"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("io.github.shakelang.shake.conventions.java")
    id("io.github.shakelang.shake.conventions.mpp.all")
    java
    `maven-publish`
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":util:parseutils"))
                implementation(project(":util:shason"))
                implementation(project(":shake:compiler:lexer"))
                implementation(project(":shake:compiler:parser"))
                implementation(project(":shake:compiler:processor"))
                implementation(project(":shake:compiler:jsgenerator"))
                implementation(project(":shake:compiler:interpreter"))
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