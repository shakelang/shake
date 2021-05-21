package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class LogicalFalseNode extends LogicalNode {

    public LogicalFalseNode(PositionMap map) {
        super(map);
    }

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public String toString() {
        return "false";
    }
}
