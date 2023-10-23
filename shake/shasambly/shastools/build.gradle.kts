import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
}

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Shake's own bytecode format interpreter"

repositories {
    mavenCentral()
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":shake:shasambly:shasambly"))
        testImplementation(kotlin("test"))
    }
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