package com.github.nsc.de.shake.parser.node.objects;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;

import java.util.Arrays;

public class ConstructorDeclarationNode extends ValuedNode {

    private final String name;
    private final Tree body;
    private final FunctionArgumentNode[] args;
    private final AccessDescriber access;

    public ConstructorDeclarationNode(PositionMap map, String name, Tree body, FunctionArgumentNode[] args) {
        this(map, name, body, args, AccessDescriber.PACKAGE);
    }


    public ConstructorDeclarationNode(PositionMap map, String name, Tree body, FunctionArgumentNode[] args,
                                      AccessDescriber access) {
        super(map);
        this.name = name;
        this.body = body;
        this.args = args;
        this.access = access;
    }


    public ConstructorDeclarationNode(PositionMap map, Tree body, FunctionArgumentNode[] args) {
        this(map, body, args, AccessDescriber.PACKAGE);
    }


    public ConstructorDeclarationNode(PositionMap map, Tree body, FunctionArgumentNode[] args, AccessDescriber access) {
        this(map, null, body, args, access);
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
