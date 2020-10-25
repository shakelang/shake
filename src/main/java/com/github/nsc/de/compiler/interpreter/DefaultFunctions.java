package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;

import java.util.HashMap;

public class DefaultFunctions {

    /**
     * Creates a HashMap with the default functions
     * @param interpreter the interpreter that needs the default functions
     * @return the default functions
     */
    public static VariableList getFunctions(Interpreter interpreter) {
        HashMap<String, Variable> functions = new HashMap<>();

        functions.put("print", new Variable("print", VariableType.FUNCTION, new Print(interpreter)));
        functions.put("println", new Variable("println", VariableType.FUNCTION, new Println(interpreter)));
        functions.put("exit", new Variable("println", VariableType.FUNCTION, new Exit(interpreter)));

        return new VariableList(functions);
    }

    /**
     * prints anything to the console, that you give as argument
     */
    private static class Print extends Function {

        public Print(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter, AccessDescriber.PUBLIC, false, false, true);
        }

        @Override
        public void call(FunctionCallNode node, Scope scope) {
            System.out.print(formatPrintArgs(node, this.getInterpreter(), scope));
        }
    }


    /**
     * Works similar to {@link Print}, but adds a new line after the printed content
     */
    private static class Println extends Function {

        public Println(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter, AccessDescriber.PUBLIC, false, false, true);
        }

        @Override
        public void call(FunctionCallNode node, Scope scope) {
            System.out.println(formatPrintArgs(node, this.getInterpreter(), scope));
        }
    }


    /**
     * Exits the program with a given exit code
     */
    private static class Exit extends Function {

        public Exit(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter, AccessDescriber.PUBLIC, false, false, true);
        }

        @Override
        public void call(FunctionCallNode node, Scope scope) {
            if(node.getArgs().length > 1) throw new Error("Expecting 0-1 args for the exit function");
            else if(node.getArgs().length == 0) System.exit(0);
            else {
                Object i = getInterpreter().visit(node.getArgs()[0], scope).getValue();
                if(i instanceof Integer) System.exit((Integer) i);
                else if(i instanceof Double) System.exit((int)(double)(Double) i);
                else throw new Error("Expecting an integer as argument for the exit function");
            }
        }
    }


    /**
     * Formatter for the Arguments to print out
     * @param node the node that called the print function
     * @param interpreter the executing interpreter instance to process arguments
     * @param scope the actual variable scope to get variables that are given as arguments
     * @return a formated string to print
     */
    private static String formatPrintArgs(FunctionCallNode node, Interpreter interpreter, Scope scope) {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < node.getArgs().length-1; i++) {
            out.append(interpreter.visit(node.getArgs()[i], scope).getValue()).append(", ");
        }
        if(node.getArgs().length > 0) out.append(interpreter.visit(node.getArgs()[node.getArgs().length - 1], scope).getValue());
        return out.toString();
    }
}
