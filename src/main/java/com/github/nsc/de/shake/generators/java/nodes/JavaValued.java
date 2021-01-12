package com.github.nsc.de.shake.generators.java.nodes;

public class JavaValued {

    public static class JavaExpression implements JavaNode.JavaValuedOperation {

        private final JavaValuedOperation left;
        private final JavaValuedOperation right;
        private final String operator;

        public JavaExpression(JavaValuedOperation left, JavaValuedOperation right, String operator) {
            this.left = left;
            this.right = right;
            this.operator = operator;
        }


        @Override
        public String toString(String indent, String add) {
            return left.toString(indent, add) + ' ' + operator + ' ' + right.toString(indent, add);
        }
    }

    public static class JavaValue implements JavaNode.JavaValuedOperation {
        private final String value;

        public JavaValue(String value) {
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return value;
        }
    }

    public static class JavaPriorityPart implements JavaNode.JavaValuedOperation {

        private final JavaValuedOperation operation;

        public JavaPriorityPart(JavaValuedOperation operation) {
            this.operation = operation;
        }

        @Override
        public String toString(String indent, String add) {
            return '(' + operation.toString() + ')';
        }
    }

    public static class JavaDoublePart implements JavaNode.JavaValuedOperation {

        private final double value;

        public JavaDoublePart(double value) {
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return String.valueOf(this.value);
        }
    }

    public static class JavaIntegerPart implements JavaNode.JavaValuedOperation {

        private final int value;

        public JavaIntegerPart(int value) {
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return String.valueOf(this.value);
        }

    }

    public static class JavaVariable implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier variable;

        public JavaVariable(JavaIdentifier variable) {
            this.variable = variable;
        }

        @Override
        public String toString(String indent, String add) {
            return this.variable.toString(indent, add);
        }
    }

    public static class JavaFunctionCall implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier function;
        private final JavaValuedOperation[] args;

        public JavaFunctionCall(JavaIdentifier function, JavaValuedOperation[] args) {
            this.function = function;
            this.args = args;
        }

        @Override
        public String toString(String indent, String add) {
            StringBuilder str = new StringBuilder();
            str.append(this.function.toString(indent, add)).append('(');
            for(int i = 0; i < args.length; i++) {
                str.append(args[i].toString(indent, add));
                if(i < args.length - 1) str.append(", ");
            }
            return str.append(")").toString();
        }

    }

    public static class JavaConstruction implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier function;
        private final JavaValuedOperation[] args;

        public JavaConstruction(JavaIdentifier function, JavaValuedOperation[] args) {
            this.function = function;
            this.args = args;
        }

        @Override
        public String toString(String indent, String add) {
            StringBuilder str = new StringBuilder("new ").append(this.function.toString(indent, add)).append('(');
            for(int i = 0; i < args.length; i++) {
                str.append(args[i].toString(indent, add));
                if(i < args.length - 1) str.append(", ");
            }
            return str.append(")").toString();
        }

    }

    public static class JavaVariableAssignment implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier name;
        private final JavaValuedOperation value;

        public JavaVariableAssignment(JavaIdentifier name, JavaValuedOperation value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return name.toString(indent, add) + " = " + value.toString(indent, add);
        }
    }

    public static class JavaVariableExpressionAssignment implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier name;
        private final JavaValuedOperation value;
        private final char operator;

        public JavaVariableExpressionAssignment(JavaIdentifier name, JavaValuedOperation value, char operator) {
            this.name = name;
            this.value = value;
            this.operator = operator;
        }

        @Override
        public String toString(String indent, String add) {
            return name.toString(indent, add) + ' ' + this.operator + "= " + value.toString(indent, add);
        }
    }

    public static class JavaVariableIncr implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier variable;

        public JavaVariableIncr(JavaIdentifier variable) {
            this.variable = variable;
        }

        @Override
        public String toString(String indent, String add) {
            return this.variable.toString(indent, add) + "++";
        }
    }

    public static class JavaVariableDecr implements JavaNode.JavaValuedOperation {

        private final JavaIdentifier variable;

        public JavaVariableDecr(JavaIdentifier variable) {
            this.variable = variable;
        }

        @Override
        public String toString(String indent, String add) {
            return this.variable.toString(indent, add) + "--";
        }
    }
}
