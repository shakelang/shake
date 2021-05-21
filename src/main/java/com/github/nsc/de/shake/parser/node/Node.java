package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public abstract class Node {

    private final PositionMap map;

    protected Node(PositionMap map) {
        this.map = map;
    }

    public PositionMap getMap() {
        return map;
    }
}
