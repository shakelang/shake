package com.shakelang.shake.util.changelog

import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JScrollPane
import javax.swing.JTextField

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

class HomePage(
    onCanceled: () -> Unit,
    onBump: () -> Unit,
    onRelease: () -> Unit,
) : JPanel() {

    private val label: JLabel = JLabel("What do you want to do?")
    private val bumpButton: JButton
    private val releaseButton: JButton
    private val cancelButton: JButton

    init {
        label.setSize(480, 40)
        label.setLocation(10, 10)
        label.font = label.font.deriveFont(20f)
        add(label)

        bumpButton = JButton("Bump")
        bumpButton.setSize(480, 30)
        bumpButton.setLocation(10, 60)
        bumpButton.addActionListener { onBump() }
        add(bumpButton)

        releaseButton = JButton("Release")
        releaseButton.setSize(480, 30)
        releaseButton.setLocation(10, 100)
        releaseButton.addActionListener { onRelease() }
        add(releaseButton)

        cancelButton = JButton("Cancel")
        cancelButton.setSize(480, 30)
        cancelButton.setLocation(10, 140)
        cancelButton.addActionListener { onCanceled() }
        add(cancelButton)

        setSize(500, 500)
        layout = null
    }

}

class BumpPanel (
    private val changed: List<PackageEntry>,
    private val unchanged: List<PackageEntry>,
    private val onCanceled: () -> Unit = {},
) : JPanel() {
    private val changedBoxes: Array<JCheckBox>
    private val unchangedBoxes: Array<JCheckBox>
    private val changedLabel: JLabel
    private val unchangedLabel: JLabel
    private val bumpButton: JButton
    private val cancelButton: JButton
    private val messageField: JTextField
    private val majorButton: JRadioButton
    private val minorButton: JRadioButton
    private val patchButton: JRadioButton
    private val versionGroup: ButtonGroup

    init {

        layout = null

        messageField = JTextField()
        messageField.setSize(480, 20)
        messageField.setLocation(10, 10)
        add(messageField)

        versionGroup = ButtonGroup()

        majorButton = JRadioButton("Major")
        majorButton.setSize(100, 20)
        majorButton.setLocation(10, 40)
        majorButton.isSelected = true
        add(majorButton)
        versionGroup.add(majorButton)

        minorButton = JRadioButton("Minor")
        minorButton.setSize(100, 20)
        minorButton.setLocation(120, 40)
        add(minorButton)
        versionGroup.add(minorButton)

        patchButton = JRadioButton("Patch")
        patchButton.setSize(100, 20)
        patchButton.setLocation(230, 40)
        add(patchButton)
        versionGroup.add(patchButton)

        var top = 100

        changedLabel = JLabel("Changed:")
        changedLabel.setSize(100, 20)
        changedLabel.setLocation(0, top)
        add(changedLabel)
        top += 20

        changedBoxes = Array(changed.size) {
            val box = JCheckBox(changed[it].name)
            box.setSize(480, 20)
            box.setLocation(10, top)
            top += 20
            add(box)
            box
        }

        unchangedLabel = JLabel("Unchanged:")
        unchangedLabel.setSize(480, 20)
        unchangedLabel.setLocation(10, top)
        add(unchangedLabel)
        top += 20


        unchangedBoxes = Array(unchanged.size) {
            val box = JCheckBox(unchanged[it].name)
            box.setSize(480, 20)
            box.setLocation(10, top)
            top += 20
            add(box)
            box
        }

        top += 20

        bumpButton = JButton("Bump")
        bumpButton.setSize(100, 20)
        bumpButton.setLocation(10, top)
        bumpButton.isVisible = true
        add(bumpButton)

        bumpButton.addActionListener {
           val message = messageField.text
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a message")
                    return@addActionListener
                }
                val bumpType = when {
                    majorButton.isSelected -> BumpType.MAJOR
                    minorButton.isSelected -> BumpType.MINOR
                    else -> BumpType.PATCH
                }
                val changed = changedBoxes.filter { it.isSelected }.map { it.text }
                val unchanged = unchangedBoxes.filter { it.isSelected }.map { it.text }

                println("BumpType: $bumpType")
                println("Message: $message")
                println("Changed: $changed")
                println("Unchanged: $unchanged")

                JOptionPane.showMessageDialog(this, "Bumped successfully")
                onCanceled()
        }

        cancelButton = JButton("Cancel")
        cancelButton.setSize(100, 20)
        cancelButton.setLocation(120, top)
        cancelButton.isVisible = true
        add(cancelButton)
        cancelButton.addActionListener { onCanceled() }
        setSize(500, top + 100)
    }
}