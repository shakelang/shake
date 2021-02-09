package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

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
