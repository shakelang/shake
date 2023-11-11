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
    test {
        java.srcDirs("src/test/kotlin")
    }
}

publishing {
    publications {
        // kotlin plugin
        create<MavenPublication>("plugin") {
            from(components["java"])
            groupId = projectGroup("util.changelog")
            artifactId = "plugin"
            version = project.version.toString()
            pom {
                name.set(project.displayName)
                description.set(project.description)
            }
        }
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
}
