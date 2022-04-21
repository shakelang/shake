package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeCast(
    val value: ShakeValue,
    val castTarget: ShakeType,
) : ShakeValue {
    override val type: ShakeType
        get() = castTarget

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "cast",
            "value" to value.toJson(),
            "castTarget" to castTarget.toJson(),
        )
    }
}