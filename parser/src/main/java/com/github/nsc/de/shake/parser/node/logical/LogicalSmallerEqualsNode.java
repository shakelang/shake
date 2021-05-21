package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class LogicalSmallerEqualsNode extends LogicalCompareNode {

    public LogicalSmallerEqualsNode(PositionMap map, ValuedNode left, ValuedNode right) { super(map, left, right); }

    @Override
    public String getOperator() {
        return "<=";
    }

}
