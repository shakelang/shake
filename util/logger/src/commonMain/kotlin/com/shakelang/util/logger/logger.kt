package com.shakelang.util.logger

class Logger(
    val pipes: MutableList<LoggerPipe> = mutableListOf(),
    val bufferSize: Int = 8192
) {

    // We'll mainly write to this list
    // For performance reasons we'll use a linked set
    // (better write performance than arraylists)
    private val entries = mutableListOf<LogEntry>()
    private var firstEntry = 0

    val logs: List<LogEntry> get() = entries.toList()

    fun pushBuffer(entry: LogEntry) {
        if (entries.size >= bufferSize) {
            entries[firstEntry++] = entry
        } else {
            entries.add(entry)
        }
    }

    fun pushBuffer(level: LogLevel, message: String) = pushBuffer(LogEntry(level, message))

    fun getBuffer(): List<LogEntry> {
        val buffer = arrayOfNulls<LogEntry>(bufferSize)
        for (i in 0 until bufferSize) {
            buffer[i] = entries[(firstEntry + i) % bufferSize]
        }
        return buffer.toList().filterNotNull()
    }

    fun log(level: LogLevel, message: String) {
        this.entries.add(LogEntry(level, message))
        this.pipes.forEach {
            it.log(level, message)
        }
    }

    fun log(level: LogLevel, creator: LogMessageCreator)
        = this.log(level, creator.toString())

    fun log(level: LogLevel, creator: LogMessageLambda)
        = this.log(level, com.shakelang.util.logger.LogMessageCreator.from(creator))

    fun debug(message: String) = this.log(LogLevel.DEBUG, message)
    fun info(message: String) = this.log(LogLevel.INFO, message)
    fun success(message: String) = this.log(LogLevel.SUCCESS, message)
    fun warn(message: String) = this.log(LogLevel.WARN, message)
    fun error(message: String) = this.log(LogLevel.ERROR, message)
    fun fatal(message: String) = this.log(LogLevel.FATAL, message)

    fun debug(creator: LogMessageCreator) = this.log(LogLevel.DEBUG, creator)
    fun info(creator: LogMessageCreator) = this.log(LogLevel.INFO, creator)
    fun success(creator: LogMessageCreator) = this.log(LogLevel.SUCCESS, creator)
    fun warn(creator: LogMessageCreator) = this.log(LogLevel.WARN, creator)
    fun error(creator: LogMessageCreator) = this.log(LogLevel.ERROR, creator)
    fun fatal(creator: LogMessageCreator) = this.log(LogLevel.FATAL, creator)

    fun debug(creator: LogMessageLambda) = this.log(LogLevel.DEBUG, creator)
    fun info(creator: LogMessageLambda) = this.log(LogLevel.INFO, creator)
    fun success(creator: LogMessageLambda) = this.log(LogLevel.SUCCESS, creator)
    fun warn(creator: LogMessageLambda) = this.log(LogLevel.WARN, creator)
    fun error(creator: LogMessageLambda) = this.log(LogLevel.ERROR, creator)
    fun fatal(creator: LogMessageLambda) = this.log(LogLevel.FATAL, creator)

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
        val transformers: List<LogTransformer>
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

val logger = Logger().run {
    transform(CommonTransformerCombinations.DATE_LEVEL).pipe(CommonColoredConsoleLoggerPipe)
    this
}
