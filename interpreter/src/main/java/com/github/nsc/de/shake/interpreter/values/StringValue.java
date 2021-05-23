package com.github.nsc.de.shake.interpreter.values;

public class StringValue implements InterpreterValue {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public StringValue add(InterpreterValue v) {
        return new StringValue(this.value + v.toJava());
    }

    /**
     * Get the java-representation of the {@link InterpreterValue}
     *
     * @return the java-representation of the {@link InterpreterValue}
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String toJava() {
        return value;
    }

    /**
     * Returns the name of the type of {@link InterpreterValue} (To identify the type of value)
     *
     * @return the name of the {@link InterpreterValue}
     * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
     */
    @Override
    public String getName() {
        return "string";
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", value);
    }
}
