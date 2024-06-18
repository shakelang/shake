package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.code.statements.ShakeStatement
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents a generic assignment type in the Shake language.
 * This interface is a common base for specific assignment types.
 */
interface ShakeAssignmentType : ShakeValuedStatement {
    /**
     * The variable being assigned to.
     */
    val variable: ShakeAssignable

    /**
     * The value being assigned.
     */
    val value: ShakeValue
}

/**
 * Represents a standard assignment operation in the Shake language.
 */
interface ShakeAssignment : ShakeAssignmentType

/**
 * Represents an addition assignment (e.g., a += b) in the Shake language.
 */
interface ShakeAddAssignment : ShakeAssignmentType

/**
 * Represents a subtraction assignment (e.g., a -= b) in the Shake language.
 */
interface ShakeSubAssignment : ShakeAssignmentType

/**
 * Represents a multiplication assignment (e.g., a *= b) in the Shake language.
 */
interface ShakeMulAssignment : ShakeAssignmentType

/**
 * Represents a division assignment (e.g., a /= b) in the Shake language.
 */
interface ShakeDivAssignment : ShakeAssignmentType

/**
 * Represents a modulus assignment (e.g., a %= b) in the Shake language.
 */
interface ShakeModAssignment : ShakeAssignmentType

/**
 * Represents a power assignment (e.g., a ^= b) in the Shake language.
 */
interface ShakePowAssignment : ShakeAssignmentType

/**
 * Represents a generic mutation type in the Shake language.
 * This interface is a common base for specific mutation types.
 */
interface ShakeMutateType : ShakeValue, ShakeStatement {
    /**
     * The variable being mutated.
     */
    val variable: ShakeAssignable
}

/**
 * Represents an increment operation before the variable is used (e.g., ++a) in the Shake language.
 */
interface ShakeIncrementBefore : ShakeMutateType

/**
 * Represents an increment operation after the variable is used (e.g., a++) in the Shake language.
 */
interface ShakeIncrementAfter : ShakeMutateType

/**
 * Represents a decrement operation before the variable is used (e.g., --a) in the Shake language.
 */
interface ShakeDecrementBefore : ShakeMutateType

/**
 * Represents a decrement operation after the variable is used (e.g., a--) in the Shake language.
 */
interface ShakeDecrementAfter : ShakeMutateType
