import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
}

group = projectGroup("util")
version = resolveVersion()
description = "Utility for getting environment information in KMP"
public = true

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}
