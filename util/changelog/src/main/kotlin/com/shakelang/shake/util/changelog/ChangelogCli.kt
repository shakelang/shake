package com.shakelang.shake.util.changelog

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun main() {
    val options = Changelog.instance.readStructureFile().projects.map { it.path }.toTypedArray()

    val checkboxList = CheckboxList(options)

    while (true) {
        checkboxList.draw()
        val keyStroke = readKeyStroke()

        if (keyStroke.keyType == KeyType.Enter) {
            println("Selected options: ${checkboxList.getSelectedOptions().joinToString(", ")}")
            break
        }

        checkboxList.handleInput(keyStroke)
    }
}

class CheckboxList(private val options: Array<String>) {
    private var selectedIndices = mutableSetOf<Int>()
    private var currentIndex = 0

    fun handleInput(keyStroke: KeyStroke) {
        when (keyStroke.keyType) {
            KeyType.ArrowUp -> moveUp()
            KeyType.ArrowDown -> moveDown()
            KeyType.Character -> toggleCheckbox()
            else -> {
            }
        }
    }

    private fun moveUp() {
        currentIndex = (currentIndex - 1 + options.size) % options.size
    }

    private fun moveDown() {
        currentIndex = (currentIndex + 1) % options.size
    }

    private fun toggleCheckbox() {
        if (currentIndex in selectedIndices) {
            selectedIndices.remove(currentIndex)
        } else {
            selectedIndices.add(currentIndex)
        }
    }

    fun draw() {
        options.forEachIndexed { index, option ->
            val checkbox = if (index in selectedIndices) "[*]" else "[ ]"
            val displayText = "$checkbox $option"
            println(displayText)
        }
    }

    fun getSelectedOptions(): List<String> {
        return selectedIndices.map { options[it] }
    }
}

fun readKeyStroke(): KeyStroke {
    val console = System.console()

    if (console != null) {
        // Reading a single character without echoing it to the console
        val input = console.reader().read()
        return KeyStroke.fromString(input.toString())
    }

    val reader = BufferedReader(InputStreamReader(System.`in`))

    try {
        val input = reader.read()
        return KeyStroke.fromString(input.toString())
    } catch (e: IOException) {
        throw RuntimeException("Error reading input", e)
    }
}
