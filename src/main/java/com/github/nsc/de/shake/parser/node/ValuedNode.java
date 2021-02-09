package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;

public abstract class ValuedNode extends Node {
    public ValuedNode(PositionMap map) {
        super(map);
    }
}
