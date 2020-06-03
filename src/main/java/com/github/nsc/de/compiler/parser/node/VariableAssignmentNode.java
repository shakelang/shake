package com.github.nsc.de.compiler.parser.node;

public class VariableAssignmentNode implements Node {

    private final String name;
    private final Node value;

    public VariableAssignmentNode(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Node getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + " = " + this.getValue();
    }
}
