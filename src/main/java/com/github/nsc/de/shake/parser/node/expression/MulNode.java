package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class MulNode extends ExpressionNode {
    public MulNode(ValuedNode left, ValuedNode right, int operatorIndex) {
        super(left, right, operatorIndex);
    }

    @Override
    public char getOperator() {
        return '*';
    }
}
