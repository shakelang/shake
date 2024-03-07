package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.*

class FieldDeclarationBuilder
internal constructor(init: FieldDeclarationBuilder.() -> Unit) : FieldSpec.FieldSpecBuilder(), Builder {
    init {
        init()
    }
}

class ParameterDeclarationBuilder
internal constructor(init: ParameterDeclarationBuilder.() -> Unit) : ParameterSpec.ParameterSpecBuilder(), Builder {
    init {
        init()
    }
}

class MethodDeclarationBuilder
internal constructor(init: MethodDeclarationBuilder.() -> Unit) : MethodSpec.MethodSpecBuilder(), Builder {
    init {
        init()
    }

    fun parameter(init: ParameterDeclarationBuilder.() -> Unit) {
        val builder = ParameterDeclarationBuilder(init)
        parameters.add(builder.build())
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }

    fun args(init: MethodDeclarationBuilder.() -> Unit) {
        init()
    }
}

class ClassDeclarationBuilder
internal constructor(init: ClassDeclarationBuilder.() -> Unit) : ClassSpec.ClassSpecBuilder(), Builder {
    init {
        init()
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit) {
        val builder = FieldDeclarationBuilder(init)
        fields.add(builder.build())
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit) {
        val builder = MethodDeclarationBuilder(init)
        methods.add(builder.build())
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit) {
        val builder = ClassDeclarationBuilder(init)
        this.classes.add(builder.build())
    }
}

class ShakeFileBuilder
internal constructor(init: ShakeFileBuilder.() -> Unit) : Builder {

    val contents = mutableListOf<String>()

    init {
        init()
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit) {
        val builder = ClassDeclarationBuilder(init)
        contents.add(builder.build().generate(GenerationContext()))
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit, name: String) {
        val builder = ClassDeclarationBuilder(init)
        builder.name = NamespaceSpec(name)
        val classSpec = builder.build()
        contents.add(classSpec.generate(GenerationContext()))
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit, name: NamespaceSpec) {
        val builder = ClassDeclarationBuilder(init)
        builder.name = name
        val classSpec = builder.build()
        contents.add(classSpec.generate(GenerationContext()))
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit) {
        val builder = FieldDeclarationBuilder(init)
        contents.add(builder.build().generate(GenerationContext()))
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit, name: String) {
        val builder = FieldDeclarationBuilder(init)
        builder.name = NamespaceSpec(name)
        val fieldSpec = builder.build()
        contents.add(fieldSpec.generate(GenerationContext()))
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit, name: NamespaceSpec) {
        val builder = FieldDeclarationBuilder(init)
        builder.name = name
        val fieldSpec = builder.build()
        contents.add(fieldSpec.generate(GenerationContext()))
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit) {
        val builder = MethodDeclarationBuilder(init)
        contents.add(builder.build().generate(GenerationContext()))
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit, name: String) {
        val builder = MethodDeclarationBuilder(init)
        builder.name = NamespaceSpec(name)
        val methodSpec = builder.build()
        contents.add(methodSpec.generate(GenerationContext()))
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit, name: NamespaceSpec) {
        val builder = MethodDeclarationBuilder(init)
        builder.name = name
        val methodSpec = builder.build()
        contents.add(methodSpec.generate(GenerationContext()))
    }

    fun Package(name: String) {
        contents.add("package $name")
    }

    fun Package(name: NamespaceSpec) {
        contents.add("package $name")
    }

    fun Import(name: String) {
        contents.add("import $name")
    }

    fun Import(name: NamespaceSpec) {
        contents.add("import $name")
    }

    fun newline() {
        contents.add("")
    }

    fun generate(): String {
        return contents.joinToString("\n")
    }
}

fun buildShakeFile(init: ShakeFileBuilder.() -> Unit): String {
    val builder = ShakeFileBuilder(init)
    return builder.generate()
}
