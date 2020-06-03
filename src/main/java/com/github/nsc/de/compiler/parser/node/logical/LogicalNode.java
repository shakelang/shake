package com.github.nsc.de.compiler.parser.node.logical;

import com.github.nsc.de.compiler.parser.node.ValuedNode;

public interface LogicalNode extends ValuedNode {

    String getOperator();

}
