package io.github.shakelang.shake.conventions.mpp

plugins {
    id("maven-publish")
    id("signing")
}

publishing {
    repositories {
        maven("Sonatype") {
            name = "Sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            val _username = System.getenv("GRADLE_SONATYPE_USERNAME") ?: project.properties["sonatype.username"] as String?
            val _password = System.getenv("GRADLE_SONATYPE_PASSWORD") ?: project.properties["sonatype.password"] as String?

            if (_username == null || _password == null) {
                logger.log(LogLevel.WARN, "No Sonatype credentials found, skipping Sonatype publishing configuration")
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

            val _username = System.getenv("GRADLE_GITHUB_USERNAME") ?: project.properties["github.username"] as String?
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


tasks.named("signJsPublication") {
    group = "signing"
}

tasks.named("signJvmPublication") {
    group = "signing"
}

tasks.named("signKotlinMultiplatformPublication") {
    group = "signing"
}
