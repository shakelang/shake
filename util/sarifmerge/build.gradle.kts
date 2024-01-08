import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.projectGroup

plugins {
    id("conventions.publishing")
    id("conventions.dokka")
    `kotlin-dsl`
}

repositories {
    maven {
        url = uri("https://clojars.org/repo")
    }
}

group = projectGroup("util")
version = resolveVersion()
description = "Utility package to merged sarif files for github report"
public = true

val projectName = name

kotlin {
    dependencies {
        implementation(gradleApi())
        implementation(project(":util:shason"))
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "src/main/java")
    }
    test {
        java.srcDirs("src/test/kotlin", "src/test/java")
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(kotlin.sourceSets.main.get().kotlin)
}

publishing {
    publications {
        // kotlin plugin
        withType<MavenPublication> {
            groupId = project.group.toString()
            artifactId = projectName
            version = project.version.toString()
        }
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
}
