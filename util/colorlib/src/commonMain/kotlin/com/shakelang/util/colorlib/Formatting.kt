package com.shakelang.util.colorlib

/**
 * Format a string to show it in the console.
 *
 * @since 0.1.0
 * @version 0.1.1
 */
enum class Formatting(

    /**
     * The formatting-code
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override val code: Int

) : FormattingType {

    /**
     * Format a string to show it in the console.
     *
     * Reset formatting of a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    RESET(0),

    /**
     * Format a string to show it in the console.
     *
     * Outline1 formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    OUTLINE(51),

    /**
     * Format a string to show it in the console.
     *
     * Outline2 formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    OUTLINE2(52),

    /**
     * Format a string to show it in the console.
     *
     * Bold formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    BOLD(1),

    /**
     * Format a string to show it in the console.
     *
     * Italic formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    ITALIC(3),

    /**
     * Format a string to show it in the console.
     *
     * Underline formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    UNDERLINE(4),

    /**
     * Format a string to show it in the console.
     *
     * Outline1 formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    UNDERLINE_THICK(21),

    /**
     * Format a string to show it in the console.
     *
     * Strikethrough formatting for a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    STRIKETHROUGH(9),

    /**
     * Format a string to show it in the console.
     *
     * Invert formatting of a string
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    INVERT(7);

    /**
     * Stringify the formatting code
     *
     * @returns The string representation of the given formatting code
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun toString() = "\u001B[" + code + 'm'

    /**
     * Format a string's color to show it in the console.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    enum class FGColor(

        /**
         * The formatting-code
         */
        override val code: Int

    ) : FormattingType {

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to black
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BLACK(30),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to red
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        RED(31),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to green
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        GREEN(32),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to yellow
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        YELLOW(33),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to blue
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BLUE(34),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to purple
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        PURPLE(35),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to cyan
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        CYAN(36),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to white
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        WHITE(37),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright black
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_BLACK(90),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright red
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_RED(91),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright green
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_GREEN(92),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright yellow
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_YELLOW(93),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright blue
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_BLUE(94),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright purple
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_PURPLE(95),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright cyan
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_CYAN(96),

        /**
         * Format a string's color to show it in the console.
         *
         * Set color to bright white
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_WHITE(97);

        /**
         * Stringify the formatting code
         *
         * @return the string representation of the formatting code
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        override fun toString(): String = "\u001B[" + code + 'm'
    }

    /**
     * Format a string's background color to show it in the console.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    enum class BGColor(

        /**
         * The formatting-code
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        override val code: Int

    ) : FormattingType {

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to black
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BLACK(40),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to red
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        RED(41),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to green
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        GREEN(42),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to yellow
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        YELLOW(43),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to blue
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BLUE(44),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to purple
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        PURPLE(45),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to cyan
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        CYAN(46),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to white
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        WHITE(47),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright black
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_BLACK(100),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright red
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_RED(101),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright green
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_GREEN(102),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright yellow
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_YELLOW(103),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright blue
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_BLUE(104),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright purple
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_PURPLE(105),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright cyan
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_CYAN(106),

        /**
         * Format a string's background color to show it in the console.
         *
         * Set background color to bright white
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        BRIGHT_WHITE(107);

        /**
         * Stringify the formatting code
         *
         * @return returns the string representation of the formatting code
         *
         * @since 0.1.0
         * @version 0.1.1
         */
        override fun toString(): String = "\u001B[" + code + 'm'
    }
}

/**
 * Format a string to show it in the console.
 *
 * See implementation enums: [Formatting], [Formatting.FGColor], [Formatting.BGColor]
 *
 * @since 0.1.0
 * @version 0.1.1
 */
interface FormattingType {

    /**
     * The formatting-code
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    val code: Int

    /**
     * Expecting override toString (Should generate a code)
     *
     * @return the string representation of the formatting code
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun toString(): String
}
