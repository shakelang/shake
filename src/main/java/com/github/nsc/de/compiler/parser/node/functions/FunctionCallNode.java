package com.github.nsc.de.compiler.parser.node.functions;

import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

import java.util.Arrays;

public class FunctionCallNode implements ValuedNode {

    private final String name;
    private final ValuedNode[] args;


    public FunctionCallNode(String name, ValuedNode[] args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public ValuedNode[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "FunctionCallNode{" +
                "name='" + name + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
