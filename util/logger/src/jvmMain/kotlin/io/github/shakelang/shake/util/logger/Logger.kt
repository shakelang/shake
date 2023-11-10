package io.github.shakelang.shake.util.logger

object JavaColoredConsoleLogger : LoggerPipe() {

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

    private fun printInfo(message: String) = println(message)
    private fun printDebug(message: String) = println(message)
    private fun printSuccess(message: String) = println(message)
    private fun printWarn(message: String) = println(message)
    private fun printError(message: String) = System.err.println(message)
    private fun printFatal(message: String) = System.err.println(message)
}

object JavaConsoleLogger : LoggerPipe() {

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

    private fun printInfo(message: String) = println(message)
    private fun printDebug(message: String) = println(message)
    private fun printSuccess(message: String) = println(message)
    private fun printWarn(message: String) = println(message)
    private fun printError(message: String) = System.err.println(message)
    private fun printFatal(message: String) = System.err.println(message)
}
