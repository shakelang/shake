package com.shakelang.shake.processor.program.types.code.statements

interface ShakeStatement {
    fun toJson(): Map<String, Any?>
}
