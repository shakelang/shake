package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.Node;

public class SubNode extends ExpressionNode {
    public SubNode(Node left, Node right) {
        super(left, right);
    }
    public SubNode(int left, Node right) {
        this(new NumberNode(left), right);
    }
    public SubNode(Node left, int right) {
        this(left, new NumberNode(right));
    }
    public SubNode(int left, int right) {
        this(new NumberNode(left), new NumberNode(right));
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
