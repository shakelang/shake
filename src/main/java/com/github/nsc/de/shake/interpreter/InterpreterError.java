package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.lexer.characterinput.position.Position;
import com.github.nsc.de.shake.lexer.characterinput.position.PositionMap;
import com.github.nsc.de.shake.util.CompilerError;

public class InterpreterError extends CompilerError {

    public InterpreterError(String message, String details, Position start, Position end) {
        super(message, "InterpretationError", details, start, end);
    }

    public InterpreterError(String message, String details, PositionMap map, int start, int end) {
        super(message, "InterpretationError", details, map, start, end);
    }

    public InterpreterError(String message, String details, PositionMap map, int position) {
        this(message, details, map, position, position);
    }

    public InterpreterError(String details, PositionMap map, int start, int end) {
        this("Error occurred in Interpreter", details, map, start, end);
    }

    public InterpreterError(String details, PositionMap map, int position) {
        this("Error occurred in Interpreter", details, map, position);
    }

}
