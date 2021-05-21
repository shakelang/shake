package com.github.nsc.de.shake.parser.node.logical;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public abstract class LogicalNode extends ValuedNode {

    public LogicalNode(PositionMap map) {
        super(map);
    }

    abstract String getOperator();

}
