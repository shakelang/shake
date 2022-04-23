package io.github.shakelang.shake.processor.program.mutable.code.statements

import io.github.shakelang.shake.processor.program.mutable.code.values.ShakeValue

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