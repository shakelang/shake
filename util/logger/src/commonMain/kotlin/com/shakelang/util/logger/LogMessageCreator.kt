package com.shakelang.util.logger

/**
 * A [LogMessageCreator] is a utility to dynamically create a log message
 * when it is needed. This can be used to save performance when the log
 * message is not needed
 *
 * @since 0.5.0
 * @version 0.5.0
 */
interface LogMessageCreator {

    /**
     * Returns the log message
     * @return the log message
     */
    override fun toString(): String

    companion object {

        /**
         * Create a [LogMessageCreator] from a [creator] lambda
         * @param creator the lambda to create the log message
         * @return the [LogMessageCreator]
         */
        fun from(creator: LogMessageLambda): LogMessageCreator {
            return object : LogMessageCreator {

                lateinit var message: String

                override fun toString(): String {
                    if(!::message.isInitialized) message = creator()
                    return message
                }
            }
        }
    }
}

/**
 * A lambda to create a log message
 */
typealias LogMessageLambda = () -> String