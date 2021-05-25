package com.github.nsc.de.shake.interpreter.values

import com.github.nsc.de.shake.interpreter.Scope
import com.github.nsc.de.shake.interpreter.values.BooleanValue.Companion.from
import com.github.nsc.de.shake.interpreter.UnformattedInterpreterError
import com.github.nsc.de.shake.interpreter.Variable
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode
import com.github.nsc.de.shake.parser.node.CastNode.CastTarget

/**
 * A Value for the Interpreter, any type of value will implement this interface
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface InterpreterValue {


    // ****************************
    // number-operators

    /**
     * This function will be executed when the operator '+' is used on the value
     *
     * @param v The Value to add to this value
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun rem(v: InterpreterValue): InterpreterValue = this.mod(v)

    /**
     * This function will be executed when the operator '**' is used on the value
     *
     * @param v The exponent value
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    operator fun invoke(node: FunctionCallNode, scope: Scope): InterpreterValue {
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun newInstance(node: ClassConstructionNode, scope: Scope): InterpreterValue {
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : InterpreterValue> to(type: Class<T>): T {
        if (type.isInstance(this)) return this as T
        throw UnformattedInterpreterError("Can't convert " + name + " to type " + type.name)
    }

    /**
     * Casts this value to another value
     *
     * @param type the type to cast to
     * @param <T> the type to cast to
     * @return the converted [InterpreterValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val name: String

    companion object {
        fun of(value: Any?): InterpreterValue {
            if (value == null) return NullValue.NULL
            if (value is Byte) return IntegerValue(value.toInt())
            if (value is Short) return IntegerValue(value.toByte().toInt())
            if (value is Int) return IntegerValue((value as Int?)!!)
            if (value is Long) return IntegerValue(value.toInt())
            if (value is Float) return DoubleValue(value.toDouble())
            if (value is Double) return DoubleValue((value as Double?)!!)
            if (value is Boolean) return from((value as Boolean?)!!)
            if (value is Char) return CharacterValue((value as Char?)!!)
            if (value is Class<*>) return Java.JavaClass((value as Class<*>?)!!)
            return if (value is String) StringValue((value as String?)!!) else Java.JavaValue<Any?>(
                value.javaClass,
                value
            )
        }
    }
}