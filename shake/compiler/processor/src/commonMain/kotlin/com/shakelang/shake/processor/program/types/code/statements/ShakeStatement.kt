package com.shakelang.shake.processor.program.types.code.statements

/**
 * Represents a statement in the Shake language.
 * A statement is an instruction or action that forms part of a program's code.
 *
 * @since 0.1.0
 */
interface ShakeStatement {

    /**
     * Converts this statement to a JSON representation.
     * This can be useful for serialization, debugging, or analyzing the structure of the code.
     *
     * @return A map representing the JSON structure of this statement.
     */
    fun toJson(): Map<String, Any?>
}
