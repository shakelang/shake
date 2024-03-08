package com.shakelang.shake.processor.program.creation.code.values

import com.shakelang.shake.processor.program.creation.CreationShakeProject
import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.types.code.values.*

class CreationShakeDoubleLiteral(
    override val project: CreationShakeProject,
    override val value: Double,
) : CreationShakeValue, ShakeDoubleLiteral {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.DOUBLE

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "doubles",
            "value" to value,
        )
    }
}

class CreationShakeIntegerLiteral(
    override val project: CreationShakeProject,
    override val value: Int,
) : CreationShakeValue, ShakeIntLiteral {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.INT

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "int",
            "value" to value,
        )
    }
}

class CreationShakeBooleanLiteral(
    override val project: CreationShakeProject,
    override val value: Boolean,
) : CreationShakeValue, ShakeBooleanLiteral {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.BOOLEAN

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "boolean",
            "value" to value,
        )
    }
}

class CreationShakeNullLiteral(
    override val project: CreationShakeProject,
) : CreationShakeValue, ShakeNullLiteral {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.NULL

    override fun toString(): String = "null"

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "null",
        )
    }
}

class CreationShakeCharacterLiteral(
    override val project: CreationShakeProject,
    override val value: Char,
) : CreationShakeValue, ShakeCharacterLiteral {

    override val type: CreationShakeType
        get() = CreationShakeType.Primitives.CHAR

    override fun toString(): String = value.toString()

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "char",
            "value" to value,
        )
    }
}

class CreationShakeStringLiteral(
    override val project: CreationShakeProject,
    override val value: String,
) : CreationShakeValue, ShakeStringLiteral {

    override val type: CreationShakeType get() = project.cores.String

    override fun toString(): String = value

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "string",
            "value" to value,
        )
    }
}
