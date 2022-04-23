package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType


open class ShakeAddition(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "addition",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeSubtraction(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "subtraction",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeMultiplication(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "multiplication",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeDivision(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "division",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeModulus(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "modulus",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakePower(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "power",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}