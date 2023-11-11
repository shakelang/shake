package com.shakelang.shake.processor.program.creation.code

import com.shakelang.shake.processor.program.creation.CreationShakeAssignable
import com.shakelang.shake.processor.program.creation.CreationShakeProject
import com.shakelang.shake.processor.program.creation.code.statements.CreationShakeStatement
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.*

open class CreationShakeAssignment(
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
    override val project: CreationShakeProject,
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
