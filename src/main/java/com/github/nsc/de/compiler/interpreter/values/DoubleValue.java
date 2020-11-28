package com.github.nsc.de.compiler.interpreter.values;

public class DoubleValue implements InterpreterValue {

    private final double value;

    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public InterpreterValue add(InterpreterValue v) {
        if(v instanceof IntegerValue) return new DoubleValue(getValue() + ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() + ((DoubleValue) v).getValue());
        throw new Error("Operator '+' is not defined for type double and " + v.getName());
    }

    @Override
    public InterpreterValue sub(InterpreterValue v) {
        if(v instanceof IntegerValue) return new DoubleValue(getValue() - ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() - ((DoubleValue) v).getValue());
        throw new Error("Operator '-' is not defined for type double and " + v.getName());
    }

    @Override
    public InterpreterValue mul(InterpreterValue v) {
        if(v instanceof IntegerValue) return new DoubleValue(getValue() * ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() * ((DoubleValue) v).getValue());
        throw new Error("Operator '*' is not defined for type double and " + v.getName());
    }

    @Override
    public InterpreterValue div(InterpreterValue v) {
        if(v instanceof IntegerValue) return new DoubleValue(getValue() / ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() / ((DoubleValue) v).getValue());
        throw new Error("Operator '/' is not defined for type double and " + v.getName());
    }

    @Override
    public InterpreterValue mod(InterpreterValue v) {
        if(v instanceof IntegerValue) return new DoubleValue(getValue() % ((IntegerValue) v).getValue());
        if(v instanceof DoubleValue) return new DoubleValue(getValue() % ((DoubleValue) v).getValue());
        throw new Error("Operator '%' is not defined for type double and " + v.getName());
    }

    @Override
    public InterpreterValue pow(InterpreterValue v) {
        if(v instanceof IntegerValue) return new DoubleValue(Math.pow(getValue(), ((IntegerValue) v).getValue()));
        if(v instanceof DoubleValue) return new DoubleValue(Math.pow(getValue(), ((DoubleValue) v).getValue()));
        throw new Error("Operator '**' is not defined for type double and " + v.getName());
    }

    @Override
    public BooleanValue equals(InterpreterValue v) {
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
        return "double";
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}
