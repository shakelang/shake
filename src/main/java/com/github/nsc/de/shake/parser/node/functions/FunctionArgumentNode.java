package com.github.nsc.de.shake.parser.node.functions;

import com.github.nsc.de.shake.parser.node.Node;

public class FunctionArgumentNode implements Node {

    private final String name;

    public FunctionArgumentNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FunctionArgumentNode{" +
                "name='" + name + '\'' +
                '}';
    }
}
