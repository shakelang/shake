package com.github.nsc.de.shake.parser.node.functions;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.VariableType;

public class FunctionArgumentNode implements Node {

    private final String name;
    private final VariableType type;

    public FunctionArgumentNode(String name, VariableType type) {
        this.name = name;
        this.type = type;
    }

    public FunctionArgumentNode(String name) {
        this(name, VariableType.DYNAMIC);
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
