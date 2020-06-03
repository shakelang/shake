package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class AddNode extends ExpressionNode {
    public AddNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }
    public AddNode(int left, ValuedNode right) {
        this(new NumberNode(left), right);
    }
    public AddNode(ValuedNode left, int right) {
        this(left, new NumberNode(right));
    }
    public AddNode(int left, int right) {
        this(new NumberNode(left), new NumberNode(right));
    }

    @Override
    public char getOperator() {
        return '+';
    }
}
