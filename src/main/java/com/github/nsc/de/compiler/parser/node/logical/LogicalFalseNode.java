package com.github.nsc.de.compiler.parser.node.logical;

public class LogicalFalseNode implements LogicalNode {

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public String toString() {
        return "false";
    }
}
