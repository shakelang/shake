package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.UnformattedInterpreterError;

/**
 * {@link InterpreterValue}s for doubles
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public double getValue() {
        // just return the value
        return value;
    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> number-operations

    /**
     * This function will be executed when the operator '+' is used on the double
     *
     * @param v The value to add to this value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue add(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the addition-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() + ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the addition-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() + ((DoubleValue) v).getValue());

        // If the given value is a CharacterValue create a new DoubleValue from
        // the addition-result
        if(v instanceof CharacterValue) return new DoubleValue(getValue() + ((CharacterValue) v).getValue());

        // If the given value is a StringValue create a String concatenation
        if(v instanceof StringValue) return new StringValue(this.value + ((StringValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '+' is not defined for type double and " + v.getName());

    }

    /**
     * This function will be executed when the operator '-' is used on the double
     *
     * @param v The value to sub from this value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue sub(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the subtraction-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() - ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the subtraction-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() - ((DoubleValue) v).getValue());

        // If the given value is a CharacterValue create a new DoubleValue from
        // the subtraction-result
        if(v instanceof CharacterValue) return new DoubleValue(getValue() - ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '-' is not defined for type double and " + v.getName());

    }

    /**
     * This function will be executed when the operator '*' is used on the double
     *
     * @param v The value to multiply with this value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue mul(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the multiplication-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() * ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the multiplication-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() * ((DoubleValue) v).getValue());

        // If the given value is a CharacterValue create a new DoubleValue from
        // the multiplication-result
        if(v instanceof CharacterValue) return new DoubleValue(getValue() * ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '*' is not defined for type double and " + v.getName());

    }

    /**
     * This function will be executed when the operator '/' is used on the double
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue div(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the division-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() / ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the division-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() / ((DoubleValue) v).getValue());

        // If the given value is a CharacterValue create a new DoubleValue from
        // the division-result
        if(v instanceof CharacterValue) return new DoubleValue(getValue() / ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '/' is not defined for type double and " + v.getName());

    }

    /**
     * This function will be executed when the operator '%' is used on the double
     *
     * @param v The divisor-value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue mod(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the modulo-result
        if(v instanceof IntegerValue) return new DoubleValue(getValue() % ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue create a new DoubleValue from
        // the modulo-result
        if(v instanceof DoubleValue) return new DoubleValue(getValue() % ((DoubleValue) v).getValue());

        // If the given value is a CharacterValue create a new DoubleValue from
        // the modulo-result
        if(v instanceof CharacterValue) return new DoubleValue(getValue() % ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '%' is not defined for type double and " + v.getName());

    }

    /**
     * This function will be executed when the operator '**' is used on the double
     *
     * @param v The exponent value
     * @return The calculation-result
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public InterpreterValue pow(InterpreterValue v) {

        // If the given value is a IntegerValue create a new DoubleValue from
        // the power-result
        if(v instanceof IntegerValue) return new DoubleValue(Math.pow(getValue(), ((IntegerValue) v).getValue()));

        // If the given value is a DoubleValue create a new DoubleValue from
        // the power-result
        if(v instanceof DoubleValue) return new DoubleValue(Math.pow(getValue(), ((DoubleValue) v).getValue()));

        // If the given value is a CharacterValue create a new DoubleValue from
        // the pow-result
        if(v instanceof CharacterValue) return new DoubleValue(Math.pow(getValue(), ((CharacterValue) v).getValue()));

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '**' is not defined for type double and " + v.getName());

    }



    // *******************************
    // implementations for extended InterpreterValue
    // >> comparison

    /**
     * This function will be executed when the operator '==' is used on the double
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
        throw new UnformattedInterpreterError("Operator '==' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '$gt;=' is used on the double
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
        throw new UnformattedInterpreterError("Operator '>=' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&lt;=' is used on the doubles
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
        throw new UnformattedInterpreterError("Operator '<=' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&gt;' is used on the double
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
        throw new UnformattedInterpreterError("Operator '>' is not defined for type integer and " + v.getName());

    }

    /**
     * This function will be executed when the operator '&lt;' is used on the double
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
        throw new UnformattedInterpreterError("Operator '<' is not defined for type integer and " + v.getName());

    }



    // ****************************
    // implementations for extended InterpreterValue
    // >> create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the {@link DoubleValue}
     *
     * @return the java-representation of the {@link DoubleValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public Object toJava() {
        return getValue();
    }



    // ****************************
    // implementations for extended InterpreterValue
    // converting & casting

    /**
     * Converts this value to another value
     *
     * @param type the type to convert to
     * @param <T> the type to convert to
     * @return the converted {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @SuppressWarnings("unchecked")
    public <T extends InterpreterValue> T to(Class<T> type) {
        if(type.isInstance(this)) return (T) this;
        return InterpreterValue.super.to(type);
    }

    /**
     * Casts this value to another value
     *
     * @param type the type to cast to
     * @param <T> the type to cast to
     * @return the converted {@link InterpreterValue}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @SuppressWarnings("unchecked")
    public <T extends InterpreterValue> T castTo(Class<T> type) {
        if(type.isInstance(this)) return (T) this;
        if(type == IntegerValue.class) return (T) new IntegerValue((int) this.getValue());
        if(type == CharacterValue.class) return (T) new CharacterValue((char) this.getValue());
        if(type == BooleanValue.class) return (T) BooleanValue.from(this);
        return InterpreterValue.super.to(type);
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
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
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        // just return the string representation of the value
        return String.valueOf(this.getValue());
    }
}
