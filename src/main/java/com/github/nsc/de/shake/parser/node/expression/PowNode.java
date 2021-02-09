package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class PowNode extends ExpressionNode {
    public PowNode(ValuedNode left, ValuedNode right, int operatorIndex) {
        super(left, right, operatorIndex);
    }

    @Override
    public char getOperator() {
        return '^';
    }
}
