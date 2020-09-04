package com.github.nsc.de.compiler.lexer.characterinputstream;

import com.github.nsc.de.compiler.lexer.Position;

public interface CharacterInputStream {

    String getSource();
    char[] getContent();
    Position getPosition();
    boolean hasNext();
    Character next();
    void skip(int number);
    void skip();
    Character actual();
    default Character peek() { return peek(1); }
    Character peek(int num);
    String peek(int num, int num2);

}