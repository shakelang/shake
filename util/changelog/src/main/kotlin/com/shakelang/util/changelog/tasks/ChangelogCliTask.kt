package com.shakelang.util.changelog.tasks

import com.shakelang.util.changelog.Changelog
import com.shakelang.util.changelog.gui.ChangelogCli
import com.shakelang.util.changelog.gui.PackageEntry
import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.readMap
import com.shakelang.util.changelog.readStructureFile
import org.gradle.api.DefaultTask

open class ChangelogCliTask : DefaultTask() {

    init {
        group = "changelog"
        description = "Changelog CLI"
        this.dependsOn("initChangelog")
    }

    @org.gradle.api.tasks.TaskAction
    @Suppress("unused_variable")
    open fun changelogCli() {
        // Set gradle console to plain
        val structure = Changelog.instance.readStructureFile()

        val entries = structure.projects.filter {
            it.project.public
        }.map {
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
            if (changelog == null) {
                changedEntries.add(it.first)
                return@forEach
            }

            if (changelog.changedSinceLastRelease) {
                changedEntries.add(it.first)
            } else {
                unchangedEntries.add(it.first)
            }
        }

        val frame = ChangelogCli(
            project,
            logger,
            changedEntries,
            unchangedEntries
        )

        frame.requestFocus()

        println("Waiting for frame to close")

        while (!frame.closed) Thread.sleep(100)
    }
}
