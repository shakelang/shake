package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class AddNode extends ExpressionNode {
    public AddNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }
    public AddNode(int left, ValuedNode right) {
        this(new IntegerNode(left), right);
    }
    public AddNode(ValuedNode left, int right) {
        this(left, new IntegerNode(right));
    }
    public AddNode(int left, int right) {
        this(new IntegerNode(left), new IntegerNode(right));
    }
    public AddNode(double left, ValuedNode right) {
        this(new DoubleNode(left), right);
    }
    public AddNode(ValuedNode left, double right) {
        this(left, new DoubleNode(right));
    }
    public AddNode(double left, double right) {
        this(new DoubleNode(left), new DoubleNode(right));
    }

    @Override
    public char getOperator() {
        return '+';
    }
}
