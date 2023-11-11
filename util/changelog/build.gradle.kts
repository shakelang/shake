import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.publishing")
    kotlin("jvm")
}

group = projectGroup("util.changelog")
version = "0.1.0"
description = "Changelog generation plugin for Shake"

val projectName = name

kotlin {
    dependencies {
        implementation(gradleApi())
        implementation(project(":util:colorlib"))
        implementation(project(":util:shason"))
        testImplementation(kotlin("test"))
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
}
