package com.github.nsc.de.shake.parser.node.variables;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.Node;
import com.github.nsc.de.shake.parser.node.ValuedNode;

public class VariableAddAssignmentNode extends ValuedNode {

    private final ValuedNode variable;
    private final Node value;
    private final int operatorPosition;

    public VariableAddAssignmentNode(PositionMap map, ValuedNode variable, Node value, int operatorPosition) {
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
        return '{' + getValue().toString() + "+=" + this.getValue().toString() + '}';
    }
}
