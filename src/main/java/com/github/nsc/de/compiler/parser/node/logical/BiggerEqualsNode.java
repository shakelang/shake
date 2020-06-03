package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class BiggerEqualsNode extends CompareNode {

    public BiggerEqualsNode(ValuedNode left, ValuedNode right) { super(left, right); }

    @Override
    public String getOperator() {
        return ">=";
    }

}
