package com.github.nsc.de.shake.lexer.token;

import com.github.nsc.de.shake.lexer.Position;

import java.util.Objects;


/**
 * The input of the {@link com.github.nsc.de.shake.lexer.Lexer} gets converted into {@link Token}s. These get parsed
 * by the parser
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class Token {

    /**
     * The type of the {@link Token}
     *
     * @see Token
     */
    private final TokenType type;

    /**
     * The value of the {@link Token} (This is for identifiers, strings or numbers. If not necessary this is null)
     *
     * @see Token
     */
    private final String value;

    /**
     * The starting {@link Position} of the {@link Token}
     *
     * @see Token
     * @see Position
     */
    private final Position start;

    /**
     * The ending {@link Position} of the {@link Token}
     *
     * @see Token
     * @see Position
     */
    private final Position end;

    /**
     * Constructor for {@link Token}
     *
     * @param type the {@link Token#type} of the {@link Token}
     * @param value the {@link Token#value} of the {@link Token}
     * @param start the {@link Token#start} of the {@link Token}
     * @param end the {@link Token#end} of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#type
     * @see Token#value
     * @see Token#start
     * @see Token#end
     */
    public Token(TokenType type, String value, Position start, Position end) {
        this.type = type;
        this.value = value;
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for {@link Token}
     *
     * @param type the {@link Token#type} of the {@link Token}
     * @param value the {@link Token#value} of the {@link Token}
     * @param position the {@link Token#start} and {@link Token#end} and of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#type
     * @see Token#value
     * @see Token#start
     * @see Token#end
     */
    public Token(TokenType type, String value, Position position) { this(type, value, position, position); }

    /**
     * Constructor for {@link Token}
     *
     * @param type the {@link Token#type} of the {@link Token}
     * @param start the {@link Token#start} of the {@link Token}
     * @param end the {@link Token#end} of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#type
     * @see Token#start
     * @see Token#end
     */
    public Token(TokenType type, Position start, Position end) { this(type, null, start, end); }

    /**
     * Constructor for {@link Token}
     *
     * @param type the {@link Token#type} of the {@link Token}
     * @param position the {@link Token#start} and {@link Token#end} and of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#type
     * @see Token#start
     * @see Token#end
     */
    public Token(TokenType type, Position position) { this(type, null, position, position); }

    /**
     * Returns the {@link Token#type} of the {@link Token}
     *
     * @return the {@link Token#type} of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#type
     * @see TokenType
     */
    public TokenType getType() { return type; }

    /**
     * Returns the {@link Token#type} of the {@link Token}
     *
     * @return the {@link Token#type} of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#type
     * @see TokenType
     */
    public String getValue() { return value; }

    /**
     * Returns the {@link Token#start} of the {@link Token}
     *
     * @return the {@link Token#start} of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#start
     * @see Position
     */
    public Position getStart() { return start; }

    /**
     * Returns the {@link Token#end} of the {@link Token}
     *
     * @return the {@link Token#end} of the {@link Token}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see Token
     * @see Token#end
     * @see Position
     */
    public Position getEnd() { return end; }

    /**
     * Returns the starting index of the {@link Token}
     *
     * @return the the starting index of the {@link Token}
     *
     * @author Nicolas
     *
     * @see Token
     * @see Token#start
     * @see Token#getStart()
     */
    public int getBefore() { return start.getIndex(); }

    /**
     * Returns the ending index of the {@link Token}
     *
     * @return the the ending index of the {@link Token}
     *
     * @author Nicolas
     *
     * @see Token
     * @see Token#end
     * @see Token#getEnd()
     */
    public int getAfter() { return end.getIndex() + 1; }

    @Override
    public String toString() {
        return start == end ? (value != null ? "" +
                "Token{" + "type=" + type + ", value=" + value + ", position=" + start + '}' :
                "Token{" + "type=" + type +  ", position=" + start + '}')
              : (value != null ? "" +
                "Token{" + "type=" + type + ", value=" + value + ", start=" + start + ", end=" + end + '}' :
                "Token{" + "type=" + type +  ", position=" + start + ", end=" + end + '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type &&
                Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
