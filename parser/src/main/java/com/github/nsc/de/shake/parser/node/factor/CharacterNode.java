package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class CharacterNode extends ValuedNode {

    private final char value;

    public CharacterNode(PositionMap map, char value) {
        super(map);
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
