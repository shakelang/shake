package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;

import java.util.HashMap;

public class DefaultFunctions {

    public static VariableList getFunctions(Interpreter interpreter) {
        HashMap<String, Variable> functions = new HashMap<>();

        functions.put("print", new Variable("print", VariableType.FUNCTION, new Print(interpreter)));
        functions.put("println", new Variable("println", VariableType.FUNCTION, new Println(interpreter)));

        return new VariableList(functions);
    }

    private static class Print extends Function {

        public Print(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter);
        }

        @Override
        public void call(FunctionCallNode node, Scope scope) {
            System.out.print(formatPrintArgs(node, this.getInterpreter()));
        }
    }

    private static class Println extends Function {

        public Println(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter);
        }

        @Override
        public void call(FunctionCallNode node, Scope scope) {
            System.out.println(formatPrintArgs(node, this.getInterpreter()));
        }
    }

    private static String formatPrintArgs(FunctionCallNode node, Interpreter interpreter) {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < node.getArgs().length-1; i++) {
            out.append(interpreter.visit(node.getArgs()[i]).getValue() + ", ");
        }
        if(node.getArgs().length > 0) out.append(interpreter.visit(node.getArgs()[node.getArgs().length - 1]).getValue());
        return out.toString();
    }
}
