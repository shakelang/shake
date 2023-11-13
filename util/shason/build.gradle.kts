import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

group = projectGroup("util")
version = resolveVersion()
description = "A json parser implemented in kotlin (mpp)"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        testImplementation(kotlin("test"))
    }
}
