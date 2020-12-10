package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public abstract class LogicalConcatenationNode implements LogicalNode {
    private final ValuedNode left;
    private final ValuedNode right;

    public LogicalConcatenationNode(ValuedNode left, ValuedNode right) {
        this.left = left;
        this.right = right;
    }

    public ValuedNode getLeft() {
        return left;
    }
    public ValuedNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "LogicalConcatenationNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
