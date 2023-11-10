package io.github.shakelang.shake.processor.program.types.code.values

import io.github.shakelang.shake.processor.program.types.ShakeType

interface ShakeCast : ShakeValue {
    val value: ShakeValue
    val castTarget: ShakeType
}
