package com.github.nsc.de.shake.parser.node;

public class IdentifierNode implements ValuedNode {

    private final ValuedNode parent;
    private final String name;
    private final int position;

    public IdentifierNode(ValuedNode parent, String name, int position) {
        this.parent = parent;
        this.name = name;
        this.position = position;
    }

    public IdentifierNode(String name, int position) {
        this.position = position;
        this.parent = null;
        this.name = name;
    }

    public ValuedNode getParent() {
        return parent;
    }
    public String getName() {
        return name;
    }
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "IdentifierNode{" +
                "parent=" + parent +
                ", name='" + name + '\'' +
                '}';
    }
}
