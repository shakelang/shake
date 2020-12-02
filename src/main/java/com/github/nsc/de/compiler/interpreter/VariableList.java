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
        return variables.get(name);
    }

    public Map<String, Variable> getVariables() {
        return Collections.unmodifiableMap(variables);
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
}
