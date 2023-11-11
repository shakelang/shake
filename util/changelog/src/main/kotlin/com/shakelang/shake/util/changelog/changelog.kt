package com.shakelang.shake.util.changelog

import com.shakelang.shake.util.shason.json
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Date

class Changelog : Plugin<Project> {
    override fun apply(project: Project) {
        if (project == project.rootProject) {
            project.tasks.create("initChangelog", InitChangelogTask::class.java) {
                it.group = "changelog"
                it.description = "Initializes the changelog"
            }
        }
    }
}


class InitChangelogTask : org.gradle.api.DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes the changelog"
    }

    @org.gradle.api.tasks.TaskAction
    fun initChangelog() {

        val changelog = project.file(".changelog")
        if (!changelog.exists()) {
            changelog.mkdirs()
        }

        val structure = project.file(".changelog/structure.json")
        if (!structure.exists()) {
            newChangelog(project)
        }
    }
}
