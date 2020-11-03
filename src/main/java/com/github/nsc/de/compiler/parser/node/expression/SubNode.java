package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class SubNode extends ExpressionNode {
    public SubNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }
    public SubNode(int left, ValuedNode right) {
        this(new IntegerNode(left), right);
    }
    public SubNode(ValuedNode left, int right) {
        this(left, new IntegerNode(right));
    }
    public SubNode(int left, int right) {
        this(new IntegerNode(left), new IntegerNode(right));
    }
    public SubNode(double left, ValuedNode right) {
        this(new DoubleNode(left), right);
    }
    public SubNode(ValuedNode left, double right) {
        this(left, new DoubleNode(right));
    }
    public SubNode(double left, double right) {
        this(new DoubleNode(left), new DoubleNode(right));
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
