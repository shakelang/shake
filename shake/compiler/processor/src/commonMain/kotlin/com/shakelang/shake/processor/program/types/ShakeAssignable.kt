package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents an entity in the Shake language that can be assigned a value.
 *
 * @since 0.1.0
 */
interface ShakeAssignable {

    /**
     * The actual value of the assignable entity.
     */
    val actualValue: ShakeValue?

    /**
     * The actual type of the assignable entity.
     */
    val actualType: ShakeType

    /**
     * The declared type of the assignable entity.
     */
    val type: ShakeType

    // fun access(scope: ShakeScope): ShakeValue

    /**
     * Assigns a type to this entity.
     *
     * @param other The type to be assigned.
     * @param scope The scope in which the assignment is happening.
     * @return The resulting type after the assignment.
     */
    fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.assignType(other, scope)
    }

    /**
     * Performs addition assignment on this entity's type.
     *
     * @param other The type to be added.
     * @param scope The scope in which the addition assignment is happening.
     * @return The resulting type after the addition assignment.
     */
    fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.additionAssignType(other, scope)
    }

    /**
     * Performs subtraction assignment on this entity's type.
     *
     * @param other The type to be subtracted.
     * @param scope The scope in which the subtraction assignment is happening.
     * @return The resulting type after the subtraction assignment.
     */
    fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.subtractionAssignType(other, scope)
    }

    /**
     * Performs multiplication assignment on this entity's type.
     *
     * @param other The type to be multiplied.
     * @param scope The scope in which the multiplication assignment is happening.
     * @return The resulting type after the multiplication assignment.
     */
    fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.multiplicationAssignType(other, scope)
    }

    /**
     * Performs division assignment on this entity's type.
     *
     * @param other The type to be divided.
     * @param scope The scope in which the division assignment is happening.
     * @return The resulting type after the division assignment.
     */
    fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.divisionAssignType(other, scope)
    }

    /**
     * Performs modulus assignment on this entity's type.
     *
     * @param other The type to be taken modulus with.
     * @param scope The scope in which the modulus assignment is happening.
     * @return The resulting type after the modulus assignment.
     */
    fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.modulusAssignType(other, scope)
    }

    /**
     * Performs power assignment on this entity's type.
     *
     * @param other The type to be powered.
     * @param scope The scope in which the power assignment is happening.
     * @return The resulting type after the power assignment.
     */
    fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.powerAssignType(other, scope)
    }

    /**
     * Handles the type before increment operation.
     *
     * @param scope The scope in which the increment is happening.
     * @return The resulting type after the increment.
     */
    fun incrementBeforeType(scope: ShakeScope): ShakeType? {
        return type.incrementBeforeType(scope)
    }

    /**
     * Handles the type after increment operation.
     *
     * @param scope The scope in which the increment is happening.
     * @return The resulting type after the increment.
     */
    fun incrementAfterType(scope: ShakeScope): ShakeType? {
        return type.incrementAfterType(scope)
    }

    /**
     * Handles the type before decrement operation.
     *
     * @param scope The scope in which the decrement is happening.
     * @return The resulting type after the decrement.
     */
    fun decrementBeforeType(scope: ShakeScope): ShakeType? {
        return type.decrementBeforeType(scope)
    }

    /**
     * Handles the type after decrement operation.
     *
     * @param scope The scope in which the decrement is happening.
     * @return The resulting type after the decrement.
     */
    fun decrementAfterType(scope: ShakeScope): ShakeType? {
        return type.decrementAfterType(scope)
    }
}
