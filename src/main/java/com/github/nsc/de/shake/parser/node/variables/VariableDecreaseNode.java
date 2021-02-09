package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableDecreaseNode implements ValuedNode {

    private final ValuedNode variable;
    private final int operatorPosition;

    public VariableDecreaseNode(ValuedNode variable, int operatorPosition) {
        this.variable = variable;
        this.operatorPosition = operatorPosition;
    }

    public ValuedNode getVariable() {
        return variable;
    }
    public int getOperatorPosition() {
        return operatorPosition;
    }

    @Override
    public String toString() {
        return '{' + this.getVariable().toString() + "--}";
    }
}
