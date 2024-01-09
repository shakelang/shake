package com.shakelang.util.logger

/**
 * A [LogTransformer] transforms a log message before it is logged
 * @since 0.3.0
 * @version 0.5.1
 */
interface LogTransformer {

    /**
     * Transform a log message
     * @param level the level of the log message
     * @param message the message to transform
     * @return the transformed message
     *
     * @since 0.3.0
     * @version 0.5.1
     */
    fun transform(level: LogLevel, message: String): String
}
