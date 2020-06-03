package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.Node;

public class MulNode extends ExpressionNode {
    public MulNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public char getOperator() {
        return '*';
    }
}
