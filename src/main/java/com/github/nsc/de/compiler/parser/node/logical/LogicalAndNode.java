package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class LogicalAndNode extends LogicalConcatinationNode {
    public LogicalAndNode(ValuedNode left, ValuedNode right) {
        super(left, right);
    }

    @Override
    public String getOperator() {
        return "&&";
    }
}
