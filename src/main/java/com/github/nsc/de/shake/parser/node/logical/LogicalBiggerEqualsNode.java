package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class LogicalBiggerEqualsNode extends LogicalCompareNode {

    public LogicalBiggerEqualsNode(PositionMap map, ValuedNode left, ValuedNode right) { super(map, left, right); }

    @Override
    public String getOperator() {
        return ">=";
    }

}
