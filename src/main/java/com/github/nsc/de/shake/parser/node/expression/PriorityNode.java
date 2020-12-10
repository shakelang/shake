package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class PriorityNode implements ValuedNode {

    private final ValuedNode value;

    public PriorityNode(ValuedNode value) {
        this.value = value;
    }

    public ValuedNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PriorityNode{" +
                "value=" + value +
                '}';
    }
}
