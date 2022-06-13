package io.github.shakelang.shake.processor.program.creation.code

import io.github.shakelang.shake.processor.program.creation.CreationShakeAssignable
import io.github.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.*

open class CreationShakeAssignment (
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakeAddAssignment(
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeAddAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakeSubAssignment(
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeSubAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakeMulAssignment(
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeMulAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakeDivAssignment(
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeDivAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakeModAssignment(
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeModAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakePowerAssignment(
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakePowAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson()
        )
    }
}

open class CreationShakeIncrementBefore(
    override val variable: CreationShakeAssignable,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeIncrementBefore {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class CreationShakeIncrementAfter(
    override val variable: CreationShakeAssignable,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeIncrementAfter {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class CreationShakeDecrementBefore(
    override val variable: CreationShakeAssignable,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeDecrementBefore {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}

open class CreationShakeDecrementAfter(
    override val variable: CreationShakeAssignable,
    override val type: ShakeType
) : CreationShakeValue, CreationShakeStatement, ShakeDecrementAfter {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson()
        )
    }
}