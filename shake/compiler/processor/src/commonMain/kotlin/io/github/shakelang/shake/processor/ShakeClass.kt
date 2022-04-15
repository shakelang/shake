package io.github.shakelang.shake.processor

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode

class ShakeClass (
    val name: String,
    val methods: List<ShakeClassMethod>,
    val fields: List<ShakeClassField>,
    val staticMethods: List<ShakeClassMethod>,
    val staticFields: List<ShakeClassField>,
    val constructors: List<ShakeConstructor> = listOf(),
) {
    var superClass: ShakeClass? = null
        private set
    private var _interfaces: List<ShakeClass?> = listOf()
        private set
    val interfaces: List<ShakeClass>
        get() = _interfaces.map { it!! }

    fun lateinitSuper(): (ShakeClass?) -> ShakeClass? {
        return {
            superClass = it
            it
        }
    }

    fun lateinitInterfaces(number: Int): List<(ShakeClass) -> ShakeClass> {
        val interfaces = MutableList<ShakeClass?>(number) { null }
        this._interfaces = interfaces
        return interfaces.indices.map { i -> {
                interfaces[i] = it
                it
            }
        }
    }

    companion object {
        fun from(baseProject: ShakeProject, clz: ShakeClassDeclarationNode): ShakeClass {
            val methods = clz.methods.filter { !it.isStatic }.map {
                val method = ShakeClassMethod(
                    it.name,
                    "",
                    it.isStatic,
                    it.isFinal,
                    false,
                    false,
                    false,
                    it.access == ShakeAccessDescriber.PRIVATE,
                    it.access == ShakeAccessDescriber.PROTECTED,
                    it.access == ShakeAccessDescriber.PUBLIC,
                )
                method.lateinitReturnType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                method
                    .lateinitParameterTypes(it.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
                method
            }
            val staticMethods = clz.methods.filter { it.isStatic }.map {
                val method = ShakeClassMethod(
                    it.name,
                    "",
                    it.isStatic,
                    it.isFinal,
                    false,
                    false,
                    false,
                    it.access == ShakeAccessDescriber.PRIVATE,
                    it.access == ShakeAccessDescriber.PROTECTED,
                    it.access == ShakeAccessDescriber.PUBLIC,
                )
                method.lateinitReturnType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                method
                    .lateinitParameterTypes(it.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
                method
            }
            val fields = clz.fields.filter { !it.isStatic }.map {
                val field = ShakeClassField(
                    it.name,
                    it.isStatic,
                    it.isFinal,
                    false,
                    it.access == ShakeAccessDescriber.PRIVATE,
                    it.access == ShakeAccessDescriber.PROTECTED,
                    it.access == ShakeAccessDescriber.PUBLIC,
                )
                field.lateinitType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                field
            }
            val staticFields = clz.fields.filter { it.isStatic }.map {
                val field = ShakeClassField(
                    it.name,
                    it.isStatic,
                    it.isFinal,
                    false,
                    it.access == ShakeAccessDescriber.PRIVATE,
                    it.access == ShakeAccessDescriber.PROTECTED,
                    it.access == ShakeAccessDescriber.PUBLIC,
                )
                field.lateinitType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
                field
            }
            val constructors = clz.constructors.map {
                val constr = ShakeConstructor(
                    it.name,
                    "",
                    false,
                    it.access == ShakeAccessDescriber.PRIVATE,
                    it.access == ShakeAccessDescriber.PROTECTED,
                    it.access == ShakeAccessDescriber.PUBLIC,
                )
                constr.lateinitParameterTypes(it.args.map { p -> p.name })
                    .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
                constr
            }
            /*
            TODO Interface & Super
            val superClass = cl.lateinitSuper()
            val interfaces = cl.lateinitInterfaces(clz..size)
             */
            return ShakeClass(
                clz.name,
                methods,
                fields,
                staticMethods,
                staticFields,
                constructors,
            )
        }
    }
}