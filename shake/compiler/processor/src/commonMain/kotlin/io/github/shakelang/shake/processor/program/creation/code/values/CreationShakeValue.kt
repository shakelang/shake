package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType

interface CreationShakeValue {
    val type: CreationShakeType
    fun toJson(): Map<String, Any?>
}