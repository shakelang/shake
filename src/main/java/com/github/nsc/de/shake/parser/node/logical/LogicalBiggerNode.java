package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class LogicalBiggerNode extends LogicalCompareNode {

    public LogicalBiggerNode(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return ">";
    }

}
