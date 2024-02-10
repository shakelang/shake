package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.ShakeInvokable

/**
 * Represents a lambda declaration in the Shake language.
 * A lambda declaration is a function that can be passed around as a value and invoked.
 * This interface extends ShakeInvokable for invocation capabilities and ShakeValue for value representation.
 *
 * @since 0.1.0
 */
interface ShakeLambdaDeclaration : ShakeInvokable, ShakeValue {

    /**
     * The content of the lambda, represented as ShakeCode.
     * This defines the behavior of the lambda when it is invoked.
     */
    val content: ShakeCode
}
