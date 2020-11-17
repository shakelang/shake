package com.github.nsc.de.compiler.interpreter.values;

public class NullValue implements InterpreterValue {

    public static final NullValue NULL = new NullValue();
    
    private NullValue() {}

    @Override
    public InterpreterValue equals(InterpreterValue v) {
        // TODO compare null with object
        if(v instanceof NullValue) return BooleanValue.TRUE;
        throw new Error("Operator '==' is not defined for null and " + v.getName());
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
