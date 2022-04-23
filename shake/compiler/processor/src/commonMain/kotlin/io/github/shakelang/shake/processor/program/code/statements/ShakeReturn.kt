package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.code.values.ShakeValue

class ShakeReturn (
    val value: ShakeValue?
) : ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "return",
            "value" to value?.toJson()
        )
    }
}