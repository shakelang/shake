package com.github.nsc.de.compiler.generators.java.nodes;

public class JavaFunction implements JavaNode {

    private final String name;
    private final JavaType type;
    private final JavaFunctionArgument[] args;
    private final JavaTree body;
    private final JavaAccess access;
    private final boolean isStatic;
    private final boolean isFinal;

    public JavaFunction(String name, JavaType type, JavaFunctionArgument[] args, JavaTree body,
                        JavaAccess access, boolean isStatic, boolean isFinal) {
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
        str.append(this.name).append("(");
        for(int i = 0; i < args.length; i++) {
            str.append(args[i].toString(indent, add));
            if(i < args.length - 1) str.append(", ");
        }
        return str.append(") ").append(this.body.toString(indent, add)).toString();
    }

    public enum JavaAccess {

        PUBLIC("public "),
        PACKAGE(""),
        PROTECTED("protected "),
        PRIVATE("private ");

        private final String value;

        JavaAccess(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
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
