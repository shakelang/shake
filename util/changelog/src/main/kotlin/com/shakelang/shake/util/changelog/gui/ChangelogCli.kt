package com.shakelang.shake.util.changelog.gui

import com.shakelang.shake.util.changelog.Version
import com.shakelang.shake.util.changelog.tasks.VersionTagsTask
import com.shakelang.shake.util.changelog.tasks.VersionTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import javax.swing.JFrame
import javax.swing.JOptionPane

data class PackageEntry(
    val name: String,
    val version: Version
)

@Suppress("unused")
class ChangelogCli(
    private val project: Project,
    private val logger: Logger,
    private val changed: List<PackageEntry>,
    private val unchanged: List<PackageEntry>
) : JFrame("Changelog") {

    var closed: Boolean = false
        private set

    init {
        setSize(500, 500)
        setLocationRelativeTo(null)
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        isVisible = true
        addWindowListener(object : java.awt.event.WindowAdapter() {
            override fun windowClosing(e: java.awt.event.WindowEvent?) = this@ChangelogCli.close()
        })
        this.showHomePage()
    }

    private fun showHomePage() {
        println("Showing home page")

        contentPane = HomePage(
            project,
            logger,
            onCanceled = { close() },
            onBump = { showBumpPage(changed, unchanged) },
            onRelease = {
                performRelease()
            },
            onCreateTags = {
                performCreateTags()
            }
        )
    }

    private fun showBumpPage(changed: List<PackageEntry>, unchanged: List<PackageEntry>) {
        println("Showing bump page")
        contentPane = BumpPanel(
            project,
            logger,
            changed,
            unchanged,
            onCanceled = { showHomePage() },
            onBumped = { showHomePage() }
        )
    }

    private fun performRelease() {
        val res = JOptionPane.showConfirmDialog(
            this,
            "You are about to release all bumped changes. Are you sure?",
            "Release",
            JOptionPane.YES_NO_OPTION
        )

        if (res != JOptionPane.YES_OPTION) return

        println("Releasing packages")

        val versionTask = project.tasks.getByName("version") as VersionTask
        versionTask.version()
    }

    private fun performCreateTags() {
        val res = JOptionPane.showConfirmDialog(
            this,
            "You are about to create tags for all newly released packages. Are you sure?",
            "Create Tags",
            JOptionPane.YES_NO_OPTION
        )

        if (res != JOptionPane.YES_OPTION) return

        println("Creating tags")

        val versionTask = project.tasks.getByName("createTags") as VersionTagsTask
        versionTask.versionTags()
    }

    fun close() {
        this.closed = true
        this.isVisible = false
        dispose()
    }
}
