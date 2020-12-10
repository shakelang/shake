package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class LogicalBiggerEqualsNode extends LogicalCompareNode {

    public LogicalBiggerEqualsNode(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return ">=";
    }

}
