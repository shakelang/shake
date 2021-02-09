package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class PowNode extends ExpressionNode {
    public PowNode(PositionMap map, ValuedNode left, ValuedNode right, int operatorPosition) {
        super(map, left, right, operatorPosition);
    }

    @Override
    public char getOperator() {
        return '^';
    }
}
