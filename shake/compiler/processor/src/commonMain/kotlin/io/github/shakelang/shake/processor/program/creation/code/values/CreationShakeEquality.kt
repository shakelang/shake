package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType


open class CreationShakeEquals(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "equals",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeNotEquals(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "notEquals",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeLessThan(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lessThan",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeLessThanOrEqual(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lessThanOrEqual",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeGreaterThan(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greaterThan",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeGreaterThanOrEqual(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greaterThanOrEqual",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeAnd(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "and",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeOr(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "or",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeXor(
    val left: CreationShakeValue,
    val right: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "xor",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeNot(
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "not",
            "value" to value.toJson()
        )
    }
}