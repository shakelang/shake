package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.expression.NumberNode;

public abstract class LogicalConcatinationNode implements LogicalNode {
    private final ValuedNode left;
    private final ValuedNode right;

    public LogicalConcatinationNode(LogicalNode left, LogicalNode right) {
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "("+(getLeft() != null && !(getLeft() instanceof NumberNode && ((NumberNode)getLeft()).getNumber() == 0) ? getLeft() : "")+getOperator()+getRight()+")";
    }
}
