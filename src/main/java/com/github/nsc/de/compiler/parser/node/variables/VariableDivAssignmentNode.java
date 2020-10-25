package com.github.nsc.de.compiler.parser.node.variables;

import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class VariableDivAssignmentNode implements ValuedNode {

    private final ValuedNode variable;
    private final Node value;

    public VariableDivAssignmentNode(ValuedNode variable, Node value) {
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
        return '{' + getValue().toString() + "/=" + this.getValue().toString() + '}';
    }
}
