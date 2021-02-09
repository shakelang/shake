package com.github.nsc.de.shake.parser.node.expression;

import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;

public class SubNode extends ExpressionNode {
    public SubNode(PositionMap map, ValuedNode left, ValuedNode right, int operatorPosition) {
        super(map, left, right, operatorPosition);
    }
    public SubNode(PositionMap map, int left, ValuedNode right, int operatorPosition) {
        this(map, new IntegerNode(map, left), right, operatorPosition);
    }
    public SubNode(PositionMap map, ValuedNode left, int right, int operatorPosition) {
        this(map, left, new IntegerNode(map, right), operatorPosition);
    }
    public SubNode(PositionMap map, int left, int right, int operatorPosition) {
        this(map, new IntegerNode(map, left), new IntegerNode(map, right), operatorPosition);
    }
    public SubNode(PositionMap map, double left, ValuedNode right, int operatorPosition) {
        this(map, new DoubleNode(map, left), right, operatorPosition);
    }
    public SubNode(PositionMap map, ValuedNode left, double right, int operatorPosition) {
        this(map, left, new DoubleNode(map, right), operatorPosition);
    }
    public SubNode(PositionMap map, double left, double right, int operatorPosition) {
        this(map, new DoubleNode(map, left), new DoubleNode(map, right), operatorPosition);
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
