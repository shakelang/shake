package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class CharacterNode implements ValuedNode {

    private final char value;

    public CharacterNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
