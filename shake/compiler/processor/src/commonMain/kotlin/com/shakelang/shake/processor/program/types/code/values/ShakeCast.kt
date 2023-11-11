package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeType

interface ShakeCast : ShakeValue {
    val value: ShakeValue
    val castTarget: ShakeType
}
