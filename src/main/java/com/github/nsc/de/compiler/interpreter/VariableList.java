package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.interpreter.values.InterpreterValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VariableList {

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
        if(this.parentList == null) {
            if (this.variables.containsKey(name)) return false;
            this.variables.put(name, new Variable(name, type));
            return true;
        } else {
            boolean answer = this.parentList.declare(name, type);
            if(answer) this.variables.put(name, this.parentList.get(name));
            return answer;
        }

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

}
