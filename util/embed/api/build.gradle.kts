import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
}

group = projectGroup("util.embed")
version = resolveVersion()
description = "Gradle plugin for embeding resources into kotlin source (api)"
public = true

val projectName = name

kotlin {
    dependencies {
        testImplementation(kotlin("test"))
    }
}
