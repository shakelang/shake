package com.shakelang.util.changelog.tasks

import com.shakelang.shake.util.changelog.*
import com.shakelang.util.changelog.*
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger

@Suppress("unused")
open class BumpTask : DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes a new version"
        this.dependsOn("initChangelog")
    }

    @org.gradle.api.tasks.TaskAction
    open fun bump() {
        val messageValue: String? = project.findProperty("message")?.toString()
        val projectArray = (project.findProperty("packages")?.toString())?.split(",")
        val typeValue: String? = project.findProperty("type")?.toString()
        performBump(project, logger, typeValue, messageValue, projectArray)
    }

    fun performBump(
        project: Project,
        logger: Logger,
        typeValue: String?,
        messageValue: String?,
        projectArray: List<String>?
    ) {
        if (typeValue != "major" && typeValue != "minor" && typeValue != "patch") {
            logger.error("Please provide a type with -Ptype=\"major|minor|patch\"")
            throw Exception("Please provide a type with -Ptype=\"major|minor|patch\"")
        }

        val type = BumpType.fromString(typeValue)

        if (messageValue == null) {
            logger.error("Please provide a message with -Pmessage=\"Your message\"")
            throw Exception("Please provide a message with -Pmessage=\"Your message\"")
        }

        if (projectArray.isNullOrEmpty()) {
            logger.error("Please provide a project with -Ppackages=\"project1,project2\"")
            throw Exception("Please provide a project with -Ppackages=\"project1,project2\"")
        }

        return performBump(type, messageValue, projectArray)
    }

    fun performBump(
        type: BumpType,
        message: String,
        projectArray: List<String>
    ) {
        val allProjects = Changelog.instance.readStructureFile().projects

        // get projects
        val projects = projectArray.map { prj ->
            allProjects.find { it.path == prj }
                ?: throw Exception("Project $prj not found")
        }

        println("Bumping version with type $type and message $message")
        println("Projects: ${projects.joinToString(", ") { it.path }}")

        val bump = Bump(type, message, projects.map { it.path })
        val bumpFile = Changelog.instance.readBumpFile()
        bumpFile.add(bump)
        Changelog.instance.writeBumpFile(bumpFile)
    }
}
