package com.github.nsc.de.compiler.util;

import java.util.AbstractList;
import java.util.List;

public class HelpFunctions {
    public static List<Character> asList(final String string) {
        return new AbstractList<Character>() {
            public int size() { return string.length(); }
            public Character get(int index) { return string.charAt(index); }
        };
    }
}
