package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;

import java.util.Arrays;

public class Function {

    private final FunctionArgumentNode[] args;
    private final Tree body;
    private final Scope scope;
    private final Interpreter interpreter;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;

    public Function(FunctionArgumentNode[] args, Tree body, Scope scope, Interpreter interpreter,
                    AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.args = args;
        this.body = body;
        this.scope = scope;
        this.interpreter = interpreter;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
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
    public AccessDescriber getAccess() { return access; }
    public boolean isInClass() { return isInClass; }
    public boolean isStatic() { return isStatic; }
    public boolean isFinal() { return isFinal; }

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
