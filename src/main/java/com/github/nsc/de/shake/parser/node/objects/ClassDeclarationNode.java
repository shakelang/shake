package com.github.nsc.de.shake.parser.node.objects;

import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.ValuedNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode;

import java.util.Arrays;
import java.util.List;

public class ClassDeclarationNode implements ValuedNode {

    private final String name;
    private final VariableDeclarationNode[] fields;
    private final FunctionDeclarationNode[] methods;
    private final ClassDeclarationNode[] classes;
    private final ConstructorDeclarationNode[] constructors;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;

    public ClassDeclarationNode(
            String name,
            VariableDeclarationNode[] fields,
            FunctionDeclarationNode[] methods,
            ClassDeclarationNode[] classes,
            ConstructorDeclarationNode[] constructors,
            AccessDescriber access,
            boolean isInClass,
            boolean isStatic,
            boolean isFinal) {

        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.classes = classes;
        this.constructors = constructors;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;

    }

    public ClassDeclarationNode(
            String name,
            VariableDeclarationNode[] fields,
            FunctionDeclarationNode[] methods,
            ClassDeclarationNode[] classes,
            ConstructorDeclarationNode[] constructors) {

        this(name, fields, methods, classes, constructors, AccessDescriber.PACKAGE, false, false, false);

    }

    public ClassDeclarationNode(
            String name,
            List<VariableDeclarationNode> fields,
            List<FunctionDeclarationNode> methods,
            List<ClassDeclarationNode> classes,
            List<ConstructorDeclarationNode> constructors,
            AccessDescriber access,
            boolean isInClass,
            boolean isStatic,
            boolean isFinal) {

        this(name, fields.toArray(new VariableDeclarationNode[] {}), methods.toArray(new FunctionDeclarationNode[] {}),
                classes.toArray(new ClassDeclarationNode[] {}), constructors.toArray(new ConstructorDeclarationNode[] {}),
                access, isInClass, isStatic, isFinal);

    }

    public ClassDeclarationNode(
            String name,
            List<VariableDeclarationNode> fields,
            List<FunctionDeclarationNode> methods,
            List<ClassDeclarationNode> classes,
            List<ConstructorDeclarationNode> constructors) {

        this(name, fields.toArray(new VariableDeclarationNode[] {}), methods.toArray(new FunctionDeclarationNode[] {}),
                classes.toArray(new ClassDeclarationNode[] {}), constructors.toArray(new ConstructorDeclarationNode[] {}));

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
    public ConstructorDeclarationNode[] getConstructors() {
        return constructors;
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
                ", classes=" + Arrays.toString(classes) +
                ", constructors=" + Arrays.toString(constructors) +
                '}';
    }
}
