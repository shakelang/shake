package io.github.shakelang.shake.interpreter.values

import io.github.shakelang.shake.interpreter.Scope
import io.github.shakelang.shake.interpreter.UnformattedInterpreterError
import io.github.shakelang.shake.interpreter.Variable
import io.github.shakelang.shake.parser.node.ShakeCastNode.CastTarget
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

/**
 * A Value for the Interpreter, any type of value will implement this interface
 */
interface InterpreterValue {


    // ****************************
    // number-operators

    /**
     * This function will be executed when the operator '+' is used on the value
     *
     * @param v The Value to add to this value
     * @return The Calculation-Result
     */
    fun add(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '+' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '-' is used on the value
     *
     * @param v The Value to sub from this value
     * @return The Calculation-Result
     */
    fun sub(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '-' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '*' is used on the value
     *
     * @param v The Value to multiply with this value
     * @return The Calculation-Result
     */
    fun mul(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '*' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '/' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     */
    operator fun div(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '/' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '%' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     */
    fun mod(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '%' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '%' is used on the value
     *
     * @param v The divisor-value
     * @return The Calculation-Result
     */
    operator fun rem(v: InterpreterValue): InterpreterValue = this.mod(v)

    /**
     * This function will be executed when the operator '**' is used on the value
     *
     * @param v The exponent value
     * @return The Calculation-Result
     */
    fun pow(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '**' is not defined for type $name")
    }


    // ****************************
    // boolean-operators

    /**
     * This function will be executed when the operator '||' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     */
    fun or(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '||' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '^' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     */
    fun xor(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '^' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '&amp;&amp;' is used on the value
     *
     * @param v The other value for the and operator
     * @return The Calculation-Result
     */
    fun and(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '&&' is not defined for type $name")
    }


    // ****************************
    // comparison

    /**
     * This function will be executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     */
    fun equals(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '==' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '&gt;=' is used on the value
     *
     * @param v The value that should be smaller
     * @return The Calculation-Result
     */
    fun biggerEquals(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '>=' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the value
     *
     * @param v The value that should be bigger
     * @return The Calculation-Result
     */
    fun smallerEquals(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '<=' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '&gt;' is used on the value
     *
     * @param v The value that should be smaller or equal
     * @return The Calculation-Result
     */
    fun bigger(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '>' is not defined for type $name")
    }

    /**
     * This function will be executed when the operator '&lt;' is used on the value
     *
     * @param v The value that should be bigger or equal
     * @return The Calculation-Result
     */
    fun smaller(v: InterpreterValue): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Operator '<' is not defined for type $name")
    }


    // ****************************
    // Children

    /**
     * This function will be executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     */
    fun getChild(c: String): Variable? {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Can't get child values of type $name")
    }

    /**
     * This function will be executed when getting all child keys
     *
     * @return the keys of all children
     */
    val children: Array<String>
        get() {
            // Throw an UnformattedInterpreterError when the operator is not implemented
            // This function will be overridden by all InterpreterValues that do support this operation
            throw UnformattedInterpreterError("Can't get child values of type $name")
        }


    // ****************************
    // invoking

    /**
     * Invoke a value
     *
     * @param node the node that called the function
     * @param scope the scope the call was made in (to process the arguments)
     * @return the value of the function
     */
    operator fun invoke(node: ShakeFunctionCallNode, scope: Scope): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Can't invoke type $name")
    }

    /**
     * Create a new instance of a class
     *
     * @param node the node that created the instance
     * @param scope the scope the creation was made in (to process the arguments)
     * @return the created [InterpreterValue]
     */
    fun newInstance(node: ShakeClassConstructionNode, scope: Scope): InterpreterValue {
        // Throw an UnformattedInterpreterError when the operator is not implemented
        // This function will be overridden by all InterpreterValues that do support this operation
        throw UnformattedInterpreterError("Can't create a new instance of type $name")
    }


    // ****************************
    // converting & casting

    /**
     * Converts this value to another value
     *
     * @param type the type to convert to
     * @param <T> the type to convert to
     * @return the converted [InterpreterValue]
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : InterpreterValue> to(type: KClass<T>): T {
        if (type.isInstance(this)) return this as T
        throw UnformattedInterpreterError("Can't convert $name to type ${type}")
    }

    /**
     * Casts this value to another value
     *
     * @param type the type to cast to
     * @param <T> the type to cast to
     * @return the converted [InterpreterValue]
     */
    fun <T : InterpreterValue> castTo(type: CastTarget): T {
        // if(type.isInstance(this)) return (T) this;
        throw UnformattedInterpreterError("Can't convert $name to type $type")
    }


    // ****************************
    // create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the [InterpreterValue]
     *
     * @return the java-representation of the [InterpreterValue]
     */
    fun toJava(): Any? {
        return name
    }


    // ****************************
    // name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     */
    val name: String

    companion object {

        @JvmStatic
        fun of(value: Any?): InterpreterValue = createInterpreterValueOf(value)
    }
}

expect fun createInterpreterValueOf(value: Any?): InterpreterValue