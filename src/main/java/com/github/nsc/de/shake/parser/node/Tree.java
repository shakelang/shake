package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;

import java.util.Arrays;
import java.util.List;

public class Tree extends Node {

    private final Node[] children;

    public Tree(PositionMap map, Node[] children) {
        super(map);
        this.children = children;
    }

    public Tree(PositionMap map, List<Node> children) {
        this(map, children.toArray(new Node[0]));
    }

    public Node[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Tree{" + "children=" + Arrays.toString(children) + '}';
    }
}
