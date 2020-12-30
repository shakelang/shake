package com.github.nsc.de.shake.lexer;

import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.position.Position;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.lexer.token.TokenType;
import com.github.nsc.de.shake.util.Characters;
import com.github.nsc.de.shake.util.CompilerError;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final CharacterInputStream in;
    private final List<Byte> tokens = new ArrayList<>();
    private final List<Integer> positions = new ArrayList<>();
    private final List<String> values = new ArrayList<>();

    public Lexer(CharacterInputStream in) {
        this.in = in;
    }

    public TokenInputStream makeTokens() {
        while(this.in.hasNext()) {
            char next = this.in.next();
            char peek = in.hasNext() ? in.peek() : 0;
            int start = in.getPosition();

            // Whitespace
            if(Characters.isWhitespaceCharacter(next)) continue;

            // Linebreaks
            if(next == '\n') addPosition(TokenType.LINE_SEPARATOR, start);

            // Punctuation
            else if(next == ';') addPosition(TokenType.SEMICOLON, start);
            else if(next == ',') addPosition(TokenType.COMMA, start);
            else if(next == '.') addPosition(TokenType.DOT, start);

            // Numbers
            else if(Characters.isNumberCharacter(next)) makeNumber();

            // Identifiers
            else if(Characters.isIdentifierStartCharacter(next)) makeIdentifier();

            else if(next == '"') makeString();
            else if(next == '\'') makeCharacter();

            // Comments
            else if (next == '/' && peek == '/') this.singleLineComment();
            else if (next == '/' && peek == '*') this.multiLineComment();

            // Operator assign
            else if (this.in.has(2) && next == '*' && peek == '*' && this.in.peek(2) == '=')
                { in.skip(2); addPosition(TokenType.POW_ASSIGN, in.getPosition()); }
            else if (next == '%' && peek == '=') { in.skip(); addPosition(TokenType.MOD_ASSIGN, in.getPosition()); }
            else if (next == '/' && peek == '=') { in.skip(); addPosition(TokenType.DIV_ASSIGN, in.getPosition()); }
            else if (next == '*' && peek == '=') { in.skip(); addPosition(TokenType.MUL_ASSIGN, in.getPosition()); }
            else if (next == '-' && peek == '=') { in.skip(); addPosition(TokenType.SUB_ASSIGN, in.getPosition()); }
            else if (next == '+' && peek == '=') { in.skip(); addPosition(TokenType.ADD_ASSIGN, in.getPosition()); }

            else if (next == '+' && peek == '+') { in.skip(); addPosition(TokenType.INCR, in.getPosition()); }
            else if (next == '-' && peek == '-') { in.skip(); addPosition(TokenType.DECR, in.getPosition()); }

            // Math operators
            else if (next == '*' && peek == '*') { in.skip(); addPosition(TokenType.POW, in.getPosition()); }
            else if (next == '%') addPosition(TokenType.MOD, in.getPosition());
            else if (next == '/') addPosition(TokenType.DIV, in.getPosition());
            else if (next == '*') addPosition(TokenType.MUL, in.getPosition());
            else if (next == '-') addPosition(TokenType.SUB, in.getPosition());
            else if (next == '+') addPosition(TokenType.ADD, in.getPosition());

            // Logical operators
            else if (next == '|' && peek == '|') { in.skip(); addPosition(TokenType.LOGICAL_OR, in.getPosition()); }
            else if (next == '&' && peek == '&') { in.skip(); addPosition(TokenType.LOGICAL_AND, in.getPosition()); }

            else if (next == '=' && peek == '=') { in.skip(); addPosition(TokenType.EQ_EQUALS, in.getPosition()); }
            else if (next == '>' && peek == '=') { in.skip(); addPosition(TokenType.BIGGER_EQUALS, in.getPosition()); }
            else if (next == '<' && peek == '=') { in.skip(); addPosition(TokenType.SMALLER_EQUALS, in.getPosition()); }
            else if (next == '>') addPosition(TokenType.BIGGER, in.getPosition());
            else if (next == '<') addPosition(TokenType.SMALLER, in.getPosition());

            // Assign
            else if (next == '=')  addPosition(TokenType.ASSIGN, in.getPosition());

            // Brackets
            else if (next == '(') addPosition(TokenType.LPAREN, in.getPosition());
            else if (next == ')') addPosition(TokenType.RPAREN, in.getPosition());

            else if (next == '{') addPosition(TokenType.LCURL, in.getPosition());
            else if (next == '}') addPosition(TokenType.RCURL, in.getPosition());
            else throw new LexerError("UnexpectedTokenError", "Unrecognised Token: '" + next + '\'');
        }

        return new TokenInputStream(this.in.getSource().getLocation(),
                createPrimitiveByteArray(tokens),
                values.toArray(new String[] {}),
                createPrimitiveIntArray(positions),
                in.getPositionMaker().createPositionMap());
    }

    public int[] createPrimitiveIntArray(List<Integer> list) {
        int size = list.size();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) arr[i] = list.get(i);
        return arr;
    }

    public byte[] createPrimitiveByteArray(List<Byte> list) {
        int size = list.size();
        byte[] arr = new byte[size];
        for(int i = 0; i < size; i++) arr[i] = list.get(i);
        return arr;
    }

    private void addPosition(byte type, int end) {
        this.tokens.add(type);
        this.positions.add(end);
    }

    private void addPosition(byte type, int end, String value) {
        this.tokens.add(type);
        this.positions.add(end);
        this.values.add(value);
    }

    private void makeNumber() {
        StringBuilder numStr = new StringBuilder();
        boolean dot = false;
        numStr.append(in.actual());
        while(in.hasNext() && Characters.isNumberOrDotCharacter(in.peek())) {
            if(in.peek() == '.') {
                if(dot) break;
                dot = true;
            }
            numStr.append(in.next());
        }

        if(dot) addPosition(TokenType.DOUBLE, in.getPosition(), numStr.toString());
        else addPosition(TokenType.INTEGER, in.getPosition(), numStr.toString());
    }

    private void makeIdentifier() {
        StringBuilder identifier = new StringBuilder();
        identifier.append(in.actual());
        while(in.hasNext() && Characters.isIdentifierCharacter(in.peek())) {
            identifier.append(in.next());
        }

        String result = identifier.toString();
        int end = in.getPosition();

        char[] chars = result.toCharArray();

        // Keywords
        switch (result.length()) {
            case 2:
                // do keyword
                if(chars[0] == 'd') {
                    if(chars[1] == 'o') {
                        addPosition(TokenType.KEYWORD_DO, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'i' && chars[1] == 'f') {
                    addPosition(TokenType.KEYWORD_IF, end);
                    return;
                }
                break;

            case 3:
                if(chars[0] == 'v') {
                    if(chars[1] == 'a' && chars[2] == 'r') {
                        addPosition(TokenType.KEYWORD_VAR, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'l') {
                    if(chars[1] == 'e' && chars[2] == 't') {
                        addPosition(TokenType.KEYWORD_VAR, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'i') {
                    if(chars[1] == 'n' && chars[2] == 't') {
                        addPosition(TokenType.KEYWORD_INT, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'n') {
                    if(chars[1] == 'e' && chars[2] == 'w') {
                        addPosition(TokenType.KEYWORD_NEW, end);
                        return;
                    }
                }
                if(chars[0] == 'f' && chars[1] == 'o' && chars[2] == 'r') {
                    addPosition(TokenType.KEYWORD_FOR, end);
                    return;
                }
                break;

            case 4:
                if(chars[0] == 'b') {
                    if(chars[1] == 'y' && chars[2] == 't' && chars[3] == 'e') {
                        addPosition(TokenType.KEYWORD_BYTE, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'l') {
                    if(chars[1] == 'o' && chars[2] == 'n' && chars[3] == 'g') {
                        addPosition(TokenType.KEYWORD_LONG, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'c') {
                    if(chars[1] == 'h' && chars[2] == 'a' && chars[3] == 'r') {
                        addPosition(TokenType.KEYWORD_CHAR, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 't') {
                    if(chars[1] == 'r' && chars[2] == 'u' && chars[3] == 'e') {
                        addPosition(TokenType.KEYWORD_TRUE, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'e' && chars[1] == 'l' && chars[2] == 's' && chars[3] == 'e') {
                    addPosition(TokenType.KEYWORD_ELSE, end);
                    return;
                }
                break;

            case 5:
                if(chars[0] == 'f') {
                    if(chars[1] == 'a' && chars[2] == 'l' && chars[3] == 's' && chars[4] == 'e') {
                        addPosition(TokenType.KEYWORD_FALSE, end);
                        return;
                    }
                    if(chars[1] == 'l' && chars[2] == 'o' && chars[3] == 'a' && chars[4] == 't') {
                        addPosition(TokenType.KEYWORD_FLOAT, end);
                        return;
                    }
                    if(chars[1] == 'i' && chars[2] == 'n' && chars[3] == 'a' && chars[4] == 'l') {
                        addPosition(TokenType.KEYWORD_FINAL, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'c') {
                    if(chars[1] == 'o' && chars[2] == 'n' && chars[3] == 's' && chars[4] == 't') {
                        addPosition(TokenType.KEYWORD_CONST, end);
                        return;
                    }
                    if(chars[1] == 'l' && chars[2] == 'a' && chars[3] == 's' && chars[4] == 's') {
                        addPosition(TokenType.KEYWORD_CLASS, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 's') {
                    if(chars[1] == 'h' && chars[2] == 'o' && chars[3] == 'r' && chars[4] == 't') {
                        addPosition(TokenType.KEYWORD_SHORT, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'w' && chars[1] == 'h' && chars[2] == 'i' && chars[3] == 'l' && chars[4] == 'e') {
                    addPosition(TokenType.KEYWORD_WHILE, end);
                    return;
                }
                break;
            case 6:
                if(chars[0] == 'd') {
                    if(chars[1] == 'o' && chars[2] == 'u' && chars[3] == 'b' && chars[4] == 'l' && chars[5] == 'e') {
                        addPosition(TokenType.KEYWORD_DOUBLE, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'r') {
                    if(chars[1] == 'e' && chars[2] == 't' && chars[3] == 'u' && chars[4] == 'r' && chars[5] == 'n') {
                        addPosition(TokenType.KEYWORD_RETURN, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 's') {
                    if(chars[1] == 't' && chars[2] == 'a' && chars[3] == 't' && chars[4] == 'i' && chars[5] == 'c') {
                        addPosition(TokenType.KEYWORD_STATIC, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'p' && chars[1] == 'u' && chars[2] == 'b' && chars[3] == 'l'
                        && chars[4] == 'i' && chars[5] == 'c') {
                    addPosition(TokenType.KEYWORD_PUBLIC, end);
                    return;
                }
                break;
            case 7:
                if(chars[0] == 'd') {
                    if(chars[1] == 'y' && chars[2] == 'n' && chars[3] == 'a' && chars[4] == 'm'
                            && chars[5] == 'i' && chars[6] == 'c') {
                        addPosition(TokenType.KEYWORD_DYNAMIC, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'b') {
                    if(chars[1] == 'o' && chars[2] == 'o' && chars[3] == 'l' && chars[4] == 'e'
                            && chars[5] == 'a' && chars[6] == 'n') {
                        addPosition(TokenType.KEYWORD_BOOLEAN, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'e') {
                    if(chars[1] == 'x' && chars[2] == 't' && chars[3] == 'e' && chars[4] == 'n'
                            && chars[5] == 'd' && chars[6] == 's') {
                        addPosition(TokenType.KEYWORD_EXTENDS, end);
                        return;
                    }
                    break;
                }
                if(chars[0] == 'p' && chars[1] == 'r' && chars[2] == 'i' && chars[3] == 'v' && chars[4] == 'a'
                            && chars[5] == 't' && chars[6] == 'e') {
                    addPosition(TokenType.KEYWORD_PRIVATE, end);
                    return;
                }
                break;
            case 8:
                if(chars[0] == 'f') {
                    if(chars[1] == 'u' && chars[2] == 'n' && chars[3] == 'c' && chars[4] == 't'
                            && chars[5] == 'i' && chars[6] == 'o' && chars[7] == 'n') {
                        addPosition(TokenType.KEYWORD_FUNCTION, end);
                        return;
                    }
                }
                break;
            case 9:
                if(chars[0] == 'p' && chars[1] == 'r' && chars[2] == 'o' && chars[3] == 't' && chars[4] == 'e'
                        && chars[5] == 'c' && chars[6] == 't' && chars[7] == 'e' && chars[8] == 'd') {
                    addPosition(TokenType.KEYWORD_PROTECTED, end);
                    return;
                }
                break;
            case 10:
                if(chars[0] == 'i' && chars[1] == 'm' && chars[2] == 'p' && chars[3] == 'l' && chars[4] == 'e'
                        && chars[5] == 'm' && chars[6] == 'e' && chars[7] == 'n' && chars[8] == 't' && chars[9] == 's') {
                    addPosition(TokenType.KEYWORD_IMPLEMENTS, end);
                    return;
                }
                break;
        }
        addPosition(TokenType.IDENTIFIER, end, identifier.toString());

    }

    private void makeString() {
        StringBuilder string = new StringBuilder();
        if(in.actual() == '"') {
            while(in.hasNext() && in.next() != '"') {
                if(in.actual() == '\\') {
                    switch(in.next()) {
                        case 't': string.append("\\t"); break;
                        case 'b': string.append("\\b"); break;
                        case 'n': string.append("\\n"); break;
                        case 'r': string.append("\\r"); break;
                        case 'f': string.append("\\f"); break;
                        case '\'': string.append("\\'"); break;
                        case '"': string.append("\\\""); break;
                        case '\\': string.append("\\\\"); break;
                        case 'u':
                            string.append("\\u");
                            for(int i = 0; i < 4; i++) {
                                char c = in.next();
                                if(!Characters.isHexCharacter(c)) throw new LexerError("Expecting hex char");
                                string.append(c);
                            }
                            break;
                        default:
                            throw new LexerError("Unknown escape sequence '\\"+in.actual()+"'");

                    }
                }
                else string.append(in.actual());
            }
            if(in.actual() != '"') throw new LexerError("String must end with a '\"'");
        }

        addPosition(TokenType.STRING, in.getPosition(), string.toString());
    }

    private void makeCharacter() {
        String c;
        if(in.next() == '\\') {
            switch(in.next()) {
                case 't': c = "\\t"; break;
                case 'b': c = "\\b"; break;
                case 'n': c = "\\n"; break;
                case 'r': c = "\\r"; break;
                case 'f': c = "\\f"; break;
                case '\'': c = "\\'"; break;
                case '"': c = "\\\""; break;
                case '\\': c = "\\\\"; break;
                case 'u':
                    StringBuilder s = new StringBuilder().append("\\u");
                    for(int i = 0; i < 4; i++) {
                        char ch = in.next();
                        if(!Characters.isHexCharacter(ch)) throw new LexerError("Expecting hex char");
                        s.append(ch);
                    }
                    c = s.toString();
                    break;
                default:
                    throw new LexerError("Unknown escape sequence '\\"+in.actual()+"'");

            }
        }
        else c = String.valueOf(in.actual());
        if(in.next() != '\'') throw new LexerError("Char must end with a \"'\"");

        addPosition(TokenType.CHARACTER, in.getPosition(), c);
    }

    public void singleLineComment() {

        this.in.skip(2);
        while(this.in.hasNext() && this.in.peek() != '\n') this.in.skip();

    }

    public void multiLineComment() {

        this.in.skip();
        while(this.in.has(2) && !(this.in.peek() == '*' && this.in.peek(2) == '/')) {
            this.in.skip();
        }
        if(!this.in.has(2)) throw new LexerError("Multi-Line-Comment did not end");
        this.in.skip(2);

    }

    public class LexerError extends CompilerError {

        public LexerError (String message, String name, String details, Position start, Position end) {
            super(message, name, details, start, end);
        }

        public LexerError (String name, String details, Position start, Position end) {
            this("Error occurred in lexer: " + name + ", " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn(),name, details, start, end);
        }

        public LexerError (String details, Position start, Position end) {
            this("Error occurred in lexer: " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn(), "LexerError", details, start, end);
        }

        public LexerError (String name, String details, Position start) { this(name, details, start, start); }
        public LexerError (String details, Position start) { this(details, start, start); }

        public LexerError (String name, String details) { this(name, details, in.getPositionMaker().createPositionAtLocation()); }
        public LexerError (String details) { this(details, in.getPositionMaker().createPositionAtLocation()); }
    }
}