import com.shakelang.shake.util.changelog.public
import com.shakelang.shake.util.changelog.resolveVersion
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
        testImplementation(kotlin("test"))

        this.js {
            implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.9.1")
        }
    }
}
