package com.shakelang.shake.util.changelog

import org.gradle.api.Plugin
import org.gradle.api.Project

class Changelog : Plugin<Project> {
    override fun apply(project: Project) {
        if (project == project.rootProject) {
            project.tasks.create("initChangelog", InitChangelogTask::class.java)
        }
    }
}


open class InitChangelogTask : org.gradle.api.DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes the changelog plugin"
    }

    @org.gradle.api.tasks.TaskAction
    open fun initChangelog() {

        val changelog = project.file(".changelog")
        if (!changelog.exists()) {
            project.logger.info("Creating .changelog directory")
            changelog.mkdirs()
        }

        val structure = project.file(".changelog/structure.json")
        if (!structure.exists()) {
            project.logger.info("Creating .changelog/structure.json file")
            newStructure(project)
        }

        project.logger.info("Updating .changelog/structure.json file")
        updateStructure(project)
    }
}
