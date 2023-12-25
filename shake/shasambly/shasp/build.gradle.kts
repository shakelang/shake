import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("shasambly.shasp")
version = resolveVersion()
description = "Shasp is a very simple programming language that outputs shasambly bytecode."

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":shake:shasambly:shasambly"))
        implementation(project(":shake:shasambly:shastools"))
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
// tasks.named<Jar>("metadataJar") {
//    archiveBaseName.set("shake-$projectName")
// }
