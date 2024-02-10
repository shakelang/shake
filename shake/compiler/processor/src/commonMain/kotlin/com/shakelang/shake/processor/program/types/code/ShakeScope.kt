package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeClass
import com.shakelang.shake.processor.program.types.ShakeMethod

/**
 * Represents a scope in the Shake language.
 * A scope is a context in which names and declarations (like variables, functions, classes) are defined.
 *
 * @since 0.1.0
 */
interface ShakeScope {
    /**
     * The unique name of this scope, used for identification purposes.
     */
    val uniqueName: String

    /**
     * The parent scope of this scope, if it exists. Null if this is a top-level scope.
     */
    val parent: ShakeScope?

    /**
     * Retrieves a field (variable) by its name within this scope.
     *
     * @param name The name of the field to retrieve.
     * @return The field if found, or null if not found.
     */
    fun getField(name: String): ShakeAssignable?

    /**
     * Retrieves all fields (variables) with the specified name within this scope.
     *
     * @param name The name of the fields to retrieve.
     * @return A list of fields with the specified name.
     */
    fun getFields(name: String): List<ShakeAssignable>

    /**
     * Retrieves all functions with the specified name within this scope.
     *
     * @param name The name of the functions to retrieve.
     * @return A list of functions with the specified name.
     */
    fun getFunctions(name: String): List<ShakeMethod>

    /**
     * Retrieves a class by its name within this scope.
     *
     * @param name The name of the class to retrieve.
     * @return The class if found, or null if not found.
     */
    fun getClass(name: String): ShakeClass?

    /**
     * Retrieves all classes with the specified name within this scope.
     *
     * @param name The name of the classes to retrieve.
     * @return A list of classes with the specified name.
     */
    fun getClasses(name: String): List<ShakeClass>

    /**
     * Retrieves all invokable entities (like functions or methods) with the specified name within this scope.
     *
     * @param name The name of the invokable entities to retrieve.
     * @return A list of invokable entities with the specified name.
     */
    fun getInvokable(name: String): List<ShakeInvokable>

    /**
     * Retrieves the 'this' reference within this scope.
     * 'This' refers to the current object in object-oriented contexts.
     *
     * @return The 'this' assignable entity if it exists, or null otherwise.
     */
    fun getThis(): ShakeAssignable?

    /**
     * Retrieves a field or method named 'name' from the 'this' object within this scope.
     *
     * @param name The name of the field or method to retrieve from the 'this' object.
     * @return The field or method if found, or null if not found.
     */
    fun getThis(name: String): ShakeAssignable?

    /**
     * Retrieves the 'super' reference within this scope.
     * 'Super' refers to the superclass object in object-oriented contexts.
     *
     * @return The 'super' assignable entity if it exists, or null otherwise.
     */
    fun getSuper(): ShakeAssignable?

    /**
     * Retrieves a field or method named 'name' from the 'super' object within this scope.
     *
     * @param name The name of the field or method to retrieve from the 'super' object.
     * @return The field or method if found, or null if not found.
     */
    fun getSuper(name: String): ShakeAssignable?

    /**
     * Indicates that a name is being used in this scope.
     * This method can be used for tracking purposes, such as identifying unused variables.
     *
     * @param name The name that is being used.
     */
    fun use(name: String)
}
