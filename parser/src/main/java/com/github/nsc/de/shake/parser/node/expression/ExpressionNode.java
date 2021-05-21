package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public abstract class ExpressionNode extends ValuedNode {
    private final ValuedNode left;
    private final ValuedNode right;
    private final int operatorPosition;

    public ExpressionNode(PositionMap map, ValuedNode left, ValuedNode right, int operatorPosition) {
        super(map);
        this.left = left;
        this.right = right;
        this.operatorPosition = operatorPosition;
    }

    public abstract char getOperator();

    public ValuedNode getLeft() {
        return left;
    }
    public ValuedNode getRight() {
        return right;
    }
    public int getOperatorPosition() {
        return operatorPosition;
    }

    @Override
    public String toString() {
        return "ExpressionNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
