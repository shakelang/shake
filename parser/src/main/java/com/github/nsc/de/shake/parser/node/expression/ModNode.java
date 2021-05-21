package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class ModNode extends ExpressionNode {
    public ModNode(PositionMap map, ValuedNode left, ValuedNode right, int operatorPosition) {
        super(map, left, right, operatorPosition);
    }

    @Override
    public char getOperator() {
        return '%';
    }
}
