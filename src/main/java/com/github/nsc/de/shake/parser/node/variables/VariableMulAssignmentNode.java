package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableMulAssignmentNode implements ValuedNode {

    private final ValuedNode variable;
    private final Node value;

    public VariableMulAssignmentNode(ValuedNode variable, Node value) {
        this.variable = variable;
        this.value = value;
    }

    public ValuedNode getVariable() {
        return variable;
    }

    public Node getValue() {
        return value;
    }

    @Override
    public String toString() {
        return '{' + getValue().toString() + "*=" + this.getValue().toString() + '}';
    }
}
