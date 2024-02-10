package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents a return statement in the Shake language.
 * Used to return a value from a block of code, such as a function or a method.
 *
 * @since 0.1.0
 */
interface ShakeReturn {

    /**
     * The value to be returned by the return statement.
     * Can be null if the statement returns no value (void return type).
     */
    val value: ShakeValue?
}
