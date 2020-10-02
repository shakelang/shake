package com.github.nsc.de.compiler.parser.node.objects;

import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;

import java.util.Arrays;

public class MethodNode {

    private final String name;
    private final Tree body;
    private final FunctionArgumentNode[] args;


    public MethodNode(String name, Tree body, FunctionArgumentNode[] args) {
        this.name = name;
        this.body = body;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public Tree getBody() {
        return body;
    }

    public FunctionArgumentNode[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "MethodNode{" +
                "name='" + name + '\'' +
                ", body=" + body +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
