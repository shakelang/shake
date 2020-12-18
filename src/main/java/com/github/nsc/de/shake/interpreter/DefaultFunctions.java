package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.interpreter.values.*;
import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;

import java.util.HashMap;

public class DefaultFunctions {

    /**
     * Creates a HashMap with the default functions
     * @param interpreter the interpreter that needs the default functions
     * @return the default functions
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public static VariableList getFunctions(Interpreter interpreter) {

        // The Map to contain the default functions
        HashMap<String, Variable> functions = new HashMap<>();

        // put instances of all default functions inside of the function-list
        functions.put("print", new Variable<>("print", Function.class, new Print(interpreter)));
        functions.put("println", new Variable<>("println", Function.class, new Println(interpreter)));
        functions.put("exit", new Variable<>("println", Function.class, new Exit(interpreter)));
        functions.put("java", new Variable<>("java", Java.class, new Java()));

        // Create a new VariableList from the default functions and return it
        return new VariableList(functions);
    }

    /**
     * Prints anything to the console, that you give as argument
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Println
     */
    private static class Print extends Function {

        /**
         * Constructor for {@link Print}
         *
         * @param interpreter the interpreter to create the print function
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        public Print(Interpreter interpreter) {
            // redirect to the constructor of Function class
            super(new FunctionArgumentNode[] {}, null, null, interpreter, AccessDescriber.PUBLIC, true);
        }

        @Override
        public InterpreterValue invoke(FunctionCallNode node, Scope scope) {
            System.out.print(formatPrintArgs(node, this.getInterpreter(), scope));
            return NullValue.NULL;
        }
    }


    /**
     * Works similar to {@link Print}, but adds a new line after the printed content
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Print
     */
    private static class Println extends Function {

        /**
         * Constructor for {@link Println}
         *
         * @param interpreter the interpreter to create the print function
         *
         * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
         */
        public Println(Interpreter interpreter) {
            // redirect to the constructor of Function class
            super(new FunctionArgumentNode[] {}, null, null, interpreter, AccessDescriber.PUBLIC, true);
        }

        @Override
        public InterpreterValue invoke(FunctionCallNode node, Scope scope) {
            System.out.println(formatPrintArgs(node, this.getInterpreter(), scope));
            return NullValue.NULL;
        }
    }


    /**
     * Exits the program with a given exit code
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    private static class Exit extends Function {

        public Exit(Interpreter interpreter) {
            super(new FunctionArgumentNode[] {}, null, null, interpreter, AccessDescriber.PUBLIC, true);
        }

        @Override
        public InterpreterValue invoke(FunctionCallNode node, Scope scope) {

            // check the number of arguments. If it is bigger than 1 then just throw an error
            if(node.getArgs().length > 1) throw new Error("Expecting 0-1 args for the exit function");

                // if no arguments are given, then just exit with code 0
            else if(node.getArgs().length == 0) System.exit(0);

                // called, if the function has one argument
            else {
                // visit the given argument
                InterpreterValue i = getInterpreter().visit(node.getArgs()[0], scope);

                // if the argument is an integer, then give it as exit-code
                if(i instanceof IntegerValue) System.exit(((IntegerValue) i).getValue());

                    // if the argument is a double, cast it to an integer and give it as exit-code
                else if(i instanceof DoubleValue) System.exit((int)((DoubleValue) i).getValue());

                    // in other case throw an error
                else throw new Error("Expecting an integer as argument for the exit function");
            }

            return NullValue.NULL;
        }
    }


    /**
     * Formatter for the Arguments to print out
     *
     * @param node the node that called the print function
     * @param interpreter the executing interpreter instance to process arguments
     * @param scope the actual variable scope to get variables that are given as arguments
     * @return a formatted string to print
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    private static String formatPrintArgs(FunctionCallNode node, Interpreter interpreter, Scope scope) {

        // Create a StringBuilder for creating the String to print
        StringBuilder out = new StringBuilder();

        // Loop over all the arguments except the last one
        // The reason we ignore the last argument in this loop
        // is that we don't want to add a comma behind it, but
        // we have to add the comma inside of this loop.
        // We also don't want to check, if it is the last argument
        // each round, so we will progress the last argument later
        for(int i = 0; i < node.getArgs().length-1; i++) {

            // append the argument value to the output-string
            out.append(interpreter.visit(node.getArgs()[i], scope).toString()).append(", ");

        }

        // This is the function for adding the value of the last argument
        if(node.getArgs().length > 0) out.append(interpreter.visit(node.getArgs()[node.getArgs().length - 1], scope).toString());

        // Create a output-string and return it
        return out.toString();
    }
}
