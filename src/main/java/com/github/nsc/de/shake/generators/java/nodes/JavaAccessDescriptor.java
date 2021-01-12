package com.github.nsc.de.shake.generators.java.nodes;

import com.github.nsc.de.shake.parser.node.AccessDescriber;

public enum JavaAccessDescriptor {

    PRIVATE ("private "),
    PACKAGE (""),
    PROTECTED ("protected "),
    PUBLIC ("public ");

    private final String name;

    JavaAccessDescriptor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static JavaAccessDescriptor from(AccessDescriber access) {
        switch (access) {
            case PUBLIC: return PUBLIC;
            case PROTECTED: return PROTECTED;
            case PACKAGE: return PACKAGE;
            case PRIVATE: return PRIVATE;
            default: throw new Error();
        }
    }
}
