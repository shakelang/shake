package com.shakelang.util.logger

/**
 * A [Logger] is used to log messages
 *
 * @property pipes The [LoggerPipe]s of the logger
 * @property bufferSize The size of the buffer
 * @property entries The entries of the logger
 * @property firstEntry The first entry of the logger
 *
 * @param pipes The [LoggerPipe]s of the logger
 * @param bufferSize The size of the buffer
 *
 * @constructor Creates a new [Logger]
 *
 * @since 0.1.0
 * @version 0.3.0
 */
class Logger(

    /**
     * The [LoggerPipe]s of the logger
     *
     * @since 0.3.0
     * @version 0.3.0
     */
    val pipes: MutableList<LoggerPipe> = mutableListOf(),

    /**
     * The size of the buffer
     *
     * @since 0.3.0
     * @version 0.3.0
     */
    val bufferSize: Int = 8192
) {

    /**
     * The entries of the logger
     * @since 0.3.0
     * @version 0.3.0
     */
    private val entries = mutableListOf<LogEntry>()

    /**
     * The first entry of the logger
     * @since 0.3.0
     * @version 0.3.0
     */
    private var firstEntry = 0


    /**
     * The logs of the logger
     * @since 0.3.0
     * @version 0.3.0
     */
    val logs: List<LogEntry> get() = entries.toList()


    /**
     * Pushes a [LogEntry] to the buffer
     * @param entry The [LogEntry] to push
     *
     * @since 0.3.0
     * @version 0.3.0
     */
    fun pushBuffer(entry: LogEntry) {
        if (entries.size >= bufferSize) {
            entries[firstEntry++] = entry
        } else {
            entries.add(entry)
        }
    }

    /**
     * Pushes a [LogEntry] to the buffer
     * @param level The [LogLevel] of the [LogEntry]
     * @param message The message of the [LogEntry]
     *
     * @since 0.3.0
     * @version 0.3.0
     */
    fun pushBuffer(level: LogLevel, message: String) = pushBuffer(LogEntry(level, message))


    /**
     * Pushes a [LogEntry] to the buffer
     * @param level The [LogLevel] of the [LogEntry]
     * @param creator The [LogMessageCreator] of the [LogEntry]
     *
     * @since 0.3.0
     * @version 0.3.0
     */
    fun getBuffer(): List<LogEntry> {
        val buffer = arrayOfNulls<LogEntry>(bufferSize)
        for (i in 0 until bufferSize) {
            buffer[i] = entries[(firstEntry + i) % bufferSize]
        }
        return buffer.toList().filterNotNull()
    }

    /**
     * Logs a message with the given [level] and [message]
     * @param level The [LogLevel] of the message
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun log(level: LogLevel, message: String) {
        this.entries.add(LogEntry(level, message))
        this.pipes.forEach {
            it.log(level, message)
        }
    }

    /**
     * Logs a message with the given [level] and [creator]
     * @param level The [LogLevel] of the message
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun log(level: LogLevel, creator: LogMessageCreator)
        = this.log(level, creator.toString())

    /**
     * Logs a message with the given [level] and [creator]
     * @param level The [LogLevel] of the message
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun log(level: LogLevel, creator: LogMessageLambda)
        = this.log(level, com.shakelang.util.logger.LogMessageCreator.from(creator))


    /**
     * Logs a debug message with the given [message]
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun debug(message: String) = this.log(LogLevel.DEBUG, message)

    /**
     * Logs an info message with the given [message]
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun info(message: String) = this.log(LogLevel.INFO, message)

    /**
     * Logs a success message with the given [message]
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun success(message: String) = this.log(LogLevel.SUCCESS, message)

    /**
     * Logs a warning message with the given [message]
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun warn(message: String) = this.log(LogLevel.WARN, message)

    /**
     * Logs an error message with the given [message]
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun error(message: String) = this.log(LogLevel.ERROR, message)

    /**
     * Logs a fatal message with the given [message]
     * @param message The message to log
     * @since 0.1.0
     * @version 0.3.0
     */
    fun fatal(message: String) = this.log(LogLevel.FATAL, message)


    /**
     * Logs a debug message with the given [creator]
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun debug(creator: LogMessageCreator) = this.log(LogLevel.DEBUG, creator)

    /**
     * Logs an info message with the given [creator]
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun info(creator: LogMessageCreator) = this.log(LogLevel.INFO, creator)

    /**
     * Logs a success message with the given [creator]
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun success(creator: LogMessageCreator) = this.log(LogLevel.SUCCESS, creator)

    /**
     * Logs a warning message with the given [creator]
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun warn(creator: LogMessageCreator) = this.log(LogLevel.WARN, creator)

    /**
     * Logs an error message with the given [creator]
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun error(creator: LogMessageCreator) = this.log(LogLevel.ERROR, creator)

    /**
     * Logs a fatal message with the given [creator]
     * @param creator The [LogMessageCreator] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun fatal(creator: LogMessageCreator) = this.log(LogLevel.FATAL, creator)


    /**
     * Logs a debug message with the given [creator]
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun debug(creator: LogMessageLambda) = this.log(LogLevel.DEBUG, creator)

    /**
     * Logs an info message with the given [creator]
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun info(creator: LogMessageLambda) = this.log(LogLevel.INFO, creator)

    /**
     * Logs a success message with the given [creator]
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun success(creator: LogMessageLambda) = this.log(LogLevel.SUCCESS, creator)

    /**
     * Logs a warning message with the given [creator]
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun warn(creator: LogMessageLambda) = this.log(LogLevel.WARN, creator)

    /**
     * Logs an error message with the given [creator]
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun error(creator: LogMessageLambda) = this.log(LogLevel.ERROR, creator)

    /**
     * Logs a fatal message with the given [creator]
     * @param creator The [LogMessageLambda] of the message
     * @since 0.5.0
     * @version 0.5.0
     */
    fun fatal(creator: LogMessageLambda) = this.log(LogLevel.FATAL, creator)


    /**
     * Pipes the logs to the given [pipe]
     * @param pipe The [LoggerPipe] to pipe to
     * @since 0.3.0
     * @version 0.3.0
     * @see LoggerPipe
     */
    fun pipe(pipe: LoggerPipe) {
        this.entries.forEach {
            pipe.log(it.level, it.message)
        }
        this.pipes.add(pipe)
    }

    /**
     * Transforms the logs with the given [transformer] before piping them to the given [pipe]
     * @param transformer The [LogTransformer] to transform the logs with
     * @return The [TransformedOutput] to pipe to
     * @since 0.3.0
     * @version 0.3.0
     */
    fun transform(transformer: LogTransformer) = TransformedOutput(listOf(transformer))

    /**
     * Transforms the logs with the given [transformers] before piping them to the given [pipe]
     * @param transformers The [LogTransformer]s to transform the logs with
     * @return The [TransformedOutput] to pipe to
     * @since 0.3.0
     * @version 0.3.0
     */
    fun transform(transformers: List<LogTransformer>) = TransformedOutput(transformers)

    /**
     * Transforms the logs with the given [transformers] before piping them to the given [pipe]
     * @param transformers The [LogTransformer]s to transform the logs with
     * @return The [TransformedOutput] to pipe to
     * @since 0.3.0
     * @version 0.3.0
     */
    fun transform(vararg transformers: LogTransformer) = TransformedOutput(transformers.toList())


    /**
     * Utility for generating a [TransformedPipe] to transform the logs before piping them to the given pipe
     * @property transformers The [LogTransformer]s to transform the logs with
     * @param transformers The [LogTransformer]s to transform the logs with
     * @since 0.3.0
     * @version 0.3.0
     */
    inner class TransformedOutput(

        /**
         * The [LogTransformer]s to transform the logs with
         * @since 0.3.0
         * @version 0.3.0
         */
        val transformers: List<LogTransformer>

    ) {

        /**
         * Pipes the logs to the given [pipe]
         * @param pipe The [LoggerPipe] to pipe to
         * @since 0.3.0
         * @version 0.3.0
         * @see LoggerPipe
         */
        fun pipe(pipe: LoggerPipe) {
            this@Logger.pipes.add(TransformedPipe(pipe, transformers))
        }

        /**
         * Transforms the logs with the given [transformer] before piping them to the given [pipe]
         * @param transformer The [LogTransformer] to transform the logs with
         * @return The transformed [TransformedOutput]
         * @since 0.3.0
         * @version 0.3.0
         */
        fun transform(transformer: LogTransformer) = TransformedOutput(transformers + transformer)
    }
}

/**
 * A [LogEntry] is a data class that represents a log entry
 *
 * @property level The [LogLevel] of the [LogEntry]
 * @property message The message of the [LogEntry]
 * @param level The [LogLevel] of the [LogEntry]
 * @param message The message of the [LogEntry]
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class LogEntry(

    /**
     * The [LogLevel] of the [LogEntry]
     * @since 0.1.0
     * @version 0.1.0
     */
    val level: LogLevel,

    /**
     * The message of the [LogEntry]
     * @since 0.1.0
     * @version 0.1.0
     */
    val message: String
)

/**
 * The [LogLevel] of a [LogEntry]
 * @since 0.1.0
 * @version 0.1.0
 */
enum class LogLevel {

    /**
     * The [LogLevel] for debug messages
     * @since 0.1.0
     * @version 0.1.0
     */
    DEBUG,

    /**
     * The [LogLevel] for info messages
     * @since 0.1.0
     * @version 0.1.0
     */
    INFO,

    /**
     * The [LogLevel] for warning messages
     * @since 0.1.0
     * @version 0.1.0
     */
    WARN,

    /**
     * The [LogLevel] for error messages
     * @since 0.1.0
     * @version 0.1.0
     */
    ERROR,

    /**
     * The [LogLevel] for fatal messages
     * @since 0.1.0
     * @version 0.1.0
     */
    FATAL,

    /**
     * The [LogLevel] for success messages
     * @since 0.1.0
     * @version 0.1.0
     */
    SUCCESS;
}

/**
 * The global logger object of the application
 * @since 0.1^.0
 * @version 0.1.0
 */
val logger = Logger().run {
    transform(CommonTransformerCombinations.DATE_LEVEL).pipe(CommonColoredConsoleLoggerPipe)
    this
}
