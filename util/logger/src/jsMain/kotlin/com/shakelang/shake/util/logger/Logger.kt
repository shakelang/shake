package com.shakelang.shake.util.logger

import com.shakelang.shake.util.colorlib.functional.blue
import com.shakelang.shake.util.colorlib.functional.cyan
import com.shakelang.shake.util.colorlib.functional.red
import com.shakelang.shake.util.colorlib.functional.yellow
import kotlin.js.Console

inline fun Console.debug(vararg message: String): Unit = asDynamic().debug(message) as Unit

object JsColoredBrowserConsoleLoggerPipe : LoggerPipe() {

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

    private fun printInfo(message: String) = console.info("%c$message", "color: cyan;")
    private fun printDebug(message: String) = console.debug("%c$message", "color: blue;")
    private fun printSuccess(message: String) = console.debug("%c$message", "color: green;")
    private fun printWarn(message: String) = console.warn("%c$message", "color: yellow;")
    private fun printError(message: String) = console.error("%c$message", "color: red;")
    private fun printFatal(message: String) = console.error("%c$message", "color: red;")
}

object JsColoredConsoleLoggerPipe : LoggerPipe() {

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

    private fun printInfo(message: String) = console.info(message.cyan().string())
    private fun printDebug(message: String) = console.debug(message.blue().string())
    private fun printSuccess(message: String) = console.debug(message.blue().string())
    private fun printWarn(message: String) = console.warn(message.yellow().string())
    private fun printError(message: String) = console.error(message.red().string())
    private fun printFatal(message: String) = console.error(message.red().string())
}

object JsConsoleLoggerPipe : LoggerPipe() {

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

    private fun printInfo(message: String) = console.info(message)
    private fun printDebug(message: String) = console.debug(message)
    private fun printSuccess(message: String) = console.debug(message)
    private fun printWarn(message: String) = console.warn(message)
    private fun printError(message: String) = console.error(message)
    private fun printFatal(message: String) = console.error(message)
}
