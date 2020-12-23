package com.github.nsc.de.shake.parser.node;

import java.util.Locale;

public class VariableType {

    public static VariableType DYNAMIC = new VariableType(Type.DYNAMIC);
    public static VariableType BYTE = new VariableType(Type.BYTE);
    public static VariableType SHORT = new VariableType(Type.SHORT);
    public static VariableType INTEGER = new VariableType(Type.INTEGER);
    public static VariableType LONG = new VariableType(Type.LONG);
    public static VariableType FLOAT = new VariableType(Type.FLOAT);
    public static VariableType DOUBLE = new VariableType(Type.DOUBLE);
    public static VariableType BOOLEAN = new VariableType(Type.BOOLEAN);
    public static VariableType CHAR = new VariableType(Type.CHAR);
    public static VariableType ARRAY = new VariableType(Type.ARRAY);
    public static VariableType OBJECT = new VariableType(Type.OBJECT);

    private final Type type;
    private final String subtype;

    public VariableType(Type type, String subtype) {
        this.type = type;
        this.subtype = subtype;
    }

    public VariableType(Type type) {
        this.type = type;
        this.subtype = null;
    }

    public Type getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    @Override
    public String toString() {
        return "VariableType{" +
                "type=" + type +
                ", subtype='" + subtype + '\'' +
                '}';
    }

    public enum Type {
        DYNAMIC,
        BYTE,
        SHORT,
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        CHAR,
        ARRAY,
        OBJECT;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}