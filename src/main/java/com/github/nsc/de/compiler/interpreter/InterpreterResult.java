package com.github.nsc.de.compiler.interpreter;

public class InterpreterResult<T> {

    private final T value;

    public InterpreterResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "InterpreterResult{" +
                "value=" + value +
                '}';
    }
}
