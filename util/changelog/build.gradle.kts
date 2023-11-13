import com.shakelang.shake.util.changelog.resolveVersion
import conventions.projectGroup

plugins {
    id("conventions.publishing")
    `kotlin-dsl`
}

group = projectGroup("util.changelog")
version = resolveVersion()
description = "Changelog generation plugin for Shake"

val projectName = name

kotlin {
    dependencies {
        implementation(gradleApi())
        implementation(project(":util:colorlib"))
        implementation(project(":util:shason"))
        implementation("com.googlecode.lanterna:lanterna:3.1.1")
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
