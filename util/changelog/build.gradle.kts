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

            artifact(tasks["kotlinSourcesJar"])
            artifact(tasks["dokkaJavadocJar"])
            artifact(tasks["dokkaHtmlJar"])

            pom {
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/${Meta.githubRepo}")
                licenses {
                    license {
                        name.set(Meta.license)
                        url.set("https://github.com/${Meta.githubRepo}/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("shake-developers")
                        name.set("Shake Developers")
                        url.set("https://github.com/shakelang")
                        organization.set("shakelang")
                        organizationUrl.set("https://shakelang.com/")
                    }
                    developer {
                        id.set("nicolas-schmidt")
                        name.set("Nicolas Schmidt")
                        url.set("https://github.com/nsc-de")
                        organization.set("shakelang")
                        organizationUrl.set("https://shakelang.com/")
                    }
                }
                scm {
                    url.set(
                        "https://github.com/${Meta.githubRepo}.git"
                    )
                    connection.set(
                        "scm:git:git://github.com/${Meta.githubRepo}.git"
                    )
                    developerConnection.set(
                        "scm:git:git://github.com/${Meta.githubRepo}.git"
                    )
                }
                issueManagement {
                    url.set("https://github.com/${Meta.githubRepo}/issues")
                }
            }
        }
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
}
