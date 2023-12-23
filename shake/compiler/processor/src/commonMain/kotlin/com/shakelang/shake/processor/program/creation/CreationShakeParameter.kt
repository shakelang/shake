package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.processor.program.types.ShakeParameter
import com.shakelang.shake.processor.program.types.ShakeType

class CreationShakeParameter(
    override val name: String,
    override val type: ShakeType
) : ShakeParameter {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type.toJson()
        )
    }
}
