package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents a 'while' loop statement in the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeWhile : ShakeStatement {

    /**
     * The condition for the while loop. The loop continues as long as this condition evaluates to true.
     */
    val condition: ShakeValue

    /**
     * The body of the while loop. This is the block of code that gets executed repeatedly as long as the condition is true.
     */
    val body: ShakeCode
}

/**
 * Represents a 'do-while' loop statement in the Shake language.
 * In a do-while loop, the body is executed at least once before the condition is checked.
 *
 * @since 0.1.0
 */
interface ShakeDoWhile : ShakeStatement {

    /**
     * The body of the do-while loop. This block of code gets executed at least once.
     */
    val body: ShakeCode

    /**
     * The condition for the do-while loop. After the first execution of the body, the loop continues as long as this condition evaluates to true.
     */
    val condition: ShakeValue
}

/**
 * Represents a 'for' loop statement in the Shake language.
 *
 * @since 0.1.0
 */
interface ShakeFor : ShakeStatement {

    /**
     * The initialization statement for the for loop. This is executed before the loop starts.
     */
    val init: ShakeStatement

    /**
     * The condition for the for loop. The loop continues as long as this condition evaluates to true.
     */
    val condition: ShakeValue

    /**
     * The update statement for the for loop. This is executed after each iteration of the loop.
     */
    val update: ShakeStatement

    /**
     * The body of the for loop. This is the block of code that gets executed repeatedly as long as the condition is true.
     */
    val body: ShakeCode
}
