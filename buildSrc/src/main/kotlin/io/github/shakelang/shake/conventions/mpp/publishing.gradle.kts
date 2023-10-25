package io.github.shakelang.shake.conventions.mpp

import org.jetbrains.kotlin.gradle.tasks.throwExceptionIfCompilationFailed
import java.util.Properties
import java.io.FileInputStream

plugins {
    `maven-publish`
    signing
}

publishing {
    repositories {
        maven("Sonatype") {
            name = "Sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                username = project.property("sonatype.username") as String
                password = project.property("sonatype.password") as String
            }
        }

        maven("GitHub") {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/shakelang/shake")

            credentials {
                username = project.property("github.username") as String
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
