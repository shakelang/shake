package com.shakelang.shake.processor.program.creation.code.statements

import com.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import com.shakelang.shake.processor.program.creation.CreationShakeProject
import com.shakelang.shake.processor.program.creation.CreationShakeScope
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeVariableUsage
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.statements.ShakeVariableDeclaration
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

class CreationShakeVariableDeclaration(
    override val scope: CreationShakeScope,
    override val name: String,
    override var type: ShakeType,
    override val initialValue: CreationShakeValue?,
    override val isFinal: Boolean,
) :
    CreationShakeDeclaration,
    CreationShakeStatement,
    ShakeVariableDeclaration {
    override var actualValue: CreationShakeValue? = initialValue
    override var actualType: ShakeType = type

    override val project: CreationShakeProject
        get() = scope.project

    override val qualifiedName: String
        get() = "local $name"

    override fun valueCompatible(value: ShakeValue): Boolean {
        return type.compatibleTo(value.type)
    }

    override fun use(scope: CreationShakeScope): CreationShakeVariableUsage {
        return CreationShakeVariableUsage(scope, this)
    }

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return CreationShakeVariableUsage(scope, this)
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to "variable-declaration",
            "variableType" to type.toJson(),
            "isFinal" to isFinal,
            "initialValue" to initialValue?.toJson(),
            "latestValue" to this.actualValue?.toJson(),
            "latestType" to this.actualType.toJson(),
        )
    }
}
