package com.shakelang.shake.util.changelog.gui

import com.shakelang.shake.util.changelog.Version
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JScrollPane

data class PackageEntry (
    val name: String,
    val version: Version,
)

class ChangelogCli (
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
        val content = JScrollPane()

        contentPane = HomePage(
            onCanceled = { close() },
            onBump = { showBumpPage(changed, unchanged) },
            onRelease = { JOptionPane.showMessageDialog(this, "Released successfully") }
        )
    }

    private fun showBumpPage(changed: List<PackageEntry>, unchanged: List<PackageEntry>) {
        println("Showing bump page")
        contentPane = BumpPanel(changed, unchanged, onCanceled = { showHomePage() })
    }

    fun close() {
        this.closed = true
        this.isVisible = false
        dispose()
    }

}
