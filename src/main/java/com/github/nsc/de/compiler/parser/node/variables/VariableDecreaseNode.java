package com.github.nsc.de.compiler.parser.node.variables;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class VariableDecreaseNode implements ValuedNode {

    private final String name;

    public VariableDecreaseNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName() + "--";
    }
}
