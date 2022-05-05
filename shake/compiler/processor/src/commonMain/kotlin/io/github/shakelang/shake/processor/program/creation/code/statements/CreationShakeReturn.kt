package io.github.shakelang.shake.processor.program.creation.code.statements

import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue

class CreationShakeReturn (
    val value: CreationShakeValue?
) : CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "return",
            "value" to value?.toJson()
        )
    }
}