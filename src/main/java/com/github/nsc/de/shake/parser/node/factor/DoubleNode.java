package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class DoubleNode extends ValuedNode {

    private final double number;

    public DoubleNode(PositionMap map, double number) {
        super(map);
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number+"";
    }
}
