package com.shakelang.shake.util.changelog

import org.gradle.api.Plugin
import org.gradle.api.Project

    class Changelog : Plugin<Project> {
    override fun apply(project: Project) {
        if(project == project.rootProject) {
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
    }

}