package com.github.nsc.de.compiler.parser.node.variables;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class VariableIncreaseNode implements ValuedNode {

    private final ValuedNode variable;

    public VariableIncreaseNode(ValuedNode variable) {
        this.variable = variable;
    }

    public ValuedNode getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return '{' + this.getVariable().toString() + "++}";
    }
}
