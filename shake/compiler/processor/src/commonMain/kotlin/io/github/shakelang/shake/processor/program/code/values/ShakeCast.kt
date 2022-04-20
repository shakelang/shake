package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeCast(
    val value: ShakeValue,
    val castTarget: ShakeType,
) : ShakeValue {
    override val type: ShakeType
        get() = castTarget
}