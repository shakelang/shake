package com.github.nsc.de.compiler.lexer.characterinputstream;

import com.github.nsc.de.compiler.lexer.Position;

public class StringCharacterInputStream implements CharacterInputStream {

    private final String source;
    private final char[] content;
    private final Position position;

    public StringCharacterInputStream(String source, String content, Position position) {
        this.source = source;
        this.content = content.replaceAll("\r\n", "\n").toCharArray();
        this.position = position;
    }

    public StringCharacterInputStream(String source, String content) {
        this(source, content, new Position(source, content));
    }

    @Override
    public boolean hasNext() {
        return this.position.getIndex() < this.content.length-1;
    }

    @Override
    public Character next() {
        skip();
        return this.content[this.position.getIndex()];
    }

    @Override
    public void skip() {
        if(this.peek() == '\n') this.position.nextLine();
        else this.position.nextColumn();
    }

    @Override
    public void skip(int number) {
        for(int i = 0; i < number; i++) skip();
    }

    @Override
    public Character actual() {
        return peek(0);
    }

    @Override
    public Character peek(int num) {
        return this.position.getIndex() + num < this.content.length ? this.content[this.position.getIndex() + num] : null;
    }

    @Override
    public String peek(int num1, int num2) {
        return this.position.getIndex() + num1 < this.content.length && this.position.getIndex() + num2 < this.content.length ?
                new String(this.content).substring(this.position.getIndex() + num1, this.position.getIndex() + num2 + 1) : "";
    }

    @Override
    public Position getPosition() {
        return this.position.copy();
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public char[] getContent() {
        return content;
    }
}