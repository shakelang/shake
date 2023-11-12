package com.shakelang.shake.util.changelog

import org.gradle.api.Plugin
import org.gradle.api.Project

val changelogs = mutableMapOf<Project, ChangelogPlugin>()

val Project.changelog
    get() = changelogs[this] ?: throw IllegalStateException("Changelog not initialized")

abstract class ChangelogPlugin : Plugin<Project> {
    abstract val project: Project
    abstract val mainChangelog: Changelog
}

class Changelog : ChangelogPlugin() {

    override val mainChangelog: Changelog
        get() = this

    override lateinit var project: Project
        private set

    override fun apply(project: Project) {
        this.project = project
        project.tasks.create("initChangelog", InitChangelogTask::class.java)

        project.subprojects {
            plugins.apply(SubprojectChangelog::class.java)
        }
    }

    inner class SubprojectChangelog : ChangelogPlugin() {



        override lateinit var project: Project
            private set

        override val mainChangelog: Changelog get() = this@Changelog

        override fun apply(project: Project) {
            this.project = project
            project.tasks.create("version", SubprojectVersionTask::class.java)
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
            project.changelog.mainChangelog.newStructure()
        }

        project.logger.info("Updating .changelog/structure.json file")
        project.changelog.mainChangelog.updateStructure()
    }
}

open class SubprojectVersionTask : org.gradle.api.DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes a new version"
    }

    @org.gradle.api.tasks.TaskAction
    open fun version() {
        dependsOn(project.changelog.mainChangelog.project.path + ":initChangelog")

    }
}