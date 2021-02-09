package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class MulNode extends ExpressionNode {
    public MulNode(ValuedNode left, ValuedNode right, int operatorPosition) {
        super(left, right, operatorPosition);
    }

    @Override
    public char getOperator() {
        return '*';
    }
}
