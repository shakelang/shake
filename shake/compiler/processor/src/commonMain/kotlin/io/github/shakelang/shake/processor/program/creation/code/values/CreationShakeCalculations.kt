package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.*


open class CreationShakeAddition(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeAddition {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "addition",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeSubtraction(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeSubtraction {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "subtraction",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeMultiplication(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeMultiplication {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "multiplication",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeDivision(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeDivision {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "division",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeModulus(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeModulus {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "modulus",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakePower(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakePower {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "power",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}