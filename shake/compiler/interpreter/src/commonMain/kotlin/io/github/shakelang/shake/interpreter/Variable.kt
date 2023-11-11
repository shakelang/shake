package com.shakelang.shake.interpreter

import com.shakelang.shake.interpreter.values.*
import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import com.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

/**
 * Variable class to keep all [InterpreterValue]s in variables
 * Type argument is the type of value that
 */
@Suppress("unused")
class Variable
    (
    /**
     * The [Variable] identifier
     */
    val identifier: String,

    /**
     * The access of the variable
     */
    val access: ShakeAccessDescriber,

    /**
     * The type of the variable
     */
    val type: KClass<*>,

    /**
     * The value of the variable
     */
    value: InterpreterValue? = NullValue.NULL,

    /**
     * Is the variable final?
     */
    var final: Boolean = false,

    ) : InterpreterValue {

    /**
     * The value of the variable
     */
    var value: InterpreterValue = value ?: NullValue.NULL
        set(value) {
            // set the value of the value field to the given value variable
            if (final) throw Error("Can't set the value of a final variable!")
            field = value
        }

    /**
     * This boolean is only true while processing the [toString] function.
     *
     * In some cases toString can be executed recursively, so this variable
     * is for preventing a [StackOverflowError]. When the [toString]
     * starts this boolean is set to true and if the function gets called again it
     * just returns the [identifier] instead of all the children. At the end
     * of [toString] this boolean will be set to false again.
     */
    private var toString = false

    /**
     * Constructor for [Variable]
     *
     * @param identifier the identifier of the variable
     * @param value the value of the variable
     */
    constructor(identifier: String, type: KClass<*>, value: InterpreterValue) :
            this(identifier, ShakeAccessDescriber.PACKAGE, type, value)

    /**
     * Constructor for [Variable]
     *
     * @param identifier the identifier of the variable
     * @param access the access of the
     */
    constructor(identifier: String, access: ShakeAccessDescriber, type: KClass<*>, finalVariable: Boolean) :
            this(identifier, access, type, null, finalVariable)

    /**
     * Constructor for [Variable]
     *
     * @param identifier the identifier of the variable
     */
    constructor(identifier: String, type: KClass<*>) : this(identifier, ShakeAccessDescriber.PACKAGE, type)


    // *******************************
    // setters

    /**
     * Checks if the [Variable] has a value ([.value] != null)
     *
     * @return has the [Variable] a value?
     */
    fun hasValue(): Boolean = value != NullValue.NULL


    // ****************************
    // implementations for extended InterpreterValue
    // number-operators

    /**
     * This function will be executed when the operator '+' is used on the value
     *
     * @param v The Value to add to this value
     * @return The Calculation-Result
     */
    override fun add(v: InterpreterValue): InterpreterValue = this.value.add(v)

    /**
     * This function will be executed when the operator '-' is used on the value
     *
     * @param v The Value to sub from this value
     * @return The Calculation-Result
     */
    override fun sub(v: InterpreterValue): InterpreterValue = this.value.sub(v)

    /**
     * This function will be executed when the operator '*' is used on the value
     *
     * @param v The Value to multiply with this value
     * @return The Calculation-Result
     */
    override fun mul(v: InterpreterValue): InterpreterValue = this.value.mul(v)

    /**
     * This function will be executed when the operator '/' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     */
    override fun div(v: InterpreterValue): InterpreterValue = this.value.div(v)

    /**
     * This function will be executed when the operator '%' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     */
    override fun mod(v: InterpreterValue): InterpreterValue = this.value.mod(v)

    /**
     * This function will be executed when the operator '**' is used on the value
     *
     * @param v The exponent value
     * @return The Calculation-Result
     */
    override fun pow(v: InterpreterValue): InterpreterValue = this.value.pow(v)


    // ****************************
    // implementations for extended InterpreterValue
    // boolean-operators

    /**
     * This function will be executed when the operator '||' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     */
    override fun or(v: InterpreterValue): InterpreterValue = this.value.or(v)

    /**
     * This function will be executed when the operator '&amp;&amp;' is used on the value
     *
     * @param v The other value for the and operator
     * @return The Calculation-Result
     */
    override fun and(v: InterpreterValue): InterpreterValue = this.value.and(v)


    // ****************************
    // implementations for extended InterpreterValue
    // comparison

    /**
     * This function will be executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     */
    override fun equals(v: InterpreterValue): InterpreterValue = this.value.equals(v)

    /**
     * This function will be executed when the operator '&gt;=' is used on the value
     *
     * @param v The value that should be smaller
     * @return The Calculation-Result
     */
    override fun biggerEquals(v: InterpreterValue): InterpreterValue = this.value.biggerEquals(v)

    /**
     * This function will be executed when the operator '&lt;=' is used on the value
     *
     * @param v The value that should be bigger
     * @return The Calculation-Result
     */
    override fun smallerEquals(v: InterpreterValue): InterpreterValue = this.value.smallerEquals(v)

    /**
     * This function will be executed when the operator '&gt;' is used on the value
     *
     * @param v The value that should be smaller or equal
     * @return The Calculation-Result
     */
    override fun bigger(v: InterpreterValue): InterpreterValue = this.value.bigger(v)

    /**
     * This function will be executed when the operator '&lt;' is used on the value
     *
     * @param v The value that should be bigger or equal
     * @return The Calculation-Result
     */
    override fun smaller(v: InterpreterValue): InterpreterValue = this.value.smaller(v)


    // ****************************
    // implementations for extended InterpreterValue
    // children

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     */
    override fun getChild(c: String): Variable? = this.value.getChild(c)

    /**
     * This function will be executed when getting all child keys
     *
     * @return the keys of all children
     */
    override val children: Array<String>
        get() = value.children


    // ****************************
    // implementations for extended InterpreterValue
    // invoke

    /**
     * Invoke a value
     *
     * @param node the node that called the function
     * @param scope the scope the call was made in (to process the arguments)
     *
     * @return the function result
     */
    override fun invoke(node: ShakeFunctionCallNode, scope: Scope): InterpreterValue {
        // redirect operator to the value
        return this.value.invoke(node, scope)
    }

    /**
     * Create a new instance of a class
     *
     * @param node the node that created the instance
     * @param scope the scope the creation was made in (to process the arguments)
     * @return the created [InterpreterValue]
     */
    override fun newInstance(node: ShakeClassConstructionNode, scope: Scope): InterpreterValue =
        this.value.newInstance(node, scope)


    // ****************************
    // implementations for extended InterpreterValue
    // get-name// redirect operator to the value

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     */
    override val name: String
        get() =// redirect operator to the value
            this.value.name


    // ****************************
    // copy

    /**
     * Copies the variable
     *
     * @return the copied [Variable]
     */
    fun copy(): Variable {
        // return a new Variable using the same values
        return Variable(identifier, access, type, value)
    }


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [Variable]
     *
     * @return the string representation of the [Variable]
     */
    override fun toString(): String {

        // prevent infinite recursion
        return if (!toString) {

            // set toString to true to prevent infinite recursion
            toString = true

            // create a string from the variable
            val ret = "Variable{identifier='$identifier', type=${type.simpleName}, access=$access, value=$value}"

            // set toString to false again to reset the toString function
            toString = false

            // return the generated string
            ret
        } else {
            // return a simple version of the Variable#toString() result
            "Variable{identifier='$identifier', ...}"
        }
    }

    /**
     * Returns the same variable, but you can declare what [Scope] to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the [Function] using the specified [Scope]
     */
    fun withScope(scope: Scope): Variable {
        return Variable(identifier, access, type, useScope(value, scope))
    }

    companion object {
        /**
         * A function that applies a scope to an [InterpreterValue]
         *
         * @param v the value to apply the scope to
         * @param scope the scope to use
         * @param <V> the type of [InterpreterValue] that is given as argument
         * @return the given [InterpreterValue], but it now uses the given [Scope]
         *
         */
        @Suppress("UNCHECKED_CAST")
        private fun <V : InterpreterValue?> useScope(v: V?, scope: Scope): V? {
            if (v is Variable) return (v as Variable).withScope(scope) as V
            if (v is VariableList) return (v as VariableList).withScope(scope) as V
            if (v is Function) return (v as Function).withScope(scope) as V
            return if (v is ClassValue) (v as ClassValue).withScope(
                scope
            ) as V else v
        }

        /**
         * This function just creates a [Variable]
         *
         * @param name the name of the variable
         * @param type the type to convert
         * @return the created variable
         *
         */
        fun create(
            name: String,
            type: ShakeVariableType,
            access: ShakeAccessDescriber,
            finalVariable: Boolean,
            value: InterpreterValue?
        ): Variable {
            return when (type.type) {
                ShakeVariableType.Type.BYTE, ShakeVariableType.Type.SHORT, ShakeVariableType.Type.INTEGER, ShakeVariableType.Type.LONG ->
                    Variable(
                        name, access, IntegerValue::class, value?.to(
                            IntegerValue::class
                        ), finalVariable
                    )

                ShakeVariableType.Type.FLOAT, ShakeVariableType.Type.DOUBLE -> Variable(
                    name, access, DoubleValue::class, value?.to(
                        DoubleValue::class
                    ), finalVariable
                )

                ShakeVariableType.Type.BOOLEAN -> Variable(
                    name, access, BooleanValue::class, value?.to(
                        BooleanValue::class
                    ), finalVariable
                )

                ShakeVariableType.Type.OBJECT -> Variable(
                    name, access, ObjectValue::class, value?.to(
                        ObjectValue::class
                    ), finalVariable
                ) // TODO object-subtypes
                ShakeVariableType.Type.DYNAMIC -> Variable(
                    name, access, InterpreterValue::class, value?.to(
                        InterpreterValue::class
                    ), finalVariable
                )

                ShakeVariableType.Type.CHAR -> Variable(
                    name, access, CharacterValue::class, value?.to(
                        CharacterValue::class
                    ), finalVariable
                )

                ShakeVariableType.Type.ARRAY -> throw Error("Not implemented yet")
                else -> throw Error("Wrong input: ${type.type}")
            }
        }

        /**
         * This function just creates a [Variable]
         *
         * @param name the name of the variable
         * @param type the type to convert
         * @return the created variable
         *
         */
        @JvmStatic
        @JvmOverloads
        fun create(
            name: String,
            type: ShakeVariableType,
            finalVariable: Boolean,
            value: InterpreterValue? = null
        ): Variable = create(name, type, ShakeAccessDescriber.PACKAGE, finalVariable, value)

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun finalOf(name: String, v: InterpreterValue): Variable =
            Variable(name, ShakeAccessDescriber.PUBLIC, v::class, v)
    }
}