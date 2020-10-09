package com.github.nsc.de.compiler.util;

public enum Formatting {

    RESET(0),
    OUTLINE(51),
    OUTLINE2(52),
    BOLD(1),
    ITALIC(3),
    UNDERLINE(4),
    UNDERLINE_THICK(21),
    STRIKETHROUGH(9),
    INVERT(7)
    ;

    private final int code;
    Formatting(int code) { this.code = code; }

    @Override
    public String toString() {
        return "\u001B[" + code + 'm';
    }

    public enum FGColor {
        BLACK (30),
        RED (31),
        GREEN (32),
        YELLOW (33),
        BLUE (34),
        PURPLE (35),
        CYAN (36),
        WHITE (37),

        BRIGHT_BLACK (90),
        BRIGHT_RED (91),
        BRIGHT_GREEN (92),
        BRIGHT_YELLOW (93),
        BRIGHT_BLUE (94),
        BRIGHT_PURPLE (95),
        BRIGHT_CYAN (96),
        BRIGHT_WHITE (97)
        ;

        private final int code;
        FGColor(int code) { this.code = code; }

        @Override
        public String toString() {
            return "\u001B[" + code + 'm';
        }
    }

    public enum BGColor {
        BLACK (40),
        RED (41),
        GREEN (42),
        YELLOW (43),
        BLUE (44),
        PURPLE (45),
        CYAN (46),
        WHITE (47),

        BRIGHT_BLACK (100),
        BRIGHT_RED (101),
        BRIGHT_GREEN (102),
        BRIGHT_YELLOW (103),
        BRIGHT_BLUE (104),
        BRIGHT_PURPLE (105),
        BRIGHT_CYAN (106),
        BRIGHT_WHITE (107)
        ;

        private final int code;
        BGColor(int code) { this.code = code; }

        @Override
        public String toString() {
            return "\u001B[" + code + 'm';
        }
    }

}
