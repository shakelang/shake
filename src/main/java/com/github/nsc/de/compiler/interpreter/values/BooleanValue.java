package com.github.nsc.de.compiler.interpreter.values;

public enum BooleanValue implements InterpreterValue {

    TRUE(true), FALSE(false);

    // *******************************
    // statics

    public static BooleanValue from(boolean b) {
        if(b) return TRUE;
        else return FALSE;
    }

    public static BooleanValue from(InterpreterValue v) {
        if(v instanceof BooleanValue) return (BooleanValue) v;
        if(v instanceof IntegerValue) {
            if(((IntegerValue) v).getValue() == 0) return FALSE;
            return TRUE;
        }
        if(v instanceof DoubleValue) {
            if(((DoubleValue) v).getValue() == 0) return FALSE;
            return TRUE;
        }
        if(v instanceof NullValue) return FALSE;
        if(v instanceof Function) return TRUE;
        throw new Error("Could not create boolean from " + v.getName());
    }


    // *******************************
    // boolean functionality

    private final boolean value;

    BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public InterpreterValue or(InterpreterValue v) {
        if(v instanceof BooleanValue) {
            return BooleanValue.from(this.value || ((BooleanValue) v).value);
        }
        throw new Error("Operator '||' is not defined for type boolean and " + v.getName());
    }

    @Override
    public InterpreterValue and(InterpreterValue v) {
        if(v instanceof BooleanValue) {
            return BooleanValue.from(this.value && ((BooleanValue) v).value);
        }
        throw new Error("Operator '&&' is not defined for type boolean and " + v.getName());
    }

    @Override
    public InterpreterValue equals(InterpreterValue v) {
        if(v instanceof BooleanValue) {
            return BooleanValue.from(this.value == ((BooleanValue) v).value);
        }
        throw new Error("Operator '==' is not defined for type boolean and " + v.getName());
    }

    @Override
    public String getName() {
        return "boolean";
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
