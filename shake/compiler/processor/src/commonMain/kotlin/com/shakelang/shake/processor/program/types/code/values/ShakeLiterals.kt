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
        const val MAX_VALUE = Double.MAX_VALUE
        const val MIN_VALUE = Double.MIN_VALUE
        const val POSITIVE_INFINITY = Double.POSITIVE_INFINITY
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
        const val MAX_VALUE = Float.MAX_VALUE
        const val MIN_VALUE = Float.MIN_VALUE
        const val POSITIVE_INFINITY = Float.POSITIVE_INFINITY
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
        const val MAX_VALUE = Byte.MAX_VALUE
        const val MIN_VALUE = Byte.MIN_VALUE
    }
}

interface ShakeShortLiteral : ShakeValue {

    /**
     * The short value of the literal
     */
    val value: Short

    companion object {
        const val MAX_VALUE = Short.MAX_VALUE
        const val MIN_VALUE = Short.MIN_VALUE
    }
}

/**
 * This interface describes a int literal
 */
interface ShakeIntLiteral : ShakeValue {

    /**
     * The int value of the literal
     */
    val value: Int

    companion object {
        const val MAX_VALUE = Int.MAX_VALUE
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
        const val MAX_VALUE = Long.MAX_VALUE
        const val MIN_VALUE = Long.MIN_VALUE
    }
}

/**
 * This interface describes a ubyte literal
 */
interface ShakeUByteLiteral : ShakeValue {

    /**
     * The ubyte value of the literal
     */
    val value: UByte

    companion object {
        const val MAX_VALUE = UByte.MAX_VALUE
        const val MIN_VALUE = UByte.MIN_VALUE
    }
}

/**
 * This interface describes a ushort literal
 */
interface ShakeUShortLiteral : ShakeValue {

    /**
     * The ushort value of the literal
     */
    val value: UShort

    companion object {
        const val MAX_VALUE = UShort.MAX_VALUE
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
        const val MAX_VALUE = UInt.MAX_VALUE
        const val MIN_VALUE = UInt.MIN_VALUE
    }
}

/**
 * This interface describes a ulong literal
 */
interface ShakeULongLiteral : ShakeValue {

    /**
     * The ulong value of the literal
     */
    val value: ULong

    companion object {
        const val MAX_VALUE = ULong.MAX_VALUE
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
        const val MAX_VALUE = Char.MAX_VALUE
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
