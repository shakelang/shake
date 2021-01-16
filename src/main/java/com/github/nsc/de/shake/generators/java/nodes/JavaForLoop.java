package com.github.nsc.de.shake.generators.java.nodes;

public class JavaForLoop implements JavaNode.JavaOperation {

    private final JavaNode.JavaOperation declaration;
    private final JavaValued condition;
    private final JavaNode.JavaOperation round;
    private final JavaTree body;

    public JavaForLoop(JavaOperation declaration, JavaValued condition, JavaOperation round, JavaTree body) {
        this.declaration = declaration;
        this.condition = condition;
        this.round = round;
        this.body = body;
    }

    @Override
    public String toString(String indent, String add) {
        return "for("
                + declaration.toString(indent, add) + "; "
                + condition.toString(indent, add) + "; "
                + round.toString(indent, add) + ") " + body.toString(indent, add);
    }
}
