package com.shakelang.shake.bytecode.tools

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.CodeAttribute
import com.shakelang.util.io.streaming.input.inputStream
import com.shakelang.util.primitives.bytes.toHexString
import kotlin.jvm.JvmName

object BytecodeStringGenerator {

    private fun List<String>.indent(indent: String = "  "): List<String> = this.map { "$indent$it" }

    fun MutableList<String>.indent(indent: String, generator: MutableList<String>.() -> Unit) {
        addAll(buildList(generator).indent(indent))
    }

    @JvmName("generateAttribute")
    fun generate(attribute: Attribute): List<String> = buildList {
        return buildList {
            add("+Attribute(${attribute.name}): ${attribute.value.toHexString()}")
        }
    }

    @JvmName("generateCodeAttribute")
    fun generate(bytecode: CodeAttribute, indent: String = "  "): List<String> {
        return buildList {
            add("@Code")

            indent(indent) {
                add("+MaxStack: ${bytecode.maxStack}")
                add("+MaxLocals: ${bytecode.maxLocals}")
                bytecode.exceptionTable.forEach {
                    add("+ExceptionTableEntry(${it.startPc}, ${it.endPc}, ${it.handlerPc}, ${it.catchType})")
                }
                bytecode.attributes.forEach { addAll(generate(it)) }

                add("@Bytecode")
                addAll(
                    InstructionStringGenerator(
                        bytecode.code.inputStream(),
                    ).generate().indent(indent),
                )
                add("@EndBytecode")
            }

            add("@EndCode")
        }
    }

    @JvmName("generateMethod")
    fun generate(method: Method, indent: String = "  "): List<String> {
        return buildList {
            add("@Method(${method.qualifiedName})")

            indent(indent) {
                if (method.isPublic) add("+Public")
                if (method.isPrivate) add("+Private")
                if (method.isProtected) add("+Protected")
                if (method.isStatic) add("+Static")
                if (method.isFinal) add("+Final")
                if (method.isAbstract) add("+Abstract")
                if (method.isSynchronized) add("+Synchronized")
                if (method.isNative) add("+Native")
                if (method.isStrict) add("+Strict")

                method.attributes.filter {
                    // Code attributes are generated separately
                    it.name != "Code"
                }.forEach { addAll(generate(it)) }
            }

            method.attributes.filter {
                // Code attributes are generated separately
                it.name == "Code"
            }.forEach { addAll(generate(it as CodeAttribute)) }

            add("@EndMethod")
        }
    }

    @JvmName("generateMethods")
    fun generate(methods: List<Method>, indent: String = "  "): List<String> = buildList {
        methods.forEach { addAll(generate(it, indent)) }
    }

    @JvmName("generateField")
    fun generate(field: Field, indent: String = "  "): List<String> {
        return buildList {
            add("@Field(${field.name})")

            indent(indent) {
                if (field.isPublic) add("+Public")
                if (field.isPrivate) add("+Private")
                if (field.isProtected) add("+Protected")
                if (field.isStatic) add("+Static")
                if (field.isFinal) add("+Final")
                if (field.isAbstract) add("+Abstract")
                field.attributes.forEach { addAll(generate(it)) }
            }

            add("@EndField")
        }
    }

    @JvmName("generateFields")
    fun generate(fields: List<Field>, indent: String = "  "): List<String> = buildList {
        fields.forEach { addAll(generate(it, indent)) }
    }

    @JvmName("generateClass")
    fun generate(clazz: Class, indent: String = "  "): List<String> {
        val builder = StringBuilder()

        return buildList {
            add("@Class(${clazz.name})")

            addAll(
                buildList {
                    add("+SuperClass: ${clazz.superName}")

                    if (clazz.isPublic) add("+Public")
                    if (clazz.isPrivate) add("+Private")
                    if (clazz.isProtected) add("+Protected")
                    if (clazz.isStatic) add("+Static")
                    if (clazz.isFinal) add("+Final")
                    if (clazz.isAbstract) add("+Abstract")
                    if (clazz.isInterface) add("+Interface")
                    if (clazz.isAnnotation) add("+Annotation")
                    if (clazz.isEnum) add("+Enum")
                    if (clazz.isSynthetic) add("+Synthetic")
                    if (clazz.isObject) add("+Object")
                    clazz.attributes.forEach { addAll(generate(it)) }

                    add("@Fields")
                    addAll(generate(clazz.fields, indent).indent(indent))
                    add("@EndFields")

                    add("@Methods")
                    addAll(generate(clazz.methods, indent).indent(indent))
                    add("@EndMethods")

                    add("@Classes")
                    addAll(generate(clazz.subClasses, indent).indent(indent))
                    add("@EndClasses")
                }.indent(indent),
            )

            add("@EndClass")
        }
    }

    @JvmName("generateClasses")
    fun generate(classes: List<Class>, indent: String = "  "): List<String> = buildList {
        classes.forEach { addAll(generate(it, indent)) }
    }

    fun generate(format: StorageFormat, indent: String = "  "): List<String> {
        return buildList {
            add("@Package(${format.packageName})")

            addAll(generate(format.classes, indent).indent(indent))

            add("@EndPackage")
        }
    }
}
