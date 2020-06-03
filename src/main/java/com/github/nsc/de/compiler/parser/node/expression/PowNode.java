package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class PowNode extends ExpressionNode {
    public PowNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }

    @Override
    public char getOperator() {
        return '^';
    }
}
