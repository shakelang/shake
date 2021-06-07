group = "com.github.shakelang.shake"
version = "0.1.0"
description = "Shake"
java.sourceCompatibility = JavaVersion.VERSION_1_8

apply(plugin = "java-library")

plugins {
    id("org.jetbrains.dokka") version "1.4.32"
    id("com.github.shakelang.shake.java-conventions")
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":util"))
    implementation(project(":lexer"))
    implementation(project(":parser"))
    implementation(project(":interpreter"))
    implementation("org.json:json:20180130")
    implementation("org.reflections:reflections:0.9.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}
/*
tasks.test {
    useJUnitPlatform()

    testLogging.showExceptions = true
    maxHeapSize = "1G"
    // ignoreFailures = true
    filter {
        includeTestsMatching("com.github.shakelang.shake.*")
    }
}
 */