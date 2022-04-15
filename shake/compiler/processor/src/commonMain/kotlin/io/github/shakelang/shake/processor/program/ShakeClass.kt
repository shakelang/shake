package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeScope

class ShakeClass (
    val pkg: ShakePackage?,
    val name: String,
    val methods: List<ShakeMethod>,
    val fields: List<ShakeClassField>,
    val staticMethods: List<ShakeMethod>,
    val staticFields: List<ShakeClassField>,
    val constructors: List<ShakeConstructor> = listOf(),
) : ShakeScope {
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
    override fun get(name: String): ShakeDeclaration? {
        return fields.find { it.name == name } ?:
            staticFields.find { it.name == name } ?: superClass?.get(name)
    }

    override fun set(name: String, value: ShakeDeclaration) {
        TODO("Not yet implemented")
    }

    override fun getFunctions(name: String): List<ShakeFunction> {
        val functions: MutableList<ShakeFunction> = this@ShakeClass.methods.filter { it.name == name }.toMutableList()
        functions.addAll(this@ShakeClass.staticMethods.filter { it.name == name })
        pkg?.getFunctions(name)?.let { functions.addAll(it) }
        return functions
    }

    override fun setFunctions(name: String, function: ShakeFunction) {
        TODO("Not yet implemented")
    }

    override fun getClass(name: String): List<ShakeClass> {
        TODO("Not yet implemented")
    }

    override fun setClass(name: String, klass: ShakeClass) {
        TODO("Not yet implemented")
    }

    companion object {
        fun from(baseProject: ShakeProject, pkg: ShakePackage?, clz: ShakeClassDeclarationNode): ShakeClass {
            val methods = clz.methods.filter { !it.isStatic }.map {
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
            val staticMethods = clz.methods.filter { it.isStatic }.map {
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
                pkg,
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