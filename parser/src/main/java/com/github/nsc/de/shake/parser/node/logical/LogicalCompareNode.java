package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public abstract class LogicalCompareNode extends LogicalNode {
    private final ValuedNode left;
    private final ValuedNode right;

    public LogicalCompareNode(PositionMap map, ValuedNode left, ValuedNode right) {
        super(map);
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
        return "LogicalCompareNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
