package com.github.nsc.de.compiler.interpreter.values;

import com.github.nsc.de.compiler.interpreter.Interpreter;
import com.github.nsc.de.compiler.interpreter.Scope;
import com.github.nsc.de.compiler.interpreter.VariableList;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;

public class Class implements InterpreterValue {

    private final String name;
    private final VariableList prototype;
    private final VariableDeclarationNode[] fields;
    private final Scope scope;
    private final Interpreter interpreter;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;


    public Class(String name, VariableDeclarationNode[] fields, Scope scope, Interpreter interpreter, VariableList prototype,
                 AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.fields = fields;
        this.scope = scope;
        this.interpreter = interpreter;
        this.prototype = prototype;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    @Override
    public String getName() {
        return "class";
    }

    public String getClassName() { return name; }
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
