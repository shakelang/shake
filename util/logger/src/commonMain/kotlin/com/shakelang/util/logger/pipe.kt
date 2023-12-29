package com.shakelang.util.logger

import com.shakelang.util.colorlib.functional.blue
import com.shakelang.util.colorlib.functional.cyan
import com.shakelang.util.colorlib.functional.red
import com.shakelang.util.colorlib.functional.yellow

/**
 * A colored [LoggerPipe] implemented in common code (using println)
 * For coloring the output the [com.shakelang.util.colorlib] is used
 * (So we use ascii formatting commands)
 * @see LoggerPipe
 */
object CommonColoredConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message to log
     */
    override fun log(level: LogLevel, message: String) {
        when (level) {
            LogLevel.DEBUG -> println(message.cyan())
            LogLevel.INFO -> println(message.blue())
            LogLevel.SUCCESS -> println(message)
            LogLevel.WARN -> println(message.yellow())
            LogLevel.ERROR -> println(message.red())
            LogLevel.FATAL -> println(message.red())
        }
    }

}

/**
 * A [LoggerPipe] implemented in common code (using println)
 * @see LoggerPipe
 */
object CommonConsoleLoggerPipe : LoggerPipe() {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message to log
     */
    override fun log(level: LogLevel, message: String) = println(message)

}

/**
 * A [LoggerPipe] that transforms the message before putting it into the next pipe
 * @see LoggerPipe
 */
class TransformedPipe(val pipe: LoggerPipe, val transformers: List<LogTransformer>) : LoggerPipe() {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message to log
     */
    override fun log(level: LogLevel, message: String) {
        var msg = message
        transformers.forEach {
            msg = it.transform(level, msg)
        }
        pipe.log(level, msg)
    }

}


/**
 * A [LoggerPipe] is a pipe output for the [Logger]
 */
abstract class LoggerPipe {

    /**
     * Log a message to the console
     * @param level the level of the message
     * @param message the message to log
     */
    abstract fun log(level: LogLevel, message: String)

    /**
     * Log a debug message to the console
     * @param message the message to log
     */
    fun debug(message: String) = log(LogLevel.DEBUG, message)

    /**
     * Log an info message to the console
     * @param message the message to log
     */
    fun info(message: String) = log(LogLevel.INFO, message)

    /**
     * Log a success message to the console
     * @param message the message to log
     */
    fun success(message: String) = log(LogLevel.SUCCESS, message)

    /**
     * Log a warning message to the console
     * @param message the message to log
     */
    fun warn(message: String) = log(LogLevel.WARN, message)

    /**
     * Log an error message to the console
     * @param message the message to log
     */
    fun error(message: String) = log(LogLevel.ERROR, message)

    /**
     * Log a fatal message to the console
     * @param message the message to log
     */
    fun fatal(message: String) = log(LogLevel.FATAL, message)

}
