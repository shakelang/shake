package io.github.shakelang.shake.processor.program.types.code.values

import io.github.shakelang.shake.processor.program.types.ShakeType

interface ShakeValue {
    val type: ShakeType
    fun toJson(): Map<String, Any?>
}