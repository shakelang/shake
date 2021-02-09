package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public abstract class LogicalConcatenationNode extends LogicalNode {
    private final ValuedNode left;
    private final ValuedNode right;

    public LogicalConcatenationNode(PositionMap map, ValuedNode left, ValuedNode right) {
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
        return "LogicalConcatenationNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
