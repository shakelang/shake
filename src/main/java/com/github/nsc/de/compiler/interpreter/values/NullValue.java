package com.github.nsc.de.compiler.interpreter.values;

public class NullValue implements InterpreterValue {

    public static final NullValue NULL = new NullValue();
    
    private NullValue() {}

    @Override
    public InterpreterValue add(InterpreterValue v) {
        throw new Error("Operator '+' is not defined for null");
    }

    @Override
    public InterpreterValue sub(InterpreterValue v) {
        throw new Error("Operator '-' is not defined for null");
    }

    @Override
    public InterpreterValue mul(InterpreterValue v) {
        throw new Error("Operator '*' is not defined for null");
    }

    @Override
    public InterpreterValue div(InterpreterValue v) {
        throw new Error("Operator '/' is not defined for null");
    }

    @Override
    public InterpreterValue pow(InterpreterValue v) {
        throw new Error("Operator '**' is not defined for null");
    }

    @Override
    public InterpreterValue or(InterpreterValue v) {
        throw new Error("Operator '||' is not defined for null");
    }

    @Override
    public InterpreterValue and(InterpreterValue v) {
        throw new Error("Operator '&&' is not defined for null");
    }

    @Override
    public InterpreterValue equals_equals(InterpreterValue v) {
        // TODO compare null with object
        if(v instanceof NullValue) return BooleanValue.TRUE;
        throw new Error("Operator '==' is not defined for null and " + v.getName());
    }

    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {
        throw new Error("Operator '>=' is not defined for null");
    }

    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {
        throw new Error("Operator '<=' is not defined for null");
    }

    @Override
    public InterpreterValue bigger(InterpreterValue v) {
        throw new Error("Operator '>' is not defined for null");
    }

    @Override
    public InterpreterValue smaller(InterpreterValue v) {
        throw new Error("Operator '<' is not defined for null");
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public String toString() {
        return "null";
    }
}
