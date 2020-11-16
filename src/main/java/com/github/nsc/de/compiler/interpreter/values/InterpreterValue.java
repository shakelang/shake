package com.github.nsc.de.compiler.interpreter.values;


public interface InterpreterValue {

    // number-operators
    default InterpreterValue add(InterpreterValue v) {
        throw new Error("Operator '+' is not defined for type " + getName());
    }

    default InterpreterValue sub(InterpreterValue v) {
        throw new Error("Operator '+' is not defined for type " + getName());
    }

    default InterpreterValue mul(InterpreterValue v) {
        throw new Error("Operator '*' is not defined for type " + getName());
    }

    default InterpreterValue div(InterpreterValue v) {
        throw new Error("Operator '/' is not defined for type " + getName());
    }

    default InterpreterValue pow(InterpreterValue v) {
        throw new Error("Operator '**' is not defined for type " + getName());
    }



    // boolean-operators
    default InterpreterValue or(InterpreterValue v) {
        throw new Error("Operator '||' is not defined for type " + getName());
    }

    default InterpreterValue and(InterpreterValue v) {
        throw new Error("Operator '&&' is not defined for type " + getName());
    }



    // comparison
    default InterpreterValue equals_equals(InterpreterValue v) {
        throw new Error("Operator '==' is not defined for type " + getName());
    }

    default InterpreterValue bigger_equals(InterpreterValue v) {
        throw new Error("Operator '&&' is not defined for type " + getName());
    }

    default InterpreterValue smaller_equals(InterpreterValue v) {
        throw new Error("Operator '&&' is not defined for type " + getName());
    }

    default InterpreterValue bigger(InterpreterValue v) {
        throw new Error("Operator '&&' is not defined for type " + getName());
    }
    default InterpreterValue smaller(InterpreterValue v) {
        throw new Error("Operator '&&' is not defined for type " + getName());
    }

    String getName();

}
