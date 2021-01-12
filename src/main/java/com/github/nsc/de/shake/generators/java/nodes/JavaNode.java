package com.github.nsc.de.shake.generators.java.nodes;

public interface JavaNode {

    /**
     * @deprecated WARNING: To generate java-code use {@link #toString(String, String)}
     */
    @Deprecated
    String toString();

    /**
     * Generates Java code
     * @param indent the space the code should be indented with
     * @param add the space to be added to indent per indention
     * @return the generated java code
     */
    String toString(String indent, String add);

    interface JavaOperation extends JavaNode {  }
    interface JavaValuedOperation extends JavaOperation {  }
    interface JavaType extends JavaNode { }


}
