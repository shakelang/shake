@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.AbstractSpec
import com.shakelang.shake.shakespeare.spec.code.CodeSpec

open class ConstructorSpec(
    open val parameters: List<ParameterSpec>,
    open val body: CodeSpec,
    open val name: NamespaceSpec? = null,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) : AbstractSpec {

    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        if (isSynchronized) builder.append("synchronized ")
        if (isNative) builder.append("native ")
        builder.append("constructor")

        if (name != null) {
            builder.append(" ")
            builder.append(name)
        }

        builder.append("(")
        builder.append(parameters.joinToString(", ") { it.generate(ctx) })
        builder.append(") ")

        builder.append(body.generate(ctx))
        return builder.toString()
    }

    class ConstructorSpecBuilder
    internal constructor() {
        val parameters: MutableList<ParameterSpec> = ArrayList()
        var body: CodeSpec? = null
        var name: NamespaceSpec? = null
        var accessModifier = AccessModifier.PUBLIC
        var isSynchronized = false
        var isNative = false

        fun addParameter(parameter: ParameterSpec): ConstructorSpecBuilder {
            parameters.add(parameter)
            return this
        }

        fun parameters(parameters: List<ParameterSpec>): ConstructorSpecBuilder {
            this.parameters.addAll(parameters)
            return this
        }

        fun parameters(vararg parameters: ParameterSpec): ConstructorSpecBuilder {
            this.parameters.addAll(parameters)
            return this
        }

        fun body(body: CodeSpec): ConstructorSpecBuilder {
            this.body = body
            return this
        }

        fun name(name: NamespaceSpec): ConstructorSpecBuilder {
            this.name = name
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): ConstructorSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun synchronized(isSynchronized: Boolean = true): ConstructorSpecBuilder {
            this.isSynchronized = isSynchronized
            return this
        }

        fun native(isNative: Boolean = true): ConstructorSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun build(): ConstructorSpec {
            return ConstructorSpec(parameters, body!!, name, accessModifier, isSynchronized, isNative)
        }
    }

    companion object {
        fun builder(): ConstructorSpecBuilder {
            return ConstructorSpecBuilder()
        }
    }
}

interface ClassLikeSpec : AbstractSpec {
    val name: String
    val methods: List<MethodSpec>
    val fields: List<FieldSpec>
    val classes: List<ClassLikeSpec>
    val accessModifier: AccessModifier
}

open class ClassSpec(
    override val name: String,
    override val methods: List<MethodSpec>,
    override val fields: List<FieldSpec>,
    override val classes: List<ClassLikeSpec>,
    open val constructors: List<ConstructorSpec>,
    val isAbstract: Boolean = false,
    val isFinal: Boolean = false,
    val isStatic: Boolean = false,
    val isNative: Boolean = false,
    override val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ClassLikeSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        if (isAbstract) builder.append("abstract ")
        if (isFinal) builder.append("final ")
        builder.append("class ")
        builder.append(name)
        builder.append(" {")
        for (field in fields) {
            builder.append(field.generate(ctx))
            builder.append(";")
        }
        for (constructor in constructors) {
            builder.append(constructor.generate(ctx.indent()))
        }
        for (method in methods) {
            builder.append(method.generate(ctx.indent()))
        }
        for (clazz in classes) {
            builder.append(clazz.generate(ctx.indent()))
        }
        builder.append("}")
        return builder.toString()
    }

    open class ClassSpecBuilder
    internal constructor() {
        var name: String? = null
        val methods: MutableList<MethodSpec> = ArrayList()
        val fields: MutableList<FieldSpec> = ArrayList()
        val constructors: MutableList<ConstructorSpec> = ArrayList()
        val classes: MutableList<ClassSpec> = ArrayList()
        var isAbstract = false
        var isFinal = false
        var isStatic = false
        var isNative = false
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: String): ClassSpecBuilder {
            this.name = name
            return this
        }

        fun addMethod(method: MethodSpec): ClassSpecBuilder {
            methods.add(method)
            return this
        }

        fun addField(field: FieldSpec): ClassSpecBuilder {
            fields.add(field)
            return this
        }

        fun addConstructor(constructor: ConstructorSpec): ClassSpecBuilder {
            constructors.add(constructor)
            return this
        }

        fun addClass(clazz: ClassSpec): ClassSpecBuilder {
            classes.add(clazz)
            return this
        }

        fun abstract(isAbstract: Boolean = true): ClassSpecBuilder {
            this.isAbstract = isAbstract
            return this
        }

        fun final(isFinal: Boolean = true): ClassSpecBuilder {
            this.isFinal = isFinal
            return this
        }

        fun static(isStatic: Boolean = true): ClassSpecBuilder {
            this.isStatic = isStatic
            return this
        }

        fun native(isNative: Boolean = true): ClassSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): ClassSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): ClassSpec {
            return ClassSpec(
                name ?: throw IllegalStateException("Name must be set"),
                methods,
                fields,
                classes,
                constructors,
                isAbstract,
                isFinal,
                isStatic,
                isNative,
                accessModifier,
            )
        }
    }

    companion object {
        fun builder(): ClassSpecBuilder {
            return ClassSpecBuilder()
        }
    }
}

open class InterfaceSpec(
    override val name: String,
    override val methods: List<MethodSpec>,
    override val fields: List<FieldSpec>,
    override val classes: List<ClassLikeSpec>,
    override val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ClassLikeSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        builder.append("interface ")
        builder.append(name)
        builder.append(" {")
        for (method in methods) {
            builder.append(method.generate(ctx))
        }
        builder.append("}")
        return builder.toString()
    }

    class InterfaceSpecBuilder
    internal constructor() {
        var name: String? = null
        val methods = mutableListOf<MethodSpec>()
        val fields = mutableListOf<FieldSpec>()
        val classes = mutableListOf<ClassLikeSpec>()
        var isAbstract = false
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: String): InterfaceSpecBuilder {
            this.name = name
            return this
        }

        fun addMethod(method: MethodSpec): InterfaceSpecBuilder {
            methods.add(method)
            return this
        }

        fun addField(field: FieldSpec): InterfaceSpecBuilder {
            fields.add(field)
            return this
        }

        fun addClass(clazz: ClassLikeSpec): InterfaceSpecBuilder {
            classes.add(clazz)
            return this
        }

        fun abstract(isAbstract: Boolean = true): InterfaceSpecBuilder {
            this.isAbstract = isAbstract
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): InterfaceSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): InterfaceSpec {
            return InterfaceSpec(name!!, methods, fields, classes, accessModifier)
        }
    }

    companion object {
        fun builder(): InterfaceSpecBuilder {
            return InterfaceSpecBuilder()
        }
    }
}

open class EnumSpec(
    override val name: String,
    open val constants: List<NamespaceSpec>,
    override val methods: List<MethodSpec>,
    override val fields: List<FieldSpec>,
    override val classes: List<ClassLikeSpec>,
    override val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ClassLikeSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        builder.append("enum ")
        builder.append(name)
        builder.append(" {")
        for (constant in constants) {
            builder.append(constant)
            builder.append(", ")
        }
        builder.append("}")
        return builder.toString()
    }

    class EnumSpecBuilder
    internal constructor() {
        var name: String? = null
        val constants = mutableListOf<NamespaceSpec>()
        val methods = mutableListOf<MethodSpec>()
        val fields = mutableListOf<FieldSpec>()
        val classes = mutableListOf<ClassLikeSpec>()
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: String): EnumSpecBuilder {
            this.name = name
            return this
        }

        fun addConstant(constant: NamespaceSpec): EnumSpecBuilder {
            constants.add(constant)
            return this
        }

        fun addMethod(method: MethodSpec): EnumSpecBuilder {
            methods.add(method)
            return this
        }

        fun addField(field: FieldSpec): EnumSpecBuilder {
            fields.add(field)
            return this
        }

        fun addClass(clazz: ClassLikeSpec): EnumSpecBuilder {
            classes.add(clazz)
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): EnumSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): EnumSpec {
            return EnumSpec(name!!, constants, methods, fields, classes, accessModifier)
        }
    }

    companion object {
        fun builder(): EnumSpecBuilder {
            return EnumSpecBuilder()
        }
    }
}

open class ObjectSpec(
    override val name: String,
    override val methods: List<MethodSpec>,
    override val fields: List<FieldSpec>,
    override val classes: List<ClassLikeSpec> = emptyList(),
    override val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) : ClassLikeSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        builder.append("object ")
        builder.append(name)
        builder.append(" {")
        for (field in fields) {
            builder.append(field.generate(ctx))
        }
        for (method in methods) {
            builder.append(method.generate(ctx))
        }
        builder.append("}")
        return builder.toString()
    }

    class ObjectSpecBuilder
    internal constructor() {
        var name: String? = null
        val methods = mutableListOf<MethodSpec>()
        val fields = mutableListOf<FieldSpec>()
        val classes = mutableListOf<ClassLikeSpec>()
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: String): ObjectSpecBuilder {
            this.name = name
            return this
        }

        fun addMethod(method: MethodSpec): ObjectSpecBuilder {
            methods.add(method)
            return this
        }

        fun addField(field: FieldSpec): ObjectSpecBuilder {
            fields.add(field)
            return this
        }

        fun addClass(clazz: ClassLikeSpec): ObjectSpecBuilder {
            classes.add(clazz)
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): ObjectSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): ObjectSpec {
            return ObjectSpec(name!!, methods, fields, classes, accessModifier)
        }
    }

    companion object {
        fun builder(): ObjectSpecBuilder {
            return ObjectSpecBuilder()
        }
    }
}
