package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeType

/**
 * Represents a cast operation in the Shake language.
 * This interface is used for casting a value from one type to another.
 *
 * @since 0.1.0
 */
interface ShakeCast : ShakeValue {

    /**
     * The value to be cast.
     */
    val value: ShakeValue

    /**
     * The target type to which the value is being cast.
     */
    val castTarget: ShakeType
}
