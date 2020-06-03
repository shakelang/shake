package com.github.nsc.de.compiler.interpreter;

public class Variable {

    private final String identifier;
    private final VariableType type;
    private Object value;


    public Variable(String identifier, VariableType type, Object value) {
        this.identifier = identifier;
        this.type = type;
        this.value = value;
    }

    public Variable(String identifier, VariableType type) {
        this(identifier, type, null);
    }

    public VariableType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
