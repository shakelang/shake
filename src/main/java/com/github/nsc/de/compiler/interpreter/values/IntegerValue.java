package com.github.nsc.de.compiler.interpreter.values;

public class IntegerValue implements InterpreterValue {

    public static final IntegerValue ONE = new IntegerValue(1);


    // *****************************


    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public InterpreterValue add(InterpreterValue v) {
        if(v instanceof IntegerValue) return new IntegerValue(getValue() + ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() + ((DoubleValue) v).getValue());
        throw new Error("Operator '+' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue sub(InterpreterValue v) {
        if(v instanceof IntegerValue) return new IntegerValue(getValue() - ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() - ((DoubleValue) v).getValue());
        throw new Error("Operator '-' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue mul(InterpreterValue v) {
        if(v instanceof IntegerValue) return new IntegerValue(getValue() * ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() * ((DoubleValue) v).getValue());
        throw new Error("Operator '*' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue div(InterpreterValue v) {
        if(v instanceof IntegerValue) return new IntegerValue(getValue() / ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() / ((DoubleValue) v).getValue());
        throw new Error("Operator '/' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue pow(InterpreterValue v) {
        if(v instanceof IntegerValue) return new IntegerValue((int) Math.pow(getValue(), ((IntegerValue) v).getValue()));
        if(v instanceof DoubleValue) return new DoubleValue(Math.pow(getValue(), ((DoubleValue) v).getValue()));
        throw new Error("Operator '**' is not defined for type integer and " + v.getName());
    }

    @Override
    public BooleanValue equals_equals(InterpreterValue v) {
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() == ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() == ((DoubleValue) v).getValue());
        throw new Error("Operator '==' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() >= ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() >= ((DoubleValue) v).getValue());
        throw new Error("Operator '>=' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() <= ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() <= ((DoubleValue) v).getValue());
        throw new Error("Operator '<=' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue bigger(InterpreterValue v) {
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() > ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() > ((DoubleValue) v).getValue());
        throw new Error("Operator '>' is not defined for type integer and " + v.getName());
    }

    @Override
    public InterpreterValue smaller(InterpreterValue v) {
        if(v instanceof IntegerValue) return BooleanValue.from(getValue() < ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return BooleanValue.from(getValue() < ((DoubleValue) v).getValue());
        throw new Error("Operator '<' is not defined for type integer and " + v.getName());
    }

    @Override
    public String getName() {
        return "integer";
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}
