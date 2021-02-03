package com.github.nsc.de.shake.parser.node.objects;

import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;

import java.util.Arrays;

public class ConstructorDeclarationNode implements ValuedNode {

    private final String name;
    private final Tree body;
    private final FunctionArgumentNode[] args;
    private final AccessDescriber access;

    public ConstructorDeclarationNode(String name, Tree body, FunctionArgumentNode[] args) {
        this(name, body, args, AccessDescriber.PACKAGE);
    }


    public ConstructorDeclarationNode(String name, Tree body, FunctionArgumentNode[] args, AccessDescriber access) {
        this.name = name;
        this.body = body;
        this.args = args;
        this.access = access;
    }


    public ConstructorDeclarationNode(Tree body, FunctionArgumentNode[] args) {
        this(body, args, AccessDescriber.PACKAGE);
    }


    public ConstructorDeclarationNode(Tree body, FunctionArgumentNode[] args, AccessDescriber access) {
        this(null, body, args, access);
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
    public AccessDescriber getAccess() { return access; }

    @Override
    public String toString() {
        return "FunctionDeclarationNode{" +
                "name='" + name + '\'' +
                ", body=" + body +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
