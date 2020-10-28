package com.github.nsc.de.compiler.interpreter;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;

public class Variable {

    private final String identifier;
    private final VariableType type;
    private final AccessDescriber access;
    private Object value;


    public Variable(String identifier, VariableType type, AccessDescriber access, Object value) {
        if(type == null) throw new Error();
        this.identifier = identifier;
        this.type = type;
        this.access = access;
        this.value = value;
    }


    public Variable(String identifier, VariableType type, Object value) {
        if(type == null) throw new Error();
        this.identifier = identifier;
        this.type = type;
        this.access = AccessDescriber.PACKAGE;
        this.value = value;
    }

    public Variable(String identifier, VariableType type, AccessDescriber access) {
        this(identifier, type, access, null);
    }

    public Variable(String identifier, VariableType type) {
        this(identifier, type, null);
    }

    public VariableType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Object getValue() {
        return value;
    }

    public AccessDescriber getAccess() { return access; }

    public void setValue(Object value) {
        if(value instanceof Byte) this.setValue((Byte) value);
        else if(value instanceof Short) this.setValue((Short) value);
        else if(value instanceof Integer) this.setValue((Integer) value);
        else if(value instanceof Long) this.setValue((Long) value);
        else if(value instanceof Float) this.setValue((Float) value);
        else if(value instanceof Double) this.setValue((Double) value);
        else if(value instanceof Character) this.setValue((Character) value);
        else if(value instanceof Boolean) this.setValue((Boolean) value);
        else this.value = value;
    }

    public void setValue(Byte value) {
        switch (this.getType()) {
            case SHORT:
                this.value = (short) (byte) value;
                break;
            case INTEGER:
                this.value = (int) (byte) value;
                break;
            case LONG:
                this.value = (long) (byte) value;
                break;
            case FLOAT:
                this.value = (float) (byte) value;
                break;
            case DOUBLE:
                this.value = (double) (byte) value;
                break;
            case CHAR:
                this.value = (char) (byte) value;
            case BOOLEAN:
                throw new Error("Can't convert byte to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Short value) {
        switch (this.getType()) {
            case BYTE:
                this.value = (byte) (short) value;
                break;
            case INTEGER:
                this.value = (int) (short) value;
                break;
            case LONG:
                this.value = (long) (short) value;
                break;
            case FLOAT:
                this.value = (float) (short) value;
                break;
            case DOUBLE:
                this.value = (double) (short) value;
                break;
            case CHAR:
                this.value = (char) (short) value;
            case BOOLEAN:
                throw new Error("Can't convert short to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Integer value) {
        switch (this.getType()) {
            case BYTE:
                this.value = (byte) (int) value;
                break;
            case SHORT:
                this.value = (short) (int) value;
                break;
            case LONG:
                this.value = (long) (int) value;
                break;
            case FLOAT:
                this.value = (float) (int) value;
                break;
            case DOUBLE:
                this.value = (double) (int) value;
                break;
            case CHAR:
                this.value = (char) (int) value;
            case BOOLEAN:
                throw new Error("Can't convert integer to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Long value) {
        switch (this.getType()) {
            case BYTE:
                this.value = (byte) (long) value;
                break;
            case SHORT:
                this.value = (short) (long) value;
                break;
            case INTEGER:
                this.value = (int) (long) value;
                break;
            case FLOAT:
                this.value = (float) (long) value;
                break;
            case DOUBLE:
                this.value = (double) (long) value;
                break;
            case CHAR:
                this.value = (char) (long) value;
            case BOOLEAN:
                throw new Error("Can't convert long to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Float value) {
        switch (this.getType()) {
            case BYTE:
                this.value = (byte) (float) value;
                break;
            case SHORT:
                this.value = (short) (float) value;
                break;
            case INTEGER:
                this.value = (int) (float) value;
                break;
            case LONG:
                this.value = (long) (float) value;
                break;
            case DOUBLE:
                this.value = (double) (float) value;
                break;
            case CHAR:
                this.value = (char) (float) value;
            case BOOLEAN:
                throw new Error("Can't convert float to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Double value) {
        switch (this.getType()) {
            case BYTE:
                this.value = (byte) (double) value;
                break;
            case SHORT:
                this.value = (short) (double) value;
                break;
            case INTEGER:
                System.out.println("aaa: "+value);
                this.value = (int) (double) value;
                break;
            case LONG:
                this.value = (long) (double) value;
                break;
            case FLOAT:
                this.value = (float) (double) value;
                break;
            case CHAR:
                this.value = (char) (double) value;
            case BOOLEAN:
                throw new Error("Can't convert double to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Character value) {
        switch (this.getType()) {
            case BYTE:
                this.value = (byte) (char) value;
                break;
            case SHORT:
                this.value = (short) (char) value;
                break;
            case INTEGER:
                this.value = (int) (char) value;
                break;
            case LONG:
                this.value = (long) (char) value;
                break;
            case FLOAT:
                this.value = (float) (char) value;
                break;
            case DOUBLE:
                this.value = (double) (char) value;
            case BOOLEAN:
                throw new Error("Can't convert character to boolean");
            default:
                this.value = value;
        }
    }

    public void setValue(Boolean value) {
        switch (this.getType()) {
            case BYTE:
                throw new Error("Can't convert boolean to byte");
            case SHORT:
                throw new Error("Can't convert boolean to short");
            case INTEGER:
                throw new Error("Can't convert boolean to integer");
            case LONG:
                throw new Error("Can't convert boolean to long");
            case FLOAT:
                throw new Error("Can't convert boolean to float");
            case DOUBLE:
                throw new Error("Can't convert boolean to double");
            default:
                this.value = value;
        }
    }
}
