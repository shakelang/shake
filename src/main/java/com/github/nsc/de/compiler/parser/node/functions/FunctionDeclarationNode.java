package com.github.nsc.de.compiler.parser.node.functions;

import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

import java.util.Arrays;

public class FunctionDeclarationNode implements ValuedNode {

    private final String name;
    private final Tree body;
    private final FunctionArgumentNode[] args;


    public FunctionDeclarationNode(String name, Tree body, FunctionArgumentNode[] args) {
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
        return "FunctionDeclarationNode{" +
                "name='" + name + '\'' +
                ", body=" + body +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
