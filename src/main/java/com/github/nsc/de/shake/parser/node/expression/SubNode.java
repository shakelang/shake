package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;

public class SubNode extends ExpressionNode {
    public SubNode(ValuedNode left, ValuedNode right, int operatorPosition) {
        super(left, right, operatorPosition);
    }
    public SubNode(int left, ValuedNode right, int operatorPosition) {
        this(new IntegerNode(left), right, operatorPosition);
    }
    public SubNode(ValuedNode left, int right, int operatorPosition) {
        this(left, new IntegerNode(right), operatorPosition);
    }
    public SubNode(int left, int right, int operatorPosition) {
        this(new IntegerNode(left), new IntegerNode(right), operatorPosition);
    }
    public SubNode(double left, ValuedNode right, int operatorPosition) {
        this(new DoubleNode(left), right, operatorPosition);
    }
    public SubNode(ValuedNode left, double right, int operatorPosition) {
        this(left, new DoubleNode(right), operatorPosition);
    }
    public SubNode(double left, double right, int operatorPosition) {
        this(new DoubleNode(left), new DoubleNode(right), operatorPosition);
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
