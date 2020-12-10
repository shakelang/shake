package com.github.nsc.de.shake.generators.java.nodes;

public class JavaVariableDeclaration implements JavaNode.JavaOperation {

    private final String name;
    private final JavaVariableType type;
    private final JavaValuedOperation assignment;

    public JavaVariableDeclaration(JavaVariableType type, String name, JavaValuedOperation assignment) {

        this.type = type;
        this.name = name;
        this.assignment = assignment;

    }

    public JavaVariableDeclaration(JavaVariableType type, String name) {

        this.type = type;
        this.name = name;
        this.assignment = null;

    }

    @Override
    public String toString(String indent, String add) {
        return type.toString() + ' ' + name +
                (assignment != null ? " = " + this.assignment.toString(indent, add) : "");
    }
}
