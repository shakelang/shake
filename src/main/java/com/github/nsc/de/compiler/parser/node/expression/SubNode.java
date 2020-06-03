package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class SubNode extends ExpressionNode {
    public SubNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }
    public SubNode(int left, ValuedNode right) {
        this(new NumberNode(left), right);
    }
    public SubNode(ValuedNode left, int right) {
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
