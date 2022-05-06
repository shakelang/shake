package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.*
import io.github.shakelang.shake.processor.program.types.code.ShakeCode
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeVariableDeclaration

class ImmutableShakeMethod (
    override val clazz: ImmutableShakeClass,
    parentScope: ImmutableShakeScope,
    name: String,
    body: ShakeCode,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isSynchronized: Boolean,
    isStrict: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
) : ImmutableShakeFunction(
    clazz.prj,
    clazz.pkg,
    parentScope,
    name,
    body,
    isStatic,
    isFinal,
    isAbstract,
    isSynchronized,
    isStrict,
    isPrivate,
    isProtected,
    isPublic
), ShakeMethod {

    override val scope = ShakeMethodScope()


    override fun toJson(): Map<String, Any?> {
        return super.toJson() + mapOf(
            "type" to "method",
            "class" to clazz.name,
            "body" to body.toJson()
        )
    }

    inner class ShakeMethodScope : ImmutableShakeScope {
        override val parent: ImmutableShakeScope = if(isStatic) clazz.staticScope else clazz.instanceScope

        val variables = mutableListOf<ShakeVariableDeclaration>()

        override fun get(name: String): ShakeAssignable? {
            return variables.find { it.name == name } ?: parent.get(name)
        }

        override fun getFunctions(name: String): List<ImmutableShakeFunction> {
            return parent.getFunctions(name)
        }

        override fun getClass(name: String): ImmutableShakeClass? {
            return parent.getClass(name)
        }
    }

}