package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class BiggerNodeLogical extends LogicalCompareNode {

    public BiggerNodeLogical(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return ">";
    }

}
