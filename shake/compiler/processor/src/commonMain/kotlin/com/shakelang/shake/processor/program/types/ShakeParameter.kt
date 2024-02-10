package com.shakelang.shake.processor.program.types

/**
 * Represents a parameter in the Shake language.
 * Parameters are used in functions or constructors and can be assigned values.
 *
 * @since 0.1.0
 */
interface ShakeParameter : ShakeAssignable {

    /**
     * The name of the parameter.
     */
    val name: String

    /**
     * The unique name of the parameter, typically used internally.
     * By default, it is prefixed with "param_" followed by the parameter's name.
     */
    // TODO uniqueName is not necessarily unique
    val uniqueName: String
        get() = "param_$name"

    /**
     * The type of the parameter.
     */
    override val type: ShakeType

    /**
     * Converts the parameter to a JSON representation.
     * Useful for serialization or debugging purposes.
     *
     * @return A map representing the JSON structure of this parameter.
     */
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "type" to type.toJson(),
    )
}
