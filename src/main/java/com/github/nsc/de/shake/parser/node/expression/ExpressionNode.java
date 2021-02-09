package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public abstract class ExpressionNode implements ValuedNode {
    private final ValuedNode left;
    private final ValuedNode right;
    private final int operatorIndex;

    public ExpressionNode(ValuedNode left, ValuedNode right, int operatorIndex) {
        this.left = left;
        this.right = right;
        this.operatorIndex = operatorIndex;
    }

    public abstract char getOperator();

    public ValuedNode getLeft() {
        return left;
    }
    public ValuedNode getRight() {
        return right;
    }
    public int getOperatorIndex() {
        return operatorIndex;
    }

    @Override
    public String toString() {
        return "ExpressionNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
