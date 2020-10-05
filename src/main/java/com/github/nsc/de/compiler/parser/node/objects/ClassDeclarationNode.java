package com.github.nsc.de.compiler.parser.node.objects;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;

import java.util.Arrays;
import java.util.List;

public class ClassDeclarationNode implements ValuedNode {

    private final String name;
    private final VariableDeclarationNode[] fields;
    private final FunctionDeclarationNode[] methods;
    private final ClassDeclarationNode[] classes;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;

    public ClassDeclarationNode(
            String name,
            VariableDeclarationNode[] fields,
            FunctionDeclarationNode[] methods,
            ClassDeclarationNode[] classes,
            AccessDescriber access,
            boolean isInClass,
            boolean isStatic,
            boolean isFinal) {

        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.classes = classes;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isStatic;

    }

    public ClassDeclarationNode(
            String name,
            VariableDeclarationNode[] fields,
            FunctionDeclarationNode[] methods,
            ClassDeclarationNode[] classes) {

        this(name, fields, methods, classes, AccessDescriber.PACKAGE, false, false, false);

    }

    public ClassDeclarationNode(
            String name,
            List<VariableDeclarationNode> fields,
            List<FunctionDeclarationNode> methods,
            List<ClassDeclarationNode> classes,
            AccessDescriber access,
            boolean isInClass,
            boolean isStatic,
            boolean isFinal) {

        this(name, fields.toArray(new VariableDeclarationNode[fields.size()]), methods.toArray(new FunctionDeclarationNode[methods.size()]),
                classes.toArray(new ClassDeclarationNode[classes.size()]), access, isInClass, isStatic, isFinal);

    }

    public ClassDeclarationNode(
            String name,
            List<VariableDeclarationNode> fields,
            List<FunctionDeclarationNode> methods,
            List<ClassDeclarationNode> classes) {

        this(name, fields.toArray(new VariableDeclarationNode[fields.size()]), methods.toArray(new FunctionDeclarationNode[methods.size()]),
                classes.toArray(new ClassDeclarationNode[classes.size()]));

    }

    public String getName() { return name; }
    public VariableDeclarationNode[] getFields() {
        return fields;
    }
    public FunctionDeclarationNode[] getMethods() {
        return methods;
    }
    public ClassDeclarationNode[] getClasses() {
        return classes;
    }
    public AccessDescriber getAccess() { return access; }
    public boolean isStatic() { return isStatic; }
    public boolean isInClass() { return isInClass; }
    public boolean isFinal() { return isFinal; }

    @Override
    public String toString() {
        return "ClassDeclarationNode{" +
                "fields=" + Arrays.toString(fields) +
                ", methods=" + Arrays.toString(methods) +
                '}';
    }
}
