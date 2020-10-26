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

    public String getNames() {
        return "{variable="  + this.variable + '}';
    }
}
