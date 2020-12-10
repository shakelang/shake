package com.github.nsc.de.shake.generators.java.nodes;

public class JavaIfCondition implements JavaNode.JavaOperation {

    private final JavaValuedOperation condition;
    private final JavaTree body;
    private final JavaTree elseBody;

    public JavaIfCondition(JavaValuedOperation condition, JavaTree body, JavaTree elseBody) {
        this.condition = condition;
        this.body = body;
        this.elseBody = elseBody;
    }

    public JavaIfCondition(JavaValuedOperation condition, JavaTree body) {
        this.condition = condition;
        this.body = body;
        this.elseBody = null;
    }

    @Override
    public String toString(String indent, String add) {
        return "if("+condition.toString(indent, add)+") " + body.toString(indent, add)
        + (this.elseBody != null ? " else " + this.elseBody.toString(indent, add) : "");
    }
}
