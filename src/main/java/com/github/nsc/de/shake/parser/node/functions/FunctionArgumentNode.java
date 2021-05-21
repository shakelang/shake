package com.github.nsc.de.shake.parser.node.functions;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.VariableType;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class FunctionArgumentNode extends Node {

    private final String name;
    private final VariableType type;

    public FunctionArgumentNode(PositionMap map, String name, VariableType type) {
        super(map);
        this.name = name;
        this.type = type;
    }

    public FunctionArgumentNode(PositionMap map, String name) {
        this(map, name, VariableType.DYNAMIC);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FunctionArgumentNode{" +
                "name='" + name + '\'' +
                "type='" + type + '\'' +
                '}';
    }
}
