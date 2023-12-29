package com.shakelang.util.logger

import kotlinx.browser.document
import kotlinx.html.dom.create
import kotlinx.html.js.span
import kotlinx.html.style

/**
 * Html console
 * (A HTML console to print messages to)
 * @since 0.1.0
 * @version 0.1.0
 */
class HtmlConsole {

    /**
     * The div container of the console
     * @since 0.1.0
     * @version 0.1.0
     */
    val consoleElement = document.createElement("div")

    /**
     * Print a message to the console
     * @since 0.1.0
     * @version 0.1.0
     */
    fun print(message: String, color: String = "black") {
        val span = document.create.span {
            style = "color: $color"
            text(message)
        }
        consoleElement.appendChild(span)
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun println(message: String, color: String = "black") {
        print("$message\n", color)
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printError(message: String) {
        println(message, "red")
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printWarning(message: String) {
        println(message, "yellow")
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printInfo(message: String) {
        println(message, "blue")
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printDebug(message: String) {
        println(message, "gray")
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printTrace(message: String) {
        println(message, "gray")
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printFatal(message: String) {
        println(message, "red")
    }

    /**
     * Print a message to the console and add a new line
     * @since 0.1.0
     * @version 0.1.0
     */
    fun printSuccess(message: String) {
        println(message, "green")
    }
}
