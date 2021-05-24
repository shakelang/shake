package com.github.nsc.de.shake.interpreter.values

/**
 * null / undefined variable [InterpreterValue] for variables
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
enum class NullValue : InterpreterValue {


    // *******************************
    // Values

    /**
     * NULL (null / undefined)
     */
    NULL;

    /**
     * This function will be executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun equals(v: InterpreterValue): InterpreterValue {
        // If the given value to compare is equal to null than return true
        // if not return false
        return if (v is NullValue) BooleanValue.TRUE else BooleanValue.FALSE
    }


    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of [InterpreterValue] (To identify the type of value)
     * For [NullValue] it just always returns "null"
     *
     * @return "null"
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun getName(): String {
        // just return "null"
        return "null"
    }


    // ****************************
    // implementations for extended InterpreterValue
    // >> create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the [NullValue]
     *
     * @return the java-representation of the [NullValue]
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toJava(): Any? = null


    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the [NullValue]
     * For [NullValue] it just always returns "null"
     *
     * @return "null"
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String = "null"
}