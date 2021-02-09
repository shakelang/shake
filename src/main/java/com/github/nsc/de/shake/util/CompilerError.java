package com.github.nsc.de.shake.util;

import com.github.nsc.de.shake.lexer.characterinput.position.Position;
import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;

public class CompilerError extends Error {

    private final String name;
    private final String details;
    private final Position start;
    private final Position end;
    private final ErrorMarker marker;
    public static int maxLength = 60;
    private static Position start_zw;
    private static Position end_zw;

    private CompilerError(String message, ErrorMarker marker, String name, String details, Position start, Position end) {
        super(String.format("%s: %s%n%n%s%n", message, details, marker));
        this.name = name;
        this.details = details;
        this.start = start;
        this.end = end;
        this.marker = marker;
    }

    private CompilerError(String message, ErrorMarker marker, String name, String details, PositionMap map, int start, int end) {
        super(String.format("%s: %s%n%n%s%n", message, details, marker));
        this.name = name;
        this.details = details;
        this.start = map.resolve(start);
        this.end = map.resolve(end);
        this.marker = marker;
    }

    public CompilerError(String message, String name, String details, Position start, Position end) {
        this(message, createPositionMarker(CompilerError.maxLength, start, end), name, details, start, end);
    }

    public CompilerError(String message, String name, String details, PositionMap map, int start, int end) {
        this(message, createPositionMarker(CompilerError.maxLength, start_zw = map.resolve(start), end_zw = map.resolve(end)), name, details, start_zw, end_zw);
        start_zw = end_zw = null;
    }

    public String getName() {
        return name;
    }
    public String getDetails() {
        return details;
    }
    public Position getStart() {
        return start;
    }
    public Position getEnd() {
        return end;
    }
    public ErrorMarker getMarker() { return marker; }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    private static ErrorMarker createPositionMarker(int maxLength, Position pos1, Position pos2) {

        // Check requirements
        if(pos1.getSource() != pos2.getSource()) throw new Error("The two have to be located in the same source");
        if(pos1.getLine() != pos2.getLine()) throw new Error("The two positions that should be marked have to be in the same line");
        if(pos1.getColumn() > pos2.getColumn()) throw new Error("Position 1 must be before Position 2");



        // Line start (linenumber + 2 spaces)
        String line_str = pos1.getLine() + "  ";

        // Length of the highlighted section
        int highlighted = pos2.getColumn() - pos1.getColumn() + 1;

        // The maximum amount of characters that will be shown around the highlighted section
        int maxAround = maxLength - highlighted - line_str.length();
        int before = maxAround / 2 + maxAround % 2;
        int after = maxAround / 2;




        // The available tokens before the highlighted section
        int before2 = pos1.getColumn() - 1;

        // The available tokens after the highlighted section
        int after2 = pos2.getSource().getAfterInLine(pos2);

        // Take the smallest value and use it
        int real_before = smallest(before, before2);
        int real_after = smallest(after, after2 + 1);

        // Get the differences (to display if there are tokens that can't be displayed)
        int before_dif = before2 - real_before;
        int after_dif = after2 - real_after;

        // Resolve numbers if there is a non-displayed part
        if(before_dif > 0) {
            int baseLen = String.valueOf(before_dif).length();
            int len = baseLen + 4;
            if(String.valueOf(len).length() != baseLen) real_before -= ++len;
            else real_before -= len;
            before_dif += len;
        }
        if(after_dif > 0) {
            int baseLen = String.valueOf(after_dif).length();
            int len = baseLen + 4;
            if(String.valueOf(len).length() != baseLen) real_after -= ++len;
            else real_after -= len;
            after_dif += len;
        }


        // The start of the line
        String start = line_str
                + (before_dif > 0 ? ("+" + before_dif + "...") : "")
                + String.valueOf(pos1.getSource().getSource().get(pos1.getIndex() - real_before, pos1.getIndex()));

        // The end of the line
        String end = String.valueOf(pos1.getSource().getSource().get(pos2.getIndex()+1, pos2.getIndex() + real_after))
                + (after_dif > 0 ? ("...+" + after_dif) : "");

        // Generate end-string
        return new ErrorMarker(
                pos1.getSource().getLocation() + ':' + pos1.getLine() + ':' + pos1.getColumn(),
                start + Formatting.INVERT + Formatting.FGColor.RED +
                        String.valueOf(pos1.getSource().getSource().get(pos1.getIndex(), pos2.getIndex()+1)) + Formatting.RESET + end,
                start + String.valueOf(pos1.getSource().getSource().get(pos1.getIndex(), pos2.getIndex()+1)) + end,
                strRepeat(' ', start.length())  + strRepeat('^', highlighted));

    }

    private static String strRepeat(char c, int number) {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < number; i++) string.append(c);
        return string.toString();
    }

    private static int smallest(int ...arr) {
        int smallest = arr[0];
        for(int i = 1; i < arr.length; i++) if(arr[i] < smallest) smallest = arr[i];
        return smallest;
    }

    public static class ErrorMarker {

        private final String source;
        private final String colorPreview;
        private final String preview;
        private final String marker;

        public ErrorMarker(String source, String colorPreview, String preview, String marker) {
            this.source = source;
            this.colorPreview = colorPreview;
            this.preview = preview;
            this.marker = marker;
        }

        public String getSource() { return source; }
        public String getPreview() { return preview; }
        public String getColorPreview() { return colorPreview; }
        public String getMarker() { return marker; }

        @Override
        public String toString() {
            return "at " + getSource() + System.lineSeparator() + getColorPreview() + System.lineSeparator() + getMarker();
        }
    }
}
