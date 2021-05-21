package com.github.nsc.de.shake.parser.node.loops;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class WhileNode extends Node {

    private final Tree body;
    private final ValuedNode condition;


    public WhileNode(PositionMap map, Tree body, ValuedNode condition) {
        super(map);
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
        return "WhileNode{" +
                "body=" + body +
                ", condition=" + condition +
                '}';
    }
}
