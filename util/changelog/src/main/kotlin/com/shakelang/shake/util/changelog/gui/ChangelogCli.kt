package com.shakelang.shake.util.changelog.gui

import com.shakelang.shake.util.changelog.Version
import com.shakelang.shake.util.changelog.tasks.VersionTagsTask
import com.shakelang.shake.util.changelog.tasks.VersionTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JScrollPane

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
        this.layout = FlowLayout()

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
        val bumpPanel = BumpPanel(project, logger, changed, unchanged, onCanceled = { showHomePage() }, onBumped = { showHomePage() })
        val scrollPane = JScrollPane(bumpPanel);
        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollPane.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        scrollPane.setSize(contentPane.width, contentPane.height - 20)

        scrollPane.setLocation(0, 0)
        scrollPane.isVisible = true
        contentPane = scrollPane
    }

    private fun performRelease() {

        val snapshot = JOptionPane.showConfirmDialog(
            this,
            "You are about to release version. Do you want to release it as a snapshot?",
            "Release",
            JOptionPane.YES_NO_OPTION
        )

        val type = if (snapshot == JOptionPane.YES_OPTION) "SNAPSHOT" else ""

        val res = JOptionPane.showConfirmDialog(
            this,
            "You are about to release all bumped changes as a $type. Are you sure?",
            "Release",
            JOptionPane.YES_NO_OPTION
        )

        if (res != JOptionPane.YES_OPTION) return

        println("Releasing packages")

        val versionTask = project.tasks.getByName("version") as VersionTask
        versionTask.applyVersion(type)
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
        this.isVisible = false
        dispose()
    }
}
