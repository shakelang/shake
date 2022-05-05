package io.github.shakelang.shake.processor.program.creation.code.statements

interface CreationShakeStatement {
    fun toJson(): Map<String, Any?>
}