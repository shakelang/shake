package io.github.shakelang.shake.util.logger

import io.github.shakelang.colorlib.functional.blue
import io.github.shakelang.colorlib.functional.cyan
import io.github.shakelang.colorlib.functional.red
import io.github.shakelang.colorlib.functional.yellow

object CommonColoredConsoleLogger {
    private fun printInfo(message: String) = println(message.cyan().string())
    private fun printDebug(message: String) = println(message.blue().string())
    private fun printWarn(message: String) = println(message.yellow().string())
    private fun printError(message: String) = println(message.red().string())
    private fun printFatal(message: String) = println(message.red().string())

    val logger = Logger(
        "",
        infoOutput = ::printInfo,
        debugOutput = ::printDebug,
        warnOutput = ::printWarn,
        errorOutput = ::printError,
        fatalOutput = ::printFatal
    )
}

object CommonConsoleLogger {
    private fun printInfo(message: String) = println(message)
    private fun printDebug(message: String) = println(message)
    private fun printWarn(message: String) = println(message)
    private fun printError(message: String) = println(message)
    private fun printFatal(message: String) = println(message)

    val logger = Logger(
        "",
        infoOutput = ::printInfo,
        debugOutput = ::printDebug,
        warnOutput = ::printWarn,
        errorOutput = ::printError,
        fatalOutput = ::printFatal
    )
}

class Logger (
    val path: String,
    val name: String = path,
    var printName: Boolean = true,
    var printInfo: Boolean = false,
    var printDebug: Boolean = true,
    var printWarn: Boolean = true,
    var printError: Boolean = true,
    var printFatal: Boolean = true,
    private val infoOutput: (String) -> Unit = { println(it) },
    private val debugOutput: (String) -> Unit = { println(it) },
    private val warnOutput: (String) -> Unit = { println(it) },
    private val errorOutput: (String) -> Unit = { println(it) },
    private val fatalOutput: (String) -> Unit = { println(it) }
) {

    // We'll mainly write to this list
    // For performance reasons we'll use a linked set
    // (better write performance than arraylists)
    private val entries = linkedSetOf<LogEntry>()
    val logs: List<LogEntry> get() = entries.toList()

    fun log(level: LogLevel, message: String) {
        when (level) {
            LogLevel.DEBUG -> if (printDebug) debug(message)
            LogLevel.INFO -> if (printInfo) info(message)
            LogLevel.WARN -> if (printWarn) warn(message)
            LogLevel.ERROR -> if (printError) error(message)
            LogLevel.FATAL -> if (printFatal) fatal(message)
        }
    }

    private fun transform(message: String) = if (printName) "[$name] $message" else message


    fun debug(message: String): Boolean {
        this.entries.add(LogEntry(LogLevel.DEBUG, message))
        if (printDebug) this.infoOutput(transform(message))
        return printDebug
    }

    fun info(message: String): Boolean {
        this.entries.add(LogEntry(LogLevel.INFO, message))
        if (printInfo) this.infoOutput(transform(message))
        return printInfo
    }

    fun warn(message: String): Boolean {
        this.entries.add(LogEntry(LogLevel.WARN, message))
        if (printWarn) this.warnOutput(transform(message))
        return printWarn
    }

    fun error(message: String): Boolean {
        this.entries.add(LogEntry(LogLevel.ERROR, message))
        if (printError) this.errorOutput(transform(message))
        return printError
    }

    fun fatal(message: String): Boolean {
        this.entries.add(LogEntry(LogLevel.FATAL, message))
        if (printFatal) this.fatalOutput(transform(message))
        return printFatal
    }
}

class LogEntry(val level: LogLevel, val message: String)

enum class LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;
}

expect val uncoloredLogger: Logger
expect val coloredLogger: Logger
expect val logger: Logger
