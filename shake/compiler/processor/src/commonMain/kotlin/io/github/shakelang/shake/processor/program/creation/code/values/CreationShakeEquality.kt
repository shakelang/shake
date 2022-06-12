package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.*


open class CreationShakeEquals(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeEquals {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "equals",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeNotEquals(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeNotEquals {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "notEquals",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeLessThan(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeLessThan {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lessThan",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeLessThanOrEqual(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeLessThanOrEqual {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "lessThanOrEqual",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeGreaterThan(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeGreaterThan {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greaterThan",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeGreaterThanOrEqual(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeGreaterThanOrEqual {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greaterThanOrEqual",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeAnd(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeAnd {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "and",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeOr(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeOr {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "or",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeXor(
    override val left: CreationShakeValue,
    override val right: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeXor {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "xor",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}

open class CreationShakeNot(
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, ShakeNot {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "not",
            "value" to value.toJson()
        )
    }
}