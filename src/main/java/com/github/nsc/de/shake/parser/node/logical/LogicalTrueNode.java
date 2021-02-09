package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;

public class LogicalTrueNode extends LogicalNode {

    public LogicalTrueNode(PositionMap map) {
        super(map);
    }

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public String toString() {
        return "true";
    }
}
