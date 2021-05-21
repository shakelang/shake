package com.github.nsc.de.shake.util.characterinput.position

interface PositionMarker {
    // ***********************************************
    // Getters
    /**
     * The index of the position
     *
     * @return The index of the position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val index: Int

    /**
     * The column of the position
     *
     * @return The column of the position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val column: Int

    /**
     * The line of the position
     *
     * @return The line of the position
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val line: Int
}