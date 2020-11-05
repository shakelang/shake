package com.github.nsc.de.compiler.generators.java.nodes;

import com.github.nsc.de.compiler.parser.node.VariableType;

public class JavaVariableType {

    private final String type;

    public JavaVariableType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }


    // Statics
    public static final JavaVariableType BYTE = new JavaVariableType("byte");
    public static final JavaVariableType SHORT = new JavaVariableType("short");
    public static final JavaVariableType INT = new JavaVariableType("int");
    public static final JavaVariableType LONG = new JavaVariableType("long");
    public static final JavaVariableType FLOAT = new JavaVariableType("float");
    public static final JavaVariableType DOUBLE = new JavaVariableType("double");
    public static final JavaVariableType CHAR = new JavaVariableType("char");
    public static final JavaVariableType BOOLEAN = new JavaVariableType("boolean");
    public static final JavaVariableType OBJECT = new JavaVariableType("Object");

    public static JavaVariableType from(VariableType type) {
        switch (type.getType()) {
            case BYTE: return BYTE;
            case SHORT: return SHORT;
            case INTEGER: return INT;
            case LONG: return LONG;
            case FLOAT: return FLOAT;
            case DOUBLE: return DOUBLE;
            case BOOLEAN: return BOOLEAN;
            case CHAR: return CHAR;
            case OBJECT: return new JavaVariableType(type.getSubtype());
            case ARRAY: throw new Error("Not implemented yet!"); // TODO
            case DYNAMIC: return OBJECT;
        }
        throw new Error();
    }
}
