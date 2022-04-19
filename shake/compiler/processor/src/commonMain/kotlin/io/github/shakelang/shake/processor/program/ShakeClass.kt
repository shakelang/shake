package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeScope

class ShakeClass (
    val pkg: ShakePackage?,
    val name: String,
    val methods: List<ShakeMethod>,
    val fields: List<ShakeClassField>,
    val classes: List<ShakeClass>,
    val staticMethods: List<ShakeMethod>,
    val staticFields: List<ShakeClassField>,
    val staticClasses: List<ShakeClass>,
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

    fun compatibleTo(other: ShakeClass): Boolean {
        if (this == other) return true
        if (this.superClass != null && this.superClass!!.compatibleTo(other)) return true
        return this.interfaces.any { it.compatibleTo(other) }
    }

    inner class StaticScope (
        override val parent: ShakeScope,
    ) : ShakeScope {

        override fun get(name: String): ShakeAssignable? {
            return staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(name: String, value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(name: String, function: ShakeFunction) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return staticClasses.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(name: String, klass: ShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override val processor: ShakeCodeProcessor
            get() = parent.processor

    }

    inner class ObjectScope (
        override val parent: ShakeScope,
    ) : ShakeScope {

        override val processor: ShakeCodeProcessor
            get() = parent.processor
        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name } ?: staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(name: String, value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return methods.filter { it.name == name } + staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(name: String, function: ShakeFunction) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(name: String, klass: ShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

    }

    companion object {
        fun from(baseProject: ShakeProject, pkg: ShakePackage?, clz: ShakeClassDeclarationNode): ShakeClass {
            val mtds = clz.methods.map {
                val method = ShakeMethod(
                    it.name,
                    ShakeCode.empty(),
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
            val methods = mtds.filter { !it.isStatic }
            val staticMethods = mtds.filter { it.isStatic }
            val flds = clz.fields.map {
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
            val fields = flds.filter { !it.isStatic }
            val staticFields = flds.filter { it.isStatic }

            // TODO inner classes

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
                pkg,
                clz.name,
                methods,
                fields,
                emptyList(),
                staticMethods,
                staticFields,
                emptyList(),
                constructors,
            )
        }
    }
}