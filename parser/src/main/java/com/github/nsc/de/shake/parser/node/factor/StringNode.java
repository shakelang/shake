package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class StringNode extends ValuedNode {

    private final String value;

    public StringNode(PositionMap map, String value) {
        super(map);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
