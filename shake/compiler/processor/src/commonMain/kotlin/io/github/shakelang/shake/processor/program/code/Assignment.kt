package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.processor.program.ShakeAssignable
import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.statements.ShakeStatement
import io.github.shakelang.shake.processor.program.code.values.ShakeValue

open class ShakeAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeAddAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeSubAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeMulAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeDivAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeModAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakePowerAssignment(
    val variable: ShakeAssignable,
    val value: ShakeValue,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeIncrementBefore(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class ShakeIncrementAfter(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class ShakeDecrementBefore(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class ShakeDecrementAfter(
    val variable: ShakeAssignable,
    override val type: ShakeType
) : ShakeValue, ShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}