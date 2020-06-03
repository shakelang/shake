package com.github.nsc.de.compiler.parser.node.logical;

public class LogicalOrNode extends LogicalConcatinationNode {
    public LogicalOrNode(LogicalNode left, LogicalNode right) {
        super(left, right);
    }

    @Override
    public String getOperator() {
        return "||";
    }
}
