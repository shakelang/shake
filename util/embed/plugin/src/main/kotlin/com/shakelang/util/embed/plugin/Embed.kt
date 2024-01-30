package com.shakelang.util.embed.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
open class Embed : Plugin<Project> {
    override fun apply(project: Project) {
        val fileBuilder = project.tasks.register("fileBuilder", FileBuilder::class.java)
    }
}
