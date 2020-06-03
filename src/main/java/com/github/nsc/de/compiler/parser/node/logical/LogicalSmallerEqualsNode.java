package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class LogicalSmallerEqualsNode extends LogicalCompareNode {

    public LogicalSmallerEqualsNode(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return "<=";
    }

}
