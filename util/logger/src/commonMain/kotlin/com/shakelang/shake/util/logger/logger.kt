package com.shakelang.shake.util.logger

class Logger(
    val path: String,
    val name: String = path,
    var printName: Boolean = true,
    var printInfo: Boolean = true,
    var printSuccess: Boolean = true,
    var printDebug: Boolean = false,
    var printWarn: Boolean = true,
    var printError: Boolean = true,
    var printFatal: Boolean = true,
    val pipes: MutableList<LoggerPipe> = mutableListOf()
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

    fun transform(transformer: LogTransformer) = TransformedOutput(listOf(transformer))
    fun transform(transformers: List<LogTransformer>) = TransformedOutput(transformers)
    fun transform(vararg transformers: LogTransformer) = TransformedOutput(transformers.toList())

    inner class TransformedOutput(
        val transformers: List<LogTransformer>,
    ) {
        fun pipe(pipe: LoggerPipe) {
            this@Logger.pipes.add(TransformedPipe(pipe, transformers))
        }

        fun transform(transformer: LogTransformer) = TransformedOutput(transformers + transformer)
    }
}

class LogEntry(val level: LogLevel, val message: String)

enum class LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL,
    SUCCESS;
}

val logger = Logger("")
