package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.VariableType;

public class VariableDeclarationNode implements ValuedNode {

    private final String name;
    private final VariableType type;
    private final VariableAssignmentNode assignment;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;

    public VariableDeclarationNode(String name, VariableType type, VariableAssignmentNode assignment) {
        this(name, type, assignment, AccessDescriber.PACKAGE, false, false, false);
    }

    public VariableDeclarationNode(String name, VariableType type, VariableAssignmentNode assignment,
                                   AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.type = type;
        this.assignment = assignment;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    public VariableDeclarationNode(String name, VariableType type) { this(name, type, null); }

    public VariableDeclarationNode(String name, VariableAssignmentNode assignment) { this(name, VariableType.DYNAMIC, assignment); }

    public VariableDeclarationNode(String name) { this(name, VariableType.DYNAMIC, null); }

    public String getName() {
        return name;
    }
    public VariableType getType() { return type; }
    public VariableAssignmentNode getAssignment() { return assignment; }
    public AccessDescriber getAccess() { return access; }
    public boolean isStatic() { return isStatic; }
    public boolean isInClass() { return isInClass; }
    public boolean isFinal() { return isFinal; }

    @Override
    public String toString() {
        return this.getAssignment() != null ? this.getType() + " " + this.getAssignment() : this.getType() + " " + this.getName();
    }
}
