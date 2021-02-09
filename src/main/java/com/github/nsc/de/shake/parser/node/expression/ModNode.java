package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class ModNode extends ExpressionNode {
    public ModNode(PositionMap map, ValuedNode left, ValuedNode right, int operatorPosition) {
        super(map, left, right, operatorPosition);
    }

    @Override
    public char getOperator() {
        return '%';
    }
}
