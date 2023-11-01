package io.github.shakelang.shake.util.logger

object JavaColoredConsoleLogger {
    private fun printInfo(message: String) = println(message)
    private fun printDebug(message: String) = println(message)
    private fun printWarn(message: String) = println(message)
    private fun printError(message: String) = System.err.println(message)
    private fun printFatal(message: String) = System.err.println(message)

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

object JavaConsoleLogger {
    private fun printInfo(message: String) = println(message)
    private fun printDebug(message: String) = println(message)
    private fun printWarn(message: String) = println(message)
    private fun printError(message: String) = System.err.println(message)
    private fun printFatal(message: String) = System.err.println(message)

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

actual val coloredLogger get() = JavaColoredConsoleLogger.logger
actual val uncoloredLogger get() = JavaConsoleLogger.logger

actual val logger get() = coloredLogger
