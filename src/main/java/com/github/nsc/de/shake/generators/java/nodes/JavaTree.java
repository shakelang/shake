package com.github.nsc.de.shake.generators.java.nodes;

public class JavaTree implements JavaNode {

    private final JavaOperation[] operations;

    public JavaTree(JavaOperation[] operations) {
        this.operations = operations;
    }

    @Override
    public String toString(String indent, String add) {
        StringBuilder str = new StringBuilder("{\n");
        String indent2 = indent + add;
        for(JavaOperation o : operations) {
            str.append(indent2).append(o.toString(indent2, add)).append('\n');
        }
        return str.append(indent).append('}').toString();
    }
}
