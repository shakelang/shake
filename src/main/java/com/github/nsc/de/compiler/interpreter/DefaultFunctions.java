package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;

import java.util.HashMap;

public class DefaultFunctions {

    public static VariableList getFunctions(Interpreter interpreter) {
        HashMap<String, Variable> functions = new HashMap<>();

        functions.put("print", new Variable("print", VariableType.FUNCTION, new Print(interpreter)));

        return new VariableList(functions);
    }

    private static class Print extends Function {

        public Print(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter);
        }

        @Override
        public void call(FunctionCallNode node, Scope scope) {
            String out = "";
            for(int i = 0; i < node.getArgs().length-1; i++) {
                out += this.getInterpreter().visit(node.getArgs()[i]).getValue() + ", ";
            }
            if(node.getArgs().length > 0) out += this.getInterpreter().visit(node.getArgs()[node.getArgs().length - 1]).getValue();
            System.out.println(out);
        }
    }
}
