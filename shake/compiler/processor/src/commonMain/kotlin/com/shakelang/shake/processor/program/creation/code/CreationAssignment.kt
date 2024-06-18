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
    override val type: ShakeType,
) : CreationShakeValuedStatement, CreationShakeStatement, ShakeAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakeAddAssignment(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeAddAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakeSubAssignment(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeSubAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakeMulAssignment(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeMulAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakeDivAssignment(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeDivAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakeModAssignment(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeModAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakePowerAssignment(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val value: CreationShakeValue,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakePowAssignment {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
            "value" to value.toJson(),
        )
    }
}

open class CreationShakeIncrementBefore(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeIncrementBefore {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
        )
    }
}

open class CreationShakeIncrementAfter(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeIncrementAfter {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
        )
    }
}

open class CreationShakeDecrementBefore(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeDecrementBefore {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
        )
    }
}

open class CreationShakeDecrementAfter(
    override val project: CreationShakeProject,
    override val variable: CreationShakeAssignable,
    override val type: ShakeType,
) : CreationShakeValuedStatement, ShakeDecrementAfter {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "assignment",
            "variable" to variable.toJson(),
        )
    }
}
