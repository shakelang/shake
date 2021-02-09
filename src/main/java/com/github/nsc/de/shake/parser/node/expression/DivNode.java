package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class DivNode extends ExpressionNode {
    public DivNode(ValuedNode left, ValuedNode right, int operatorPosition) { super(left, right, operatorPosition); }

    @Override
    public char getOperator() {
        return '/';
    }
}
