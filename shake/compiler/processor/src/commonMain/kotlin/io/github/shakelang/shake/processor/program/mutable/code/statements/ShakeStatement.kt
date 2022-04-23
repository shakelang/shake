package io.github.shakelang.shake.processor.program.mutable.code.statements

interface ShakeStatement {
    fun toJson(): Map<String, Any?>
}