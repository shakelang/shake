package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableDivAssignmentNode implements ValuedNode {

    private final ValuedNode variable;
    private final Node value;
    private final int operatorPosition;

    public VariableDivAssignmentNode(ValuedNode variable, Node value, int operatorPosition) {
        this.variable = variable;
        this.value = value;
        this.operatorPosition = operatorPosition;
    }

    public ValuedNode getVariable() {
        return variable;
    }
    public Node getValue() {
        return value;
    }
    public int getOperatorPosition() {
        return operatorPosition;
    }

    @Override
    public String toString() {
        return '{' + getValue().toString() + "/=" + this.getValue().toString() + '}';
    }
}
