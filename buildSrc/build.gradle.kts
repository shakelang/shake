plugins {
    `kotlin-dsl`
}

repositories {
    maven(url = "https://s01.oss.sonatype.org/content/repositories/releases/")
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.10")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.5")
    implementation("com.github.node-gradle:gradle-node-plugin:7.0.1")
    implementation("gradle.plugin.org.gradle.crypto:checksum:1.4.0")
    implementation("gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin:0.16.1")
    implementation("com.shakelang.shake.util.changelog:plugin:0.5.0")
    implementation("com.shakelang.shake.util:sarifmerge:0.1.1")
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.30.0")
    implementation("io.kotest:kotest-framework-multiplatform-plugin-gradle:5.8.0")
}