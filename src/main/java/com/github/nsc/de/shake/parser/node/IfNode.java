package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;

public class IfNode extends Node {

    private final Tree body;
    private final Tree elseBody;
    private final ValuedNode condition;


    public IfNode(PositionMap map, Tree body, Tree elseBody, ValuedNode condition) {
        super(map);
        this.body = body;
        this.elseBody = elseBody;
        this.condition = condition;
    }


    public IfNode(PositionMap map, Tree body, ValuedNode condition) {
        this(map, body, null, condition);
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
