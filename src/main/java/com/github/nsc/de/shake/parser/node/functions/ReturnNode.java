package com.github.nsc.de.shake.parser.node.functions;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class ReturnNode extends Node {

    private final ValuedNode value;

    public ReturnNode(PositionMap map, ValuedNode value) {
        super(map);
        this.value = value;
    }

    public ValuedNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ReturnNode{" +
                "value=" + value +
                '}';
    }
}
