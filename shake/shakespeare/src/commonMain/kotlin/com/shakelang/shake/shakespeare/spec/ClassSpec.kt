@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec

class ConstructorSpec(
    val parameters: List<ParameterSpec>,
    val body: CodeSpec,
    val name: Identifier? = null,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) {

    fun generate(ctx: GenerationContext): String {
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
        var name: Identifier? = null
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

        fun name(name: Identifier): ConstructorSpecBuilder {
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

class ClassSpec(
    val name: Identifier,
    val methods: List<MethodSpec>,
    val fields: List<FieldSpec>,
    val constructors: List<ConstructorSpec>,
    val isAbstract: Boolean = false,
    val isFinal: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) {
    fun generate(ctx: GenerationContext): String {
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
            builder.append(constructor.generate(ctx))
        }
        for (method in methods) {
            builder.append(method.generate(ctx))
        }
        builder.append("}")
        return builder.toString()
    }

    class ClassSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        val methods: MutableList<MethodSpec> = ArrayList()
        val fields: MutableList<FieldSpec> = ArrayList()
        val constructors: MutableList<ConstructorSpec> = ArrayList()
        var isAbstract = false
        var isFinal = false
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: Identifier): ClassSpecBuilder {
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

        fun abstract(isAbstract: Boolean = true): ClassSpecBuilder {
            this.isAbstract = isAbstract
            return this
        }

        fun final(isFinal: Boolean = true): ClassSpecBuilder {
            this.isFinal = isFinal
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): ClassSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): ClassSpec {
            return ClassSpec(name!!, methods, fields, constructors)
        }
    }

    companion object {
        fun builder(): ClassSpecBuilder {
            return ClassSpecBuilder()
        }
    }
}

class InterfaceSpec(
    val name: Identifier,
    val methods: List<MethodSpec>,
    val isAbstract: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) {
    fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        if (isAbstract) builder.append("abstract ")
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
        var name: Identifier? = null
        val methods: MutableList<MethodSpec> = ArrayList()
        var isAbstract = false
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: Identifier): InterfaceSpecBuilder {
            this.name = name
            return this
        }

        fun addMethod(method: MethodSpec): InterfaceSpecBuilder {
            methods.add(method)
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
            return InterfaceSpec(name!!, methods)
        }
    }

    companion object {
        fun builder(): InterfaceSpecBuilder {
            return InterfaceSpecBuilder()
        }
    }
}

class EnumSpec(
    val name: Identifier,
    val constants: List<Identifier>,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) {
    fun generate(ctx: GenerationContext): String {
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
        var name: Identifier? = null
        val constants: MutableList<Identifier> = ArrayList()
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: Identifier): EnumSpecBuilder {
            this.name = name
            return this
        }

        fun addConstant(constant: Identifier): EnumSpecBuilder {
            constants.add(constant)
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): EnumSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): EnumSpec {
            return EnumSpec(name!!, constants)
        }
    }

    companion object {
        fun builder(): EnumSpecBuilder {
            return EnumSpecBuilder()
        }
    }
}

class ObjectSpec(
    val name: Identifier,
    val methods: List<MethodSpec>,
    val fields: List<FieldSpec>,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
) {
    fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        builder.append("object ")
        builder.append(name)
        builder.append(" {")
        for (field in fields) {
            builder.append(field.generate(ctx))
            builder.append(";")
        }
        for (method in methods) {
            builder.append(method.generate(ctx))
        }
        builder.append("}")
        return builder.toString()
    }

    class ObjectSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        val methods: MutableList<MethodSpec> = ArrayList()
        val fields: MutableList<FieldSpec> = ArrayList()
        var accessModifier = AccessModifier.PUBLIC

        fun name(name: Identifier): ObjectSpecBuilder {
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

        fun accessModifier(accessModifier: AccessModifier): ObjectSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun build(): ObjectSpec {
            return ObjectSpec(name!!, methods, fields)
        }
    }

    companion object {
        fun builder(): ObjectSpecBuilder {
            return ObjectSpecBuilder()
        }
    }
}
