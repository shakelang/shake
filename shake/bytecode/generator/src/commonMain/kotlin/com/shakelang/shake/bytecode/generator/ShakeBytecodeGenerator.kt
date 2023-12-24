package com.shakelang.shake.bytecode.generator

import com.shakelang.shake.bytecode.interpreter.generator.*
import com.shakelang.shake.processor.program.types.*
import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.ShakeInvocation
import com.shakelang.shake.processor.program.types.code.statements.ShakeStatement
import com.shakelang.shake.processor.program.types.code.values.*

class ShakeBytecodeGenerator {

    fun visitValue(ctx: BytecodeGenerationContext, v: ShakeValue) {
        when (v) {
            is ShakeByteLiteral -> ctx.bytecodeInstructionGenerator.bpush(v.value)
            is ShakeShortLiteral -> ctx.bytecodeInstructionGenerator.spush(v.value)
            is ShakeIntLiteral -> ctx.bytecodeInstructionGenerator.ipush(v.value)
            is ShakeLongLiteral -> ctx.bytecodeInstructionGenerator.lpush(v.value)
            is ShakeFloatLiteral -> ctx.bytecodeInstructionGenerator.ipush(v.value)
            is ShakeDoubleLiteral -> ctx.bytecodeInstructionGenerator.lpush(v.value)
            is ShakeUByteLiteral -> ctx.bytecodeInstructionGenerator.bpush(v.value)
            is ShakeUShortLiteral -> ctx.bytecodeInstructionGenerator.spush(v.value)
            is ShakeUIntLiteral -> ctx.bytecodeInstructionGenerator.ipush(v.value)
            is ShakeULongLiteral -> ctx.bytecodeInstructionGenerator.lpush(v.value)
            is ShakeNullLiteral -> {
                TODO("Null constant??")
            }

            is ShakeStringLiteral -> {
                TODO("string push is not implemented in the interpreter")
            }

            is ShakeBooleanLiteral -> {
                ctx.bytecodeInstructionGenerator.bpush(v.value)
            }
            is ShakeInvocation -> {
                visitInvocation(ctx, v, true)
            }
//            is ShakeAssignment -> visitAssignment(v)
//            is ShakeAddAssignment -> visitAdditionAssignment(v)
//            is ShakeSubAssignment -> visitSubtractionAssignment(v)
//            is ShakeMulAssignment -> visitMultiplicationAssignment(v)
//            is ShakeDivAssignment -> visitDivisionAssignment(v)
//            is ShakeModAssignment -> visitModulusAssignment(v)
//            is ShakePowAssignment -> visitPowerAssignment(v)
//            is ShakeIncrementBefore -> visitIncrementBefore(v)
//            is ShakeIncrementAfter -> visitIncrementAfter(v)
//            is ShakeDecrementBefore -> visitDecrementBefore(v)
//            is ShakeDecrementAfter -> visitDecrementAfter(v)
//            is ShakeUsage -> visitUsage(v)
//            is ShakeInvocation -> visitInvocationValue(v)
//            is ShakeNew -> visitNew(v)
//            is ShakeCast -> visitCast(v)
            else -> throw IllegalArgumentException("Unsupported value type: ${v::class.simpleName}")
        }
    }

    private fun visitInvocation(
        ctx: BytecodeGenerationContext,
        v: ShakeInvocation,
        keepResultOnStack: Boolean) {
        val invokable = v.callable
        when (invokable) {
            is ShakeMethod -> {
                if (invokable.isNative) {
                    val native = Natives.get(invokable.signature)
                        ?: throw IllegalStateException("Native method ${invokable.signature} is not registered")
                    native.handle(ctx, v, keepResultOnStack)
                }
            }
            else -> TODO()
        }
    }

    fun visitCode(
        ctx: BytecodeGenerationContext,
        code: ShakeCode,
    ) {
        code.statements.forEach {
            visitStatement(ctx, it)
        }
    }

    fun visitStatement(
        ctx: BytecodeGenerationContext,
        s: ShakeStatement,
    ) {
        return when (s) {
//            is ShakeAssignment -> visitAssignment(s)
//            is ShakeAddAssignment -> visitAdditionAssignment(s)
//            is ShakeSubAssignment -> visitSubtractionAssignment(s)
//            is ShakeMulAssignment -> visitMultiplicationAssignment(s)
//            is ShakeDivAssignment -> visitDivisionAssignment(s)
//            is ShakeModAssignment -> visitModulusAssignment(s)
//            is ShakePowAssignment -> visitPowerAssignment(s)
//            is ShakeIncrementBefore -> visitIncrementBefore(s)
//            is ShakeIncrementAfter -> visitIncrementAfter(s)
//            is ShakeDecrementBefore -> visitDecrementBefore(s)
//            is ShakeDecrementAfter -> visitDecrementAfter(s)
//            is ShakeInvocation -> visitInvocationStatement(s)
//            is ShakeNew -> visitNew(s)
//            is ShakeDoWhile -> visitDoWhile(s)
//            is ShakeWhile -> visitWhile(s)
//            is ShakeFor -> visitFor(s)
//            is ShakeIf -> visitIf(s)
//            is ShakeReturn -> visitReturn(s)
//            is ShakeVariableDeclaration -> visitVariableDeclaration(s)
            else -> throw IllegalArgumentException("Unsupported value type: ${s::class.simpleName}")
        }
    }

    fun generateProject(project: ShakeProject): MutableList<GenerationContext> {
        val contexts = mutableListOf<GenerationContext>()
        project.subpackages.forEach {
            contexts.addAll(generatePackage(it))
        }
        return contexts
    }

    fun generatePackage(pkg: ShakePackage): List<GenerationContext> {
        val contexts = mutableListOf<GenerationContext>()
        pkg.subpackages.forEach {
            contexts.addAll(generatePackage(it))
        }

        val ctx = GenerationContext()

        generatePackage(pkg, ctx)

        contexts.add(ctx)

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
        if (method.isNative) return
        ctx.run {

            val returnType = generateTypeDescriptor(method.returnType)
            val parameters = method.parameters.map { generateTypeDescriptor(it.type) }

            name = "${method.name}(${parameters.joinToString(",")})$returnType"
            isPublic = method.isPublic
            isStatic = method.isStatic
            isFinal = method.isFinal
            isStatic = method.isStatic
            isFinal = method.isFinal

            if(method.body != null) {

                val body = method.body!!

                code {
                    maxStack = 100
                    maxLocals = 100

                    bytecode {
                        val localTable = LocalTable()
                        visitCode(
                            BytecodeGenerationContext(
                                this@ShakeBytecodeGenerator,
                                this,
                                ctx,
                                localTable,
                            ),
                            body
                        )
                    }
                }
            }
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
        return when (type.kind) {
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

    class LocalTable (
        val locals: MutableMap<String, Int> = mutableMapOf(),
        var size : Int = 0
    ) {
        fun createLocal(name: String, size: Int) {
            locals[name] = this.size
            this.size += size
        }

        fun getLocal(name: String): Int {
            return locals[name] ?: throw IllegalStateException("Local $name is not defined")
        }
    }

    class BytecodeGenerationContext(
        val gen: ShakeBytecodeGenerator,
        val bytecodeInstructionGenerator: PooledShakeBytecodeInstructionGenerator,
        val method: MethodGenerationContext,
        val localTable: LocalTable,
    )
}