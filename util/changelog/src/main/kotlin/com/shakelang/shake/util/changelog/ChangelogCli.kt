package com.shakelang.shake.util.changelog

import javax.swing.JCheckBox
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

data class PackageEntry (
    val name: String,
    val version: Version,
)

class ChangelogCli (
    changed: List<PackageEntry>,
    unchanged: List<PackageEntry>,
) : JFrame("Changelog") {

    init {
        setSize(500, 500)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        add(BumpPanel(changed, unchanged))
        isVisible = true
    }

}

class BumpPanel (
    val changed: List<PackageEntry>,
    val unchanged: List<PackageEntry>,
) : JPanel() {
    val changedBoxes: Array<JCheckBox>
    val unchangedBoxes: Array<JCheckBox>
    val changedLabel: JLabel
    val unchangedLabel: JLabel

    init {

        var top = 0

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
            box.setSize(100, 20)
            box.setLocation(0, top)
            top += 20
            add(box)
            box
        }

        setSize(500, top)
        layout = null
    }
}