package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.InterpreterValue;
import com.github.nsc.de.compiler.interpreter.values.NullValue;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;

public class Variable {

    private final String identifier;
    private final VariableType type;
    private final AccessDescriber access;
    private InterpreterValue value;

    public Variable(String identifier, VariableType type, AccessDescriber access, InterpreterValue value) {
        if(type == null) throw new Error("Variable type must not be null!");
        this.identifier = identifier;
        this.type = type;
        this.access = access;
        this.value = value;
    }


    public Variable(String identifier, VariableType type, InterpreterValue value) {
        if(type == null) throw new Error();
        this.identifier = identifier;
        this.type = type;
        this.access = AccessDescriber.PACKAGE;
        this.value = value;
    }

    public Variable(String identifier, VariableType type, AccessDescriber access) {
        this(identifier, type, access, NullValue.NULL);
    }

    public Variable(String identifier, VariableType type) {
        this(identifier, type, NullValue.NULL);
    }

    public VariableType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public InterpreterValue getValue() {
        return value;
    }

    public AccessDescriber getAccess() { return access; }

    public void setValue(InterpreterValue value) {
        this.value = value;
    }
}
