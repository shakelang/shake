package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class DivNode extends ExpressionNode {
    public DivNode(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public char getOperator() {
        return '/';
    }
}
