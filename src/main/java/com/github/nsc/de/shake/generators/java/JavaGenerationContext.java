package com.github.nsc.de.shake.generators.java;

import com.github.nsc.de.shake.generators.java.nodes.JavaClass;

public class JavaGenerationContext {

    private final JavaClass actualClass;
    private final boolean isInRoot;

    public JavaGenerationContext(JavaClass actualClass, boolean isInRoot) {
        this.actualClass = actualClass;
        this.isInRoot = isInRoot;
    }

    public JavaClass getActualClass() {
        return actualClass;
    }

    public boolean isInRoot() {
        return isInRoot;
    }

}
