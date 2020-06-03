package com.github.nsc.de.compiler.parser.node;

public class VariableDeclarationNode implements ValuedNode {
    private final String name;
    private final VariableAssignmentNode assignment;

    public VariableDeclarationNode(String name, VariableAssignmentNode assignment) {
        this.name = name;
        this.assignment = assignment;
    }

    public VariableDeclarationNode(String name) { this(name, null); }

    public String getName() {
        return name;
    }
    public VariableAssignmentNode getAssignment() {
        return assignment;
    }

    @Override
    public String toString() {
        return this.getAssignment() != null ? "var " + this.getAssignment() : "var " + this.getName();
    }
}
