package io.github.shakelang.shake.util.logger

import io.github.shakelang.shake.util.colorlib.functional.blue
import io.github.shakelang.shake.util.colorlib.functional.cyan
import io.github.shakelang.shake.util.colorlib.functional.red
import io.github.shakelang.shake.util.colorlib.functional.yellow

object CommonColoredConsoleLogger : LoggerPipe() {
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

object CommonConsoleLogger : LoggerPipe() {
    override fun log(level: LogLevel, message: String) = println(message)
}

class Logger (
    val path: String,
    val name: String = path,
    var printName: Boolean = true,
    var printInfo: Boolean = true,
    var printSuccess: Boolean = true,
    var printDebug: Boolean = false,
    var printWarn: Boolean = true,
    var printError: Boolean = true,
    var printFatal: Boolean = true,
    val pipes: MutableList<LoggerPipe> = mutableListOf(),
) {

    // We'll mainly write to this list
    // For performance reasons we'll use a linked set
    // (better write performance than arraylists)
    private val entries = linkedSetOf<LogEntry>()
    val logs: List<LogEntry> get() = entries.toList()

    fun log(level: LogLevel, message: String) {
        this.entries.add(LogEntry(level, message))
        this.pipes.forEach {
            it.log(level, message)
        }
    }

    private fun transform(message: String) = if (printName) "[$name] $message" else message


    fun debug(message: String) = this.log(LogLevel.DEBUG, message)
    fun info(message: String) = this.log(LogLevel.INFO, message)
    fun success(message: String) = this.log(LogLevel.SUCCESS, message)
    fun warn(message: String) = this.log(LogLevel.WARN, message)
    fun error(message: String) = this.log(LogLevel.ERROR, message)
    fun fatal(message: String) = this.log(LogLevel.FATAL, message)

    fun pipe(pipe: LoggerPipe) {
        this.entries.forEach {
            pipe.log(it.level, it.message)
        }
        this.pipes.add(pipe)
    }
}

class LogEntry(val level: LogLevel, val message: String)

abstract class LoggerPipe {
    abstract fun log(level: LogLevel, message: String)
    fun debug(message: String) = log(LogLevel.DEBUG, message)
    fun info(message: String) = log(LogLevel.INFO, message)
    fun success(message: String) = log(LogLevel.SUCCESS, message)
    fun warn(message: String) = log(LogLevel.WARN, message)
    fun error(message: String) = log(LogLevel.ERROR, message)
    fun fatal(message: String) = log(LogLevel.FATAL, message)
}

enum class LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL,
    SUCCESS;
}

val logger = Logger("")
