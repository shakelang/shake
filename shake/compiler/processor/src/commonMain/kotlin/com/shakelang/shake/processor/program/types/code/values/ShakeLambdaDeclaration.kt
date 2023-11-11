package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.ShakeInvokable

interface ShakeLambdaDeclaration : ShakeInvokable, ShakeValue {
    val content: ShakeCode
}
