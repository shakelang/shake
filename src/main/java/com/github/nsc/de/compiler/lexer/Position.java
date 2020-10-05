package com.github.nsc.de.compiler.lexer;

public class Position {

    private String source;
    private String content;
    private int index;
    private int column;
    private int line;

    public Position(String source, String content, int index, int column, int line) {
        this.source = source;
        this.content = content;
        this.index = index;
        this.column = column;
        this.line = line;
    }

    public Position(String file, String content) {
        this(file, content, -1, 0, 1);
    }


    // Getters
    public String getSource() {
        return source;
    }
    public String getContent() {
        return content;
    }
    public int getIndex() {
        return index;
    }
    public int getColumn() {
        return column;
    }
    public int getLine() {
        return line;
    }

    // Setters
    public Position setSource(String source) {
        this.source = source;
        return this;
    }

    public Position setContent(String content) {
        this.content = content;
        return this;
    }

    public Position setIndex(int index) {
        this.index = index;
        return this;
    }

    public Position setColumn(int column) {
        this.column = column;
        return this;
    }

    public Position setLine(int line) {
        this.line = line;
        return this;
    }

    // increase
    public Position increaseIndex() {
        this.index++;
        return this;
    }

    public Position increaseColumn() {
        this.column++;
        return this;
    }

    public Position increaseLine() {
        this.line++;
        return this;
    }

    // next
    public Position nextColumn() {
        return this.increaseIndex().increaseColumn();
    }

    public Position nextLine() {
        return this.increaseIndex().setColumn(1).increaseLine();
    }

    // Copy
    public Position copy() {
        return new Position(this.getSource(), this.getContent(), this.getIndex(), this.getColumn(), this.getLine());
    }

    @Override
    public String toString() {
        return source  + ":" + line + ":" + column;
    }
}