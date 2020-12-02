package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.Function;
import com.github.nsc.de.compiler.interpreter.values.InterpreterValue;
import com.github.nsc.de.compiler.interpreter.values.NullValue;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;

public class Variable implements InterpreterValue {

    private final String identifier;
    private final Class<? extends InterpreterValue> type;
    private final AccessDescriber access;
    private InterpreterValue value;

    public Variable(String identifier, Class<? extends InterpreterValue> type, AccessDescriber access, InterpreterValue value) {
        if(type == null) throw new Error("Variable type must not be null!");
        this.identifier = identifier;
        this.type = type;
        this.access = access;
        this.value = value;
    }


    public Variable(String identifier, Class<? extends InterpreterValue> type, InterpreterValue value) {
        if(type == null) throw new Error();
        this.identifier = identifier;
        this.type = type;
        this.access = AccessDescriber.PACKAGE;
        this.value = value;
    }

    public Variable(String identifier, Class<? extends InterpreterValue> type, AccessDescriber access) {
        this(identifier, type, access, NullValue.NULL);
    }

    public Variable(String identifier, Class<? extends InterpreterValue> type) {
        this(identifier, type, NullValue.NULL);
    }

    public Class<? extends InterpreterValue> getType() {
        return type;
    }
    public String getIdentifier() {
        return identifier;
    }
    public InterpreterValue getValue() {
        return value != null ? value : NullValue.NULL;
    }
    public AccessDescriber getAccess() { return access; }
    public void setValue(InterpreterValue value) {
        this.value = value;
    }
    public boolean hasValue() { return this.value != null; }

    @Override
    public InterpreterValue add(InterpreterValue v) {
        return this.getValue().add(v);
    }

    @Override
    public InterpreterValue sub(InterpreterValue v) {
        return this.getValue().sub(v);
    }

    @Override
    public InterpreterValue mul(InterpreterValue v) {
        return this.getValue().mul(v);
    }

    @Override
    public InterpreterValue div(InterpreterValue v) {
        return this.getValue().div(v);
    }

    @Override
    public InterpreterValue pow(InterpreterValue v) {
        return this.getValue().pow(v);
    }

    @Override
    public InterpreterValue or(InterpreterValue v) {
        return this.getValue().or(v);
    }

    @Override
    public InterpreterValue and(InterpreterValue v) {
        return this.getValue().and(v);
    }

    @Override
    public InterpreterValue equals(InterpreterValue v) {
        return this.getValue().equals(v);
    }

    @Override
    public InterpreterValue bigger_equals(InterpreterValue v) {
        return this.getValue().bigger_equals(v);
    }

    @Override
    public InterpreterValue smaller_equals(InterpreterValue v) {
        return this.getValue().smaller_equals(v);
    }

    @Override
    public InterpreterValue bigger(InterpreterValue v) {
        return this.getValue().bigger(v);
    }

    @Override
    public InterpreterValue smaller(InterpreterValue v) {
        return this.getValue().smaller(v);
    }

    @Override
    public Variable getChild(String s) { return this.getValue().getChild(s); }

    @Override
    public String getName() {
        return this.getValue().getName();
    }

    public Variable copy() {
        return new Variable(this.identifier, this.type, this.access, this.value);
    }

    /**
     * Returns the same variable, but you can declare what {@link Scope} to use (for class declarations)
     *
     * @param scope the scope to use
     * @return the {@link Function} using the specified {@link Scope}
     *
     * @author Nicolas Schmidt
     */
    public Variable withScope(Scope scope) {
        return new Variable(identifier, type, access, useScope(value, scope));
    }

    private static InterpreterValue useScope(InterpreterValue v, Scope scope) {
        if(v instanceof Variable) return ((Variable) v).withScope(scope);
        if(v instanceof VariableList) return ((VariableList) v).withScope(scope);
        if(v instanceof Function) return ((Function) v).withScope(scope);
        if(v instanceof com.github.nsc.de.compiler.interpreter.values.Class)
            return ((com.github.nsc.de.compiler.interpreter.values.Class) v).withScope(scope);
        return v;
    }
}
