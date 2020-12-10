package com.github.nsc.de.shake.interpreter.values;

/**
 * {@link InterpreterValue}s for integers
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 *
 * @see IntegerValue#ONE
 */
public class IntegerValue implements InterpreterValue {



    // *******************************
    // statics

    /**
     * As one is often needed as number (for incr and decr) we have a field
     * for it here that is used to save resources.
     */
    public static final IntegerValue ONE = new IntegerValue(1);



    // *******************************
    // integer functionality

    /**
     * The value of the {@link IntegerValue}
     *
     * @see IntegerValue#getValue() IntegerValue#getValue() - the getter for the {@link IntegerValue}
     */
    private final int value;

    /**
     * Constructor for {@link IntegerValue}
     *
     * @param value the value of the {@link IntegerValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public IntegerValue(int value) {
        // Apply the value to the IntegerValue
        this.value = value;
    }

    /**
     * Returns the {@link #value} of the {@link IntegerValue}
     *
     * @return the {@link #value} of the {@link IntegerValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int getValue() {
        // just return the value
        return value;
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> number-operations

    /**
     * This function will be executed when the operator '+' is used on the integer
     *
     * @param v The value to add to this value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue add(InterpreterValue v) {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the addition-result
        if(v instanceof IntegerValue) return new IntegerValue(getValue() + ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the addition-result (because when you add an integer and a double
        // the result is a double)
        if(v instanceof DoubleValue) return new DoubleValue(getValue() + ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '+' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '-' is used on the integer
     *
     * @param v The value to sub from this value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue sub(InterpreterValue v) {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the subtraction-result
        if(v instanceof IntegerValue) return new IntegerValue(getValue() - ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the subtraction-result (because when you add an integer and a double
        // the result is a double)
        if(v instanceof DoubleValue) return new DoubleValue(getValue() - ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '-' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '*' is used on the integer
     *
     * @param v The value to multiply with this value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue mul(InterpreterValue v) {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the multiplication-result
        if(v instanceof IntegerValue) return new IntegerValue(getValue() * ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the multiplication-result (because when you add an integer and a double
        // the result is a double)
        if(v instanceof DoubleValue) return new DoubleValue(getValue() * ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '*' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '/' is used on the integer
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue div(InterpreterValue v) {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the division-result
        if(v instanceof IntegerValue) return new IntegerValue(getValue() / ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the division-result (because when you add an integer and a double
        // the result is a double)
        if(v instanceof DoubleValue) return new DoubleValue(getValue() / ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '/' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '%' is used on the integer
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue mod(InterpreterValue v) {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the modulo-result
        if(v instanceof IntegerValue) return new IntegerValue(getValue() % ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the modulo-result (because when you add an integer and a double
        // the result is a double)
        if(v instanceof DoubleValue) return new DoubleValue(getValue() % ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '%' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '**' is used on the integer
     *
     * @param v The exponent value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue pow(InterpreterValue v) {

        // If the given value is a IntegerValue create a new IntegerValue from
        // the power-result
        if(v instanceof IntegerValue) return new IntegerValue((int) Math.pow(getValue(), ((IntegerValue) v).getValue()));

        // If the given value is a DoubleValue create a new DoubleValue from
        // the power-result (because when you add an integer and a double
        // the result is a double)
        if(v instanceof DoubleValue) return new DoubleValue(Math.pow(getValue(), ((DoubleValue) v).getValue()));

        // In other case just throw an error
        throw new Error("Operator '**' is not defined for type integer and " + v.getName());

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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public BooleanValue equals(InterpreterValue v) {

        // If the given value is a IntegerValue then check if the value is equal
        // to the own value and return a BooleanValue
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() == ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is equal
        // to the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() == ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '==' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&gt;=' is used on the integer
     *
     * @param v The value that should be smaller
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {

        // If the given value is a IntegerValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() >= ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() >= ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '>=' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the integer
     *
     * @param v The value that should be bigger
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {

        // If the given value is a IntegerValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() <= ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() <= ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '<=' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&gt;' is used on the integer
     *
     * @param v The value that should be smaller or equal
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue bigger(InterpreterValue v) {

        // If the given value is a IntegerValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() > ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() > ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '>' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&lt;' is used on the integer
     *
     * @param v The value that should be bigger or equal
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue smaller(InterpreterValue v) {

        // If the given value is a IntegerValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() < ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() < ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '<' is not defined for type integer and " + v.getName());

    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> get-name

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link IntegerValue} it just always returns "integer"
     *
     * @return "integer"
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        // just return "integer"
        return "integer";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link IntegerValue}
     *
     * @return the string representation of the {@link IntegerValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // just return the string representation of the value
        return String.valueOf(this.getValue());
    }
}
