package conventions

import gradle.kotlin.dsl.accessors._bed5c5adebf3efe38c055e8c4dfb71ee.kotlin
import org.gradle.crypto.checksum.Checksum
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmDependency

plugins {
    id("maven-publish")
    id("signing")
    id("org.gradle.crypto.checksum")
}

val publishScopes = PublishScopesConfig(project)

fun publishScopes(block: PublishScopesConfig.() -> Unit) = block(publishScopes)

tasks.register("listDependencies") {
    group = "info"
    doLast {

        kotlin.sourceSets.all {
            println("Source Set: $name, Configuration: $implementationConfigurationName, Dependencies:")

            configurations.getByName(this.implementationConfigurationName).dependencies.forEach {
                val dep = mutableListOf<String>()
                if (it.group != null) dep.add(it.group!!)
                if (it.name != null) dep.add(it.name)
                if (it.version != null) dep.add(it.version.toString())
                println("  - ${if (it is NpmDependency) "npm" else "maven"} ${dep.joinToString(":")}")
            }
        }
    }
}

// kotlin {
//    val publicationsFromMainHost =
//        listOf(jvm(), js()).map { it.name } + "kotlinMultiplatform"
// }

// tasks.withType(PublishToMavenRepository::class.java) {
//    println(this.name)
//
// }
signing {
    val signingKey = System.getenv("GRADLE_SIGNING_KEY")
    val signingPassword: String? = System.getenv("GRADLE_SIGNING_PASSWORD")

    if (signingKey != null && signingPassword != null) {
        logger.log(LogLevel.INFO, "Found signing key and password, using in-memory PGP keys, using them")
        useInMemoryPgpKeys(signingKey, signingPassword)
    }

    sign(publishing.publications)
}

afterEvaluate {

//    nexusPublishing {
//        repositories {
//            sonatype {  //only for users registered in Sonatype after 24 Feb 2021
//                nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
//                snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
//            }
//        }
//    }

    publishing {

        repositories {

            val sonartype = if (publishScopes.configured) publishScopes.sonartype.get() else true
            val githubPackages = if (publishScopes.configured) publishScopes.githubPackages.get() else true
            val mavenLocal = if (publishScopes.configured) publishScopes.mavenLocal.get() else true
            val gradlePluginPortal = if (publishScopes.configured) publishScopes.gradlePluginPortal.get() else false

//            if(sonartype) maven("Sonatype") {
//                name = "Sonatype"
//                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//
//                val _username =
//                    System.getenv("GRADLE_SONATYPE_USERNAME") ?: project.properties["sonatype.username"] as String?
//                val _password =
//                    System.getenv("GRADLE_SONATYPE_PASSWORD") ?: project.properties["sonatype.password"] as String?
//
//                if (_username == null || _password == null) {
//                    logger.log(
//                        LogLevel.WARN,
//                        "No Sonatype credentials found, skipping Sonatype publishing configuration"
//                    )
//                    return@maven
//                }
//
//                credentials {
//                    username = _username
//                    password = _password
//                }
//            }

            if (githubPackages) {
                maven("GitHub") {
                    name = "GitHub"
                    url = uri("https://maven.pkg.github.com/shakelang/shake")

                    val usernameLocal =
                        System.getenv("GRADLE_GITHUB_USERNAME") ?: project.properties["github.username"] as String?
                    val tokenLocal = System.getenv("GRADLE_GITHUB_TOKEN") ?: project.properties["github.token"] as String?

                    if (usernameLocal == null || tokenLocal == null) {
                        logger.log(LogLevel.WARN, "No GitHub credentials found, skipping GitHub publishing configuration")
                        return@maven
                    }

                    credentials {
                        username = usernameLocal
                        password = tokenLocal
                    }
                }
            }

            if (mavenLocal) mavenLocal()
        }

        publications.withType<MavenPublication> {

            if (!project.ext.has("isMultiplatform") || project.ext["isMultiplatform"] == false) {
                artifact(tasks["sourcesJar"])
            }

            artifact(tasks["dokkaJavadocJar"])
            artifact(tasks["dokkaHtmlJar"])

            pom {
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/${Meta.GITHUB_REPO}")
                licenses {
                    license {
                        name.set(Meta.LICENSE)
                        url.set("https://github.com/${Meta.GITHUB_REPO}/blob/master/LICENSE")
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
                        "https://github.com/${Meta.GITHUB_REPO}.git",
                    )
                    connection.set(
                        "scm:git:git://github.com/${Meta.GITHUB_REPO}.git",
                    )
                    developerConnection.set(
                        "scm:git:git://github.com/${Meta.GITHUB_REPO}.git",
                    )
                }
                issueManagement {
                    url.set("https://github.com/${Meta.GITHUB_REPO}/issues")
                }
            }
        }
    }

    tasks.withType<Sign> {
        group = "signing"
    }

    tasks.create("signAllPublications") {
        group = "signing"
        tasks.withType<Sign> {
            this@create.dependsOn(this)
        }
    }

    tasks.create<Checksum>("createChecksums") {
        group = "checksums"
        dependsOn("build", "signAllPublications")

        val dir = file("build/libs")
        val files = dir.listFiles { _, name -> name.endsWith(".jar") }

        inputFiles.setFrom(files)
        outputDirectory.set(file("build/libs"))
    }
}
