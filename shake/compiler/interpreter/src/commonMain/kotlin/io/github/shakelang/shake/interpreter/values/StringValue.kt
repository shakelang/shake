package io.github.shakelang.shake.interpreter.values

class StringValue(val value: String) : InterpreterValue {

    override fun add(v: InterpreterValue): StringValue = StringValue(value + v.toJava())

    /**
     * Get the java-representation of the [InterpreterValue]
     *
     * @return the java-representation of the [InterpreterValue]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toJava(): String = value

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     *
     * @return the name of the [InterpreterValue]
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override val name: String get() = "string"

    override fun toString(): String = "\"$value\""
}