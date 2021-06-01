package com.github.nsc.de.shake.interpreter

import com.github.nsc.de.shake.interpreter.values.InterpreterValue

/**
 * A list that contains variables
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
open class VariableList : InterpreterValue {

    /**
     * The variables
     */
    private val variables: MutableMap<String, Variable>

    /**
     * The parent list (or null, if the list hast no parent list)
     */
    val parentList: VariableList?

    override val children: Array<String> get() = getVariables().keys.toTypedArray()

    // *******************************
    // Constructors
    /**
     * Constructor for [VariableList]
     *
     * @param variables a map of the variables ([.variables])
     * @param parentList the parent list
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(variables: MutableMap<String, Variable>, parentList: VariableList?) {
        // apply values to fields
        this.variables = variables
        this.parentList = parentList
    }

    /**
     * Constructor for [VariableList]
     *
     * @param parentList the parent list
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(parentList: VariableList?) {
        // apply values to fields
        variables = HashMap()
        this.parentList = parentList
    }

    /**
     * Constructor for [VariableList]
     *
     * @param variables a map of the variables ([.variables])
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor(variables: HashMap<String, Variable>) {
        // apply given values to fields
        this.variables = variables
        parentList = null
    }

    /**
     * Constructor for [VariableList]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    constructor() {
        // apply values to fields
        variables = HashMap()
        parentList = null
    }
    // *******************************
    // getters
    /**
     * Getter for [.variables] (the variable map)
     *
     * @return the variable map (this.[.variables])
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open fun getVariables(): Map<String, Variable> {
        // just return the variables field
        return variables
    }
    // *******************************
    // VariableList functionality
    /**
     * Declare a [Variable]
     *
     * @param v the variable
     * @return true, if the operation was successful, false if a with this name variable is already declared
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun declare(v: Variable): Boolean {
        // Check if the variable-map already contains a Variable with this name (if so return false).
        // In other case put the variable into the map using the identifier as key and return true
        if (variables.containsKey(v.identifier)) return false
        variables[v.identifier] = v
        return true
    }

    /**
     * Get a variable from the [VariableList]
     *
     * @param name the name of the [Variable] to get
     * @return the [Variable] (or null if the [Variable] is not declared)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    open operator fun get(name: String): Variable? {

        // If the variable map contains the variable then return it.
        if (variables.containsKey(name)) return variables[name] else if (parentList != null) return parentList[name]

        // In other case just return null (the variable is not declared)
        return null
    }

    /**
     * Puts together this [VariableList] with another given one (ignores the [.parentList] of the
     * [VariableList] that is given as argument)
     *
     * @param list the [VariableList] to put together with this one
     * @return the two merged [VariableList]s
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun concat(list: VariableList): VariableList {

        // Create a new HashMap from the variables of the VariableList
        val variables = HashMap(variables)

        // Loop over the given list and put the variables into the variables map
        list.getVariables().forEach { (key: String, value: Variable) -> variables[key] = value }

        // return the variable map
        return VariableList(variables, list.parentList)
    }

    /**
     * Copies the [VariableList]
     *
     * @return the copy of the [VariableList]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun copy(): VariableList {

        // Create a new HashMap for the variables
        val vars: MutableMap<String, Variable> = HashMap()

        // Loop over the variables and put a copy of the variable into the vars Map
        variables.forEach { (k: String, v: Variable) -> vars[k] = v }

        // Return a new VariableList created using the created vars map
        return VariableList(vars, parentList)
    }

    /**
     * Let's all the children of the [VariableList] use the given scope
     *
     * @param scope the scope to use
     * @return the [VariableList] using the scope
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun withScope(scope: Scope?): VariableList {
        // Create a new ScopeVariableList from the fields of this VariableList and the given Scope.
        return ScopeVariableList(variables, parentList, scope)
    }
    // ****************************
    // implementations for extended InterpreterValue
    // Children
    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun getChild(c: String): Variable? {
        // just get the child
        return this[c]
    }
    // ****************************
    // implementations for extended InterpreterValue
    // get-name// just return "variable_list"
    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [VariableList] it just always returns "variable_list"
     *
     * @return the name of the [InterpreterValue]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String
        get() =// just return "variable_list"
            "variable_list"
    // *******************************
    // Override toString()
    /**
     * Returns the string representation of the [VariableList]
     *
     * @return the string representation of the [VariableList]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String {
        // just create a string out of the properties of the VariableList
        return "{variables=$variables,parentList=$parentList}"
    }

    fun reset() {
        variables.clear()
    }

    /**
     * This is a VariableList that forces all of its children to use a scope, it is required for the implementation
     * of [VariableList.withScope]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    class ScopeVariableList// call super constructor with specified arguments

    // set scope field
    /**
     * Constructor for [ScopeVariableList]
     *
     * @param variables the variables
     * @param parentList the parent-list
     * @param scope the scope to use
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     *
     * @see VariableList
     */(
        variables: MutableMap<String, Variable>, parentList: VariableList?,
        /**
         * The scope to use for the children
         */
        var scope: Scope?
    ) : VariableList(variables, parentList) {// just return the scope field
        /**
         * Getter for [.scope] (the scope to use for all variables)
         *
         * @return the scope to use for all variables (this.[.scope])
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */// just set the scope field
        /**
         * Setter for [.scope] (the scope to use for all variables)
         *
         * @param scope the scope to use for all variables (this.[.scope])
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */

        /**
         * Get a variable from the [VariableList]
         *
         * @param name the name of the [Variable] to get
         * @return the [Variable] (or null if the [Variable] is not declared)
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun get(name: String): Variable {
            // Get the Variable from the parent and call the withScope function on it
            return super.get(name)!!.withScope(scope!!)
        }

        /**
         * Getter for [.variables] (the variable map)
         *
         * @return the variable map (this.[.variables])
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun getVariables(): Map<String, Variable> {
            // Create a new map
            val variableMap: MutableMap<String, Variable> = HashMap()

            // Copy all the variables to the variable-map, but always calling the #withScope function on them
            super.getVariables().forEach { (key: String, variable: Variable) ->
                variableMap[key] = variable.withScope(
                    scope!!
                )
            }

            // return the variable-map
            return variableMap
        }

        override fun getChild(c: String): Variable {
            return this[c]
        }
    }
}