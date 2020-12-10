package com.github.nsc.de.shake.parser.node;

public class IfNode implements Node {

    private final Tree body;
    private final Tree elseBody;
    private final ValuedNode condition;


    public IfNode(Tree body, Tree elseBody, ValuedNode condition) {
        this.body = body;
        this.elseBody = elseBody;
        this.condition = condition;
    }


    public IfNode(Tree body, ValuedNode condition) {
        this(body, null, condition);
    }

    public Tree getBody() {
        return body;
    }
    public Tree getElseBody() { return elseBody; }
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
