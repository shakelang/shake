package com.github.nsc.de.shake.parser.node.objects;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

import java.util.Arrays;

public class ClassConstructionNode extends ValuedNode {

    private final ValuedNode type;
    private final ValuedNode[] args;
    private final int newKeywordPosition;

    public ClassConstructionNode(PositionMap map, ValuedNode type, ValuedNode[] args, int newKeywordPosition) {
        super(map);
        this.type = type;
        this.args = args;
        this.newKeywordPosition = newKeywordPosition;
    }



    public ValuedNode getType() {
        return type;
    }
    public ValuedNode[] getArgs() {
        return args;
    }
    public int getNewKeywordPosition() {
        return newKeywordPosition;
    }

    @Override
    public String toString() {
        return "ClassConstructionNode{" +
                "type='" + getType() + '\'' +
                ", args=" + Arrays.toString(getArgs()) +
                '}';
    }
}
