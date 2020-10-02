package com.github.nsc.de.compiler.parser.node.objects;

import java.util.Arrays;
import java.util.List;

public class ClassNode {

    private final String name;
    private final FieldNode[] fields;
    private final MethodNode[] methods;
    private final MethodNode[] statics;

    public ClassNode(String name, FieldNode[] fields, MethodNode[] methods, MethodNode[] statics) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.statics = statics;
    }

    public ClassNode(String name, List<FieldNode> fields, List<MethodNode> methods, List<MethodNode> statics) {
        this(name, (FieldNode[]) fields.toArray(), (MethodNode[]) methods.toArray(), (MethodNode[]) statics.toArray());
    }

    public String getName() { return name; }
    public FieldNode[] getFields() {
        return fields;
    }
    public MethodNode[] getMethods() {
        return methods;
    }
    public MethodNode[] getStatics() {
        return statics;
    }

    @Override
    public String toString() {
        return "ClassNode{" +
                "fields=" + Arrays.toString(fields) +
                ", methods=" + Arrays.toString(methods) +
                ", statics=" + Arrays.toString(statics) +
                '}';
    }
}
