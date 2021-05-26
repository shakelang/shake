package com.github.nsc.de.shake.generators.java.nodes;

public class JavaVariableDeclaration implements JavaNode.JavaOperation {

    private final String name;
    private final JavaVariableType type;
    private final JavaValued assignment;
    private final boolean isStatic;
    private final boolean isFinal;
    private final JavaAccessDescriptor access;

    public JavaVariableDeclaration(JavaVariableType type, String name, JavaValued assignment, boolean isStatic, boolean isFinal, JavaAccessDescriptor access) {

        this.type = type;
        this.name = name;
        this.assignment = assignment;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.access = access;
    }

    public JavaVariableDeclaration(JavaVariableType type, String name, boolean isStatic, boolean isFinal, JavaAccessDescriptor access) {

        this.type = type;
        this.name = name;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.access = access;
        this.assignment = null;

    }

    @Override
    public String toString(String indent, String add) {
        return access.toString() + (isStatic ? "static " : "") + (isFinal ? "final " : "") + type.toString()
                + ' ' + name + (assignment != null ? " = " + this.assignment.toString(indent, add) : "");
    }
}
