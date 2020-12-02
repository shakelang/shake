package com.github.nsc.de.compiler.interpreter.values;

/**
 * null / undefined variable {@link InterpreterValue} for variables
 *
 * @author Nicolas Schmidt
 */
public enum NullValue implements InterpreterValue {



    // *******************************
    // Values

    /**
     * NULL (null / undefined)
     */
    NULL();

    /**
     * This function gets executed when the operator '==' is used on the value
     *
     * @param v The value that should be the same
     * @return The Calculation-Result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue equals(InterpreterValue v) {
        // If the given value to compare is equal to null than return true
        // if not return false
        if(v instanceof NullValue) return BooleanValue.TRUE;
        return BooleanValue.FALSE;
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link NullValue} it just always returns "null"
     *
     * @return "null"
     *
     * @author Nicolas Schmidt
     */
    @Override
    public String getName() {
        // just return "null"
        return "null";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link NullValue}
     * For {@link NullValue} it just always returns "null"
     *
     * @return "null"
     *
     * @author Nicolas Schmidt
     */
    @Override
    public String toString() {
        // just return "null"
        return "null";
    }
}
