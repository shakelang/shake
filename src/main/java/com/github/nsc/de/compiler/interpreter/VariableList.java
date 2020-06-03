package com.github.nsc.de.compiler.interpreter;

import java.util.HashMap;

public class VariableList {

    private final HashMap<String, Variable> variables = new HashMap<>();

    boolean declare(String name, VariableType type) {

        if(this.variables.containsKey(name)) return false;
        this.variables.put(name, new Variable(name, type));
        return true;

    }

    Variable get(String name) {
        return variables.get(name);
    }

}
