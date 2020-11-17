package com.github.nsc.de.compiler.interpreter.values;

public class VariableType {

    public static java.lang.Class<? extends InterpreterValue> valueOf(com.github.nsc.de.compiler.parser.node.VariableType type) {
        switch (type.getType()) {
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
                return IntegerValue.class;
            case FLOAT:
            case DOUBLE:
                return DoubleValue.class;
            case BOOLEAN:
                return BooleanValue.class;
            case CHAR:
            case OBJECT:
            case ARRAY:
                throw new Error("Not implemented yet");
        }
        throw new Error("Wrong input");
    }


}
