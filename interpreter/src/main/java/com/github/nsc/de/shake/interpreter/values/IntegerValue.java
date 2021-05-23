package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.interpreter.UnformattedInterpreterError;
import com.github.nsc.de.shake.parser.node.CastNode;

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

        // If the given value is a CharacterValue create a new IntegerValue from
        // the addition-result
        if(v instanceof CharacterValue) return new IntegerValue(getValue() + ((CharacterValue) v).getValue());

        // If the given value is a StringValue create a String concatenation
        if(v instanceof StringValue) return new StringValue(this.value + ((StringValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '+' is not defined for type integer and " + v.getName());

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

        // If the given value is a CharacterValue create a new IntegerValue from
        // the subtraction-result
        if(v instanceof CharacterValue) return new IntegerValue(getValue() - ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '-' is not defined for type integer and " + v.getName());

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

        // If the given value is a CharacterValue create a new IntegerValue from
        // the multiplication-result
        if(v instanceof CharacterValue) return new IntegerValue(getValue() * ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '*' is not defined for type integer and " + v.getName());

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

        // If the given value is a CharacterValue create a new IntegerValue from
        // the division-result
        if(v instanceof CharacterValue) return new IntegerValue(getValue() / ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '/' is not defined for type integer and " + v.getName());

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

        // If the given value is a CharacterValue create a new IntegerValue from
        // the modulo-result
        if(v instanceof CharacterValue) return new IntegerValue(getValue() % ((CharacterValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '%' is not defined for type integer and " + v.getName());

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

        // If the given value is a CharacterValue create a new IntegerValue from
        // the pow-result
        if(v instanceof CharacterValue) return new IntegerValue((int) Math.pow(getValue(), ((CharacterValue) v).getValue()));

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '**' is not defined for type integer and " + v.getName());

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
        if(v instanceof IntegerValue) return BooleanValue.Companion.from(getValue() == ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is equal
        // to the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.Companion.from(getValue() == ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '==' is not defined for type integer and " + v.getName());

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
        if(v instanceof IntegerValue) return BooleanValue.Companion.from(getValue() >= ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is bigger than
        // or equal to the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.Companion.from(getValue() >= ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '>=' is not defined for type integer and " + v.getName());

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
        if(v instanceof IntegerValue) return BooleanValue.Companion.from(getValue() <= ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is smaller than
        // or equal to the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.Companion.from(getValue() <= ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '<=' is not defined for type integer and " + v.getName());

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
        if(v instanceof IntegerValue) return BooleanValue.Companion.from(getValue() > ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is bigger than
        // the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.Companion.from(getValue() > ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '>' is not defined for type integer and " + v.getName());

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
        if(v instanceof IntegerValue) return BooleanValue.Companion.from(getValue() < ((IntegerValue) v).getValue());

        // If the given value is a DoubleValue then check if the value is smaller than
        // the own value and return a BooleanValue
        if(v instanceof DoubleValue) return BooleanValue.Companion.from(getValue() < ((DoubleValue) v).getValue());

        // In other case just throw an error
        throw new UnformattedInterpreterError("Operator '<' is not defined for type integer and " + v.getName());

    }



    // ****************************
    // implementations for extended InterpreterValue
    // >> create a java-representation of the InterpreterValue

    /**
     * Get the java-representation of the {@link IntegerValue}
     *
     * @return the java-representation of the {@link IntegerValue}
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
        if(type == DoubleValue.class) return (T) new DoubleValue(this.getValue());
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
    @Override
    public <T extends InterpreterValue> T castTo(CastNode.CastTarget type) {
        if(type == CastNode.CastTarget.BYTE) return (T) new IntegerValue((byte) this.getValue());
        if(type == CastNode.CastTarget.SHORT) return (T) new IntegerValue((short) this.getValue());
        if(type == CastNode.CastTarget.INTEGER) return (T) this;
        if(type == CastNode.CastTarget.LONG) return (T) new IntegerValue(this.getValue());
        if(type == CastNode.CastTarget.FLOAT) return (T) new DoubleValue((float) this.getValue());
        if(type == CastNode.CastTarget.DOUBLE) return (T) new DoubleValue(this.getValue());
        if(type == CastNode.CastTarget.CHAR) return (T) new CharacterValue((char) this.getValue());
        if(type == CastNode.CastTarget.BOOLEAN) return (T) BooleanValue.Companion.from(this);
        if(type == CastNode.CastTarget.STRING) return (T) new StringValue(String.valueOf(this.getValue()));
        return InterpreterValue.super.castTo(type);
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
