package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType

interface ShakeValue {
    val type: ShakeType
    fun toJson(): Map<String, Any?>
}