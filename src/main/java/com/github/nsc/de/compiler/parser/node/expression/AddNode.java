package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.Node;

public class AddNode extends ExpressionNode {
    public AddNode(Node left, Node right) {
        super(left, right);
    }
    public AddNode(int left, Node right) {
        this(new NumberNode(left), right);
    }
    public AddNode(Node left, int right) {
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
