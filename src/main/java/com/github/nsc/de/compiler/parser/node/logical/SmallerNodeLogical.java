package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class SmallerNodeLogical extends LogicalCompareNode {

    public SmallerNodeLogical(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return "<";
    }

}
