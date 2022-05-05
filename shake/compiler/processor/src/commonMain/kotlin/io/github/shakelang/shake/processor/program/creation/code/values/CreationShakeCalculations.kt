package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType


open class CreationShakeAddition(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "addition",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeSubtraction(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "subtraction",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeMultiplication(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "multiplication",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeDivision(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "division",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeModulus(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "modulus",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakePower(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "power",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}