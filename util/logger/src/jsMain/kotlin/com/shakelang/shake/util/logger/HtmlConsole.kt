package com.shakelang.shake.util.logger

import kotlinx.browser.document
import kotlinx.html.dom.create
import kotlinx.html.js.span
import kotlinx.html.style

class HtmlConsole {

    val consoleElement = document.createElement("div")

    fun print(message: String, color: String = "black") {
        val span = document.create.span {
            style = "color: $color"
            text(message)
        }
        consoleElement.appendChild(span)
    }

    fun println(message: String, color: String = "black") {
        print("$message\n", color)
    }

    fun printError(message: String) {
        println(message, "red")
    }

    fun printWarning(message: String) {
        println(message, "yellow")
    }

    fun printInfo(message: String) {
        println(message, "blue")
    }

    fun printDebug(message: String) {
        println(message, "gray")
    }

    fun printTrace(message: String) {
        println(message, "gray")
    }

    fun printFatal(message: String) {
        println(message, "red")
    }

    fun printSuccess(message: String) {
        println(message, "green")
    }
}
