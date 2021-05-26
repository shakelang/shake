group = "com.github.nsc.de.shake"
version = "0.1.0"
description = "interpreter"
java.sourceCompatibility = JavaVersion.VERSION_1_8

apply(plugin = "java-library")

plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.dokka")
    id("com.github.nsc.de.shake.java-conventions")
    java
    `maven-publish`
}

val srcDirs = arrayOf("src/main/java", "src/main/kotlin")
val testDirs = arrayOf("src/test/java", "src/test/kotlin")


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
    implementation(project(":util"))
    implementation(project(":lexer"))
    implementation(project(":parser"))
    implementation("org.json:json:20180130")
    implementation("org.reflections:reflections:0.9.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

tasks.register<Jar>("resourceJar") {
    this.archiveFileName.set("${project.name}-${project.version}-resources.jar")
    from("src/main/resources") {
        include("**")
    }
}

tasks.register<Jar>("sourceJar") {
    this.archiveFileName.set("${project.name}-${project.version}-sources.jar")
    srcDirs.forEach {
        from(it) {
            include("**")
        }
    }
}

java {
    withJavadocJar()
}

tasks.build {
    dependsOn("resourceJar")
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