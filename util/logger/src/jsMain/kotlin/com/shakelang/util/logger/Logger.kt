package com.shakelang.util.logger

import com.shakelang.util.colorlib.functional.blue
import com.shakelang.util.colorlib.functional.cyan
import com.shakelang.util.colorlib.functional.red
import com.shakelang.util.colorlib.functional.yellow
import kotlin.js.Console

/**
 * Declaration of the `console.debug` function of the browser console
 * @since 0.1.0
 * @version 0.1.0
 */
inline fun Console.debug(vararg message: String): Unit = asDynamic().debug(message) as Unit

/**
 * Pipe for colored logs to the browser console (using css styles)
 * @since 0.1.0
 * @version 0.1.0
 */
object JsColoredBrowserConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message
     */
    override fun log(level: LogLevel, message: String) {
        when (level) {
            LogLevel.INFO -> printInfo(message)
            LogLevel.DEBUG -> printDebug(message)
            LogLevel.SUCCESS -> printSuccess(message)
            LogLevel.WARN -> printWarn(message)
            LogLevel.ERROR -> printError(message)
            LogLevel.FATAL -> printFatal(message)
        }
    }

    /**
     * Print an info message to the console
     * @param message the message
     */
    private fun printInfo(message: String) = console.info("%c$message", "color: cyan;")

    /**
     * Print a debug message to the console
     * @param message the message
     */
    private fun printDebug(message: String) = console.debug("%c$message", "color: blue;")

    /**
     * Print a success message to the console
     * @param message the message
     */
    private fun printSuccess(message: String) = console.debug("%c$message", "color: green;")

    /**
     * Print a warning message to the console
     * @param message the message
     */
    private fun printWarn(message: String) = console.warn("%c$message", "color: yellow;")

    /**
     * Print an error message to the console
     * @param message the message
     */
    private fun printError(message: String) = console.error("%c$message", "color: red;")

    /**
     * Print a fatal message to the console
     * @param message the message
     */
    private fun printFatal(message: String) = console.error("%c$message", "color: red;")
}

/**
 * Pipe for colored logs to the terminal (using ANSI escape codes)
 * @since 0.1.0
 * @version 0.1.0
 */
object JsColoredConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message
     */
    override fun log(level: LogLevel, message: String) {
        when (level) {
            LogLevel.INFO -> printInfo(message)
            LogLevel.DEBUG -> printDebug(message)
            LogLevel.SUCCESS -> printSuccess(message)
            LogLevel.WARN -> printWarn(message)
            LogLevel.ERROR -> printError(message)
            LogLevel.FATAL -> printFatal(message)
        }
    }

    /**
     * Print an info message to the console
     * @param message the message
     */
    private fun printInfo(message: String) = console.info(message.cyan().string())

    /**
     * Print a debug message to the console
     * @param message the message
     */
    private fun printDebug(message: String) = console.debug(message.blue().string())

    /**
     * Print a success message to the console
     * @param message the message
     */
    private fun printSuccess(message: String) = console.debug(message.blue().string())

    /**
     * Print a warning message to the console
     * @param message the message
     */
    private fun printWarn(message: String) = console.warn(message.yellow().string())

    /**
     * Print an error message to the console
     * @param message the message
     */
    private fun printError(message: String) = console.error(message.red().string())

    /**
     * Print a fatal message to the console
     * @param message the message
     */
    private fun printFatal(message: String) = console.error(message.red().string())

}

/**
 * Pipe for logs to the terminal / browser console
 * @since 0.1.0
 * @version 0.1.0
 */
object JsConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message
     */
    override fun log(level: LogLevel, message: String) {
        when (level) {
            LogLevel.INFO -> printInfo(message)
            LogLevel.DEBUG -> printDebug(message)
            LogLevel.SUCCESS -> printSuccess(message)
            LogLevel.WARN -> printWarn(message)
            LogLevel.ERROR -> printError(message)
            LogLevel.FATAL -> printFatal(message)
        }
    }

    /**
     * Print an info message to the console
     * @param message the message
     */
    private fun printInfo(message: String) = console.info(message)

    /**
     * Print a debug message to the console
     * @param message the message
     */
    private fun printDebug(message: String) = console.debug(message)

    /**
     * Print a success message to the console
     * @param message the message
     */
    private fun printSuccess(message: String) = console.debug(message)

    /**
     * Print a warning message to the console
     * @param message the message
     */
    private fun printWarn(message: String) = console.warn(message)

    /**
     * Print an error message to the console
     * @param message the message
     */
    private fun printError(message: String) = console.error(message)

    /**
     * Print a fatal message to the console
     * @param message the message
     */
    private fun printFatal(message: String) = console.error(message)

}
