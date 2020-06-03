package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.Node;

public abstract class ExpressionNode implements Node {
    private final Node left;
    private final Node right;

    public ExpressionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public abstract char getOperator();

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
