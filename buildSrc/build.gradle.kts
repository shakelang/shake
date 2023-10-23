val KOTLIN_VERSION = "1.9.10"

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$KOTLIN_VERSION")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION")
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.4")
    implementation("com.github.node-gradle:gradle-node-plugin:7.0.1")
}