package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.types.ShakeProject
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

class CreationShakeCast(
    override val project: ShakeProject,
    val value: CreationShakeValue,
    val castTarget: CreationShakeType
) : CreationShakeValue, ShakeValue {
    override val type: CreationShakeType
        get() = castTarget

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "cast",
            "value" to value.toJson(),
            "castTarget" to castTarget.toJson()
        )
    }
}
