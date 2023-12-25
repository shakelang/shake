package com.shakelang.shake.util.logger

import com.shakelang.util.colorlib.functional.blue
import com.shakelang.util.colorlib.functional.cyan
import com.shakelang.util.colorlib.functional.red
import com.shakelang.util.colorlib.functional.yellow

object CommonColoredConsoleLoggerPipe : LoggerPipe() {
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

object CommonConsoleLoggerPipe : LoggerPipe() {
    override fun log(level: LogLevel, message: String) = println(message)
}

class TransformedPipe(val pipe: LoggerPipe, val transformers: List<LogTransformer>) : LoggerPipe() {
    override fun log(level: LogLevel, message: String) {
        var msg = message
        transformers.forEach {
            msg = it.transform(level, msg)
        }
        pipe.log(level, msg)
    }
}

abstract class LoggerPipe {
    abstract fun log(level: LogLevel, message: String)
    fun debug(message: String) = log(LogLevel.DEBUG, message)
    fun info(message: String) = log(LogLevel.INFO, message)
    fun success(message: String) = log(LogLevel.SUCCESS, message)
    fun warn(message: String) = log(LogLevel.WARN, message)
    fun error(message: String) = log(LogLevel.ERROR, message)
    fun fatal(message: String) = log(LogLevel.FATAL, message)
}
