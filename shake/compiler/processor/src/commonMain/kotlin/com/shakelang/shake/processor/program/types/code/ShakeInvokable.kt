package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeParameter
import com.shakelang.shake.processor.program.types.ShakeType

/**
 * Represents an invokable entity in the Shake language, such as a function or a method.
 * Invokable entities can be called with parameters and return a value of a specified type.
 *
 * @since 0.1.0
 */
interface ShakeInvokable {
    /**
     * The body of the invokable entity, containing the executable code.
     */
    val body: ShakeCode?

    /**
     * The fully qualified name of the invokable entity.
     */
    val qualifiedName: String

    /**
     * A list of parameters that the invokable entity accepts.
     */
    val parameters: List<ShakeParameter>

    /**
     * The return type of the invokable entity.
     */
    val returnType: ShakeType

    /**
     * Converts the invokable entity to a JSON representation.
     * Useful for serialization or debugging purposes.
     *
     * @return A map representing the JSON structure of this invokable entity.
     */
    fun toJson(): Map<String, Any?>
}
