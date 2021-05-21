package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class IdentifierNode extends ValuedNode {

    private final ValuedNode parent;
    private final String name;
    private final int position;

    public IdentifierNode(PositionMap map, ValuedNode parent, String name, int position) {
        super(map);
        this.parent = parent;
        this.name = name;
        this.position = position;
    }

    public IdentifierNode(PositionMap map, String name, int position) {
        super(map);
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
