package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.IdentifierNode;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableUsageNode extends ValuedNode {

    private final IdentifierNode variable;

    public VariableUsageNode(PositionMap map, IdentifierNode variable) {
        super(map);
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
