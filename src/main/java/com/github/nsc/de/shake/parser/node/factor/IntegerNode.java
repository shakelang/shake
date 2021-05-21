package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

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
