package com.shakelang.util.logger

/**
 * Pipe for colored logs into java console (System.out + System.err for errors)
 * @since 0.1.0
 * @version 0.1.0
 */
object JavaColoredConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message
     * @param level the level of the message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
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
     * Print an info message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printInfo(message: String) = println(message)

    /**
     * Print a debug message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printDebug(message: String) = println(message)

    /**
     * Print a success message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printSuccess(message: String) = println(message)

    /**
     * Print a warning message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printWarn(message: String) = println(message)

    /**
     * Print an error message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printError(message: String) = System.err.println(message)

    /**
     * Print a fatal message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printFatal(message: String) = System.err.println(message)

}

/**
 * Pipe for logs into java console (System.out + System.err for errors)
 * @since 0.1.0
 * @version 0.1.0
 */
object JavaConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message
     * @param level the level of the message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
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
     * Print an info message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printInfo(message: String) = println(message)

    /**
     * Print a debug message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printDebug(message: String) = println(message)

    /**
     * Print a success message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printSuccess(message: String) = println(message)

    /**
     * Print a warning message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printWarn(message: String) = println(message)

    /**
     * Print an error message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printError(message: String) = System.err.println(message)

    /**
     * Print a fatal message
     * @param message the message
     * @since 0.1.0
     * @version 0.1.0
     */
    private fun printFatal(message: String) = System.err.println(message)

}
