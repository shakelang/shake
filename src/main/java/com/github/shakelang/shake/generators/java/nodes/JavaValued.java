package com.github.shakelang.shake.generators.java.nodes;

public interface JavaValued extends JavaNode {

    class JavaExpression implements JavaValued {

        private final JavaValued left;
        private final JavaValued right;
        private final String operator;

        public JavaExpression(JavaValued left, JavaValued right, String operator) {
            this.left = left;
            this.right = right;
            this.operator = operator;
        }

        @Override
        public String toString(String indent, String add) {
            return left.toString(indent, add) + ' ' + operator + ' ' + right.toString(indent, add);
        }
    }

    class JavaValue implements JavaValued {
        private final String value;

        public JavaValue(String value) {
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return value;
        }
    }

    class JavaPriorityPart implements JavaValued {

        private final JavaValued operation;

        public JavaPriorityPart(JavaValued operation) {
            this.operation = operation;
        }

        @Override
        public String toString(String indent, String add) {
            return '(' + operation.toString() + ')';
        }
    }

    class JavaDoublePart implements JavaValued {

        private final double value;

        public JavaDoublePart(double value) {
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return String.valueOf(this.value);
        }
    }

    class JavaIntegerPart implements JavaValued {

        private final int value;

        public JavaIntegerPart(int value) {
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return String.valueOf(this.value);
        }

    }

    class JavaVariable implements JavaValued {

        private final JavaIdentifier variable;

        public JavaVariable(JavaIdentifier variable) {
            this.variable = variable;
        }

        @Override
        public String toString(String indent, String add) {
            return this.variable.toString(indent, add);
        }
    }

    class JavaFunctionCall implements JavaValuedOperation {

        private final JavaIdentifier function;
        private final JavaValued[] args;

        public JavaFunctionCall(JavaIdentifier function, JavaValued[] args) {
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

    class JavaConstruction implements JavaValuedOperation {

        private final JavaIdentifier function;
        private final JavaValued[] args;

        public JavaConstruction(JavaIdentifier function, JavaValued[] args) {
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

    class JavaVariableAssignment implements JavaValuedOperation {

        private final JavaIdentifier name;
        private final JavaValued value;

        public JavaVariableAssignment(JavaIdentifier name, JavaValued value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString(String indent, String add) {
            return name.toString(indent, add) + " = " + value.toString(indent, add);
        }
    }

    class JavaVariableExpressionAssignment implements JavaValuedOperation {

        private final JavaIdentifier name;
        private final JavaValued value;
        private final char operator;

        public JavaVariableExpressionAssignment(JavaIdentifier name, JavaValued value, char operator) {
            this.name = name;
            this.value = value;
            this.operator = operator;
        }

        @Override
        public String toString(String indent, String add) {
            return name.toString(indent, add) + ' ' + this.operator + "= " + value.toString(indent, add);
        }
    }

    class JavaVariableIncr implements JavaValuedOperation {

        private final JavaIdentifier variable;

        public JavaVariableIncr(JavaIdentifier variable) {
            this.variable = variable;
        }

        @Override
        public String toString(String indent, String add) {
            return this.variable.toString(indent, add) + "++";
        }
    }

    class JavaVariableDecr implements JavaValuedOperation {

        private final JavaIdentifier variable;

        public JavaVariableDecr(JavaIdentifier variable) {
            this.variable = variable;
        }

        @Override
        public String toString(String indent, String add) {
            return this.variable.toString(indent, add) + "--";
        }
    }

    interface JavaValuedOperation extends JavaOperation, JavaValued {  }
}
