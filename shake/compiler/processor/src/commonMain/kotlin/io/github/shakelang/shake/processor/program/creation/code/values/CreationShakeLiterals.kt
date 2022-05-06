package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.types.code.values.ShakeBooleanLiteral
import io.github.shakelang.shake.processor.program.types.code.values.ShakeCharacterLiteral
import io.github.shakelang.shake.processor.program.types.code.values.ShakeDoubleLiteral
import io.github.shakelang.shake.processor.program.types.code.values.ShakeIntLiteral

class CreationShakeDoubleLiteral(override val value: Double) : CreationShakeValue, ShakeDoubleLiteral {

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

class CreationShakeIntegerLiteral(override val value: Int) : CreationShakeValue, ShakeIntLiteral {

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

class CreationShakeBooleanLiteral(override val value: Boolean) : CreationShakeValue, ShakeBooleanLiteral {

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

class CreationShakeCharacterLiteral(override val value: Char) : CreationShakeValue, ShakeCharacterLiteral {

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