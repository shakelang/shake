package com.shakelang.shake.util.changelog.tasks

import com.shakelang.shake.util.changelog.Changelog
import com.shakelang.shake.util.changelog.newStructure
import com.shakelang.shake.util.changelog.updateStructure
import org.gradle.api.DefaultTask

open class InitChangelogTask : DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes the changelog plugin"
        this.dependsOn("resolveProjectDependencies", "resolveProjectDependents")
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
            Changelog.instance.newStructure()
        }

        project.logger.info("Updating .changelog/structure.json file")
        Changelog.instance.updateStructure()
    }
}