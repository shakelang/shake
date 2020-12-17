package com.github.nsc.de.shake.lexer.characterinputstream.charactersource;

class CharArraySource implements CharacterSource {

    private final char[] chars;
    private final String location;

    public CharArraySource(char[] chars, String location) {
        this.chars = chars;
        this.location = location;
    }

    @Override
    public char[] get(int start, int end) {
        char[] chars = new char[end - start];
        int length = end - start;
        if (length >= 0) System.arraycopy(this.chars, start, chars, 0, length);
        return chars;
    }

    @Override
    public char[] getAll() {
        return this.chars;
    }

    @Override
    public int getLength() {
        return this.chars.length;
    }

    @Override
    public String getLocation() {
        return location;
    }

}