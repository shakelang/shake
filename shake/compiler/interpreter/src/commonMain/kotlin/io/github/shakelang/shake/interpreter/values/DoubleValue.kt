package io.github.shakelang.shake.interpreter.values

import io.github.shakelang.shake.interpreter.UnformattedInterpreterError
import io.github.shakelang.shake.parser.node.ShakeCastNode.CastTarget
import kotlin.math.pow
import kotlin.reflect.KClass

/**
 * [InterpreterValue]s for doubles
 */
class DoubleValue

/**
 * Constructor for [DoubleValue]
 *
 * @param value the value of the [DoubleValue]
 */(
    
    /**
     * The value of the [DoubleValue]
     *
     * @see DoubleValue.value
     */
    val value: Double
    
) : InterpreterValue {
    
    
    // *******************************
    // implementations for extended InterpreterValue
    // >> number-operations
    
    
    /**
     * This function will be executed when the operator '+' is used on the double
     *
     * @param v The value to add to this value
     * @return The calculation-result
     */
    override fun add(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the addition-result
        if (v is IntegerValue) return DoubleValue(value + v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the addition-result
        if (v is DoubleValue) return DoubleValue(value + v.value)

        // If the given value is a CharacterValue create a new DoubleValue from
        // the addition-result
        if (v is CharacterValue) return DoubleValue(value + v.value.code)

        // If the given value is a StringValue create a String concatenation
        if (v is StringValue) return StringValue(this.value.toString() + v.value)
        throw UnformattedInterpreterError("Operator '+' is not defined for type double and " + v.name)
    }

    /**
     * This function will be executed when the operator '-' is used on the double
     *
     * @param v The value to sub from this value
     * @return The calculation-result
     */
    override fun sub(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the subtraction-result
        if (v is IntegerValue) return DoubleValue(value - v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the subtraction-result
        if (v is DoubleValue) return DoubleValue(value - v.value)

        // If the given value is a CharacterValue create a new DoubleValue from
        // the subtraction-result
        if (v is CharacterValue) return DoubleValue(value - v.value.code)
        throw UnformattedInterpreterError("Operator '-' is not defined for type double and " + v.name)
    }

    /**
     * This function will be executed when the operator '*' is used on the double
     *
     * @param v The value to multiply with this value
     * @return The calculation-result
     */
    override fun mul(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the multiplication-result
        if (v is IntegerValue) return DoubleValue(value * v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the multiplication-result
        if (v is DoubleValue) return DoubleValue(value * v.value)

        // If the given value is a CharacterValue create a new DoubleValue from
        // the multiplication-result
        if (v is CharacterValue) return DoubleValue(value * v.value.code)
        throw UnformattedInterpreterError("Operator '*' is not defined for type double and " + v.name)
    }

    /**
     * This function will be executed when the operator '/' is used on the double
     *
     * @param v The divisor-value
     * @return The calculation-result
     */
    override fun div(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the division-result
        if (v is IntegerValue) return DoubleValue(value / v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the division-result
        if (v is DoubleValue) return DoubleValue(value / v.value)

        // If the given value is a CharacterValue create a new DoubleValue from
        // the division-result
        if (v is CharacterValue) return DoubleValue(value / v.value.code)
        throw UnformattedInterpreterError("Operator '/' is not defined for type double and " + v.name)
    }

    /**
     * This function will be executed when the operator '%' is used on the double
     *
     * @param v The divisor-value
     * @return The calculation-result
     */
    override fun mod(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the modulo-result
        if (v is IntegerValue) return DoubleValue(value % v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the modulo-result
        if (v is DoubleValue) return DoubleValue(value % v.value)

        // If the given value is a CharacterValue create a new DoubleValue from
        // the modulo-result
        if (v is CharacterValue) return DoubleValue(value % v.value.code)
        throw UnformattedInterpreterError("Operator '%' is not defined for type double and " + v.name)
        
    }

    /**
     * This function will be executed when the operator '**' is used on the double
     *
     * @param v The exponent value
     * @return The calculation-result
     */
    override fun pow(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the power-result
        if (v is IntegerValue) return DoubleValue(value.pow(v.value.toDouble()))

        // If the given value is a DoubleValue create a new DoubleValue from
        // the power-result
        if (v is DoubleValue) return DoubleValue(value.pow(v.value))

        // If the given value is a CharacterValue create a new DoubleValue from
        // the pow-result
        if (v is CharacterValue) return DoubleValue(value.pow(v.value.code))
        throw UnformattedInterpreterError("Operator '**' is not defined for type double and " + v.name)
        
    }
    
    
    // *******************************
    // implementations for extended InterpreterValue
    // >> comparison
    
    /**
     * This function will be executed when the operator '==' is used on the double
     *
     * @param v The value that should be the same
     * @return The calculation-result
     */
    override fun equals(v: InterpreterValue): BooleanValue {

        // If the given value is a IntegerValue then check if the value is equal
        // to the own value and return a BooleanValue
        if (v is IntegerValue) return BooleanValue.from(value == v.value.toDouble())

        // If the given value is a DoubleValue then check if the value is equal
        // to the own value and return a BooleanValue
        if (v is DoubleValue) return BooleanValue.from(value == v.value)
        throw UnformattedInterpreterError("Operator '==' is not defined for type integer and " + v.name)
        
    }

    /**
     * This function will be executed when the operator '$gt;=' is used on the double
     *
     * @param v The value that should be smaller
     * @return The calculation-result
     */
    override fun biggerEquals(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if (v is IntegerValue) return BooleanValue.from(value >= v.value)

        // If the given value is a DoubleValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if (v is DoubleValue) return BooleanValue.from(value >= v.value)
        throw UnformattedInterpreterError("Operator '>=' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the doubles
     *
     * @param v The value that should be bigger
     * @return The calculation-result
     */
    override fun smallerEquals(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if (v is IntegerValue) return BooleanValue.from(value <= v.value)

        // If the given value is a DoubleValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if (v is DoubleValue) return BooleanValue.from(value <= v.value)
        throw UnformattedInterpreterError("Operator '<=' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&gt;' is used on the double
     *
     * @param v The value that should be smaller or equal
     * @return The calculation-result
     */
    override fun bigger(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if (v is IntegerValue) return BooleanValue.from(value > v.value)

        // If the given value is a DoubleValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if (v is DoubleValue) return BooleanValue.from(value > v.value)
        throw UnformattedInterpreterError("Operator '>' is not defined for type integer and " + v.name)
    }

    /**
     * This function will be executed when the operator '&lt;' is used on the double
     *
     * @param v The value that should be bigger or equal
     * @return The calculation-result
     */
    override fun smaller(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if (v is IntegerValue) return BooleanValue.from(value < v.value)

        // If the given value is a DoubleValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if (v is DoubleValue) return BooleanValue.from(value < v.value)
        throw UnformattedInterpreterError("Operator '<' is not defined for type integer and " + v.name)
    }


    // ****************************
    // implementations for extended InterpreterValue
    // >> create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the [DoubleValue]
     *
     * @return the java-representation of the [DoubleValue]
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
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : InterpreterValue> to(type: KClass<T>): T {
        return if (type.isInstance(this)) this as T else super.to(type)
    }

    /**
     * Casts this value to another value
     *
     * @param type the type to cast to
     * @param <T> the type to cast to
     * @return the converted [InterpreterValue]
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : InterpreterValue> castTo(type: CastTarget): T {
        if (type == CastTarget.BYTE) return IntegerValue(this.value.toInt()) as T
        if (type == CastTarget.SHORT) return IntegerValue(this.value.toInt()) as T
        if (type == CastTarget.INTEGER) return IntegerValue(this.value.toInt()) as T
        if (type == CastTarget.LONG) return IntegerValue(this.value.toInt()) as T
        if (type == CastTarget.FLOAT) return DoubleValue(this.value) as T
        if (type == CastTarget.DOUBLE) return DoubleValue(this.value) as T
        if (type == CastTarget.CHAR) return CharacterValue(this.value.toInt().toChar()) as T
        if (type == CastTarget.BOOLEAN) return BooleanValue.from(this) as T
        return if (type == CastTarget.STRING) StringValue(this.value.toString()) as T
            else super.castTo(type)
    }


    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [DoubleValue] it just always returns "double"
     *
     * @return "double"
     */
    override val name: String get() = "double"


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [IntegerValue]
     *
     * @return the string representation of the [IntegerValue]
     */
    override fun toString(): String = this.value.toString()
}