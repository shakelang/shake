package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class IntegerNode extends ValuedNode {

    private final int number;

    public IntegerNode(PositionMap map, int number) {
        super(map);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
