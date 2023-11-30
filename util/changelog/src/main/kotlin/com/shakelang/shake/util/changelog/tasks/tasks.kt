package com.shakelang.shake.util.changelog.tasks

import com.shakelang.shake.util.changelog.*
import com.shakelang.shake.util.changelog.gui.ChangelogCli
import com.shakelang.shake.util.changelog.gui.PackageEntry
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import kotlin.math.max

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

        if (typeValue != "major" && typeValue != "minor" && typeValue != "patch") {
            logger.error("Please provide a type with -Ptype=\"major|minor|patch\"")
            throw Exception("Please provide a type with -Ptype=\"major|minor|patch\"")
        }

        val type = BumpType.fromString(typeValue)

        if (messageValue == null) {
            logger.error("Please provide a message with -Pmessage=\"Your message\"")
            throw Exception("Please provide a message with -Pmessage=\"Your message\"")
        }

        val message = messageValue

        if (projectArray.isNullOrEmpty()) {
            logger.error("Please provide a project with -Ppackages=\"project1,project2\"")
            throw Exception("Please provide a project with -Ppackages=\"project1,project2\"")
        }

        val allProjects = Changelog.instance.readStructureFile().projects

        // get projects
        val projects = projectArray.map { project ->
            allProjects.find { it.path == project }
                ?: throw Exception("Project $project not found")
        }

        println("Bumping version with type $typeValue and message $messageValue")
        println("Projects: ${projects.joinToString(", ") { it.path }}")

        val bump = Bump(type, message, projects.map { it.path })
        val bumpFile = Changelog.instance.readBumpFile()
        bumpFile.add(bump)
        Changelog.instance.writeBumpFile(bumpFile)
    }
}

open class VersionTask : DefaultTask() {
    init {
        group = "changelog"
        description = "Prints the current version"
        this.dependsOn("initChangelog")
    }

    private var tagFormat: TagCreation = {
        "${it.project.name}@${it.version}"
    }

    fun tagFormat(tagFormat: TagCreation) {
        this.tagFormat = tagFormat
    }

    @org.gradle.api.tasks.TaskAction
    open fun version() {
        val structureFile = Changelog.instance.readStructureFile()
        val bumpFile = Changelog.instance.readBumpFile()
        val mapFile = Changelog.instance.readMap()

        val changelog = structureFile.projects.associate { it.path to mutableListOf<String>() }
        val bumpTypes = mutableMapOf<String, BumpType>()

        println("Updating dependencies...")

        val packagesWithUpdatedDependencies = mutableSetOf<Project>()

        bumpFile.bumps.forEach { bump ->
            bump.paths.forEach { path ->
                project.project(path).allDependents.forEach {
                    if(it !in packagesWithUpdatedDependencies) {
                        packagesWithUpdatedDependencies.add(it)
                    }
                }
            }
        }

        println("Noticed changed dependencies for the following packages (bumping patch):")

        packagesWithUpdatedDependencies.forEach {
            println(" - ${it.path}")
        }

        val dependenciesUpdated = Bump(
            BumpType.PATCH,
            "Updated dependencies",
            packagesWithUpdatedDependencies.map { it.path }
        )

        bumpFile.add(dependenciesUpdated)

        bumpFile.bumps.forEach { bump ->
            bump.paths.forEach { path ->
                changelog[path]?.add(bump.message)
                bumpTypes[path] = BumpType.fromInt(max(bumpTypes[path]?.toInt() ?: -1, bump.type.toInt()))
            }
        }

        bumpFile.bumps.clear()
        val tagFormat = this.tagFormat

        val stash = Changelog.instance.readStash()

        changelog.entries.forEach { (path, messages) ->
            val bumpType = bumpTypes[path]

            if (messages.isEmpty() || bumpType == null) return@forEach

            // Update version
            if(project.project(path).private) {
                project.logger.warn("Skipping version bump for private project $path")
                return@forEach
            }

            val prj = structureFile.projects.find { it.path == path }!!
            val version = prj.version
            when (bumpType) {
                BumpType.MAJOR -> version.incrementMajor()
                BumpType.MINOR -> version.incrementMinor()
                BumpType.PATCH -> version.incrementPatch()
            }

            val tagName = tagFormat(TagCreationInfo(prj, version, messages.joinToString("\n")))

            // stash tag
            stash.add(TagStash(tagName))

            // Add map entry
            var it = mapFile.packages.find { it.path == path }
            if (it == null) {
                it = PackageChangelog(
                    path,
                    path,
                    description ?: ""
                )
                mapFile.add(it)
            }

            it.add(ChangelogVersion(version, messages))
        }

        // save files
        Changelog.instance.writeBumpFile(bumpFile)
        Changelog.instance.writeMap(mapFile)
        Changelog.instance.writeStructure(structureFile)
        Changelog.instance.writeStash(stash)

        // render new changelog files
        Changelog.instance.renderChangelog(mapFile)
    }
}

open class VersionTags : DefaultTask() {

    @Input
    val push: Property<Boolean> = project.objects.property(Boolean::class.java)

    @Input
    val origin: Property<String> = project.objects.property(String::class.java)

    init {
        group = "changelog"
        description = "Creates git tags for stashed versions"
        this.dependsOn("initChangelog")

        push.convention(true)
        origin.convention("origin")
    }

    @org.gradle.api.tasks.TaskAction
    open fun versionTags() {
        val stash = Changelog.instance.readStash()

        val rt = Runtime.getRuntime()

        stash.tags.forEach {
            println("Creating tag ${it.name}...")

            val tagCreation = rt.exec(arrayOf("git", "tag", it.name, "-m", "Release ${it.name}"))

            val exitCode = tagCreation.waitFor()
            if (exitCode != 0) {
                println("Failed to create tag ${it.name}")
                while (tagCreation.errorStream.available() > 0)
                    System.err.print(tagCreation.errorStream.read().toChar())

                return@forEach
            }

            if (push.get()) {
                println("Pushing tag ${it.name}...")
                val pushTag = rt.exec(arrayOf("git", "push", origin.get(), tagRef(it.name)))
                val exitCode2 = pushTag.waitFor()

                if (exitCode2 != 0) {
                    println("Failed to push tag ${it.name}")
                    return@forEach
                }
            }
        }

        stash.clear()
        Changelog.instance.writeStash(stash)
    }
}

open class ChangelogCliTask : DefaultTask() {

    init {
        group = "changelog"
        description = "Changelog CLI"
        this.dependsOn("initChangelog")
    }

    @org.gradle.api.tasks.TaskAction
    open fun changelogCli() {
        // Set gradle console to plain
        val structure = Changelog.instance.readStructureFile()

        val entries = structure.projects.filter {
            it.project.public
        } . map {
            PackageEntry(it.path, it.version)
        }

        val changelogs = Changelog.instance.readMap()

        val zipped = entries.map {
            it to changelogs.packages.find { pkg ->
                pkg.path == it.name
            }
        }

        val changedEntries = mutableListOf<PackageEntry>()
        val unchangedEntries = mutableListOf<PackageEntry>()

        zipped.forEach {
            val (entry, changelog) = it
            if(changelog == null) {
                changedEntries.add(it.first)
                return@forEach
            }

            if(changelog.changedSinceLastRelease) {
                changedEntries.add(it.first)
            } else {
                unchangedEntries.add(it.first)
            }
        }

        val frame = ChangelogCli(
            changedEntries,
            unchangedEntries
        )

        frame.requestFocus()

        println("Waiting for frame to close")

        while(!frame.closed) Thread.sleep(100)


    }

}