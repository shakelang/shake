package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

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
