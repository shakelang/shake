package io.github.shakelang.shake.conventions.mpp

import java.util.Properties
import java.io.FileInputStream

plugins {
    `maven-publish`
    signing
}

val secretsFile = rootProject.file("secrets.properties")
if (secretsFile.exists()) {
    val secrets = Properties()
    secrets.load(FileInputStream(secretsFile))

    publishing {
        publications {
            create("mavenJava", MavenPublication::class) {
                from(components["java"])
            }
//            create<MavenPublication> {
//                // ...
//                credentials {
//                    username = secrets["sonatypeUsername"]?.toString()
//                    password = secrets["sonatypePassword"]?.toString()
//                }
//            }
        }
    }

    signing {
        sign(publishing.publications)
//        useInMemoryPgpKeys {
//            keyId = secrets["signing.keyId"]?.toString()
//            keyPassword = secrets["signing.password"]?.toString()
//        }
    }
}
