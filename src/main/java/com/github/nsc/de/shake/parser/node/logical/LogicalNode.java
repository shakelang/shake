package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public abstract class LogicalNode extends ValuedNode {

    public LogicalNode(PositionMap map) {
        super(map);
    }

    abstract String getOperator();

}
