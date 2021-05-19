plugins {
    kotlin("jvm") version "1.5.0"
    id("org.jetbrains.dokka") version "1.4.32"
    java
    `maven-publish`
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

sourceSets.test {
    java.srcDirs("src/test/java", "src/test/kotlin")
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

group = "com.github.nsc.de.shake"
version = "0.1.0"
description = "Compiler"
java.sourceCompatibility = JavaVersion.VERSION_1_8

java {
    withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"
    ignoreFailures = true
    filter {
        includeTestsMatching("com.github.nsc.de.shake.*")
    }
}