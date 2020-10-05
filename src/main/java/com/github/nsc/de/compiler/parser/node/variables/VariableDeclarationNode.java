package com.github.nsc.de.compiler.parser.node.variables;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

public class VariableDeclarationNode implements ValuedNode {

    private final String name;
    private final VariableType type;
    private final VariableAssignmentNode assignment;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;

    public VariableDeclarationNode(String name, VariableType type, VariableAssignmentNode assignment) {
        this(name, type, assignment, AccessDescriber.PACKAGE, false, false, false);
    }

    public VariableDeclarationNode(String name, VariableType type, VariableAssignmentNode assignment,
                                   AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.type = type;
        this.assignment = assignment;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    public VariableDeclarationNode(String name, VariableType type) { this(name, type, null); }

    public VariableDeclarationNode(String name, VariableAssignmentNode assignment) { this(name, VariableType.DYNAMIC, assignment); }

    public VariableDeclarationNode(String name) { this(name, VariableType.DYNAMIC, null); }

    public String getName() {
        return name;
    }
    public VariableType getType() { return type; }
    public VariableAssignmentNode getAssignment() { return assignment; }
    public AccessDescriber getAccess() { return access; }
    public boolean isStatic() { return isStatic; }
    public boolean isInClass() { return isInClass; }
    public boolean isFinal() { return isFinal; }

    @Override
    public String toString() {
        return this.getAssignment() != null ? "var " + this.getAssignment() : "var " + this.getName();
    }

    public static class VariableType {

        public static VariableType DYNAMIC = new VariableType(Type.DYNAMIC);
        public static VariableType BYTE = new VariableType(Type.BYTE);
        public static VariableType SHORT = new VariableType(Type.SHORT);
        public static VariableType INTEGER = new VariableType(Type.INTEGER);
        public static VariableType LONG = new VariableType(Type.LONG);
        public static VariableType FLOAT = new VariableType(Type.FLOAT);
        public static VariableType DOUBLE = new VariableType(Type.DOUBLE);
        public static VariableType BOOLEAN = new VariableType(Type.BOOLEAN);
        public static VariableType CHAR = new VariableType(Type.CHAR);
        public static VariableType ARRAY = new VariableType(Type.ARRAY);
        public static VariableType OBJECT = new VariableType(Type.OBJECT);

        private final Type type;
        private final String subtype;

        public VariableType(Type type, String subtype) {
            this.type = type;
            this.subtype = subtype;
        }

        public VariableType(Type type) {
            this.type = type;
            this.subtype = null;
        }

        public Type getType() {
            return type;
        }

        public String getSubtype() {
            return subtype;
        }

        public enum Type {
            DYNAMIC,
            BYTE,
            SHORT,
            INTEGER,
            LONG,
            FLOAT,
            DOUBLE,
            BOOLEAN,
            CHAR,
            ARRAY,
            OBJECT,
        }
    }
}
