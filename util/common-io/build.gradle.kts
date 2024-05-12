import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
}

group = projectGroup("util")
version = resolveVersion()
description = "Kotlin common io utilities (Streams, etc.)"
public = true

val projectName = name

kotlin {
    dependencies {
        implementation(project(":util:primitives"))
        testImplementation(kotlin("test"))
    }
}
