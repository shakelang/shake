package com.github.nsc.de.shake.lexer;

import static com.github.nsc.de.shake.util.HelpFunctions.asList;
import com.github.nsc.de.shake.lexer.characterinput.characterinputstream.CharacterInputStream;
import com.github.nsc.de.shake.lexer.characterinput.position.Position;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.lexer.token.TokenType;
import com.github.nsc.de.shake.util.CompilerError;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final List<Character> NUMBERS = asList("0123456789");
    private static final List<Character> NUMBERS_DOT = asList("0123456789.");
    private static final List<Character> WHITESPACE = asList(" \t");
    private static final List<Character> IDENTIFIER = asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789");
    private static final List<Character> IDENTIFIER_START = asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_");
    private static final List<Character> HEX_CHARS = asList("0123456789ABCDEFabcdef");

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
            if(WHITESPACE.contains(next)) continue;

            // Linebreaks
            if(next == '\r')  continue;
            if(next == '\n') addPosition(TokenType.LINE_SEPARATOR, start);

            // Punctuation
            else if(next == ';') addPosition(TokenType.SEMICOLON, start);
            else if(next == ',') addPosition(TokenType.COMMA, start);
            else if(next == '.') addPosition(TokenType.DOT, start);

            // Numbers
            else if(NUMBERS.contains(next)) makeNumber();

            // Identifiers
            else if(IDENTIFIER_START.contains(next)) makeIdentifier();

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
        while(in.hasNext() && NUMBERS_DOT.contains(in.peek())) {
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
        while(in.hasNext() && IDENTIFIER.contains(in.peek())) {
            identifier.append(in.next());
        }

        String result = identifier.toString();
        int end = in.getPosition();

        // Keywords
        switch (result) {
            case "var":
            case "let":
                addPosition(TokenType.KEYWORD_VAR, end);
                return;
            case "const":
                addPosition(TokenType.KEYWORD_CONST, end);
                return;
            case "dynamic":
                addPosition(TokenType.KEYWORD_DYNAMIC, end);
                return;
            case "byte":
                addPosition(TokenType.KEYWORD_BYTE, end);
                return;
            case "short":
                addPosition(TokenType.KEYWORD_SHORT, end);
                return;
            case "int":
                addPosition(TokenType.KEYWORD_INT, end);
                return;
            case "long":
                addPosition(TokenType.KEYWORD_LONG, end);
                return;
            case "float":
                addPosition(TokenType.KEYWORD_FLOAT, end);
                return;
            case "double":
                addPosition(TokenType.KEYWORD_DOUBLE, end);
                return;
            case "char":
                addPosition(TokenType.KEYWORD_CHAR, end);
                return;
            case "boolean":
                addPosition(TokenType.KEYWORD_BOOLEAN, end);
                return;
            case "function":
                addPosition(TokenType.KEYWORD_FUNCTION, end);
                return;
            case "return":
                addPosition(TokenType.KEYWORD_RETURN, end);
                return;
            case "true":
                addPosition(TokenType.KEYWORD_TRUE, end);
                return;
            case "false":
                addPosition(TokenType.KEYWORD_FALSE, end);
                return;
            case "do":
                addPosition(TokenType.KEYWORD_DO, end);
                return;
            case "while":
                addPosition(TokenType.KEYWORD_WHILE, end);
                return;
            case "for":
                addPosition(TokenType.KEYWORD_FOR, end);
                return;
            case "if":
                addPosition(TokenType.KEYWORD_IF, end);
                return;
            case "else":
                addPosition(TokenType.KEYWORD_ELSE, end);
                return;
            case "class":
                addPosition(TokenType.KEYWORD_CLASS, end);
                return;
            case "extends":
                addPosition(TokenType.KEYWORD_EXTENDS, end);
                return;
            case "implements":
                addPosition(TokenType.KEYWORD_IMPLEMENTS, end);
                return;
            case "static":
                addPosition(TokenType.KEYWORD_STATIC, end);
                return;
            case "final":
                addPosition(TokenType.KEYWORD_FINAL, end);
                return;
            case "public":
                addPosition(TokenType.KEYWORD_PUBLIC, end);
                return;
            case "protected":
                addPosition(TokenType.KEYWORD_PROTECTED, end);
                return;
            case "private":
                addPosition(TokenType.KEYWORD_PRIVATE, end);
                return;
            case "new":
                addPosition(TokenType.KEYWORD_NEW, end);
                return;
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
                                if(!HEX_CHARS.contains(c)) throw new LexerError("Expecting hex char");
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
                        if(!HEX_CHARS.contains(ch)) throw new LexerError("Expecting hex char");
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