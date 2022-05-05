package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType

class CreationShakeDoubleLiteral(val value: Double) : CreationShakeValue {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.DOUBLE

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "double",
            "value" to value
        )
    }

}

class CreationShakeIntegerLiteral(val value: Int) : CreationShakeValue {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.INT

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "int",
            "value" to value
        )
    }

}

class CreationShakeBooleanLiteral(val value: Boolean) : CreationShakeValue {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.BOOLEAN

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "boolean",
            "value" to value
        )
    }

    companion object {
        val TRUE = CreationShakeBooleanLiteral(true)
        val FALSE = CreationShakeBooleanLiteral(false)
    }

}

class CreationShakeCharacterLiteral(val value: Char) : CreationShakeValue {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.CHAR

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "char",
            "value" to value
        )
    }

}