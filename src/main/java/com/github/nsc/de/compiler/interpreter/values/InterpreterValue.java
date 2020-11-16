package com.github.nsc.de.compiler.interpreter.values;


public interface InterpreterValue {

    // number-operators
    InterpreterValue add(InterpreterValue v);
    InterpreterValue sub(InterpreterValue v);
    InterpreterValue mul(InterpreterValue v);
    InterpreterValue div(InterpreterValue v);
    InterpreterValue pow(InterpreterValue v);

    // boolean-operators
    InterpreterValue or(InterpreterValue v);
    InterpreterValue and(InterpreterValue v);

    // comparison
    InterpreterValue equals_equals(InterpreterValue v);
    InterpreterValue bigger_equals(InterpreterValue v);
    InterpreterValue smaller_equals(InterpreterValue v);
    InterpreterValue bigger(InterpreterValue v);
    InterpreterValue smaller(InterpreterValue v);

    String getName();

}
