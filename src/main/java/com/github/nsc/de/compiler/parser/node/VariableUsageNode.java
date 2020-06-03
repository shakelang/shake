package com.github.nsc.de.compiler.parser.node;

public class VariableUsageNode implements Node {
    private final String name;

    public VariableUsageNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
