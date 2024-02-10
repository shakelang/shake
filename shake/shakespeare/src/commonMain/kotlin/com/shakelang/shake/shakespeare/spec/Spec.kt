package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec

class ParameterSpec(
    val name: Identifier,
    val type: Type,
) {
    fun generate(ctx: GenerationContext): String {
        return "${type.generate(ctx)} name"
    }

    class ParameterSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var type: Type? = null
        fun name(name: Identifier): ParameterSpecBuilder {
            this.name = name
            return this
        }

        fun type(type: Type): ParameterSpecBuilder {
            this.type = type
            return this
        }

        fun build(): ParameterSpec {
            return ParameterSpec(name!!, type!!)
        }
    }

    companion object {
        fun builder(): ParameterSpecBuilder {
            return ParameterSpecBuilder()
        }
    }
}

enum class AccessModifier(val value: String) {
    PUBLIC("public"),
    PROTECTED("protected"),
    PRIVATE("private"),
    PACKAGE_PRIVATE(""),

    ;

    override fun toString(): String {
        return value
    }

    fun prefix(): String {
        return if (this == PACKAGE_PRIVATE) "" else "$value "
    }
}

class MethodSpec(
    val name: Identifier,
    val returnType: Type,
    val parameters: List<ParameterSpec>,
    val body: CodeSpec,
    val isStatic: Boolean = false,
    val isAbstract: Boolean = false,
    val isFinal: Boolean = false,
    val isOverride: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) {
    fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()

        builder.append(accessModifier.prefix())
        if (isStatic) builder.append("static ")
        if (isAbstract) builder.append("abstract ")
        if (isFinal) builder.append("final ")
        if (isOverride) builder.append("override ")
        if (isSynchronized) builder.append("synchronized ")
        if (isNative) builder.append("native ")

        builder.append(returnType.generate(ctx))
        builder.append(" ")
        builder.append(name)
        builder.append("(")
        builder.append(parameters.joinToString(", ") { it.generate(ctx) })
        builder.append(")")
        builder.append(body.generate(ctx))
        return builder.toString()
    }

    class MethodSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var returnType: Type? = null
        val parameters: MutableList<ParameterSpec> = ArrayList()
        var body: CodeSpec? = null
        var isStatic = false
        var isAbstract = false
        var isFinal = false
        var isOverride = false
        var accessModifier = AccessModifier.PUBLIC
        var isSynchronized = false
        var isNative = false

        fun name(name: Identifier): MethodSpecBuilder {
            this.name = name
            return this
        }

        fun returnType(returnType: Type): MethodSpecBuilder {
            this.returnType = returnType
            return this
        }

        fun addParameter(parameter: ParameterSpec): MethodSpecBuilder {
            parameters.add(parameter)
            return this
        }

        fun body(body: CodeSpec): MethodSpecBuilder {
            this.body = body
            return this
        }

        fun static(isStatic: Boolean = true): MethodSpecBuilder {
            this.isStatic = isStatic
            return this
        }

        fun abstract(isAbstract: Boolean = true): MethodSpecBuilder {
            this.isAbstract = isAbstract
            return this
        }

        fun final(isFinal: Boolean = true): MethodSpecBuilder {
            this.isFinal = isFinal
            return this
        }

        fun override(isOverride: Boolean = true): MethodSpecBuilder {
            this.isOverride = isOverride
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): MethodSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun synchronized(isSynchronized: Boolean = true): MethodSpecBuilder {
            this.isSynchronized = isSynchronized
            return this
        }

        fun native(isNative: Boolean = true): MethodSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun build(): MethodSpec {
            return MethodSpec(name!!, returnType!!, parameters, body!!)
        }
    }

    companion object {
        fun builder(): MethodSpecBuilder {
            return MethodSpecBuilder()
        }
    }
}

class FieldSpec(
    val name: Identifier,
    val type: Type,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) {
    fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        if (isStatic) builder.append("static ")
        if (isFinal) builder.append("final ")
        if (isSynchronized) builder.append("synchronized ")
        if (isNative) builder.append("native ")
        builder.append(type.generate(ctx))
        builder.append(" ")
        builder.append(name)
        return builder.toString()
    }

    class FieldSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var type: Type? = null
        var isStatic = false
        var isFinal = false
        var accessModifier = AccessModifier.PUBLIC
        var isSynchronized = false
        var isNative = false

        fun name(name: Identifier): FieldSpecBuilder {
            this.name = name
            return this
        }

        fun type(type: Type): FieldSpecBuilder {
            this.type = type
            return this
        }

        fun static(isStatic: Boolean = true): FieldSpecBuilder {
            this.isStatic = isStatic
            return this
        }

        fun final(isFinal: Boolean = true): FieldSpecBuilder {
            this.isFinal = isFinal
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): FieldSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun synchronized(isSynchronized: Boolean = true): FieldSpecBuilder {
            this.isSynchronized = isSynchronized
            return this
        }

        fun native(isNative: Boolean = true): FieldSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun build(): FieldSpec {
            return FieldSpec(name!!, type!!)
        }
    }

    companion object {
        fun builder(): FieldSpecBuilder {
            return FieldSpecBuilder()
        }
    }
}

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
        builder.append(")")

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
            return ConstructorSpec(parameters, body!!)
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
