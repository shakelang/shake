package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class PriorityNode extends ValuedNode {

    private final ValuedNode value;

    public PriorityNode(PositionMap map, ValuedNode value) {
        super(map);
        this.value = value;
    }

    public ValuedNode getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PriorityNode{" +
                "value=" + value +
                '}';
    }
}
