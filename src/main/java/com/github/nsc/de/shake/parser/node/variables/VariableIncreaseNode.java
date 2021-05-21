package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class VariableIncreaseNode extends ValuedNode {

    private final ValuedNode variable;
    private final int operatorPosition;

    public VariableIncreaseNode(PositionMap map, ValuedNode variable, int operatorPosition) {
        super(map);
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
        return '{' + this.getVariable().toString() + "++}";
    }
}
