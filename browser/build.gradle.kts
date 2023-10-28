plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("js") version "1.5.10"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(project(":util"))
    implementation(project(":lexer"))
    implementation(project(":parser"))
    implementation(project(":interpreter"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

kotlin {
    js {
        browser {
            dceTask {
                keep("shake.execute")
                keep("shake.addInterpreterFile")
            }
            compilations {
                "main" {
                    packageJson {
                        customField("browser", mapOf( "fs" to false, "path" to false, "os" to false, "readline" to false))
                    }
                }
            }
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}