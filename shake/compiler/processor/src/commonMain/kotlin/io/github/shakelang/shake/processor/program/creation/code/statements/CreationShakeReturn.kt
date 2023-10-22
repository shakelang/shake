package io.github.shakelang.shake.processor.program.creation.code.statements

import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeReturn

class CreationShakeReturn (
    override val value: CreationShakeValue?
) : CreationShakeStatement, ShakeReturn {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "return",
            "value" to value?.toJson()
        )
    }
}