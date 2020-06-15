package com.github.nsc.de.compiler.interpreter;

public class Scope {

    private final VariableList variables;
    private final Scope parent;

    public Scope(Scope parent, VariableList variables) {
        this.parent = parent;
        this.variables = variables;
    }

    public Scope(Scope parent) {
        this(parent, new VariableList());
    }

    public Scope() {
        this(null);
    }

    public VariableList getScopeVariables() {
        return variables;
    }

    public VariableList getVariables() {
        return this.parent != null ? this.parent.getVariables().concat(variables) : variables;
    }
}
