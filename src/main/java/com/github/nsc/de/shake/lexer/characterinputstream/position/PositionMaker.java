package com.github.nsc.de.shake.lexer.characterinputstream.position;

import com.github.nsc.de.shake.lexer.characterinputstream.charactersource.CharacterSource;

import java.util.ArrayList;
import java.util.List;

/**
 * A modifiable {@link PositionMarker} that stores the positions
 * of the line-separators
 */
public class PositionMaker implements PositionMarker {

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
     * Stores the locations of the line-separators
     */
    private List<Integer> lineSeparators = new ArrayList<>();

    /**
     * The source of the {@link PositionMaker}
     */
    private final CharacterSource source;

    /**
     * Constructor for the {@link PositionMaker}
     *
     * @param index The {@link PositionMaker#index} of the position
     * @param column The {@link PositionMaker#column} of the position
     * @param line The {@link PositionMaker#line} of the position
     * @param source the {@link CharacterSource} the chars come from
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public PositionMaker(int index, int column, int line, CharacterSource source) {
        this.index = index;
        this.column = column;
        this.line = line;
        this.source = source;
    }

    /**
     * Constructor for the {@link PositionMaker}
     *
     * @param source the {@link CharacterSource} the chars come from
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public PositionMaker(CharacterSource source) {
        this.source = source;
        this.index = -1;
        this.column = 0;
        this.line = 1;
    }


    // ***********************************************
    // Getters

    /**
     * Getter for {@link PositionMaker#index}
     *
     * @return The {@link PositionMaker#index} of the {@link PositionMaker}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     * Getter for {@link PositionMaker#column}
     *
     * @return The {@link PositionMaker#column} of the {@link PositionMaker}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public int getColumn() {
        return this.column;
    }

    /**
     * Getter for {@link PositionMaker#line}
     *
     * @return The {@link PositionMaker#line} of the {@link PositionMaker}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public int getLine() {
        return this.line;
    }

    /**
     * Getter for {@link PositionMaker#source}
     *
     * @return the {@link PositionMaker#source} of the {@link PositionMaker}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public CharacterSource getSource() {
        return source;
    }

    public int[] getLineSeparators() {
        // Convert map to int[]
        int[] arr = new int[this.lineSeparators.size()];
        for(int i = 0; i < arr.length; i++) arr[i] = this.lineSeparators.get(i);
        return arr;
    }



    // ***********************************************
    // Others

    /**
     * Increases the {@link PositionMaker#index} and {@link PositionMaker#column}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void nextColumn() {
        this.index++;
        this.column++;
    }

    /**
     * Increases the {@link PositionMaker#index} and {@link PositionMaker#line} and sets the
     * {@link PositionMaker#column} to 1
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public void nextLine() {
        this.lineSeparators.add(++this.index);
        this.line++;
        this.column = 1;
    }

    public PositionMap createPositionMap() {
        return new PositionMap(this.getSource(), this.getLineSeparators());
    }

    public Position createPositionAtLocation() {
        return new Position(createPositionMap(), getIndex(), getColumn(), getLine());
    }
}
