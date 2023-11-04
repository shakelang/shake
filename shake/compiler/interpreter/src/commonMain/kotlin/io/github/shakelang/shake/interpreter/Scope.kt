package io.github.shakelang.shake.interpreter

import io.github.shakelang.shake.interpreter.values.InterpreterValue

/**
 * A scope to keep all the variables inside
 *
 *
 * @see VariableList
 */
class Scope


/**
 * Constructor for [Scope]
 *
 * @param parent the parent of the scope (value for [parent])
 * @param scopeVariables the variables of the scope (value for [scopeVariables])
 * @param interpreter the [interpreter] of the scope
 */
    (

    /**
     * The parent [Scope] (or null if the [Scope] has no parent)
     */
    val parent: Scope?,

    /**
     * The variables of the [Scope]
     */
    val scopeVariables: VariableList,

    /**
     * The interpreter of the [Scope]
     */
    val interpreter: Interpreter

) {

    // *******************************
    // fields

    /**
     * Getter for the [.returnValue] field
     *
     * @return the [.returnValue]
     */
    var returnValue: InterpreterValue? = null

    /**
     * Constructor for [Scope]
     *
     * @param parent the parent of the scope (value for [.parent])
     */
    constructor(parent: Scope?, interpreter: Interpreter) : this(parent, VariableList(), interpreter)


    // *******************************
    // getters

    /**
     * Getter for all scope variables (including all the variables of the parents)
     *
     * @return the scope's variables (this.[.variables])
     */
    fun getVariables(): VariableList {
        return if (this.parent != null) this.parent.getVariables().concat(this.scopeVariables) else this.scopeVariables
    }

    /**
     * Copies the scope
     *
     * @return the copy of the scope
     */
    fun copy(): Scope {
        return Scope(this.parent, this.scopeVariables.copy(), interpreter)
    }

    fun reset() {
        this.getVariables().reset()
    }
    // *******************************
    // constructors
}