package com.github.nsc.de.compiler.parser.node;

public class IfNode implements Node {

    private final Tree body;
    private final ValuedNode condition;


    public IfNode(Tree body, ValuedNode condition) {
        this.body = body;
        this.condition = condition;
    }

    public Tree getBody() {
        return body;
    }

    public ValuedNode getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "IfNode{" +
                "body=" + body +
                ", condition=" + condition +
                '}';
    }
}
