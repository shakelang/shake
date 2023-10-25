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

            if (project.properties["sonatype.username"] == null || project.properties["sonatype.password"] == null) {
                logger.log(LogLevel.WARN, "No Sonatype credentials found, skipping Sonatype publishing configuration")
                return@maven
            }

            credentials {
                username = project.property("sonatype.username") as String
                password = project.property("sonatype.password") as String
            }
        }

        maven("GitHub") {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/shakelang/shake")


            if (project.properties["github.username"] == null || project.properties["github.token"] == null) {
                logger.log(LogLevel.WARN, "No GitHub credentials found, skipping GitHub publishing configuration")
                return@maven
            }

            credentials {
                username = project.properties["github.username"] as String
                password = project.property("github.token") as String
            }
        }
    }
}

//tasks.withType(PublishToMavenRepository::class.java) {
//    println(this.name)
//
//}

signing {
//    val signingKey: String? by project
//    val signingPassword: String? by project
//    useInMemoryPgpKeys(signingKey, signingPassword)
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
