package com.github.nsc.de.shake.interpreter;

import com.github.nsc.de.shake.util.CompilerError;
import com.github.nsc.de.shake.util.characterinput.position.Position;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class InterpreterError extends CompilerError {

    public InterpreterError(String message, String details, Position start, Position end, Throwable cause) {
        super(message, "InterpretationError", details, start, end, cause);
    }

    public InterpreterError(String message, String details, PositionMap map, int start, int end, Throwable cause) {
        super(message, "InterpretationError", details, map, start, end, cause);
    }

    public InterpreterError(String message, String details, PositionMap map, int position, Throwable cause) {
        this(message, details, map, position, position, cause);
    }

    public InterpreterError(String details, PositionMap map, int start, int end, Throwable cause) {
        this("Error occurred in Interpreter", details, map, start, end, cause);
    }

    public InterpreterError(String details, PositionMap map, int position, Throwable cause) {
        this("Error occurred in Interpreter", details, map, position, cause);
    }

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
