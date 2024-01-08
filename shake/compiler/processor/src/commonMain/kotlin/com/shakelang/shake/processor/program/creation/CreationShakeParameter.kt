package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeVariableUsage
import com.shakelang.shake.processor.program.types.ShakeParameter
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope

class CreationShakeParameter(
    override val project: CreationShakeProject,
    override val name: String,
    override val type: ShakeType,
    override var actualValue: CreationShakeValue? = null,
    override var actualType: ShakeType = type,
) : ShakeParameter, CreationShakeDeclaration {

    // TODO: THESE MAY NOT BE UNIQUE
    override val qualifiedName: String
        get() = name
    override val uniqueName: String
        get() = name

    override fun use(scope: CreationShakeScope): CreationShakeUsage {
        return CreationShakeVariableUsage(scope, this)
    }

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return CreationShakeVariableUsage(scope, this)
    }

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.assignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.additionAssignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.subtractionAssignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.multiplicationAssignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.divisionAssignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.modulusAssignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val typeAssign = type.powerAssignType(other, scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun incrementBeforeType(scope: ShakeScope): ShakeType? {
        val typeAssign = type.incrementBeforeType(scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return null
    }

    override fun incrementAfterType(scope: ShakeScope): ShakeType? {
        val typeAssign = type.incrementAfterType(scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return null
    }

    override fun decrementBeforeType(scope: ShakeScope): ShakeType? {
        val typeAssign = type.decrementBeforeType(scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return null
    }

    override fun decrementAfterType(scope: ShakeScope): ShakeType? {
        val typeAssign = type.decrementAfterType(scope)
        if (typeAssign != null) {
            actualType = typeAssign
            return typeAssign
        }
        return null
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type.toJson(),
        )
    }
}
