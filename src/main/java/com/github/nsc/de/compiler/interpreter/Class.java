package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;

public class Class {

    private final VariableList prototype;
    private final Scope scope;
    private final Interpreter interpreter;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;


    public Class(Scope scope, Interpreter interpreter, VariableList prototype,
                 AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.scope = scope;
        this.interpreter = interpreter;
        this.prototype = prototype;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    public VariableList getPrototype() { return prototype; }
    public Interpreter getInterpreter() {
        return interpreter;
    }
    public Scope getScope() {
        return scope;
    }
    public boolean isInClass() { return isInClass; }
    public boolean isStatic() { return isStatic; }
    public boolean isFinal() { return isFinal; }

    @Override
    public String toString() {
        return "Class{" +
                "access=" + access +
                ", isStatic=" + isStatic +
                ", isFinal=" + isFinal +
                '}';
    }
}
