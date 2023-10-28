import io.github.shakelang.shake.conventions.mpp.dependencies

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "interpreter"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    java
}

kotlin {
    dependencies {
        common {
            implementation(project(":util:parseutils"))
            implementation(project(":util:shason"))
            implementation(project(":shake:compiler:lexer"))
            implementation(project(":shake:compiler:parser"))
            implementation(project(":shake:compiler:processor"))
            implementation(project(":shake:compiler:jsgenerator"))
            implementation(project(":shake:compiler:interpreter"))
            testImplementation(kotlin("test"))
        }
        jvm {
            implementation("org.reflections:reflections:0.9.12")
        }
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