package com.github.nsc.de.shake.interpreter.values

import com.github.nsc.de.shake.interpreter.UnformattedInterpreterError
import com.github.nsc.de.shake.interpreter.values.BooleanValue.Companion.from
import com.github.nsc.de.shake.parser.node.CastNode.CastTarget
import kotlin.math.pow


/**
 * [InterpreterValue]s for integers
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 *
 * @see IntegerValue.ONE
 */
class IntegerValue

/**
 * Constructor for [IntegerValue]
 *
 * @param value the value of the [IntegerValue]
 *
 * @author [Nicolas Schmidt &lt;@nsc -de&gt;](https://github.com/nsc-de)
 */
(

    /**
     * The value of the [IntegerValue]
     */
    val value: Int

) : InterpreterValue {


    // *******************************
    // implementations for extended InterpreterValue
    // >> number-operations

    /**
     * This function will be executed when the operator '+' is used on the integer
     *
     * @param v The value to add to this value
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun add(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the addition-result
        if (v is IntegerValue) return IntegerValue(value + v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the addition-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value + v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the addition-result
        if (v is CharacterValue) return IntegerValue(value + v.value.code)

        // If the given value is a StringValue create a String concatenation
        if (v is StringValue) return StringValue(this.value.toString() + v.value)
        throw UnformattedInterpreterError("Operator '+' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '-' is used on the integer
     *
     * @param v The value to sub from this value
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun sub(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the subtraction-result
        if (v is IntegerValue) return IntegerValue(value - v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the subtraction-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value - v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the subtraction-result
        if (v is CharacterValue) return IntegerValue(value - v.value.code)
        throw UnformattedInterpreterError("Operator '-' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '*' is used on the integer
     *
     * @param v The value to multiply with this value
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun mul(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the multiplication-result
        if (v is IntegerValue) return IntegerValue(value * v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the multiplication-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value * v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the multiplication-result
        if (v is CharacterValue) return IntegerValue(value * v.value.code)
        throw UnformattedInterpreterError("Operator '*' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '/' is used on the integer
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun div(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the division-result
        if (v is IntegerValue) return IntegerValue(value / v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the division-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value / v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the division-result
        if (v is CharacterValue) return IntegerValue(value / v.value.code)
        throw UnformattedInterpreterError("Operator '/' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '%' is used on the integer
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun mod(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the modulo-result
        if (v is IntegerValue) return IntegerValue(value % v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the modulo-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value % v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the modulo-result
        if (v is CharacterValue) return IntegerValue(value % v.value.code)
        throw UnformattedInterpreterError("Operator '%' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '**' is used on the integer
     *
     * @param v The exponent value
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun pow(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the power-result
        if (v is IntegerValue) return IntegerValue(
            value.toDouble().pow(v.value.toDouble()).toInt()
        )

        // If the given value is a DoubleValue create a new DoubleValue from
        // the power-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value.toDouble().pow(v.value))

        // If the given value is a CharacterValue create a new IntegerValue from
        // the pow-result
        if (v is CharacterValue) return IntegerValue(
            value.toDouble().pow(v.value.code.toDouble()).toInt()
        )
        throw UnformattedInterpreterError("Operator '**' is not defined for type integer and " + v.name)
    }


    // *******************************
    // implementations for extended InterpreterValue
    // >> comparison

    /**
     * This function will be executed when the operator '==' is used on the integer
     *
     * @param v The value that should be the same
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun equals(v: InterpreterValue): BooleanValue {

        // If the given value is a IntegerValue then check if the value is equal
        // to the own value and return a BooleanValue
        if (v is IntegerValue) return from(value == v.value)

        // If the given value is a DoubleValue then check if the value is equal
        // to the own value and return a BooleanValue
        if (v is DoubleValue) return from(value.toDouble() == v.value)
        throw UnformattedInterpreterError("Operator '==' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&gt;=' is used on the integer
     *
     * @param v The value that should be smaller
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun biggerEquals(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if (v is IntegerValue) return from(value >= v.value)

        // If the given value is a DoubleValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if (v is DoubleValue) return from(value >= v.value)
        throw UnformattedInterpreterError("Operator '>=' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the integer
     *
     * @param v The value that should be bigger
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun smallerEquals(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if (v is IntegerValue) return from(value <= v.value)

        // If the given value is a DoubleValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if (v is DoubleValue) return from(value <= v.value)
        throw UnformattedInterpreterError("Operator '<=' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&gt;' is used on the integer
     *
     * @param v The value that should be smaller or equal
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun bigger(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if (v is IntegerValue) return from(value > v.value)

        // If the given value is a DoubleValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if (v is DoubleValue) return from(value > v.value)
        throw UnformattedInterpreterError("Operator '>' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&lt;' is used on the integer
     *
     * @param v The value that should be bigger or equal
     * @return The calculation-result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun smaller(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if (v is IntegerValue) return from(value < v.value)

        // If the given value is a DoubleValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if (v is DoubleValue) return from(value < v.value)
        throw UnformattedInterpreterError("Operator '<' is not defined for type integer and " + v.name)
    }


    // ****************************
    // implementations for extended InterpreterValue
    // >> create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the [IntegerValue]
     *
     * @return the java-representation of the [IntegerValue]
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
     * Converts this value to another value
     *
     * @param type the type to convert to
     * @param <T> the type to convert to
     * @return the converted [InterpreterValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : InterpreterValue> to(type: Class<T>): T {
        if (type.isInstance(this)) return this as T
        return if (type == DoubleValue::class.java) DoubleValue(this.value.toDouble()) as T else super.to(type)
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
    @Suppress("UNCHECKED_CAST")
    override fun <T : InterpreterValue> castTo(type: CastTarget): T {
        if (type == CastTarget.BYTE) return IntegerValue(this.value) as T
        if (type == CastTarget.SHORT) return IntegerValue(this.value) as T
        if (type == CastTarget.INTEGER) return this as T
        if (type == CastTarget.LONG) return IntegerValue(this.value) as T
        if (type == CastTarget.FLOAT) return DoubleValue(this.value.toDouble()) as T
        if (type == CastTarget.DOUBLE) return DoubleValue(this.value.toDouble()) as T
        if (type == CastTarget.CHAR) return CharacterValue(this.value.toChar()) as T
        if (type == CastTarget.BOOLEAN) return from(this) as T
        return if (type == CastTarget.STRING) StringValue(this.value.toString()) as T else super.castTo(type)
    }


    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [IntegerValue] it just always returns "integer"
     *
     * @return "integer"
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String get() = "integer"


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [IntegerValue]
     *
     * @return the string representation of the [IntegerValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String = this.value.toString()


    companion object {


        // *******************************
        // statics

        /**
         * As one is often needed as number (for incr and decr) we have a field
         * for it here that is used to save resources.
         */
        @JvmField
        val ONE = IntegerValue(1)

    }
}