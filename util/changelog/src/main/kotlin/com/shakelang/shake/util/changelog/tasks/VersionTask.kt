package com.shakelang.shake.util.changelog.tasks

import com.shakelang.shake.util.changelog.*
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import kotlin.math.max

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

        var typeValue: String = project.findProperty("type")?.toString() ?: "SNAPSHOT"
        if (typeValue == "RELEASE") typeValue = ""

        applyVersion(typeValue)

    }

    open fun applyVersion(type: String) {

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
                    if (it !in packagesWithUpdatedDependencies) {
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
            if (project.project(path).private) {
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

            if(type != "") version.suffix = type

            println("Bumping $path to $version")

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
