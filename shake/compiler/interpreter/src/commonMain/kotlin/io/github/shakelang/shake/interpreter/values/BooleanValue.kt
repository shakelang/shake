package io.github.shakelang.shake.interpreter.values

import io.github.shakelang.shake.interpreter.UnformattedInterpreterError
import io.github.shakelang.shake.parser.node.CastNode.CastTarget
import kotlin.jvm.JvmField

/**
 * [InterpreterValue]s for booleans ([BooleanValue.TRUE] &amp; [BooleanValue.FALSE]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
class BooleanValue

/**
 * Constructor for [BooleanValue]
 *
 * @param value the value of the boolean
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
(

    /**
     * The value of the [BooleanValue]
     */
    val value: Boolean

) : InterpreterValue {


    // *******************************
    // implementations for extended InterpreterValue

    /**
     * This function will be executed when the operator '||' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun or(v: InterpreterValue): InterpreterValue {
        // if the given value is a BooleanValue check if at least one of the values is true and return a BooleanValue again
        // if not just throw an error
        if (v is BooleanValue) return from(this.value || v.value)
        throw UnformattedInterpreterError("Operator '||' is not defined for type boolean and " + v.name)
    }

    /**
     * This function will be executed when the operator '^' is used on the value
     *
     * @param v The other value for the or operator
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun xor(v: InterpreterValue): InterpreterValue {
        // if the given value is a BooleanValue check if at least one of the values is true and return a BooleanValue
        // again if not just throw an error
        if (v is BooleanValue) return from(this.value xor v.value)
        throw UnformattedInterpreterError("Operator '^' is not defined for type boolean and " + v.name)
    }

    /**
     * This function will be executed when the operator '&amp;&amp;' is used on the value
     *
     * @param v The other value for the and operator
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun and(v: InterpreterValue): InterpreterValue {
        // if the given value is a BooleanValue check if the values are both true and return a BooleanValue again
        // if not just throw an error
        if (v is BooleanValue) return from(this.value && v.value)
        throw UnformattedInterpreterError("Operator '&&' is not defined for type boolean and " + v.name)
    }

    /**
     * This function will be executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun equals(v: InterpreterValue): InterpreterValue {
        // if the given value is a BooleanValue check if the values are the same and return a BooleanValue again
        // if not just throw an error
        if (v is BooleanValue) return from(this.value == v.value)
        throw UnformattedInterpreterError("Operator '==' is not defined for type boolean and " + v.name)
    }


    // ****************************
    // implementations for extended InterpreterValue
    // >> create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the [BooleanValue]
     *
     * @return the java-representation of the [BooleanValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toJava(): Any {
        return value
    }


    // ****************************
    // implementations for extended InterpreterValue
    // converting & casting

    /**
     * Casts this value to another value
     *
     * @param type the type to cast to
     * @param <T> the type to cast to
     * @return the converted [InterpreterValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : InterpreterValue> castTo(type: CastTarget): T {
        if (type == CastTarget.BYTE) return IntegerValue(if (this.value) 1 else 0) as T
        if (type == CastTarget.SHORT) return IntegerValue(if (this.value) 1 else 0) as T
        if (type == CastTarget.INTEGER) return IntegerValue(if (this.value) 1 else 0) as T
        if (type == CastTarget.LONG) return IntegerValue(if (this.value) 1 else 0) as T
        if (type == CastTarget.FLOAT) return DoubleValue(if (this.value) 1.0 else 0.0) as T
        if (type == CastTarget.DOUBLE) return DoubleValue(if (this.value) 1.0 else 0.0) as T
        if (type == CastTarget.BOOLEAN) return this as T
        return if (type == CastTarget.STRING) StringValue(this.value.toString()) as T else super.castTo(type)
    }

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String get() = "boolean"


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [BooleanValue]
     *
     * @return the string representation of the [BooleanValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String = this.value.toString()


    companion object {

        /**
         * TRUE (true value for boolean)
         */
        @JvmField
        val TRUE = BooleanValue(true)

        /**
         * FALSE (false value for boolean)
         */
        @JvmField
        val FALSE = BooleanValue(false)

        /**
         * Create a [BooleanValue] from a boolean
         *
         * @param b the boolean to create the [BooleanValue]  from
         * @return the [BooleanValue] representation of the boolean
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun from(b: Boolean): BooleanValue = if (b) TRUE else FALSE

        /**
         * Create a [BooleanValue] from a [InterpreterValue]
         *
         * @param v the [InterpreterValue] to create the [BooleanValue]  from
         * @return the [BooleanValue] representation of the boolean
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun from(v: InterpreterValue?): BooleanValue {
            // If v is a BooleanValue just return v
            if (v is BooleanValue) return v

            // if v is m IntegerValue return FALSE if v is <= 0
            if (v is IntegerValue) {
                return if (v.value <= 0) FALSE else TRUE
            }

            // if v is DoubleValue return FALSE if v is <= 0
            if (v is DoubleValue) {
                return if (v.value <= 0) FALSE else TRUE
            }

            // if v is a NullValue just return FALSE
            if (v is NullValue) return FALSE

            // if v is a Function just return TRUE
            if (v is Function || v is ClassValue || v is ObjectValue) return TRUE

            // if v is null return null
            if (v == null) return FALSE
            throw UnformattedInterpreterError("Could not create boolean from " + v.name)
        }
    }
}