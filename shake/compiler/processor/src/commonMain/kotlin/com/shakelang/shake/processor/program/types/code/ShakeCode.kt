package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.code.statements.ShakeStatement

/**
 * Represents a block of code in the Shake language.
 * This includes a sequence of statements that are executed in the Shake programming environment.
 *
 * @since 0.1.0
 */
interface ShakeCode {

    /**
     * A list of statements that make up this block of code.
     */
    val statements: List<ShakeStatement>

    /**
     * Converts this block of code to a JSON representation.
     * This can be useful for serialization, debugging, or for interfacing with other components that consume JSON.
     *
     * @return A map representing the JSON structure of this block of code.
     */
    fun toJson(): Map<String, Any>
}
