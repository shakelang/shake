package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class DoubleNode implements ValuedNode {

    private final double number;

    public DoubleNode(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number+"";
    }
}
