package com.github.nsc.de.shake.parser.node.loops;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class ForNode extends Node {

    private final Tree body;
    private final Node declaration;
    private final ValuedNode condition;
    private final Node round;


    public ForNode(PositionMap map, Tree body, Node declaration, ValuedNode condition, Node round) {
        super(map);
        this.body = body;
        this.declaration = declaration;
        this.condition = condition;
        this.round = round;
    }

    public Tree getBody() {
        return body;
    }

    public Node getDeclaration() { return declaration; }

    public ValuedNode getCondition() {
        return condition;
    }

    public Node getRound() { return round; }

    @Override
    public String toString() {
        return "ForNode{" +
                "body=" + body +
                ", declaration=" + declaration +
                ", condition=" + condition +
                ", round=" + round +
                '}';
    }
}
