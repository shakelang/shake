package com.github.nsc.de.shake.parser.node.functions;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class ReturnNode implements Node {

    private final ValuedNode value;

    public ReturnNode(ValuedNode value) {
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
