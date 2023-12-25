package com.shakelang.shake.util.logger

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object CommonTransformers {

    object DateTransformer : LogTransformer {

        fun leadingZero(number: Int, length: Int = 2): String {
            var str = number.toString()
            while (str.length < length) str = "0$str"
            return str
        }
        
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

        override fun transform(level: LogLevel, message: String): String {
            return "[${generateTimestamp()}] $message"
        }
    }

    object LevelTransformer : LogTransformer {
        override fun transform(level: LogLevel, message: String): String {
            return "[${level.name}] $message"
        }
    }
}

object CommonTransformerCombinations {

    val DATE_LEVEL = listOf(CommonTransformers.DateTransformer, CommonTransformers.LevelTransformer)
    val DATE = listOf(CommonTransformers.DateTransformer)
    val LEVEL = listOf(CommonTransformers.LevelTransformer)

}
