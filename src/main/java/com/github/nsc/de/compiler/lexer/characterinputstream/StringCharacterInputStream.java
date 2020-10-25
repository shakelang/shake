package com.github.nsc.de.compiler.lexer.characterinputstream;

import com.github.nsc.de.compiler.lexer.Position;

public class StringCharacterInputStream implements CharacterInputStream {

    /**
     * The source (mostly file) of the {@link StringCharacterInputStream}
     */
    private final String source;

    /**
     * The characters of the {@link StringCharacterInputStream}
     */
    private final char[] content;

    /**
     * The actual position of the {@link StringCharacterInputStream}
     */
    private final Position position;


    /**
     * Constructor for {@link StringCharacterInputStream} with given position
     *
     * @param source the source (mostly file) of the characters
     * @param content the content characters
     * @param position the starting position
     *
     * @author Nicolas Schmidt
     */
    public StringCharacterInputStream(String source, String content, Position position) {
        // Set fields
        this.source = source;
        this.content = content.toCharArray();
        this.position = position;

        // Throw an error if the input content contains a dos-style line-separator
        if(content.contains("\r\n")) throw new Error("Using this constructor you must not give a string that contains \"\\r\\n\" as line-separator");

        // Throw an error if the given source and content are not equal to the values of the position
        if(!source.equals(position.getSource())) throw new Error("The source of the given position and the given source should be the same");
        if(!content.equals(position.getContent())) throw new Error("The content of the given position and the given content should be the same");
    }

    /**
     * Constructor for {@link StringCharacterInputStream} with default position (starting from the start)
     *
     * @param source the source (mostly file) of the characters
     * @param content the content characters
     *
     * @author Nicolas Schmidt
     */
    public StringCharacterInputStream(String source, String content) {

        // Replace windows line-separators with linux line-separators
        content = content.replaceAll("\r\n", "\n");

        // Set fields
        this.source = source;
        this.content = content.toCharArray();
        this.position = new Position(this.source, content);
    }


    /**
     * Returns the source (mostly file) of the {@link CharacterInputStream}
     *
     * @return the source (mostly file) of the {@link CharacterInputStream}
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#getSource()
     * @see StringCharacterInputStream#getContent()
     * @see StringCharacterInputStream#getPosition()
     */
    @Override
    public String getSource() {
        // Return the source of the StringCharacterInputStream
        return source;
    }


    /**
     * Returns the chars of the {@link CharacterInputStream}
     *
     * @return the chars of the {@link CharacterInputStream}
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#getContent()
     * @see StringCharacterInputStream#getSource()
     * @see StringCharacterInputStream#getPosition()
     */
    @Override
    public char[] getContent() {
        // Return the content of the StringCharacterInputStream
        return content;
    }


    /**
     * Returns the actual position of the {@link CharacterInputStream}
     *
     * @return the actual position of the {@link CharacterInputStream}
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#getPosition()
     * @see StringCharacterInputStream#getContent()
     * @see StringCharacterInputStream#getSource()
     */
    @Override
    public Position getPosition() {
        // Return the actual position of the StringCharacterInputStream
        return this.position.copy();
    }


    /**
     * Checks if the {@link CharacterInputStream} has a next character left
     *
     * @return if the {@link CharacterInputStream} has a next character
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#hasNext()
     * @see StringCharacterInputStream#has(int number)
     */
    @Override
    public boolean hasNext() {
        // We could also use has(1) here, but for performance reasons that should be better
        return this.position.getIndex() + 1 < this.content.length;
    }


    /**
     * Checks if the {@link CharacterInputStream} has a given number of characters left
     *
     * @param number the num of characters to check
     * @return if the {@link CharacterInputStream} has a given number of characters left
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#has(int number)
     * @see StringCharacterInputStream#hasNext()
     */
    @Override
    public boolean has(int number) {
        // throw an error, if the given number is smaller than 1
        if(number < 1) throw new Error("The given number must be 1 or bigger");
        return this.position.getIndex() + number < this.content.length;
    }


    /**
     * Returns the next character and continues to the next token
     *
     * @return the next character
     *
     * @author Nicolas Schmidt
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
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#skip(int number)
     * @see StringCharacterInputStream#skip()
     */
    @Override
    public void skip(int number) {
        // Skip as many times, as required
        for(int i = 0; i < number; i++) skip();
    }


    /**
     * Skips the next character
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#skip()
     * @see StringCharacterInputStream#skip(int number)
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
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#actual()
     */
    @Override
    public char actual() {
        // return the character at the actual position
        return this.content[this.position.getIndex()];
    }


    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#peek()
     * @see StringCharacterInputStream#peek(int from, int to)
     * @see StringCharacterInputStream#peek(int num)
     */
    @Override
    public char peek() {
        // we could also use peek(1) here, but for performance reasons a direct implementation is better
        // throw an error if the StringCharacterInputStream has not enough tokens left
        if(!this.hasNext()) throw new Error("Not enough characters left");

        // return the content at the required position
        return this.content[this.position.getIndex() + 1];
    }


    /**
     * Gives back the next character without skipping
     *
     * @return the next character
     *
     * @param num the position to get
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#peek(int num)
     * @see StringCharacterInputStream#peek(int from, int to)
     * @see StringCharacterInputStream#peek()
     */
    @Override
    public char peek(int num) {
        // throw an error if the StringCharacterInputStream has not enough tokens left
        // (this will also automatically throw an error, if a number that is smaller than 1 is given as input, because
        // of the implementation in the has method)
        if(!this.has(num)) throw new Error("Not enough characters left");

        // return the content at the required position
        return this.content[this.position.getIndex() + num];
    }


    /**
     * Gives back a part of the {@link CharacterInputStream} as string
     * (relative to the actual position)
     *
     * @param from the starting position of the string to get
     * @param to the end position of the string to get
     * @return the character at the requested position
     *
     * @author Nicolas Schmidt
     *
     * @see CharacterInputStream#peek(int from, int to)
     * @see StringCharacterInputStream#peek(int num)
     * @see StringCharacterInputStream#peek()
     */
    @Override
    public String peek(int from, int to) {
        if(from < 0) throw new Error("Peek argument must not be smaller than 0");
        if(to <= from) throw new Error("To-argument must be bigger than from-argument");
        if(!this.has(to)) throw new Error("Not enough characters left");

        return this.position.getIndex() + from < this.content.length && this.position.getIndex() + to < this.content.length ?
                new String(this.content).substring(this.position.getIndex() + from, this.position.getIndex() + to + 1) : "";
    }
}