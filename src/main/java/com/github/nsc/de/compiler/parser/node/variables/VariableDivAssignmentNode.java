package com.github.nsc.de.compiler.parser.node.variables;

import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class VariableDivAssignmentNode implements ValuedNode {

    private final String name;
    private final Node value;

    public VariableDivAssignmentNode(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Node getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + " /= " + this.getValue();
    }
}
