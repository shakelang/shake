package com.shakelang.shake.bytecode.generator

import com.shakelang.shake.bytecode.interpreter.generator.ClassGenerationContext
import com.shakelang.shake.bytecode.interpreter.generator.FieldGenerationContext
import com.shakelang.shake.bytecode.interpreter.generator.GenerationContext
import com.shakelang.shake.bytecode.interpreter.generator.MethodGenerationContext
import com.shakelang.shake.processor.program.types.*

class ShakeBytecodeGenerator {

    fun generatePackage(pkg: ShakePackage): List<GenerationContext> {
        val contexts = mutableListOf<GenerationContext>()
        pkg.subpackages.forEach {
            contexts.addAll(generatePackage(it))
        }
        contexts.add(GenerationContext().apply { generatePackage(pkg, this) })
        return contexts
    }

    fun generatePackage(pkg: ShakePackage, ctx: GenerationContext) {
        ctx.run {
            name = pkg.qualifiedName

            pkg.classes.forEach {
                Class { generateClass(it, this) }
            }

            pkg.functions.forEach {
                Method { generateMethod(it, this) }
            }

            pkg.fields.forEach {
                Field { generateField(it, this) }
            }

        }
    }

    fun generateClass(clazz: ShakeClass, ctx: ClassGenerationContext) {
        ctx.run {

            name = clazz.name
            isPublic = clazz.isPublic
            isStatic = clazz.isStatic
            isFinal = clazz.isFinal
            isStatic = clazz.isStatic
            isFinal = clazz.isFinal

            superName = clazz.superClass.qualifiedName

            clazz.interfaces.forEach {
                implements(it.qualifiedName)
            }

            clazz.classes.forEach {
                Class { generateClass(it, this) }
            }

            clazz.methods.forEach {
                Method { generateMethod(it, this) }
            }

            clazz.fields.forEach {
                Field { generateField(it, this) }
            }
        }
    }

    fun generateMethod(method: ShakeMethod, ctx: MethodGenerationContext) {
        ctx.run {

            val returnType = generateTypeDescriptor(method.returnType)
            val parameters = method.parameters.map { generateTypeDescriptor(it.type) }

            name = "${method.name}(${parameters.joinToString(",")})$returnType"
            isPublic = method.isPublic
            isStatic = method.isStatic
            isFinal = method.isFinal
            isStatic = method.isStatic
            isFinal = method.isFinal

        }
    }

    fun generateField(field: ShakeField, ctx: FieldGenerationContext) {
        ctx.run {

            name = field.name
            isPublic = field.isPublic
            isStatic = field.isStatic
            isFinal = field.isFinal
            isStatic = field.isStatic
            isFinal = field.isFinal
            type = generateTypeDescriptor(field.type)

        }
    }

    fun generateTypeDescriptor(type: ShakeType): String {
        return when(type.kind) {
            ShakeType.Kind.PRIMITIVE -> {
                when ((type as ShakeType.Primitive).type) {
                    ShakeType.PrimitiveType.BYTE -> "B"
                    ShakeType.PrimitiveType.SHORT -> "S"
                    ShakeType.PrimitiveType.INT -> "I"
                    ShakeType.PrimitiveType.LONG -> "J"
                    ShakeType.PrimitiveType.UNSIGNED_BYTE -> "b"
                    ShakeType.PrimitiveType.UNSIGNED_SHORT -> "s"
                    ShakeType.PrimitiveType.UNSIGNED_INT -> "i"
                    ShakeType.PrimitiveType.UNSIGNED_LONG -> "j"
                    ShakeType.PrimitiveType.FLOAT -> "F"
                    ShakeType.PrimitiveType.DOUBLE -> "D"
                    ShakeType.PrimitiveType.BOOLEAN -> "Z"
                    ShakeType.PrimitiveType.CHAR -> "C"
                    ShakeType.PrimitiveType.NULL -> "Lshakelang/lang/Object;"
                    ShakeType.PrimitiveType.VOID -> "V"
                    ShakeType.PrimitiveType.DYNAMIC -> "Lshakelang/lang/Object;"
                }
            }

            ShakeType.Kind.ARRAY -> {
                val array = type as ShakeType.Array
                val subType = generateTypeDescriptor(array.elementType)
                "[$subType"
            }

            ShakeType.Kind.OBJECT -> {
                val clazz = type as ShakeType.Object
                "L${clazz.name};"
            }

            ShakeType.Kind.LAMBDA -> TODO()
        }
    }
}