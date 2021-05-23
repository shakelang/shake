package com.github.nsc.de.shake.interpreter.values;

import com.github.nsc.de.shake.parser.node.CastNode;

public class CharacterValue implements InterpreterValue {

    private final char value;

    public CharacterValue(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

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
        throw new Error("Operator '+' is not defined for type char and " + v.getName());
    }

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
        throw new Error("Operator -' is not defined for type char and " + v.getName());
    }

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
        throw new Error("Operator '*' is not defined for type char and " + v.getName());
    }

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
        throw new Error("Operator '/' is not defined for type char and " + v.getName());
    }

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
        throw new Error("Operator '%' is not defined for type char and " + v.getName());
    }

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
        // the power-result
        if(v instanceof CharacterValue) return new IntegerValue((int) Math.pow(getValue(), ((CharacterValue) v).getValue()));

        // In other case just throw an error
        throw new Error("Operator '**' is not defined for type char and " + v.getName());
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
        if(type == CastNode.CastTarget.INTEGER) return (T) new IntegerValue(this.getValue());
        if(type == CastNode.CastTarget.LONG) return (T) new IntegerValue(this.getValue());
        if(type == CastNode.CastTarget.FLOAT) return (T) new DoubleValue((float) this.getValue());
        if(type == CastNode.CastTarget.DOUBLE) return (T) new DoubleValue(this.getValue());
        if(type == CastNode.CastTarget.CHAR) return (T) this;
        if(type == CastNode.CastTarget.BOOLEAN) return (T) BooleanValue.from(this);
        if(type == CastNode.CastTarget.STRING) return (T) new StringValue(String.valueOf(this.getValue()));
        return InterpreterValue.super.castTo(type);
    }

    /**
     * Get the java-representation of the {@link InterpreterValue}
     *
     * @return the java-representation of the {@link InterpreterValue}
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public Character toJava() {
        return value;
    }

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     *
     * @return the name of the {@link InterpreterValue}
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        return "char";
    }

    @Override
    public String toString() {
        return String.format("'%s'", value);
    }
}
