package com.github.nsc.de.shake.parser.node.factor;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;

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
