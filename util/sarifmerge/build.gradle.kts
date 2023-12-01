import com.shakelang.shake.util.changelog.public
import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.dokka")
    id("conventions.publishing")
    `kotlin-dsl`
}

group = projectGroup("util")
version = resolveVersion()
description = "Utility for working with colors in console applications (Kotlin Multiplatform)"
public = true

val projectName = name

kotlin {
    dependencies {
        implementation(gradleApi())
        implementation(project(":util:shason"))
        testImplementation(kotlin("test"))
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
        create<MavenPublication>("plugin") {
            from(components["kotlin"])
            groupId = project.group.toString()
            artifactId = "plugin"
            version = project.version.toString()
        }
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
}
