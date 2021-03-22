package com.github.nsc.de.shake.generators.java.nodes;

import java.util.Arrays;

public class JavaIdentifier implements JavaValued {

    private final String identifier;
    private final JavaValued parent;

    public JavaIdentifier(String identifier, JavaValued parent) {
        this.identifier = identifier;
        this.parent = parent;
    }

    public JavaIdentifier(String identifier, String ...parents) {
        this.identifier = identifier;
        if(parents.length > 0) {
            this.parent = new JavaIdentifier(parents[0],
                    Arrays.copyOfRange(parents, 1, parents.length));
        } else this.parent = null;
    }

    @Override
    public String toString(String indent, String add) {
        return (this.parent != null ? this.parent.toString(indent, add) + '.': "") + identifier;
    }
}
