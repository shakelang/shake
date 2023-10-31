import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("shasambly.shastools")
version = "0.1.0"
description = "Shake's own bytecode format interpreter"

kotlin {
    dependencies {
        implementation(project(":util:common-io"))
        implementation(project(":util:primitives"))
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