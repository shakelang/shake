package io.github.shakelang.shake.util.logger

import io.github.shakelang.colorlib.functional.blue
import io.github.shakelang.colorlib.functional.cyan
import io.github.shakelang.colorlib.functional.red
import io.github.shakelang.colorlib.functional.yellow
import io.github.shakelang.environment.getRunningEnvironment
import kotlin.js.Console

inline fun Console.debug(vararg message: String): Unit = asDynamic().debug(message) as Unit

object JsColoredBrowserConsoleLogger {
    private fun printInfo(message: String) = console.info("%c$message", "color: cyan;")
    private fun printDebug(message: String) = console.debug("%c$message", "color: blue;")
    private fun printWarn(message: String) = console.warn("%c$message", "color: yellow;")
    private fun printError(message: String) = console.error("%c$message", "color: red;")
    private fun printFatal(message: String) = console.error("%c$message", "color: red;")

    val logger = Logger(
        "",
        infoOutput = ::printInfo,
        debugOutput = ::printDebug,
        warnOutput = ::printWarn,
        errorOutput = ::printError,
        fatalOutput = ::printFatal,
        printDebug = true
    )
}

object JsColoredConsoleLogger {
    private fun printInfo(message: String) = console.info(message.cyan().string())
    private fun printDebug(message: String) = console.debug(message.blue().string())
    private fun printWarn(message: String) = console.warn(message.yellow().string())
    private fun printError(message: String) = console.error(message.red().string())
    private fun printFatal(message: String) = console.error(message.red().string())

    val logger = Logger(
        "",
        infoOutput = ::printInfo,
        debugOutput = ::printDebug,
        warnOutput = ::printWarn,
        errorOutput = ::printError,
        fatalOutput = ::printFatal,
        printDebug = true
    )
}

object JsConsoleLogger {
    private fun printInfo(message: String) = console.info(message)
    private fun printDebug(message: String) = console.debug(message)
    private fun printWarn(message: String) = console.warn(message)
    private fun printError(message: String) = console.error(message)
    private fun printFatal(message: String) = console.error(message)

    val logger = Logger(
        "",
        infoOutput = ::printInfo,
        debugOutput = ::printDebug,
        warnOutput = ::printWarn,
        errorOutput = ::printError,
        fatalOutput = ::printFatal,
        printDebug = true
    )
}

actual val coloredLogger get() =
    if(getRunningEnvironment().isJavaScriptBrowser) JsColoredBrowserConsoleLogger.logger
    else if(getRunningEnvironment().isJavaScript) JsColoredConsoleLogger.logger
    else JsConsoleLogger.logger

actual val uncoloredLogger get() = JsConsoleLogger.logger

actual val logger get() = coloredLogger
