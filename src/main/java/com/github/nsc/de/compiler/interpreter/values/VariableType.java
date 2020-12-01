package com.github.nsc.de.compiler.interpreter.values;

public class VariableType {

    /**
     * This function just converts a {@link com.github.nsc.de.compiler.parser.node.VariableType} into a {@link InterpreterValue}-class
     * that can be used as type for a {@link com.github.nsc.de.compiler.interpreter.Variable}
     *
     * @param type the type to convert
     * @return the converted type
     *
     * @author Nicolas Schmidt
     */
    public static java.lang.Class<? extends InterpreterValue> valueOf(com.github.nsc.de.compiler.parser.node.VariableType type) {
        switch (type.getType()) {
            // Return for all number-value types without decimal places the IntegerValue class
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
                return IntegerValue.class;

            // Return for all number-value types with decimal places the DoubleValue class
            case FLOAT:
            case DOUBLE:
                return DoubleValue.class;

            // For booleans just return the BooleanValue class
            case BOOLEAN:
                return BooleanValue.class;
            // For Objects return the ObjectValue class
            case OBJECT:
                return ObjectValue.class; // TODO object-subtypes
            // For Dynamic return the InterpreterValue class (as super-class of all
            // interpreter-values it can hold all of them)
            case DYNAMIC:
                return InterpreterValue.class;
            // TODO Char & Array are not implemented at the moment
            case CHAR:
            case ARRAY:
                throw new Error("Not implemented yet");
        }
        throw new Error(String.format("Wrong input: %s", type.getType()));
    }


}
