package com.github.nsc.de.compiler.util;

import com.github.nsc.de.compiler.lexer.Position;

public class CompilerError extends Error {

    private final String name;
    private final String details;
    private final Position start;
    private final Position end;

    public CompilerError(String message, String name, String details, Position start, Position end) {
        super(message);
        this.name = name;
        this.details = details;
        this.start = start;
        this.end = end;
    }

    public CompilerError(String name, String details, Position start, Position end) {
        this("Error occurred: " + name + ", " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn(),name,details,start,end);
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Error occurred: " + name + ", " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn();
    }
}
