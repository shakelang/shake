package com.shakelang.shake.processor.program.types.code.values

/**
 * This interface describes a double literal
 */
interface ShakeDoubleLiteral : ShakeValue {

    /**
     * The double value of the literal
     */
    val value: Double

    companion object {

        /**
         * The maximum value of a double
         */
        const val MAX_VALUE = Double.MAX_VALUE

        /**
         * The minimum value of a double
         */
        const val MIN_VALUE = Double.MIN_VALUE

        /**
         * The positive infinity value of a double
         */
        const val POSITIVE_INFINITY = Double.POSITIVE_INFINITY

        /**
         * The negative infinity value of a double
         */
        const val NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY
    }
}

/**
 * This interface describes a float literal
 */
interface ShakeFloatLiteral : ShakeValue {

    /**
     * The float value of the literal
     */
    val value: Float

    companion object {

        /**
         * The maximum value of a float
         */
        const val MAX_VALUE = Float.MAX_VALUE

        /**
         * The minimum value of a float
         */
        const val MIN_VALUE = Float.MIN_VALUE

        /**
         * The positive infinity value of a float
         */
        const val POSITIVE_INFINITY = Float.POSITIVE_INFINITY

        /**
         * The negative infinity value of a float
         */
        const val NEGATIVE_INFINITY = Float.NEGATIVE_INFINITY
    }
}

/**
 * This interface describes a byte literal
 */
interface ShakeByteLiteral : ShakeValue {

    /**
     * The byte value of the literal
     */
    val value: Byte

    companion object {

        /**
         * The maximum value of a byte
         */
        const val MAX_VALUE = Byte.MAX_VALUE

        /**
         * The minimum value of a byte
         */
        const val MIN_VALUE = Byte.MIN_VALUE
    }
}

interface ShakeShortLiteral : ShakeValue {

    /**
     * The shorts value of the literal
     */
    val value: Short

    companion object {

        /**
         * The maximum value of a shorts
         */
        const val MAX_VALUE = Short.MAX_VALUE

        /**
         * The minimum value of a shorts
         */
        const val MIN_VALUE = Short.MIN_VALUE
    }
}

/**
 * This interface describes an int literal
 */
interface ShakeIntLiteral : ShakeValue {

    /**
     * The int value of the literal
     */
    val value: Int

    companion object {

        /**
         * The maximum value of an int
         */
        const val MAX_VALUE = Int.MAX_VALUE

        /**
         * The minimum value of an int
         */
        const val MIN_VALUE = Int.MIN_VALUE
    }
}

/**
 * This interface describes a long literal
 */
interface ShakeLongLiteral : ShakeValue {

    /**
     * The long value of the literal
     */
    val value: Long

    companion object {

        /**
         * The maximum value of a long
         */
        const val MAX_VALUE = Long.MAX_VALUE

        /**
         * The minimum value of a long
         */
        const val MIN_VALUE = Long.MIN_VALUE
    }
}

/**
 * This interface describes an ubyte literal
 */
interface ShakeUByteLiteral : ShakeValue {

    /**
     * The ubyte value of the literal
     */
    val value: UByte

    companion object {

        /**
         * The maximum value of an ubyte
         */
        const val MAX_VALUE = UByte.MAX_VALUE

        /**
         * The minimum value of an ubyte
         */
        const val MIN_VALUE = UByte.MIN_VALUE
    }
}

/**
 * This interface describes an ushort literal
 */
interface ShakeUShortLiteral : ShakeValue {

    /**
     * The ushort value of the literal
     */
    val value: UShort

    companion object {

        /**
         * The maximum value of an ushort
         */
        const val MAX_VALUE = UShort.MAX_VALUE

        /**
         * The minimum value of an ushort
         */
        const val MIN_VALUE = UShort.MIN_VALUE
    }
}

/**
 * This interface describes a uint literal
 */
interface ShakeUIntLiteral : ShakeValue {

    /**
     * The uint value of the literal
     */
    val value: UInt

    companion object {

        /**
         * The maximum value of an uint
         */
        const val MAX_VALUE = UInt.MAX_VALUE

        /**
         * The minimum value of an uint
         */
        const val MIN_VALUE = UInt.MIN_VALUE
    }
}

/**
 * This interface describes an ulong literal
 */
interface ShakeULongLiteral : ShakeValue {

    /**
     * The ulong value of the literal
     */
    val value: ULong

    companion object {

        /**
         * The maximum value of an ulong
         */
        const val MAX_VALUE = ULong.MAX_VALUE

        /**
         * The minimum value of an ulong
         */
        const val MIN_VALUE = ULong.MIN_VALUE
    }
}

/**
 * This interface describes a boolean literal
 */
interface ShakeBooleanLiteral : ShakeValue {

    /**
     * The boolean value of the literal
     */
    val value: Boolean
}

/**
 * This interface describes a null literal
 */
interface ShakeNullLiteral : ShakeValue

/**
 * This interface describes a char literal
 */
interface ShakeCharacterLiteral : ShakeValue {

    /**
     * The char value of the literal
     */
    val value: Char

    companion object {

        /**
         * The maximum value of a char
         */
        const val MAX_VALUE = Char.MAX_VALUE

        /**
         * The minimum value of a char
         */
        const val MIN_VALUE = Char.MIN_VALUE
    }
}

/**
 * This interface describes a string literal
 */
interface ShakeStringLiteral : ShakeValue {

    /**
     * The string value of the literal
     */
    val value: String
}
