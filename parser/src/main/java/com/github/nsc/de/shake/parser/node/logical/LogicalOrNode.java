package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class LogicalOrNode extends LogicalConcatenationNode {
    public LogicalOrNode(PositionMap map, ValuedNode left, ValuedNode right) {
        super(map, left, right);
    }

    @Override
    public String getOperator() {
        return "||";
    }
}
