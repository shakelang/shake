package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class SmallerEqualsNodeLogical extends LogicalCompareNode {

    public SmallerEqualsNodeLogical(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return "<=";
    }

}
