package com.github.nsc.de.shake.interpreter.values

import com.github.nsc.de.shake.parser.node.CastNode.CastTarget
import java.lang.Error
import kotlin.math.pow

class CharacterValue(val value: Char) : InterpreterValue {
    override fun add(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the addition-result
        if (v is IntegerValue) return IntegerValue(
            value.code + v.value
        )

        // If the given value is a DoubleValue create a new DoubleValue from
        // the addition-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value.code + v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the addition-result
        if (v is CharacterValue) return IntegerValue((value + v.value.code).code)

        // If the given value is a StringValue create a String concatenation
        if (v is StringValue) return StringValue(
            value.toString() + v.value
        )
        throw Error("Operator '+' is not defined for type char and " + v.name)

    }

    override fun sub(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the subtraction-result
        if (v is IntegerValue) return IntegerValue(value.code - v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the subtraction-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value.code - v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the subtraction-result
        if (v is CharacterValue) return IntegerValue(value - v.value)
        throw Error("Operator -' is not defined for type char and ${v.name}")

    }

    override fun mul(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the multiplication-result
        if (v is IntegerValue) return IntegerValue(value.code * v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the multiplication-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value.code * v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the multiplication-result
        if (v is CharacterValue) return IntegerValue(value.code * v.value.code)
        throw Error("Operator '*' is not defined for type char and " + v.name)

    }

    override fun div(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the division-result
        if (v is IntegerValue) return IntegerValue(value.code / v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the division-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value.code / v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the division-result
        if (v is CharacterValue) return IntegerValue(value.code / v.value.code)
        throw Error("Operator '/' is not defined for type char and " + v.name)

    }

    override fun mod(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the modulo-result
        if (v is IntegerValue) return IntegerValue(value.code % v.value)

        // If the given value is a DoubleValue create a new DoubleValue from
        // the modulo-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(value.code % v.value)

        // If the given value is a CharacterValue create a new IntegerValue from
        // the modulo-result
        if (v is CharacterValue) return IntegerValue(value.code % v.value.code)
        throw Error("Operator '%' is not defined for type char and " + v.name)

    }

    override fun pow(v: InterpreterValue): InterpreterValue {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the power-result
        if (v is IntegerValue) return IntegerValue(
            value.code.toDouble().pow(v.value.toDouble()).toInt()
        )

        // If the given value is a DoubleValue create a new DoubleValue from
        // the power-result (because when you add an integer and a double
        // the result is a double)
        if (v is DoubleValue) return DoubleValue(
            value.code.toDouble().pow(v.value)
        )

        // If the given value is a CharacterValue create a new IntegerValue from
        // the power-result
        if (v is CharacterValue) return IntegerValue(
            value.code.toDouble().pow(v.value.code.toDouble()).toInt()
        )
        throw Error("Operator '**' is not defined for type char and " + v.name)

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
    @Suppress("unchecked")
    override fun <T : InterpreterValue?> castTo(type: CastTarget): T {
        if (type == CastTarget.BYTE) return IntegerValue(value.code) as T
        if (type == CastTarget.SHORT) return IntegerValue(value.code) as T
        if (type == CastTarget.INTEGER) return IntegerValue(value.code) as T
        if (type == CastTarget.LONG) return IntegerValue(value.code) as T
        if (type == CastTarget.FLOAT) return DoubleValue(value.code.toFloat().toDouble()) as T
        if (type == CastTarget.DOUBLE) return DoubleValue(value.code.toDouble()) as T
        if (type == CastTarget.CHAR) return this as T
        if (type == CastTarget.BOOLEAN) return BooleanValue.from(this) as T
        return if (type == CastTarget.STRING) StringValue(value.toString()) as T else super.castTo(type)
    }

    /**
     * Get the java-representation of the [InterpreterValue]
     *
     * @return the java-representation of the [InterpreterValue]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toJava(): Char {
        return value
    }

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun getName(): String {
        return "char"
    }

    override fun toString(): String {
        return String.format("'%s'", value)
    }
}