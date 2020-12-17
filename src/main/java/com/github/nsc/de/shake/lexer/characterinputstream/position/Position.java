package com.github.nsc.de.shake.lexer.characterinputstream.position;

/**
 * The {@link Position} marks a position in the source-code.
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class Position implements PositionMarker {

    /**
     * The source
     */
    private final PositionMap source;

    /**
     * The index of the position
     */
    private final int index;

    /**
     * The column of the position
     */
    private final int column;

    /**
     * The line of the position
     */
    private final int line;

    /**
     * Constructor for the position
     *
     * @param source The {@link Position#source} (mostly file) of the content
     * @param index The {@link Position#index} of the position
     * @param column The {@link Position#column} of the position
     * @param line The {@link Position#line} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position(PositionMap source, int index, int column, int line) {
        this.source = source;
        this.index = index;
        this.column = column;
        this.line = line;
    }


    /**
     * Constructor for the position
     *
     * @param source The {@link Position#source} (mostly file) of the content
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position(PositionMap source) {
        this(source, -1, 0, 1);
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
    public PositionMap getSource() {
        return source;
    }

    /**
     * Getter for {@link Position#index}
     *
     * @return The {@link Position#index} of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
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
    @Override
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
    @Override
    public int getLine() {
        return line;
    }

    /**
     * Copies the position
     *
     * @return a copy of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public Position copy() {
        return new Position(this.getSource(), this.getIndex(), this.getColumn(), this.getLine());
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