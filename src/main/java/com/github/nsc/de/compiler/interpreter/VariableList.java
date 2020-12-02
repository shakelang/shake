package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.InterpreterValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VariableList implements InterpreterValue {

    private final HashMap<String, Variable> variables;
    private final VariableList parentList;

    public VariableList(HashMap<String, Variable> variables, VariableList parentList) {
        this.variables = variables;
        this.parentList = parentList;
    }

    public VariableList(HashMap<String, Variable> variables) {
        this.variables = variables;
        this.parentList = null;
    }

    public VariableList() {
        this(new HashMap<>());
    }

    public boolean declare(String name, Class<? extends InterpreterValue> type) {

        if (this.variables.containsKey(name)) return false;
        this.variables.put(name, new Variable(name, type));
        return true;

    }

    public Variable get(String name) {
        if(variables.containsKey(name)) return variables.get(name);
        else if (this.getParentList() != null) return this.getParentList().get(name);
        return null; // << The variable is not declared
    }

    public Map<String, Variable> getVariables() {
        return Collections.unmodifiableMap(variables);
    }

    public VariableList getParentList() {
        return parentList;
    }

    VariableList concat(VariableList list) {
        HashMap<String, Variable> variables = new HashMap<>(this.variables);
        for(Map.Entry<String, Variable> entry : list.getVariables().entrySet()) variables.put(entry.getKey(), entry.getValue());
        return new VariableList(variables);
    }

    public VariableList copy() {

        HashMap<String, Variable> vars = new HashMap<>();
        this.variables.forEach((k, v) -> vars.put(k, v.copy()));

        return new VariableList(vars, this.parentList);

    }

    public VariableList deepCopy() {

        HashMap<String, Variable> vars = new HashMap<>();
        this.variables.forEach((k, v) -> vars.put(k, v.copy()));

        if(this.parentList != null) return new VariableList(vars, this.parentList.copy());
        else return new VariableList(vars);

    }

    /**
     * This function gets executed when getting a child (variable.child)
     *
     * @param c the child to get
     * @return the child variable
     * @author Nicolas Schmidt
     */
    @Override
    public Variable getChild(String c) {
        // just get the child
        return this.get(c);
    }

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     * For {@link VariableList} it just always returns "variable_list"
     *
     * @return the name of the {@link InterpreterValue}
     * @author Nicolas Schmidt
     */
    @Override
    public String getName() {
        // just return "variable_list"
        return "variable_list";
    }



    // *******************************
    // Override toString()

    /**
     * Returns the string representation of the {@link VariableList}
     *
     * @return the string representation of the {@link VariableList}
     *
     * @author Nicolas Schmidt
     */
    @Override
    public String toString() {
        // just create a string out of the properties of the VariableList
        return String.format("{variables=%s,parentList=%s}", this.variables, this.parentList);
    }

    public VariableList withScope(Scope scope) {
        return new ScopeVariableList(this.variables, this.parentList, scope);
    }

    public static class ScopeVariableList extends VariableList {

        private Scope scope;

        public ScopeVariableList(HashMap<String, Variable> variables, VariableList parentList, Scope scope) {
            super(variables, parentList);
            this.scope = scope;
        }

        @Override
        public Variable get(String name) {
            return super.getVariables().get(name).withScope(this.scope);
        }

        @Override
        public Map<String, Variable> getVariables() {
            Map<String, Variable> variableMap = new HashMap<>();
            super.getVariables().forEach((String key, Variable value) -> variableMap.put(key, value.withScope(this.scope)));
            return variableMap;
        }

        public Scope getScope() {
            return scope;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }
    }
}
