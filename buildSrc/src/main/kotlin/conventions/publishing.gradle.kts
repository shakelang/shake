package conventions

import gradle.kotlin.dsl.accessors._bed5c5adebf3efe38c055e8c4dfb71ee.kotlin
import org.gradle.crypto.checksum.Checksum
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmDependency

plugins {
    id("maven-publish")
    id("signing")
    id("org.gradle.crypto.checksum")
}

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


//kotlin {
//    val publicationsFromMainHost =
//        listOf(jvm(), js()).map { it.name } + "kotlinMultiplatform"
//}

publishing {

    repositories {
        maven("Sonatype") {
            name = "Sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            val _username =
                System.getenv("GRADLE_SONATYPE_USERNAME") ?: project.properties["sonatype.username"] as String?
            val _password =
                System.getenv("GRADLE_SONATYPE_PASSWORD") ?: project.properties["sonatype.password"] as String?

            if (_username == null || _password == null) {
                logger.log(
                    LogLevel.WARN,
                    "No Sonatype credentials found, skipping Sonatype publishing configuration"
                )
                return@maven
            }

            credentials {
                username = _username
                password = _password
            }
        }

        maven("GitHub") {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/shakelang/shake")

            val _username =
                System.getenv("GRADLE_GITHUB_USERNAME") ?: project.properties["github.username"] as String?
            val _token = System.getenv("GRADLE_GITHUB_TOKEN") ?: project.properties["github.token"] as String?

            if (_username == null || _token == null) {
                logger.log(LogLevel.WARN, "No GitHub credentials found, skipping GitHub publishing configuration")
                return@maven
            }

            credentials {
                username = _username
                password = _token
            }
        }
        mavenLocal()
    }
}
//tasks.withType(PublishToMavenRepository::class.java) {
//    println(this.name)
//
//}
signing {
    val signingKey = System.getenv("GRADLE_SIGNING_KEY")
    val signingPassword: String? = System.getenv("GRADLE_SIGNING_PASSWORD")

    if (signingKey != null && signingPassword != null) {
        logger.log(LogLevel.INFO, "Found signing key and password, using in-memory PGP keys, using them")
        useInMemoryPgpKeys(signingKey, signingPassword)
    }

    sign(publishing.publications)
}

if(project.tasks.findByName("signJsPublication") != null)
    tasks.named("signJsPublication") {
        group = "signing"
    }

if(project.tasks.findByName("signJvmPublication") != null)
    tasks.named("signJvmPublication") {
        group = "signing"
    }

if(project.tasks.findByName("signKotlinMultiplatformPublication") != null)
    tasks.named("signKotlinMultiplatformPublication") {
        group = "signing"
    }

tasks.create("signAllPublications") {
    group = "signing"
    if(project.tasks.findByName("signKotlinMultiplatformPublication") != null)
        dependsOn("signJsPublication")

    if(project.tasks.findByName("signKotlinMultiplatformPublication") != null)
        dependsOn("signJvmPublication")

    if(project.tasks.findByName("signKotlinMultiplatformPublication") != null)
        dependsOn("signKotlinMultiplatformPublication")
}

tasks.create<Checksum>("createChecksums") {
    group = "checksums"
    dependsOn("build", "signAllPublications")

    val dir = file("build/libs")
    val files = dir.listFiles { _, name -> name.endsWith(".jar") }

    inputFiles.setFrom(files)
    outputDirectory.set(file("build/libs"))
}