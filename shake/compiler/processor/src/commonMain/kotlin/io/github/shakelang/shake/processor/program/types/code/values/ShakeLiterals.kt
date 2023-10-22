package io.github.shakelang.shake.processor.program.types.code.values

interface ShakeDoubleLiteral : ShakeValue {
    val value: Double
}

interface ShakeIntLiteral : ShakeValue {
    val value: Int
}

interface ShakeBooleanLiteral : ShakeValue {
    val value: Boolean
}

interface ShakeCharacterLiteral : ShakeValue {
    val value: Char
}

interface ShakeStringLiteral : ShakeValue {
    val value: String
}