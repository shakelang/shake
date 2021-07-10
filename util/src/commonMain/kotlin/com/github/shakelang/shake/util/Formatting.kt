package com.github.shakelang.shake.util


/**
 * Format a string to show it in the console.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
enum class Formatting (

    /**
     * The formatting-code
     */
    override val code: Int

) : FormattingType {

    /**
     * Format a string to show it in the console.
     *
     * Reset formatting of a string
     */
    RESET(0),

    /**
     * Format a string to show it in the console.
     *
     * Outline1 formatting for a string
     */
    OUTLINE(51),

    /**
     * Format a string to show it in the console.
     *
     * Outline2 formatting for a string
     */
    OUTLINE2(52),

    /**
     * Format a string to show it in the console.
     *
     * Bold formatting for a string
     */
    BOLD(1),

    /**
     * Format a string to show it in the console.
     *
     * Italic formatting for a string
     */
    ITALIC(3),

    /**
     * Format a string to show it in the console.
     *
     * Underline formatting for a string
     */
    UNDERLINE(4),

    /**
     * Format a string to show it in the console.
     *
     * Outline1 formatting for a string
     */
    UNDERLINE_THICK(21),

    /**
     * Format a string to show it in the console.
     *
     * Strikethrough formatting for a string
     */
    STRIKETHROUGH(9),

    /**
     * Format a string to show it in the console.
     *
     * Invert formatting of a string
     */
    INVERT(7);


    /**
     * Stringify the formatting code
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString() = "\u001B[" + code + 'm'


    /**
     * Format a string's color to show it in the console.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    enum class FGColor (

        /**
         * The formatting-code
         */
        override val code: Int

    ) : FormattingType {

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to black
         */
        BLACK(30),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to red
         */
        RED(31),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to green
         */
        GREEN(32),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to yellow
         */
        YELLOW(33),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to blue
         */
        BLUE(34),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to purple
         */
        PURPLE(35),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to cyan
         */
        CYAN(36),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to white
         */
        WHITE(37),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright black
         */
        BRIGHT_BLACK(90),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright red
         */
        BRIGHT_RED(91),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright green
         */
        BRIGHT_GREEN(92),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright yellow
         */
        BRIGHT_YELLOW(93),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright blue
         */
        BRIGHT_BLUE(94),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright purple
         */
        BRIGHT_PURPLE(95),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright cyan
         */
        BRIGHT_CYAN(96),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright white
         */
        BRIGHT_WHITE(97);


        /**
         * Stringify the formatting code
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toString(): String = "\u001B[" + code + 'm'
    }

    enum class BGColor (

        /**
         * The formatting-code
         */
        override val code: Int

    ) : FormattingType {

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to black
         */
        BLACK(40),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to red
         */
        RED(41),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to green
         */
        GREEN(42),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to yellow
         */
        YELLOW(43),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to blue
         */
        BLUE(44),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to purple
         */
        PURPLE(45),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to cyan
         */
        CYAN(46),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to white
         */
        WHITE(47),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright black
         */
        BRIGHT_BLACK(100),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright red
         */
        BRIGHT_RED(101),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright green
         */
        BRIGHT_GREEN(102),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright yellow
         */
        BRIGHT_YELLOW(103),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright blue
         */
        BRIGHT_BLUE(104),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright purple
         */
        BRIGHT_PURPLE(105),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright cyan
         */
        BRIGHT_CYAN(106),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright white
         */
        BRIGHT_WHITE(107);


        /**
         * Stringify the formatting code
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        override fun toString(): String = "\u001B[" + code + 'm'
    }

}

/**
 * Format a string to show it in the console.
 *
 * See implementation enums: [Formatting], [Formatting.FGColor], [Formatting.BGColor]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface FormattingType {

    /**
     * The formatting-code
     */
    val code: Int

    /**
     * Expecting override toString (Should generate a code)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun toString(): String

}