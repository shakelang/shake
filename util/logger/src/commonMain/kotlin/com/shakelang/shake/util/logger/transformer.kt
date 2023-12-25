package com.shakelang.shake.util.logger

interface LogTransformer {
    fun transform(level: LogLevel, message: String): String
}
