package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class VariableModAssignmentNode extends ValuedNode {

    private final ValuedNode variable;
    private final Node value;
    private final int operatorPosition;

    public VariableModAssignmentNode(PositionMap map, ValuedNode variable, Node value, int operatorPosition) {
        super(map);
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
        return '{' + getValue().toString() + "%=" + this.getValue().toString() + '}';
    }
}
