package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableDecreaseNode implements ValuedNode {

    private final ValuedNode variable;

    public VariableDecreaseNode(ValuedNode variable) {
        this.variable = variable;
    }

    public ValuedNode getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return '{' + this.getVariable().toString() + "--}";
    }
}
