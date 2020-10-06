package com.github.nsc.de.compiler.util;

import com.github.nsc.de.compiler.lexer.Position;

public class CompilerError extends Error {

    private final String name;
    private final String details;
    private final Position start;
    private final Position end;

    public CompilerError(String message, String name, String details, Position start, Position end) {
        super(message + "\n\nat\n\n" + createPositionMarker(60, start, end) + "\n\n");
        this.name = name;
        this.details = details;
        this.start = start;
        this.end = end;
    }

    public CompilerError(String name, String details, Position start, Position end) {
        this("Error occurred: " + name + ", " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn(),name,details,start,end);
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

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    private static String createPositionMarker(int maxLength, Position pos1, Position pos2) {

        if(!pos1.getSource().equals(pos2.getSource()) || !pos1.getContent().equals(pos2.getContent())) throw new Error("The two have to be located in the same source");
        if(pos1.getLine() != pos2.getLine()) throw new Error("The two positions that should be marked have to be in the same line");
        if(pos1.getColumn() > pos2.getColumn()) throw new Error("Position 1 must be before Position 2");

        int highlighted = pos2.getColumn() - pos1.getColumn() + 1;
        int maxAround = maxLength - highlighted;
        int before = maxAround / 2 + maxAround % 2;
        int after = maxAround / 2;

        before = smallest(pos1.getColumn() - 1, before) ;

        int after2 = pos2.getIndex();
        for(;pos2.getContent().length() > after2 + 1 && pos2.getContent().charAt(after2) != '\n'; after2++);

        after2 -= pos2.getIndex();
        after = smallest(after, after2 + 1, before + 10);

        return pos1.getContent().substring(pos1.getIndex() - before, pos2.getIndex() + after)
                + '\n' + strRepeat(' ', before) + strRepeat('^', highlighted);

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
}
