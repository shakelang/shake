package com.github.nsc.de.shake.util;


public class Characters {

    public static boolean isHexCharacter(char c) {

        //       48-57 (0-9)         65-70 (A-F)        97-102 (a-f)
        return c > 47 && c < 58 || c > 64 && c < 71 || c > 96 && c < 103;

    }

    public static boolean isNumberCharacter(char c) {
        //       48-57 (0-9)
        return c > 47 && c < 58;
    }

    public static boolean isNumberOrDotCharacter(char c) {
        //       48-57 (0-9)       46 ('.')
        return c > 47 && c < 58 || c == 46;
    }

    public static boolean isWhitespaceCharacter(char c) {
        //      (' ')     ('\t')    ('\r')
        return c == 32 || c == 9 || c == 13;
    }

    public static boolean isIdentifierCharacter(char c) {

        //       48-57 (0-9)         65-90 (A-Z)         97-122 (a-z)        '_'
        return c > 47 && c < 58 || c > 64 && c < 91 || c > 96 && c < 123 || c == 95;

    }

    public static boolean isIdentifierStartCharacter(char c) {

        //       65-90 (A-Z)         97-122 (a-z)        '_'
        return c > 64 && c < 91 || c > 96 && c < 123 || c == 95;

    }


    public static String parseString(String s) {

        StringBuilder string = new StringBuilder();
        int length = s.length();
        for(int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(c == '\\') {
                switch(s.charAt(++i)) {
                    case 't': string.append('\t'); break;
                    case 'b': string.append('\b'); break;
                    case 'n': string.append('\n'); break;
                    case 'r': string.append('\r'); break;
                    case 'f': string.append('\f'); break;
                    case '\'': string.append('\''); break;
                    case '"': string.append('\"'); break;
                    case '\\': string.append('\\'); break;
                    case 'u':
                        StringBuilder unicode = new StringBuilder();
                        int to = i + 5;
                        for(; i < to; ++i) {
                            c = s.charAt(i);
                            if(!isHexCharacter(c)) throw new Error("Expecting hex char");
                            unicode.append(c);
                        }
                        string.append(Integer.parseInt(unicode.toString(), 16));
                        break;
                    default:
                        throw new Error();

                }
            }
            else string.append(c);

        }
        return string.toString();
    }

}
