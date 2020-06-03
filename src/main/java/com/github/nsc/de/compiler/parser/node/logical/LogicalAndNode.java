package com.github.nsc.de.compiler.parser.node.logical;

public class LogicalAndNode extends LogicalConcatinationNode {
    public LogicalAndNode(LogicalNode left, LogicalNode right) {
        super(left, right);
    }

    @Override
    public String getOperator() {
        return "&&";
    }
}
