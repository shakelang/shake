package com.shakelang.shake.processor.program.types.code.statements

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeDeclaration
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

/**
 * Represents a variable declaration in the Shake language.
 * A variable declaration is a statement that declares a new variable, optionally initializing it with a value.
 *
 * @since 0.1.0
 */
interface ShakeVariableDeclaration : ShakeDeclaration, ShakeAssignable, ShakeStatement {

    /**
     * The scope in which the variable is declared.
     */
    val scope: ShakeScope

    /**
     * The initial value assigned to the variable at the time of declaration, if any.
     */
    val initialValue: ShakeValue?

    /**
     * The actual value of the variable.
     */
    override val actualValue: ShakeValue?

    /**
     * The actual type of the variable.
     */
    override val actualType: ShakeType

    /**
     * Indicates whether the variable is declared as final (i.e., its value cannot be changed once initialized).
     */
    val isFinal: Boolean

    /**
     * The unique name of the variable, combining the variable's name and the unique name of its scope.
     */
    override val uniqueName: String
        get() = "$name@${scope.uniqueName}"

    /**
     * Checks if the provided value is compatible with the variable's type.
     *
     * @param value The value to check for compatibility.
     * @return True if the value is compatible, false otherwise.
     */
    fun valueCompatible(value: ShakeValue): Boolean

    // override fun use(scope: ShakeScope): ShakeVariableUsage
}
