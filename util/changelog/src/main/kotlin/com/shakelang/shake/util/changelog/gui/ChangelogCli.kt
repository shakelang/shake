package com.shakelang.shake.util.changelog.gui

import com.shakelang.shake.util.changelog.Version
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import javax.swing.JFrame
import javax.swing.JOptionPane

data class PackageEntry(
    val name: String,
    val version: Version,
)

@Suppress("unused")
class ChangelogCli(
    private val project: Project,
    private val logger: Logger,
    private val changed: List<PackageEntry>,
    private val unchanged: List<PackageEntry>,
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
                JOptionPane.showMessageDialog(this, "Released successfully")
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
        JOptionPane.showConfirmDialog(
            this,
            "You are about to release the following packages:\n\n${changed.joinToString("\n") { it.name }}\n\nAre you sure?",
            "Release",
            JOptionPane.YES_NO_OPTION
        )
    }

    fun close() {
        this.closed = true
        this.isVisible = false
        dispose()
    }

}
