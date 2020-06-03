package com.github.nsc.de.compiler.parser.node.expression;

import com.github.nsc.de.compiler.parser.node.Node;

public class DivNode extends ExpressionNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public char getOperator() {
        return '/';
    }
}
