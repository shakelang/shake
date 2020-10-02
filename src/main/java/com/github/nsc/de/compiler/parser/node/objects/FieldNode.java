package com.github.nsc.de.compiler.parser.node.objects;

import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableAssignmentNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;

public class FieldNode implements ValuedNode {

    private final String name;
    private final VariableDeclarationNode.VariableType type;
    private final VariableAssignmentNode assignment;

    public FieldNode(String name, VariableDeclarationNode.VariableType type, VariableAssignmentNode assignment) {
        this.name = name;
        this.type = type;
        this.assignment = assignment;
    }

    public FieldNode(String name, VariableDeclarationNode.VariableType type) { this(name, type, null); }

    public FieldNode(String name, VariableAssignmentNode assignment) { this(name, VariableDeclarationNode.VariableType.DYNAMIC, assignment); }

    public FieldNode(String name) { this(name, VariableDeclarationNode.VariableType.DYNAMIC, null); }

    public String getName() {
        return name;
    }
    public VariableDeclarationNode.VariableType getType() { return type; }
    public VariableAssignmentNode getAssignment() { return assignment; }

    @Override
    public String toString() {
        return this.getAssignment() != null ? "var " + this.getAssignment() : "var " + this.getName();
    }
}
