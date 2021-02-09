package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;

public class SubNode extends ExpressionNode {
    public SubNode(ValuedNode left, ValuedNode right, int operatorIndex) {
        super(left, right, operatorIndex);
    }
    public SubNode(int left, ValuedNode right, int operatorIndex) {
        this(new IntegerNode(left), right, operatorIndex);
    }
    public SubNode(ValuedNode left, int right, int operatorIndex) {
        this(left, new IntegerNode(right), operatorIndex);
    }
    public SubNode(int left, int right, int operatorIndex) {
        this(new IntegerNode(left), new IntegerNode(right), operatorIndex);
    }
    public SubNode(double left, ValuedNode right, int operatorIndex) {
        this(new DoubleNode(left), right, operatorIndex);
    }
    public SubNode(ValuedNode left, double right, int operatorIndex) {
        this(left, new DoubleNode(right), operatorIndex);
    }
    public SubNode(double left, double right, int operatorIndex) {
        this(new DoubleNode(left), new DoubleNode(right), operatorIndex);
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
