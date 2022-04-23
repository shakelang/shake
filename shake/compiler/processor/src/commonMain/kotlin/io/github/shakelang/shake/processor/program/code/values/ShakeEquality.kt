package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType


open class ShakeEquals(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "equals",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeNotEquals(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "notEquals",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeLessThan(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lessThan",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeLessThanOrEqual(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lessThanOrEqual",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeGreaterThan(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greaterThan",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeGreaterThanOrEqual(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greaterThanOrEqual",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeAnd(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "and",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeOr(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "or",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeXor(
    val left: ShakeValue,
    val right: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "xor",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class ShakeNot(
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "not",
            "value" to value.toJson()
        )
    }
}