package com.github.nsc.de.compiler.interpreter;

public enum VariableType {

    ANY,
    BYTE,
    SHORT,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    CHAR,
    OBJECT,
    ARRAY,
    FUNCTION
    ;

    public static VariableType valueOf(com.github.nsc.de.compiler.parser.node.VariableType type) {
        switch (type.getType()) {
            case BYTE: return BYTE;
            case SHORT: return SHORT;
            case INTEGER: return INTEGER;
            case LONG: return LONG;
            case FLOAT: return FLOAT;
            case DOUBLE: return DOUBLE;
            case BOOLEAN: return BOOLEAN;
            case CHAR: return CHAR;
            case OBJECT: return OBJECT;
            case ARRAY: return ARRAY;
        }
        return null;
    }


}
