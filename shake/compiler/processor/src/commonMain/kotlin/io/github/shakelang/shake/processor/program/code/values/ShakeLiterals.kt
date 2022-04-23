package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType

class ShakeDoubleLiteral(val value: Double) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.DOUBLE

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "double",
            "value" to value
        )
    }

}

class ShakeIntegerLiteral(val value: Int) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.INT

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "int",
            "value" to value
        )
    }

}

class ShakeBooleanLiteral(val value: Boolean) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.BOOLEAN

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "boolean",
            "value" to value
        )
    }

    companion object {
        val TRUE = ShakeBooleanLiteral(true)
        val FALSE = ShakeBooleanLiteral(false)
    }

}

class ShakeCharacterLiteral(val value: Char) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.CHAR

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "char",
            "value" to value
        )
    }

}