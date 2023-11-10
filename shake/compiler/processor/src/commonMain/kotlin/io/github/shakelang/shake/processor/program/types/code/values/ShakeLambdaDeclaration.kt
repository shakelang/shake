package io.github.shakelang.shake.processor.program.types.code.values

import io.github.shakelang.shake.processor.program.types.code.ShakeCode
import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable

interface ShakeLambdaDeclaration : ShakeInvokable, ShakeValue {
    val content: ShakeCode
}
