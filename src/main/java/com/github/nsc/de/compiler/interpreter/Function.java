package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;

import java.util.Arrays;

public class Function {

    private final FunctionArgumentNode[] args;
    private final Tree body;
    private final Scope scope;
    private final Interpreter interpreter;


    public Function(FunctionArgumentNode[] args, Tree body, Scope scope, Interpreter interpreter) {
        this.args = args;
        this.body = body;
        this.scope = scope;
        this.interpreter = interpreter;
    }

    public FunctionArgumentNode[] getArgs() {
        return args;
    }
    public Tree getBody() {
        return body;
    }
    public Interpreter getInterpreter() {
        return interpreter;
    }
    public Scope getScope() {
        return scope;
    }

    public void call(FunctionCallNode node, Scope scope) {
        Scope function_scope = new Scope(this.getScope());
        for(int i = 0; i < this.getArgs().length; i++) {

            function_scope.getScopeVariables().declare(this.getArgs()[i].getName(), VariableType.ANY);
            function_scope.getScopeVariables().get(this.getArgs()[i].getName()).setValue(interpreter.visit(node.getArgs()[i], scope).getValue());

        }
        this.getInterpreter().visit(this.getBody(), function_scope);
    }

    @Override
    public String toString() { return "Function{" + "args=" + Arrays.toString(args) + ", body=" + body + '}'; }
}
