package io.github.shakelang.shake.conventions

import gradle.kotlin.dsl.accessors._bdd125900c342096ebc27a61977d96e4.java
import gradle.kotlin.dsl.accessors._bdd125900c342096ebc27a61977d96e4.publishing
import org.gradle.kotlin.dsl.*

plugins {
    `java-library`
    `maven-publish`
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

group = "io.github.shakelang.shake"
version = "0.1.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
