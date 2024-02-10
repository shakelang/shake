package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents an 'if' statement in the Shake language.
 * An 'if' statement is a conditional statement that executes a block of code based on the evaluation of a condition.
 *
 * @since 0.1.0
 */
interface ShakeIf : ShakeStatement {

    /**
     * The condition that determines whether the 'if' block or the 'else' block (if present) should be executed.
     * This is a value that should evaluate to a boolean.
     */
    val condition: ShakeValue

    /**
     * The block of code to be executed if the condition is true.
     */
    val body: ShakeCode

    /**
     * The block of code to be executed if the condition is false.
     * This is optional and may be null if no 'else' block is present.
     */
    val elseBody: ShakeCode?
}
