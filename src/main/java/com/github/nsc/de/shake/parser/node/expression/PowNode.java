package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class PowNode extends ExpressionNode {
    public PowNode(ValuedNode left, ValuedNode right, int operatorPosition) {
        super(left, right, operatorPosition);
    }

    @Override
    public char getOperator() {
        return '^';
    }
}
