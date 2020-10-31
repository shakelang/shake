package com.github.nsc.de.compiler.parser.node.variables;

import com.github.nsc.de.compiler.parser.node.IdentifierNode;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

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
