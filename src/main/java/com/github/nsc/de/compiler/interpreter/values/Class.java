package com.github.nsc.de.compiler.interpreter.values;

import com.github.nsc.de.compiler.interpreter.Interpreter;
import com.github.nsc.de.compiler.interpreter.Scope;
import com.github.nsc.de.compiler.interpreter.Variable;
import com.github.nsc.de.compiler.interpreter.VariableList;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;

public class Class implements InterpreterValue {

    private final String name;
    private final VariableList statics;
    private final VariableList prototype;
    private final VariableDeclarationNode[] fields;
    private final Scope scope;
    private final Interpreter interpreter;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;


    public Class(String name, VariableList statics, VariableDeclarationNode[] fields, Scope scope, Interpreter interpreter,
                 VariableList prototype, AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.statics = statics;
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
    public VariableList getStatics() { return statics; }
    public VariableList getPrototype() { return prototype; }
    public VariableDeclarationNode[] getFields() { return fields; }
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
    public Variable getChild(String s) {
        if(getStatics().get(name).hasValue()) throw new Error(String.format("Class \"%s\" has no property called %s", getClassName(), name));
        return getStatics().get(name);
    }

    @Override
    public String toString() {
        return "Class{" +
                "access=" + access +
                ", isStatic=" + isStatic +
                ", isFinal=" + isFinal +
                '}';
    }
}
