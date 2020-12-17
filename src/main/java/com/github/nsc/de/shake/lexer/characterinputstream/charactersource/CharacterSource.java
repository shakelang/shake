package com.github.nsc.de.shake.lexer.characterinputstream.charactersource;

import java.io.*;

public interface CharacterSource {

    char[] get(int start, int end);
    char[] getAll();
    int getLength();
    String getLocation();

    static CharacterSource from(String s, String source) {
        return from(s.toCharArray(), source);
    }

    static CharacterSource from(char[] chars, String source) {
        return new CharArraySource(chars, source);
    }

    static CharacterSource from(File f, String source) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        char[] chars = new char[(int) f.length()]; // FIXME files that are longer than 2^31 (integer limit)
        for(int i = 0; i < chars.length; i++) chars[i] = (char) reader.read();
        return from(chars, source);
    }

}
