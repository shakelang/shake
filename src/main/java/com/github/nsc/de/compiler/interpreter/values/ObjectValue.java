package com.github.nsc.de.compiler.interpreter.values;

import com.github.nsc.de.compiler.interpreter.Scope;
import com.github.nsc.de.compiler.interpreter.Variable;
import com.github.nsc.de.compiler.interpreter.VariableList;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;

public class ObjectValue implements InterpreterValue {

    private Scope scope;
    private VariableList this_object;

    public ObjectValue(Class parent, Scope s) {
        this.scope = new Scope(parent.getScope());
        this.this_object = parent.getPrototype().copy();

        // TODO 2 Declarations with the same name
        for(VariableDeclarationNode node : parent.getFields()) {
            this.this_object.declare(node.getName(), Function.class);
            this.this_object.get(node.getName()).setValue(parent.getInterpreter().visit(node, scope));
        }
    }

    public Scope getScope() {
        return scope;
    }

    public VariableList getThisObject() {
        return this_object;
    }

    @Override
    public Variable getChild(String name) {
        return getThisObject().get(name);
    }

    @Override
    public String getName() {
        return "object";
    }
}
