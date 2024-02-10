package com.shakelang.shake.processor.program.types

/**
 * Represents a declaration in the Shake language, such as a variable, a function, or a class.
 * A declaration is a named entity that can be assigned a value or type.
 *
 * @since 0.1.0
 */
interface ShakeDeclaration : ShakeAssignable {

    /**
     * The name of the declaration.
     */
    val name: String

    /**
     * A unique name for the declaration, used internally for identification.
     */
    val uniqueName: String

    /**
     * The fully qualified name of the declaration, including its scope and namespace.
     */
    val qualifiedName: String

    // The use function is commented out. If it's part of the interface, uncomment and document it.
    // fun use(scope: ShakeScope): ShakeUsage

    /**
     * Converts the declaration to a JSON representation.
     * Useful for serialization or debugging purposes.
     *
     * @return A map containing the JSON representation of the declaration.
     */
    fun toJson(): Map<String, Any?>
}
