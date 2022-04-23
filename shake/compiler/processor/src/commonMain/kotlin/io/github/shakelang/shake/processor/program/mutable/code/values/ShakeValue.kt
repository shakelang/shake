package io.github.shakelang.shake.processor.program.mutable.code.values

import io.github.shakelang.shake.processor.program.mutable.ShakeType

interface ShakeValue {
    val type: ShakeType
    fun toJson(): Map<String, Any?>
}