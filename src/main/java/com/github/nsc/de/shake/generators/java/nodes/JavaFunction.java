package com.github.nsc.de.shake.generators.java.nodes;

public class JavaFunction implements JavaNode {

    private final String name;
    private final JavaVariableType type;
    private final JavaFunctionArgument[] args;
    private final JavaTree body;
    private final JavaAccessDescriptor access;
    private final boolean isStatic;
    private final boolean isFinal;

    public JavaFunction(String name, JavaVariableType type, JavaFunctionArgument[] args, JavaTree body,
                        JavaAccessDescriptor access, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.type = type;
        this.args = args;
        this.body = body;
        this.access = access;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    @Override
    public String toString(String indent, String add) {
        StringBuilder str = new StringBuilder().append(this.access);
        if(isStatic) str.append("static ");
        if(isFinal) str.append("final ");
        str.append(this.type.toString()).append(' ').append(this.name).append("(");
        for(int i = 0; i < args.length; i++) {
            str.append(args[i].toString(indent, add));
            if(i < args.length - 1) str.append(", ");
        }
        return str.append(") ").append(this.body.toString(indent, add)).toString();
    }

    public static class JavaFunctionArgument implements JavaNode {

        private final String type;
        private final String name;

        public JavaFunctionArgument(String type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString(String _indent, String _add) {
            return this.type + ' ' + this.name;
        }
    }

}
