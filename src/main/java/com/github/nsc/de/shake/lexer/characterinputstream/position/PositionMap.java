package com.github.nsc.de.shake.lexer.characterinputstream.position;

import com.github.nsc.de.shake.lexer.characterinputstream.charactersource.CharacterSource;

public class PositionMap {

    private final CharacterSource source;
    private final int[] lineSeparators;


    public PositionMap(CharacterSource source, int[] lineSeparators) {
        this.source = source;
        this.lineSeparators = lineSeparators;
    }

    public CharacterSource getSource() {
        return source;
    }

    public String getLocation() {
        return getSource().getLocation();
    }

    public int[] getLineSeparators() {
        return lineSeparators;
    }

    public Position resolve(int index) {

        for(int i = 0; i < this.getLineSeparators().length; i++) {
            if(index < this.getLineSeparators()[i]) {
                if(i == 0) return new Position(this, index, index + 1, 1);
                else return new Position(this, index, index - this.getLineSeparators()[i - 1], i + 1);
            }
        }

        return new Position(this, index,
                index - (this.getLineSeparators().length > 0 ? this.getLineSeparators()[this.getLineSeparators().length - 1] : 0) + 1,
                this.getLineSeparators().length + 1);

    }

    public int getAfterInLine(Position p) {

        return p.getLine() - 1 == lineSeparators.length ?
                getSource().getLength() - p.getColumn() :
                lineSeparators[p.getLine() - 1] - p.getIndex();

    }

    @Override
    public String toString() {
        return getLocation();
    }
}
