package com.shakelang.shake.interpreter.values

class StringValue(val value: String) : InterpreterValue {

    override fun add(v: InterpreterValue): StringValue = StringValue(value + v.toJava())

    /**
     * Get the java-representation of the [InterpreterValue]
     *
     * @return the java-representation of the [InterpreterValue]
     */
    override fun toJava(): String = value

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     */
    override val name: String get() = "string"

    override fun toString(): String = "\"$value\""
}