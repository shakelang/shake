package com.shakelang.util.logger

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * This object contains some common transformers
 * @since 0.3.0
 * @version 0.3.1
 */
object CommonTransformers {

    /**
     * A transformer that adds a timestamp to the message
     * @since 0.3.0
     * @version 0.3.0
     */
    object DateTransformer : LogTransformer {

        /**
         * Stringify a number and add leading zeros
         * @param number the number to stringify
         * @param length the length of the string
         * @return the stringified number
         * @since 0.3.0
         * @version 0.3.0
         */
        fun leadingZero(number: Int, length: Int = 2): String {
            var str = number.toString()
            while (str.length < length) str = "0$str"
            return str
        }

        /**
         * Generate a timestamp
         * @return the timestamp
         * @since 0.3.0
         * @version 0.3.0
         */
        fun generateTimestamp(): String {
            val current = Clock.System.now()
            val timeZone = TimeZone.currentSystemDefault()
            val localDateTime = current.toLocalDateTime(timeZone)
            val year = leadingZero(localDateTime.year, 4)
            val month = leadingZero(localDateTime.monthNumber, 2)
            val day = leadingZero(localDateTime.dayOfMonth, 2)
            val hour = leadingZero(localDateTime.hour, 2)
            val minute = leadingZero(localDateTime.minute, 2)
            val second = leadingZero(localDateTime.second, 2)
            val millisecond = leadingZero(localDateTime.nanosecond / 1000000, 3)
            return "$year-$month-$day $hour:$minute:$second.$millisecond"
        }

        /**
         * Transform the message
         * @param level the level of the message
         * @param message the message
         * @return the transformed message
         * @since 0.3.0
         * @version 0.3.0
         */
        override fun transform(level: LogLevel, message: String): String {
            return "[${generateTimestamp()}] $message"
        }
    }

    /**
     * A transformer that adds the level to the message
     * @since 0.3.0
     * @version 0.3.0
     */
    object LevelTransformer : LogTransformer {

        /**
         * Transform the message
         * @param level the level of the message
         * @param message the message
         * @return the transformed message
         * @since 0.3.0
         * @version 0.3.0
         */
        override fun transform(level: LogLevel, message: String): String {
            return "[${level.name}] $message"
        }
    }
}

/**
 * This object contains some common transformer combinations
 * @since 0.3.0
 * @version 0.3.0
 */
object CommonTransformerCombinations {

    /**
     * A combination of [CommonTransformers.LevelTransformer] and [CommonTransformers.DateTransformer]
     * @since 0.3.0
     * @version 0.3.0
     */
    val DATE_LEVEL = listOf(CommonTransformers.LevelTransformer, CommonTransformers.DateTransformer)

    /**
     * A combination containing only [CommonTransformers.DateTransformer]
     * @since 0.3.0
     * @version 0.3.0
     */
    val DATE = listOf(CommonTransformers.DateTransformer)

    /**
     * A combination containing only [CommonTransformers.LevelTransformer]
     * @since 0.3.0
     * @version 0.3.0
     */
    val LEVEL = listOf(CommonTransformers.LevelTransformer)
}
