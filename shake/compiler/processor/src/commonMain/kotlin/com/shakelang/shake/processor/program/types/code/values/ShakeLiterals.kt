package com.shakelang.shake.processor.program.types.code.values

interface ShakeDoubleLiteral : ShakeValue {
    val value: Double
}

interface ShakeFloatLiteral : ShakeValue {
    val value: Float
}

interface ShakeByteLiteral : ShakeValue {
    val value: Byte
}

interface ShakeShortLiteral : ShakeValue {
    val value: Short
}

interface ShakeIntLiteral : ShakeValue {
    val value: Int
}

interface ShakeLongLiteral : ShakeValue {
    val value: Long
}

interface ShakeUByteLiteral : ShakeValue {
    val value: UByte
}

interface ShakeUShortLiteral : ShakeValue {
    val value: UShort
}

interface ShakeUIntLiteral : ShakeValue {
    val value: UInt
}

interface ShakeULongLiteral : ShakeValue {
    val value: ULong
}

interface ShakeBooleanLiteral : ShakeValue {
    val value: Boolean
}

interface ShakeNullLiteral : ShakeValue

interface ShakeCharacterLiteral : ShakeValue {
    val value: Char
}

interface ShakeStringLiteral : ShakeValue {
    val value: String
}
