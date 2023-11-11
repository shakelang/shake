package com.shakelang.shake.util.changelog

import org.gradle.api.Plugin
import org.gradle.api.Project

class Changelog : Plugin<Project> {
    override fun apply(project: Project) {
        // Your plugin logic here
        println("Hello from your Gradle plugin!")
    }
}
