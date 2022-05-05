package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeAssignable
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue

open class ShakeAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeAddAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeSubAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeMulAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeDivAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeModAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakePowerAssignment(
    val variable: CreationShakeAssignable,
    val value: CreationShakeValue,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class ShakeIncrementBefore(
    val variable: CreationShakeAssignable,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class ShakeIncrementAfter(
    val variable: CreationShakeAssignable,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class ShakeDecrementBefore(
    val variable: CreationShakeAssignable,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class ShakeDecrementAfter(
    val variable: CreationShakeAssignable,
    override val type: CreationShakeType
) : CreationShakeValue, CreationShakeStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}