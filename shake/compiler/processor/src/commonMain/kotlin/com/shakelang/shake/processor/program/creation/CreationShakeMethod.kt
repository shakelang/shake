package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.creation.code.CreationShakeCode
import com.shakelang.shake.processor.program.creation.code.CreationShakeInvokable
import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeMethod
import com.shakelang.shake.processor.program.types.ShakeType

class CreationShakeMethod(
    override val prj: CreationShakeProject,
    override val pkg: CreationShakePackage?,
    override val clazz: CreationShakeClass?,
    override val parentScope: CreationShakeScope,
    override val name: String,
    body: CreationShakeCode?,
    override val isStatic: Boolean,
    override val isFinal: Boolean,
    override val isAbstract: Boolean,
    override val isSynchronized: Boolean,
    override val isStrict: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isPublic: Boolean,
    override val isNative: Boolean,
    override val isOperator: Boolean,
    returnType: CreationShakeType,
    parameters: List<CreationShakeParameter>,
    override val expanding: ShakeType?,

) : CreationShakeInvokable(
    body,
    parameters,
    returnType,
),
    ShakeMethod {

    constructor(
        prj: CreationShakeProject,
        pkg: CreationShakePackage?,
        clazz: CreationShakeClass?,
        parentScope: CreationShakeScope,
        name: String,
        flags: ShakeMethod.ShakeMethodFlags,
        returnType: CreationShakeType,
        parameters: List<CreationShakeParameter>,
        expanding: ShakeType?,
    ) : this(
        prj,
        pkg,
        clazz,
        parentScope,
        name,
        null,
        flags.isStatic,
        flags.isFinal,
        flags.isAbstract,
        flags.isSynchronized,
        flags.isStrict,
        flags.isPrivate,
        flags.isProtected,
        flags.isPublic,
        flags.isNative,
        flags.isOperator,
        returnType,
        parameters,
        expanding,
    )

    private var phase: Byte = 0

    override val qualifiedName: String
        get() = super.qualifiedName

    override val scope: CreationShakeScope = ShakeFunctionScope()

    override fun phase3() {
        debug("phases", "Phase 3 of method $qualifiedSignature")

        if (phase > 2) {
            debug("phases", "Skipping phase 3 of method $qualifiedSignature")
            return
        }
        phase = 3

        // TODO: Implement
    }

    override fun phase4() {
        debug("phases", "Phase 4 of method $qualifiedSignature")

        if (phase > 3) {
            debug("phases", "Skipping phase 4 of method $qualifiedSignature")
            return
        }
        phase = 4

        if (body is CreationShakeCode.ShakeLateProcessCode) body.process(scope)
    }

    override fun toJson(): Map<String, Any?> = super.toJson()

    inner class ShakeFunctionScope : CreationShakeScope() {

        override val uniqueName: String get() = qualifiedSignature

        override val parent: CreationShakeScope = parentScope
        override val project get() = prj

        override fun getField(name: String): CreationShakeAssignable? {
            parameters.find { it.name == name }?.let {
                debug("scope", "Found parameter $name in $uniqueName")
                return it
            }
            debug("scope", "Searching for variable $name in $uniqueName (just redirecting to parent)")
            return parentScope.getField(name)
        }

        override fun getFields(name: String): List<CreationShakeAssignable> {
            debug("scope", "Searching for variable $name in $uniqueName (just redirecting to parent)")
            return parentScope.getFields(name)
        }

        override fun setField(value: CreationShakeDeclaration): Unit = throw IllegalArgumentException("Cannot set a value in a method scope")

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            debug("scope", "Searching for method $name in $uniqueName (just redirecting to parent)")
            return parentScope.getFunctions(name)
        }

        override fun getClass(name: String): CreationShakeClass? {
            debug("scope", "Searching for class $name in $uniqueName (just redirecting to parent)")
            return parentScope.getClass(name)
        }

        override fun getClasses(name: String): List<CreationShakeClass> {
            debug("scope", "Searching for class $name in $uniqueName (just redirecting to parent)")
            return parentScope.getClasses(name)
        }

        override val processor: ShakeASTProcessor
            get() = prj.projectScope.processor

        override fun getThis(): ShakeAssignable? {
            // if method is extension method, the extended type is the this type
            TODO()
        }

        override fun getThis(name: String): ShakeAssignable? {
            // if method is extension method, the extended type is the this type
            TODO()
        }

        override fun getSuper(): ShakeAssignable? = parentScope.getSuper()

        override fun getSuper(name: String): ShakeAssignable? = parentScope.getSuper(name)
    }

    companion object {

        val debug = ShakeProcessor.debug.child("creation", "method")

        fun from(
            baseProject: CreationShakeProject,
            pkg: CreationShakePackage?,
            parentScope: CreationShakeScope,
            node: ShakeMethodDeclarationNode,
        ): CreationShakeMethod = CreationShakeMethod(
            baseProject,
            pkg,
            null,
            parentScope,
            node.name,
            node.body?.let { CreationShakeCode.fromTree(it) },
            node.isStatic,
            node.isFinal,
            false,
            false,
            false,
            node.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PRIVATE,
            node.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PROTECTED,
            node.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PUBLIC,
            node.isNative,
            node.isOperator,
            parentScope.getType(node.type),
            node.args.map {
                CreationShakeParameter(
                    baseProject,
                    it.name,
                    parentScope.getType(it.type),
                )
            },
            node.expandedType?.let { parentScope.getType(it) },
        )

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            node: ShakeMethodDeclarationNode,
        ): CreationShakeMethod = CreationShakeMethod(
            clazz.prj,
            clazz.pkg,
            clazz,
            parentScope,
            node.name,
            node.body?.let { CreationShakeCode.fromTree(it) },
            isStatic = node.isStatic,
            isFinal = node.isFinal,
            isAbstract = node.isAbstract,
            isSynchronized = node.isSynchronized,
            isStrict = false,
            isPrivate = node.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PRIVATE,
            isProtected = node.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PROTECTED,
            isPublic = node.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PUBLIC,
            isNative = node.isNative,
            isOperator = node.isOperator,
            parentScope.getType(node.type),
            node.args.map {
                CreationShakeParameter(
                    clazz.prj,
                    it.name,
                    parentScope.getType(it.type),
                )
            },
            node.expandedType?.let { parentScope.getType(it) },
        )

        fun disablePhases(e: CreationShakeMethod) {
            e.phase = 4
        }

        fun initCode(e: CreationShakeMethod, code: CreationShakeCode) {
        }
    }
}
