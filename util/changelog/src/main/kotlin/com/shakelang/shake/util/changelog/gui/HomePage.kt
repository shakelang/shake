package com.shakelang.shake.util.changelog.gui

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

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