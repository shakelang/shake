package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeFieldUsage
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeUsage
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.types.ShakeField
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope

open class CreationShakeField(
    override val project: CreationShakeProject,
    override val pkg: CreationShakePackage?,
    override val clazz: CreationShakeClass?,
    override val parentScope: CreationShakeScope,
    override val name: String,
    override val isStatic: Boolean,
    override val isFinal: Boolean,
    override val isAbstract: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isPublic: Boolean,
    override val isNative: Boolean,
    override val initialValue: CreationShakeValue?,
    override val type: CreationShakeType,
    override val expanding: CreationShakeType?
) : CreationShakeDeclaration, CreationShakeAssignable, ShakeField {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val actualValue: CreationShakeValue?
        get() = TODO("Not yet implemented")

    override val actualType: CreationShakeType
        get() = TODO("Not yet implemented")

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType = type.assignType(other, scope) ?: other
    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.additionAssignType(other, scope) ?: type

    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.subtractionAssignType(other, scope) ?: type

    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.multiplicationAssignType(other, scope) ?: type

    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.divisionAssignType(other, scope) ?: type

    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.modulusAssignType(other, scope) ?: type

    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.powerAssignType(other, scope) ?: type

    override fun incrementBeforeType(scope: ShakeScope): ShakeType = type.incrementBeforeType(scope) ?: type
    override fun incrementAfterType(scope: ShakeScope): ShakeType = type.incrementAfterType(scope) ?: type
    override fun decrementBeforeType(scope: ShakeScope): ShakeType? = type.decrementBeforeType(scope) ?: type
    override fun decrementAfterType(scope: ShakeScope): ShakeType? = type.decrementAfterType(scope) ?: type

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return CreationShakeFieldUsage(scope, this)
    }

    override fun use(scope: CreationShakeScope): CreationShakeUsage {
        return CreationShakeFieldUsage(scope, this)
    }

    override fun phase3() {
        debug("phases", "Phase 3 of field $qualifiedName")
        // Nothing to do here
    }
    override fun phase4() {
        debug("phases", "Phase 4 of field $qualifiedName")
        // TODO: Visit initial value
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "isStatic" to isStatic,
            "isFinal" to isFinal,
            "isAbstract" to isAbstract,
            "isPrivate" to isPrivate,
            "isProtected" to isProtected,
            "isPublic" to isPublic,
            "type" to type.toJson()
        )
    }

    companion object {

        val debug = ShakeProcessor.debug.child("creation", "field")

        fun from(
            baseProject: CreationShakeProject,
            pkg: CreationShakePackage?,
            parentScope: CreationShakeScope,
            node: ShakeVariableDeclarationNode
        ): CreationShakeField {
            return CreationShakeField(
                baseProject,
                pkg,
                null,
                parentScope,
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC,
                node.isNative,
                null,
                parentScope.getType(node.type),
                node.expandedType?.let { parentScope.getType(it) }
            )
        }

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            node: ShakeVariableDeclarationNode
        ): CreationShakeField {
            return CreationShakeField(
                clazz.prj,
                clazz.pkg,
                clazz,
                parentScope,
                node.name,
                node.isStatic,
                node.isFinal,
                false,
                node.access == ShakeAccessDescriber.PRIVATE,
                node.access == ShakeAccessDescriber.PROTECTED,
                node.access == ShakeAccessDescriber.PUBLIC,
                node.isNative,
                null,
                parentScope.getType(node.type),
                node.expandedType?.let { parentScope.getType(it) }
            )
        }
    }
}
