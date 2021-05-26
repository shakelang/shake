package com.github.nsc.de.shake.generators.java.nodes;

public class JavaDoWhileLoop implements JavaNode.JavaOperation {
    
    private final JavaValued condition;
    private final JavaTree body;

    public JavaDoWhileLoop(JavaValued condition, JavaTree body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString(String indent, String add) {
        return "do " + body.toString(indent, add) + " while("+condition.toString(indent, add)+")";
    }
}
