package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.IdentifierNode;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableUsageNode implements ValuedNode {

    private final IdentifierNode variable;

    public VariableUsageNode(IdentifierNode variable) {
        this.variable = variable;
    }
    
    public IdentifierNode getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return "{variable="  + this.variable + '}';
    }
}
