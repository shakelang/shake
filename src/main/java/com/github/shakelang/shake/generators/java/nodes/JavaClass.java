package com.github.shakelang.shake.generators.java.nodes;

import com.github.shakelang.shake.util.Characters;

import java.util.ArrayList;
import java.util.List;

public class JavaClass implements JavaNode {

    private final String name;
    private final List<JavaVariableDeclaration> fields;
    private final List<JavaFunction> functions;
    private final List<JavaClass> subClasses;
    private final JavaAccessDescriptor access;
    private final boolean isStatic;
    private final boolean isFinal;


    public JavaClass(String name,  JavaAccessDescriptor access, boolean isStatic, boolean isFinal) {
        this.name = reformatClassName(name);
        this.fields = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.subClasses = new ArrayList<>();
        this.access = access;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    public String getName() {
        return name;
    }

    public List<JavaFunction> getFunctions() {
        return functions;
    }

    public List<JavaClass> getSubClasses() {
        return subClasses;
    }

    public List<JavaVariableDeclaration> getFields() {
        return fields;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public boolean isStatic() {
        return isStatic;
    }

    /**
     * Generates Java code
     *
     * @param indent the space the code should be indented with
     * @param add    the space to be added to indent per indention
     * @return the generated java code
     */
    @Override
    public String toString(String indent, String add) {
        StringBuilder str = new StringBuilder(this.access.toString());
        if(this.isStatic()) str.append("static ");
        if(this.isFinal()) str.append("final ");

        str.append("class ").append(this.name).append(" {\n\n");

        String indent2 = indent + add;
        for(JavaVariableDeclaration f : this.getFields()) {
            str.append(indent2).append(f.toString(indent2, add)).append(";\n");
        }
        if(this.getFields().size() > 0) str.append('\n');
        for(JavaFunction f : this.getFunctions()) {
            str.append(indent2).append(f.toString(indent2, add)).append("\n\n");
        }
        for(JavaClass c : this.getSubClasses()) {
            str.append(indent2).append(c.toString(indent2, add)).append("\n\n");
        }
        return str.append(indent).append('}').toString();
    }

    @Override
    public String toString() {
        return this.toString("", "    ");
    }

    private static String reformatClassName(String s) {

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length(); i++) {
            if(s.charAt(i) == '-') {
                do i++; while(i < s.length() && !Characters.isIdentifierStartCharacter(s.charAt(i)));
                if(i < s.length()) sb.append(Character.toUpperCase(s.charAt(i)));
                else break;
            }
            else if(Characters.isIdentifierCharacter(s.charAt(i))) sb.append(s.charAt(i));
        }

        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
