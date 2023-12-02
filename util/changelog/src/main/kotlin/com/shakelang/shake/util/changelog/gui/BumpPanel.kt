package com.shakelang.shake.util.changelog.gui

import com.shakelang.shake.util.changelog.BumpType
import com.shakelang.shake.util.changelog.tasks.BumpTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import javax.swing.*

@Suppress("unused")
class BumpPanel(
    private val project: Project,
    private val logger: Logger,
    private val changed: List<PackageEntry>,
    private val unchanged: List<PackageEntry>,
    private val onCanceled: () -> Unit,
    private val onBumped: () -> Unit
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

            val combined = changed + unchanged

            println("BumpType: $bumpType")
            println("Message: $message")
            println("Bumped: $changed")

            val bumpTask = project.tasks.getByName("bump") as BumpTask


            bumpTask.performBump(
                bumpType,
                message,
                combined
            )

            JOptionPane.showMessageDialog(this, "Bumped successfully")
            onBumped()
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
