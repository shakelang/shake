package com.github.nsc.de.compiler.parser.node.functions;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

import java.util.Arrays;

public class FunctionCallNode implements ValuedNode {

    private final ValuedNode function;
    private final ValuedNode[] args;


    public FunctionCallNode(ValuedNode function, ValuedNode[] args) {
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
