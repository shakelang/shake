package com.github.nsc.de.compiler.generators.java.nodes;

public class JavaWhileLoop implements JavaNode.JavaOperation {

    private final JavaValuedOperation condition;
    private final JavaTree body;

    public JavaWhileLoop(JavaValuedOperation condition, JavaTree body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString(String indent, String add) {
        return "while("+condition.toString(indent, add)+") " + body.toString(indent, add);
    }
}
