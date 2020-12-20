package com.github.nsc.de.shake.lexer.characterinput.position;

public interface PositionMarker {


    // ***********************************************
    // Getters

    /**
     * The index of the position
     *
     * @return Thei ndex of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    int getIndex();

    /**
     * The column of the position
     *
     * @return The column of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    int getColumn();

    /**
     * The line of the position
     *
     * @return The line of the position
     *
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    int getLine();
}
