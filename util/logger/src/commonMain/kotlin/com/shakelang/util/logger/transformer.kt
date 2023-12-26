package com.shakelang.util.logger

interface LogTransformer {
    fun transform(level: LogLevel, message: String): String
}
