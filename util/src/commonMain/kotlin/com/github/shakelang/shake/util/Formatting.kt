package com.github.shakelang.shake.util

@Suppress("unused")
enum class Formatting(private val code: Int) {
    RESET(0), OUTLINE(51), OUTLINE2(52), BOLD(1), ITALIC(3), UNDERLINE(4), UNDERLINE_THICK(21), STRIKETHROUGH(9), INVERT(
        7
    );

    override fun toString(): String {
        return "\u001B[" + code + 'm'
    }

    enum class FGColor(private val code: Int) {
        BLACK(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), PURPLE(35), CYAN(36), WHITE(37), BRIGHT_BLACK(90), BRIGHT_RED(
            91
        ),
        BRIGHT_GREEN(92), BRIGHT_YELLOW(93), BRIGHT_BLUE(94), BRIGHT_PURPLE(95), BRIGHT_CYAN(96), BRIGHT_WHITE(97);

        override fun toString(): String {
            return "\u001B[" + code + 'm'
        }
    }

    enum class BGColor(private val code: Int) {
        BLACK(40), RED(41), GREEN(42), YELLOW(43), BLUE(44), PURPLE(45), CYAN(46), WHITE(47), BRIGHT_BLACK(100), BRIGHT_RED(
            101
        ),
        BRIGHT_GREEN(102), BRIGHT_YELLOW(103), BRIGHT_BLUE(104), BRIGHT_PURPLE(105), BRIGHT_CYAN(106), BRIGHT_WHITE(107);

        override fun toString(): String {
            return "\u001B[" + code + 'm'
        }
    }
}