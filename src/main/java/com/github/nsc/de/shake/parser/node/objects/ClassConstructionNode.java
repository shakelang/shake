package com.github.nsc.de.shake.parser.node.objects;

import com.github.nsc.de.shake.parser.node.ValuedNode;

import java.util.Arrays;

public class ClassConstructionNode implements ValuedNode {

    private final ValuedNode type;
    private final ValuedNode[] args;

    public ClassConstructionNode(ValuedNode type, ValuedNode[] args) {
        this.type = type;
        this.args = args;
    }



    public ValuedNode getType() {
        return type;
    }

    public ValuedNode[] getArgs() {
        return args;
    }


    @Override
    public String toString() {
        return "ClassConstructionNode{" +
                "type='" + getType() + '\'' +
                ", args=" + Arrays.toString(getArgs()) +
                '}';
    }
}
