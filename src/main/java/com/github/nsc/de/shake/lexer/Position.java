package com.github.nsc.de.shake.lexer;

/**
 * The {@link Position} marks a position in the source-code.
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class Position {

    /**
     * The source (mostly file) of the content
     */
    private String source;

    /**
     * The source content
     */
    private String content;

    /**
     * The index of the position
     */
    private int index;

    /**
     * The column of the position
     */
    private int column;

    /**
     * The line of the position
     */
    private int line;

    /**
     * Constructor for the position
     *
     * @param source The {@link Position#source} (mostly file) of the content
     * @param content The {@link Position#content} of the position
     * @param index The {@link Position#index} of the position
     * @param column The {@link Position#column} of the position
     * @param line The {@link Position#line} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position(String source, String content, int index, int column, int line) {
        this.source = source;
        this.content = content;
        this.index = index;
        this.column = column;
        this.line = line;
    }


    /**
     * Constructor for the position
     *
     * @param source The {@link Position#source} (mostly file) of the content
     * @param content The {@link Position#content} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position(String source, String content) {
        this(source, content, -1, 0, 1);
    }


    // ***********************************************
    // Getters

    /**
     * Getter for {@link Position#source}
     *
     * @return The {@link Position#source} (mostly file) of the content
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String getSource() {
        return source;
    }

    /**
     * Getter for {@link Position#content}
     *
     * @return The {@link Position#content} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter for {@link Position#index}
     *
     * @return The {@link Position#index} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for {@link Position#column}
     *
     * @return The {@link Position#column} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int getColumn() {
        return column;
    }

    /**
     * Getter for {@link Position#line}
     *
     * @return The {@link Position#line} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public int getLine() {
        return line;
    }



    // ***********************************************
    // Others

    /**
     * Increases the {@link Position#index} and {@link Position#column}
     *
     * @return The position itself, so you can chain these expressions
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position nextColumn() {
        this.index++;
        this.column++;
        return this;
    }

    /**
     * Increases the {@link Position#index} and {@link Position#line} and sets the {@link Position#column} to 1
     *
     * @return The position itself, so you can chain these expressions
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position nextLine() {
        this.index++;
        this.line++;
        this.column = 1;
        return this;
    }

    /**
     * Copies the position
     *
     * @return a copy of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position copy() {
        return new Position(this.getSource(), this.getContent(), this.getIndex(), this.getColumn(), this.getLine());
    }

    /**
     * Creates a string-representation of the string
     *
     * @return A string-representation of the string
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toString() {
        return source  + ":" + line + ":" + column;
    }
}