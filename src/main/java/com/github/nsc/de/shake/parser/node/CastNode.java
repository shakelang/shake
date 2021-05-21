package com.github.nsc.de.shake.parser.node;

import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

public class CastNode extends ValuedNode {

    private final ValuedNode value;
    private final CastTarget castTarget;

    public CastNode(PositionMap map, ValuedNode value, CastTarget castTarget) {
        super(map);
        this.value = value;
        this.castTarget = castTarget;
    }

    public ValuedNode getValue() {
        return value;
    }

    public CastTarget getCastTarget() {
        return castTarget;
    }

    public static class CastTarget {

        public static final CastTarget BYTE = new CastTarget(CastTargetType.BYTE);
        public static final CastTarget SHORT = new CastTarget(CastTargetType.SHORT);
        public static final CastTarget INTEGER = new CastTarget(CastTargetType.INT);
        public static final CastTarget LONG = new CastTarget(CastTargetType.LONG);
        public static final CastTarget FLOAT = new CastTarget(CastTargetType.FLOAT);
        public static final CastTarget DOUBLE = new CastTarget(CastTargetType.DOUBLE);
        public static final CastTarget BOOLEAN = new CastTarget(CastTargetType.BOOLEAN);
        public static final CastTarget CHAR = new CastTarget(CastTargetType.CHAR);
        public static final CastTarget STRING = new CastTarget(CastTargetType.STRING);

        private final CastTargetType type;
        private final IdentifierNode subtype;

        public CastTarget(CastTargetType type, IdentifierNode subtype) {
            this.type = type;
            this.subtype = subtype;
        }

        public CastTarget(CastTargetType type) {
            this(type, null);
        }

        public CastTarget(IdentifierNode type) {
            this(CastTargetType.OBJECT, type);
        }

        public CastTargetType getType() {
            return type;
        }

        public IdentifierNode getSubtype() {
            return subtype;
        }

        public enum CastTargetType {
            BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR, STRING, OBJECT
        }

        @Override
        public String toString() {
            return subtype != null ? String.valueOf(subtype) : String.valueOf(type);
        }
    }

}
