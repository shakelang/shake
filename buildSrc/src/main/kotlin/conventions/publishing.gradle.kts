package conventions

import gradle.kotlin.dsl.accessors._11c752c08fee604d934630adf5f25d89.kotlin
import org.gradle.crypto.checksum.Checksum
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmDependency

plugins {
    id("maven-publish")
    id("signing")
    id("org.gradle.crypto.checksum")
}

if (project.rootProject !== project) {
    throw IllegalStateException("This plugin must be applied to the root project")
}

println("Applying publishing conventions")

class RootPublishingConfig(
    val ghUsername: String?,
    val ghToken: String?,
    val signingKey: String?,
    val signingPassword: String?,
) {
    val ghConfigured: Boolean = ghUsername != null && ghToken != null
    val signingConfigured: Boolean = signingKey != null && signingPassword != null
}

val ghUsername =
    System.getenv("GRADLE_GITHUB_USERNAME") ?: project.properties["github.username"] as String?
val ghToken = System.getenv("GRADLE_GITHUB_TOKEN") ?: project.properties["github.token"] as String?

if (ghUsername == null || ghToken == null) {
    logger.log(LogLevel.WARN, "No GitHub credentials found, skipping GitHub publishing configuration")
}

val signingKey = System.getenv("GRADLE_SIGNING_KEY")
val signingPassword: String? = System.getenv("GRADLE_SIGNING_PASSWORD")

if (signingKey == null || signingPassword == null) {
    logger.log(LogLevel.WARN, "No signing key and password found, skipping signing configuration")
}

val rootPublishingConfig = RootPublishingConfig(ghUsername, ghToken, signingKey, signingPassword)
project.extensions.add(RootPublishingConfig::class.java, "rootPublishingConfig", rootPublishingConfig)

allprojects {

    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "org.gradle.crypto.checksum")

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

    signing {
        if (rootPublishingConfig.signingConfigured) {
            useInMemoryPgpKeys(rootPublishingConfig.signingKey, rootPublishingConfig.signingPassword)
        }

        sign(publishing.publications)
    }

    afterEvaluate {
        publishing {
            repositories {
                val sonartype = if (publishScopes.configured) publishScopes.sonartype.get() else true
                val githubPackages = if (publishScopes.configured) publishScopes.githubPackages.get() else true
                val mavenLocal = if (publishScopes.configured) publishScopes.mavenLocal.get() else true
                val gradlePluginPortal = if (publishScopes.configured) publishScopes.gradlePluginPortal.get() else false

                if (githubPackages) {
                    maven("GitHub") {
                        name = "GitHub"
                        url = uri("https://maven.pkg.github.com/shakelang/shake")

                        if (!rootPublishingConfig.ghConfigured) return@maven

                        credentials {
                            username = rootPublishingConfig.ghUsername
                            password = rootPublishingConfig.ghToken
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
}
