package com.github.nsc.de.shake.parser.node;

import java.util.Arrays;
import java.util.List;

public class Tree implements Node {

    private final Node[] children;

    public Tree(Node[] children) {
        this.children = children;
    }

    public Tree(List<Node> children) {
        this(children.toArray(new Node[0]));
    }

    public Node[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Tree{" + "children=" + Arrays.toString(children) + '}';
    }
}
