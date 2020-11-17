package com.github.nsc.de.compiler.interpreter.values;

public class BooleanValue implements InterpreterValue {

    public static final BooleanValue TRUE = new BooleanValue(true);
    public static final BooleanValue FALSE = new BooleanValue(false);

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
        throw new Error("Could not create boolean from " + v.getName());
    }


    // *******************************


    private final boolean value;

    private BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public InterpreterValue add(InterpreterValue v) {
        throw new Error("Operator '+' is not defined for type boolean");
    }

    @Override
    public InterpreterValue sub(InterpreterValue v) {
        throw new Error("Operator '-' is not defined for type boolean");
    }

    @Override
    public InterpreterValue mul(InterpreterValue v) {
        throw new Error("Operator '*' is not defined for type boolean");
    }

    @Override
    public InterpreterValue div(InterpreterValue v) {
        throw new Error("Operator '/' is not defined for type boolean");
    }

    @Override
    public InterpreterValue pow(InterpreterValue v) {
        throw new Error("Operator '**' is not defined for type boolean");
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
    public InterpreterValue equals_equals(InterpreterValue v) {
        if(v instanceof BooleanValue) {
            return BooleanValue.from(this.value == ((BooleanValue) v).value);
        }
        throw new Error("Operator '==' is not defined for type boolean and " + v.getName());
    }

    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {
        throw new Error("Operator '>=' is not defined for type boolean");
    }

    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {
        throw new Error("Operator '<=' is not defined for type boolean");
    }

    @Override
    public InterpreterValue bigger(InterpreterValue v) {
        throw new Error("Operator '>' is not defined for type boolean");
    }

    @Override
    public InterpreterValue smaller(InterpreterValue v) {
        throw new Error("Operator '<' is not defined for type boolean");
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
