package com.github.nsc.de.shake.lexer.characterinput.characterinputstream;


import com.github.nsc.de.shake.lexer.characterinput.charactersource.CharacterSource;
import com.github.nsc.de.shake.lexer.characterinput.position.PositionMaker;

import java.io.File;
import java.io.IOException;

/**
 * An implementation of {@link CharacterInputStream} using just a string as argument
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class SourceCharacterInputStream implements CharacterInputStream {

    /**
     * The source (mostly file) of the {@link SourceCharacterInputStream}
     */
    private final CharacterSource source;

    /**
     * The actual position of the {@link SourceCharacterInputStream}
     */
    private final PositionMaker position;


    /**
     * Constructor for {@link SourceCharacterInputStream} with given position
     *
     * @param content the characters
     * @param source the source of the characters
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public SourceCharacterInputStream( String source, char[] content) {
        this(CharacterSource.from(content, source));
    }


    /**
     * Constructor for {@link SourceCharacterInputStream} with given position
     *
     * @param content the characters
     * @param source the source of the characters
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public SourceCharacterInputStream(String source, String content) {
        this(CharacterSource.from(content, source));
    }


    /**
     * Constructor for {@link SourceCharacterInputStream} with given position
     *
     * @param file the file source
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public SourceCharacterInputStream(File file) throws IOException {
        this(CharacterSource.from(file, '<' + file.getPath() + '>'));
    }


    /**
     * Constructor for {@link SourceCharacterInputStream} with given position
     *
     * @param source the source of the characters
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public SourceCharacterInputStream(CharacterSource source) {
        // Set fields
        this.source = source;
        this.position = new PositionMaker(source);
    }


    /**
     * Constructor for {@link SourceCharacterInputStream} with given position
     *
     * @param position the starting position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    public SourceCharacterInputStream(PositionMaker position) {
        // Set fields
        this.source = position.getSource();
        this.position = position;
    }


    /**
     * Returns the source (mostly file) of the {@link CharacterInputStream}
     *
     * @return the source (mostly file) of the {@link CharacterInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#getSource()
     * @see SourceCharacterInputStream#getContent()
     * @see SourceCharacterInputStream#getPosition()
     */
    @Override
    public CharacterSource getSource() {
        // Return the source of the StringCharacterInputStream
        return source;
    }


    /**
     * Returns the chars of the {@link CharacterInputStream}
     *
     * @return the chars of the {@link CharacterInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#getContent()
     * @see SourceCharacterInputStream#getSource()
     * @see SourceCharacterInputStream#getPosition()
     */
    @Override
    public char[] getContent() {
        // Return the content of the StringCharacterInputStream
        return source.getAll();
    }


    /**
     * Returns the actual position of the {@link CharacterInputStream}
     *
     * @return the actual position of the {@link CharacterInputStream}
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#getPosition()
     * @see SourceCharacterInputStream#getContent()
     * @see SourceCharacterInputStream#getSource()
     */
    @Override
    public int getPosition() {
        // Return the actual position-index of the StringCharacterInputStream
        return this.position.getIndex();
    }

    /**
     * Returns the actual position-maker of the {@link CharacterInputStream}
     *
     * @return the actual position-maker of the {@link CharacterInputStream}
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public PositionMaker getPositionMaker() {
        // return the position of the StringCharacterInputStream
        return this.position;
    }


    /**
     * Checks if the {@link CharacterInputStream} has a next character left
     *
     * @return if the {@link CharacterInputStream} has a next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#hasNext()
     * @see SourceCharacterInputStream#has(int number)
     */
    @Override
    public boolean hasNext() {
        // We could also use has(1) here, but for performance reasons that should be better
        return this.position.getIndex() + 1 < this.getSource().getLength();
    }


    /**
     * Checks if the {@link CharacterInputStream} has a given number of characters left
     *
     * @param number the num of characters to check
     * @return if the {@link CharacterInputStream} has a given number of characters left
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#has(int number)
     * @see SourceCharacterInputStream#hasNext()
     */
    @Override
    public boolean has(int number) {
        // throw an error, if the given number is smaller than 1
        if(number < 1) throw new Error("The given number must be 1 or bigger");
        return this.position.getIndex() + number < this.getSource().getLength();
    }


    /**
     * Returns the next character and continues to the next token
     *
     * @return the next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#next()
     */
    @Override
    public char next() {
        // Skip and return the actual character
        skip();
        return actual();
    }


    /**
     * Skips a given number of characters
     *
     * @param number the number of characters to skip
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#skip(int number)
     * @see SourceCharacterInputStream#skip()
     */
    @Override
    public void skip(int number) {
        // Skip as many times, as required
        for(int i = 0; i < number; i++) skip();
    }


    /**
     * Skips the next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#skip()
     * @see SourceCharacterInputStream#skip(int number)
     */
    @Override
    public void skip() {
        // if the actual position is a line-separator go to next line, if not then to next column
        if(this.peek() == '\n') this.position.nextLine();
        else this.position.nextColumn();
    }


    /**
     * Returns the actual character (the same as returned by {@link #next()} when used before)
     *
     * @return the actual character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#actual()
     */
    @Override
    public char actual() {
        // return the character at the actual position
        return this.getContent()[this.position.getIndex()];
    }


    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#peek()
     * @see SourceCharacterInputStream#peek(int from, int to)
     * @see SourceCharacterInputStream#peek(int num)
     */
    @Override
    public char peek() {
        // we could also use peek(1) here, but for performance reasons a direct implementation is better
        // throw an error if the StringCharacterInputStream has not enough tokens left
        if(!this.hasNext()) throw new Error("Not enough characters left");

        // return the content at the required position
        return this.getContent()[this.position.getIndex() + 1];
    }


    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @param num the position to get
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#peek(int num)
     * @see SourceCharacterInputStream#peek(int from, int to)
     * @see SourceCharacterInputStream#peek()
     */
    @Override
    public char peek(int num) {
        // throw an error if the StringCharacterInputStream has not enough tokens left
        // (this will also automatically throw an error, if a number that is smaller than 1 is given as input, because
        // of the implementation in the has method)
        if(!this.has(num)) throw new Error("Not enough characters left");

        // return the content at the required position
        return this.getContent()[this.position.getIndex() + num];
    }


    /**
     * Gives back a part of the {@link CharacterInputStream} as string
     * (relative to the actual position)
     *
     * @param from the starting position of the string to get
     * @param to the end position of the string to get
     * @return the character at the requested position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     *
     * @see CharacterInputStream#peek(int from, int to)
     * @see SourceCharacterInputStream#peek(int num)
     * @see SourceCharacterInputStream#peek()
     */
    @Override
    public String peek(int from, int to) {
        if(from < 0) throw new Error("Peek argument must not be smaller than 0");
        if(to <= from) throw new Error("To-argument must be bigger than from-argument");
        //if(!this.has(from)) throw new Error("Not enough characters left");

        return this.position.getIndex() + from < this.getSource().getLength() && this.position.getIndex() + to < this.getSource().getLength() ?
                new String(this.getContent()).substring(this.position.getIndex() + from, this.position.getIndex() + to + 1) : "";
    }
}