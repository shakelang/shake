package com.github.nsc.de.compiler.interpreter.values;

/**
 * {@link InterpreterValue}s for doubles
 *
 * @author Nicolas Schmidt
 */
public class DoubleValue implements InterpreterValue {



    // *******************************
    // double functionality

    /**
     * The value of the {@link DoubleValue}
     *
     * @see DoubleValue#getValue() DoubleValue#getValue() - the getter for the {@link DoubleValue}
     */
    private final double value;

    /**
     * Constructor for {@link DoubleValue}
     *
     * @param value the value of the {@link DoubleValue}
     *
     * @author Nicolas Schmidt
     */
    public DoubleValue(double value) {
        // Apply the value to the IntegerValue
        this.value = value;
    }

    /**
     * Returns the {@link #value} of the {@link DoubleValue}
     *
     * @return the {@link #value} of the {@link DoubleValue}
     *
     * @author Nicolas Schmidt
     */
    public double getValue() {
        // just return the value
        return value;
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> number-operations

    /**
     * This function gets executed when the operator '+' is used on the double
     *
     * @param v The value to add to this value
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue add(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the addition-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() + ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the addition-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() + ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '+' is not defined for type double and " + v.getName());

    }

    /**
     * This function gets executed when the operator '-' is used on the double
     *
     * @param v The value to sub from this value
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue sub(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the subtraction-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() - ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the subtraction-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() - ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '-' is not defined for type double and " + v.getName());

    }

    /**
     * This function gets executed when the operator '*' is used on the double
     *
     * @param v The value to multiply with this value
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue mul(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the multiplication-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() * ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the multiplication-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() * ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '*' is not defined for type double and " + v.getName());

    }

    /**
     * This function gets executed when the operator '/' is used on the double
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue div(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the division-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() / ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the division-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() / ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '/' is not defined for type double and " + v.getName());

    }

    /**
     * This function gets executed when the operator '%' is used on the double
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue mod(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the modulo-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() % ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the modulo-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() % ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new Error("Operator '%' is not defined for type double and " + v.getName());

    }

    /**
     * This function gets executed when the operator '**' is used on the double
     *
     * @param v The exponent value
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
     */
    @Override
    public InterpreterValue pow(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the power-result
        if(v instanceof IntegerValue) return new DoubleValue(Math.pow(getValue(), ((IntegerValue) v).getValue()));

        // If the given value is a DoubleValue create a new DoubleValue from
        // the power-result
        if(v instanceof DoubleValue) return new DoubleValue(Math.pow(getValue(), ((DoubleValue) v).getValue()));

        // In other case just throw an error
        throw new Error("Operator '**' is not defined for type double and " + v.getName());

    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> comparison

    /**
     * This function gets executed when the operator '==' is used on the double
     *
     * @param v The value that should be the same
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when the operator '$gt;=' is used on the double
     *
     * @param v The value that should be smaller
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when the operator '&lt;=' is used on the doubles
     *
     * @param v The value that should be bigger
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when the operator '&gt;' is used on the double
     *
     * @param v The value that should be smaller or equal
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
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
     * This function gets executed when the operator '&lt;' is used on the double
     *
     * @param v The value that should be bigger or equal
     * @return The calculation-result
     *
     * @author Nicolas Schmidt
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
     * For {@link DoubleValue} it just always returns "double"
     *
     * @return "double"
     *
     * @author Nicolas Schmidt
     */
    @Override
    public String getName() {
        // just return "double"
        return "double";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link IntegerValue}
     *
     * @return the string representation of the {@link IntegerValue}
     *
     * @author Nicolas Schmidt
     */
    @Override
    public String toString() {
        // just return the string representation of the value
        return String.valueOf(this.getValue());
    }
}
