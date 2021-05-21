package com.github.nsc.de.shake.parser.node.functions;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

import java.util.Arrays;

public class FunctionCallNode extends ValuedNode {

    private final ValuedNode function;
    private final ValuedNode[] args;


    public FunctionCallNode(PositionMap map, ValuedNode function, ValuedNode[] args) {
        super(map);
        this.function = function;
        this.args = args;
    }

    public ValuedNode getFunction() {
        return function;
    }

    public ValuedNode[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "FunctionCallNode{" +
                "function='" + getFunction() + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
