package com.github.nsc.de.compiler.lexer;

import static com.github.nsc.de.compiler.util.HelpFunctions.asList;
import com.github.nsc.de.compiler.lexer.characterinputstream.CharacterInputStream;
import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.util.CompilerError;

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

    public Lexer(CharacterInputStream in) {
        this.in = in;
    }

    public TokenInputStream makeTokens() {
        List<Token> tokens = new ArrayList<>();
        while(this.in.hasNext()) {
            char next = this.in.next();
            Position start = in.getPosition().copy();

            // Whitespace
            if(WHITESPACE.contains(next)) continue;

            // Linebreaks
            if(next == '\n') tokens.add(new Token(TokenType.LINE_SEPARATOR, start));

            // Punctuation
            else if(next == ';') tokens.add(new Token(TokenType.SEMICOLON, start));
            else if(next == ',') tokens.add(new Token(TokenType.COMMA, start));
            else if(next == '.') tokens.add(new Token(TokenType.DOT, start));

            // Numbers
            else if(NUMBERS.contains(next)) tokens.add(makeNumber(start));

            // Identifiers
            else if(IDENTIFIER_START.contains(next)) tokens.add(makeIdentifier(start));

            else if(next == '"') tokens.add(makeString(start));

            // Comments
            else if (this.in.peek(0,1).equals("//")) this.singleLineComment();
            else if (this.in.peek(0,1).equals("/*")) this.multiLineComment();

            // Operator assign
            else if (this.in.peek(0,2).equals("**=")) { in.skip(2);  tokens.add(new Token(TokenType.POW_ASSIGN, "**=", start, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("^=")) { in.skip(); tokens.add(new Token(TokenType.POW_ASSIGN, "^=", start, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("%=")) { in.skip(); tokens.add(new Token(TokenType.MOD_ASSIGN, start, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("/=")) { in.skip(); tokens.add(new Token(TokenType.DIV_ASSIGN, start, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("*=")) { in.skip(); tokens.add(new Token(TokenType.MUL_ASSIGN, start, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("-=")) { in.skip(); tokens.add(new Token(TokenType.SUB_ASSIGN, start, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("+=")) { in.skip(); tokens.add(new Token(TokenType.ADD_ASSIGN, start, in.getPosition().copy())); }

            else if (this.in.peek(0,1).equals("++")) { in.skip(); tokens.add(new Token(TokenType.INCR, in.getPosition().copy())); }
            else if (this.in.peek(0,1).equals("--")) { in.skip(); tokens.add(new Token(TokenType.DECR, in.getPosition().copy())); }

            // Math operators
            else if (next == '*' && this.in.hasNext() && in.peek() == '*') { in.skip(); tokens.add(new Token(TokenType.POW, "**", in.getPosition().copy())); }
            else if (next == '^') tokens.add(new Token(TokenType.POW, start));
            else if (next == '%') tokens.add(new Token(TokenType.MOD, start));
            else if (next == '/') tokens.add(new Token(TokenType.DIV, start));
            else if (next == '*') tokens.add(new Token(TokenType.MUL, start));
            else if (next == '-') tokens.add(new Token(TokenType.SUB, start));
            else if (next == '+') tokens.add(new Token(TokenType.ADD, start));

            // Logical operators
            else if (next == '|' && this.in.hasNext() && in.peek() == '|') { in.skip(); tokens.add(new Token(TokenType.LOGICAL_OR, start, in.getPosition().copy())); }
            else if (next == '&' && this.in.hasNext() && in.peek() == '&') { in.skip(); tokens.add(new Token(TokenType.LOGICAL_AND, start, in.getPosition().copy())); }

            else if (next == '=' && this.in.hasNext() && in.peek() == '=') { in.skip(); tokens.add(new Token(TokenType.EQ_EQUALS, start, in.getPosition().copy())); }
            else if (next == '>' && this.in.hasNext() && in.peek() == '=') { in.skip(); tokens.add(new Token(TokenType.BIGGER_EQUALS, start, in.getPosition().copy())); }
            else if (next == '<' && this.in.hasNext() && in.peek() == '=') { in.skip(); tokens.add(new Token(TokenType.SMALLER_EQUALS, start, in.getPosition().copy())); }
            else if (next == '>') tokens.add(new Token(TokenType.BIGGER, start));
            else if (next == '<') tokens.add(new Token(TokenType.SMALLER, start));

            // Assign
            else if (next == '=') tokens.add(new Token(TokenType.ASSIGN, start));

            // Brackets
            else if (next == '(') tokens.add(new Token(TokenType.LPAREN, start));
            else if (next == ')') tokens.add(new Token(TokenType.RPAREN, start));

            else if (next == '{') tokens.add(new Token(TokenType.LCURL, start));
            else if (next == '}') tokens.add(new Token(TokenType.RCURL, start));
            else throw new LexerError("UnexpectedTokenError", "Unrecognised Token: '" + next + '\'');
        }
        return new TokenInputStream(this.in.getSource(), tokens);
    }

    private Token makeNumber(Position start) {
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
        return dot ?
                new Token(TokenType.DOUBLE, numStr.toString(), start, in.getPosition().copy()) :
                new Token(TokenType.INTEGER, numStr.toString(), start, in.getPosition().copy());

    }

    private Token makeIdentifier(Position start) {
        StringBuilder identifier = new StringBuilder();
        identifier.append(in.actual());
        while(in.hasNext() && IDENTIFIER.contains(in.peek())) {
            identifier.append(in.next());
        }

        String result = identifier.toString();
        Position end = in.getPosition().copy();

        // Keywords
        switch (result) {
            case "var":
            case "let":
                return new Token(TokenType.KEYWORD_VAR, start, end);
            case "const":
                return new Token(TokenType.KEYWORD_CONST, start, end);
            case "dynamic":
                return new Token(TokenType.KEYWORD_DYNAMIC, start, end);
            case "byte":
                return new Token(TokenType.KEYWORD_BYTE, start, end);
            case "short":
                return new Token(TokenType.KEYWORD_SHORT, start, end);
            case "int":
                return new Token(TokenType.KEYWORD_INT, start, end);
            case "long":
                return new Token(TokenType.KEYWORD_LONG, start, end);
            case "float":
                return new Token(TokenType.KEYWORD_FLOAT, start, end);
            case "double":
                return new Token(TokenType.KEYWORD_DOUBLE, start, end);
            case "char":
                return new Token(TokenType.KEYWORD_CHAR, start, end);
            case "boolean":
                return new Token(TokenType.KEYWORD_BOOLEAN, start, end);
            case "function":
                return new Token(TokenType.KEYWORD_FUNCTION, start, end);
            case "true":
                return new Token(TokenType.KEYWORD_TRUE, start, end);
            case "false":
                return new Token(TokenType.KEYWORD_FALSE, start, end);
            case "do":
                return new Token(TokenType.KEYWORD_DO, start, end);
            case "while":
                return new Token(TokenType.KEYWORD_WHILE, start, end);
            case "for":
                return new Token(TokenType.KEYWORD_FOR, start, end);
            case "if":
                return new Token(TokenType.KEYWORD_IF, start, end);
            case "else":
                return new Token(TokenType.KEYWORD_ELSE, start, end);
            case "class":
                return new Token(TokenType.KEYWORD_CLASS, start, end);
            case "extends":
                return new Token(TokenType.KEYWORD_EXTENDS, start, end);
            case "implements":
                return new Token(TokenType.KEYWORD_IMPLEMENTS, start, end);
            case "static":
                return new Token(TokenType.KEYWORD_STATIC, start, end);
            case "final":
                return new Token(TokenType.KEYWORD_FINAL, start, end);
            case "public":
                return new Token(TokenType.KEYWORD_PUBLIC, start, end);
            case "protected":
                return new Token(TokenType.KEYWORD_PROTECTED, start, end);
            case "private":
                return new Token(TokenType.KEYWORD_PRIVATE, start, end);
            case "new":
                return new Token(TokenType.KEYWORD_NEW, start, end);
        }
        return new Token(TokenType.IDENTIFIER, identifier.toString(), start, end);

    }

    private Token makeString(Position start) {
        StringBuilder string = new StringBuilder();
        if(in.actual() == '"') {
            while(in.hasNext() && in.next() != '"') {
                if(in.actual() == '\\') {
                    switch(in.next()) {
                        case 't': string.append('\t'); break;
                        case 'b': string.append('\b'); break;
                        case 'n': string.append('\n'); break;
                        case 'r': string.append('\r'); break;
                        case 'f': string.append('\f'); break;
                        case '\'': string.append('\''); break;
                        case '"': string.append('"'); break;
                        case '\\': string.append('\\'); break;
                        case 'u':
                            StringBuilder s = new StringBuilder();
                            for(int i = 0; i < 4; i++) {
                                char c = in.next();
                                if(!HEX_CHARS.contains(c)) throw new LexerError("Expecting hex char");
                                s.append(HEX_CHARS);
                            }
                            string.append((char) Integer.parseInt(s.toString(), 16));
                            break;
                        default:
                            throw new LexerError("Unknown escape sequence '\\"+in.actual()+"'");

                    }
                }
                else string.append(in.actual());
            }
            if(in.actual() != '"') throw new LexerError("String must end with a '\"'");
        }
        return new Token(TokenType.STRING, string.toString(), start, in.getPosition().copy());
    }

    public void singleLineComment() {

        this.in.skip(2);
        while(this.in.hasNext() && this.in.peek() != '\n') this.in.skip();

    }

    public void multiLineComment() {

        this.in.skip(2);
        while(this.in.hasNext() && this.in.peek() != '*' && this.in.peek(2) != '/') this.in.skip();
        if(!this.in.hasNext()) throw new LexerError("Multi-Line-Comment did not end");
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

        public LexerError (String name, String details) { this(name, details, in.getPosition()); }
        public LexerError (String details) { this(details, in.getPosition()); }
    }
}