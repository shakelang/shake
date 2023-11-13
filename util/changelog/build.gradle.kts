import com.shakelang.shake.util.changelog.resolveVersion
import conventions.Meta
import conventions.projectGroup

plugins {
    id("conventions.publishing")
    id("conventions.dokka")
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
            groupId = projectGroup("util.changelog")
            artifactId = "plugin"
            version = project.version.toString()
        }
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
}
