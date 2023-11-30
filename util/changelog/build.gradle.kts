import com.shakelang.shake.util.changelog.public
import com.shakelang.shake.util.changelog.resolveVersion
import conventions.projectGroup

plugins {
    id("conventions.publishing")
    id("conventions.dokka")
    `kotlin-dsl`
    java
}

repositories {
    maven {
        url = uri("https://clojars.org/repo")
    }
}

group = projectGroup("util.changelog")
version = resolveVersion()
description = "Changelog generation plugin for Shake"
public = true

val projectName = name

kotlin {
    dependencies {
        implementation(gradleApi())
        implementation(project(":util:colorlib"))
        implementation(project(":util:shason"))
        implementation(project(":util:markdown"))
        implementation("com.googlecode.lanterna:lanterna:3.1.1")
        implementation("org.jline:jline:3.1.3")
        implementation("org.fusesource.jansi:jansi:2.4.1")
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
