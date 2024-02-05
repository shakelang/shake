import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util")
version = resolveVersion()

description = "Logger utility for Kotlin Multiplatform."
public = true

val projectName = name

kotlin {
    dependencies {
        implementation(project(":util:environment"))
        implementation(project(":util:colorlib"))
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
        testImplementation(kotlin("test"))

        this.js {
            implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.11.0")
        }
    }
}
