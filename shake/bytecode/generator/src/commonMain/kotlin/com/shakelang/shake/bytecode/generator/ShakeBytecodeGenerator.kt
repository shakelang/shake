package com.shakelang.shake.bytecode.generator

import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.generator.*
import com.shakelang.shake.processor.program.types.*
import com.shakelang.shake.processor.program.types.code.*
import com.shakelang.shake.processor.program.types.code.statements.*
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

            is ShakeBooleanLiteral -> ctx.bytecodeInstructionGenerator.bpush(v.value)
            is ShakeInvocation -> visitInvocation(ctx, v, true)
            is ShakeUsage -> visitUsage(ctx, v)
            is ShakeAssignment -> visitAssignment(ctx, v, true)
//            is ShakeAddAssignment -> visitAdditionAssignment(v)
//            is ShakeSubAssignment -> visitSubtractionAssignment(v)
//            is ShakeMulAssignment -> visitMultiplicationAssignment(v)
//            is ShakeDivAssignment -> visitDivisionAssignment(v)
//            is ShakeModAssignment -> visitModulusAssignment(v)
//            is ShakePowAssignment -> visitPowerAssignment(v)
            is ShakeIncrementBefore -> visitIncrementBefore(ctx, v, true)
            is ShakeIncrementAfter -> visitIncrementAfter(ctx, v, true)
            is ShakeDecrementBefore -> visitDecrementBefore(ctx, v, true)
            is ShakeDecrementAfter -> visitDecrementAfter(ctx, v, true)
//            is ShakeInvocation -> visitInvocationValue(v)
//            is ShakeNew -> visitNew(v)
//            is ShakeCast -> visitCast(v)
            else -> throw IllegalArgumentException("Unsupported value type: ${v::class.simpleName}")
        }
    }

    private fun visitAssignment(ctx: BytecodeGenerationContext, v: ShakeAssignment, keepResultOnStack: Boolean = false) {
        visitValue(ctx, v.value)
        val variable = v.variable
        storeVariable(ctx, variable)
    }

    private fun visitUsage(ctx: BytecodeGenerationContext, v: ShakeUsage) {
        loadVariable(ctx, v.declaration)
    }

    private fun visitIncrementBefore(ctx: BytecodeGenerationContext, v: ShakeIncrementBefore, keepResultOnStack: Boolean = false) {
        loadVariable(ctx, v.variable)
        ctx.bytecodeInstructionGenerator.inc(generateTypeDescriptor(v.type))
        if (keepResultOnStack) ctx.bytecodeInstructionGenerator.dup(generateTypeDescriptor(v.type))
        storeVariable(ctx, v.variable)
    }

    private fun visitIncrementAfter(ctx: BytecodeGenerationContext, v: ShakeIncrementAfter, keepResultOnStack: Boolean = false) {
        loadVariable(ctx, v.variable)
        if (keepResultOnStack) ctx.bytecodeInstructionGenerator.dup(generateTypeDescriptor(v.type))
        ctx.bytecodeInstructionGenerator.inc(generateTypeDescriptor(v.type))
        storeVariable(ctx, v.variable)
    }

    private fun visitDecrementBefore(ctx: BytecodeGenerationContext, v: ShakeDecrementBefore, keepResultOnStack: Boolean = false) {
        loadVariable(ctx, v.variable)
        ctx.bytecodeInstructionGenerator.dec(generateTypeDescriptor(v.type))
        if (keepResultOnStack) ctx.bytecodeInstructionGenerator.dup(generateTypeDescriptor(v.type))
        storeVariable(ctx, v.variable)
    }

    private fun visitDecrementAfter(ctx: BytecodeGenerationContext, v: ShakeDecrementAfter, keepResultOnStack: Boolean = false) {
        loadVariable(ctx, v.variable)
        if (keepResultOnStack) ctx.bytecodeInstructionGenerator.dup(generateTypeDescriptor(v.type))
        ctx.bytecodeInstructionGenerator.dec(generateTypeDescriptor(v.type))
        storeVariable(ctx, v.variable)
    }

    private fun visitInvocation(
        ctx: BytecodeGenerationContext,
        v: ShakeInvocation,
        keepResultOnStack: Boolean,
    ) {
        val invokable = v.callable
        when (invokable) {
            is ShakeMethod -> {
                if (invokable.isNative) {
                    // There are two types to handle native methods:
                    // 1. Native methods that are registered in the generator and will
                    //    be inlined into the bytecode (e.g. int.plus(int))
                    // 2. Native methods that are not registered in the generator and
                    //    will generate a call to the native method (e.g. int.toString())

                    // Let's first check if the native method is registered in the generator
                    // and we can inline it
                    val native = Natives.get(invokable.qualifiedSignature)
                    if (native != null) {
                        native.handle(ctx, v, keepResultOnStack)
                        return
                    }

                    // If the native method is not registered in the generator, we will
                    // just generate a normal call to the native method
                }

                // If the method is not handled by the native generator, we will just
                // generate a normal call to the method

                // Load all arguments
                v.arguments.forEach {
                    visitValue(ctx, it)
                }

                if (v.parent != null) {
                    visitValue(ctx, v.parent!!)
                }

                // Call the method
                ctx.bytecodeInstructionGenerator.call(
                    invokable.qualifiedSignature,
                )
            }

            else -> TODO()
        }

        if (!keepResultOnStack) {
            ctx.bytecodeInstructionGenerator.pop(generateTypeDescriptor(v.type))
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
            is ShakeVariableDeclaration -> visitVariableDeclaration(ctx, s)
            is ShakeInvocation -> visitInvocation(ctx, s, false)
            is ShakeAssignment -> visitAssignment(ctx, s, false)
//            is ShakeAddAssignment -> visitAdditionAssignment(s)
//            is ShakeSubAssignment -> visitSubtractionAssignment(s)
//            is ShakeMulAssignment -> visitMultiplicationAssignment(s)
//            is ShakeDivAssignment -> visitDivisionAssignment(s)
//            is ShakeModAssignment -> visitModulusAssignment(s)
//            is ShakePowAssignment -> visitPowerAssignment(s)
            is ShakeIncrementBefore -> visitIncrementBefore(ctx, s, false)
            is ShakeIncrementAfter -> visitIncrementAfter(ctx, s, false)
            is ShakeDecrementBefore -> visitDecrementBefore(ctx, s, false)
            is ShakeDecrementAfter -> visitDecrementAfter(ctx, s, false)
//            is ShakeNew -> visitNew(s)
            is ShakeDoWhile -> visitDoWhile(ctx, s)
            is ShakeWhile -> visitWhile(ctx, s)
            is ShakeFor -> visitFor(ctx, s)
            is ShakeIf -> visitIf(ctx, s)
            is ShakeReturn -> visitReturn(ctx, s)
            else -> throw IllegalArgumentException("Unsupported value type: ${s::class.simpleName}")
        }
    }

    private fun visitReturn(ctx: BytecodeGenerationContext, s: ShakeReturn) {
        if (s.value != null) {
            visitValue(ctx, s.value!!)
            ctx.bytecodeInstructionGenerator.ret(generateTypeDescriptor(s.value!!.type))
        }
        ctx.bytecodeInstructionGenerator.ret()
    }

    private fun visitVariableDeclaration(ctx: BytecodeGenerationContext, s: ShakeVariableDeclaration) {
        if (ctx.localTable.containsLocal(s.uniqueName)) {
            throw IllegalStateException("Local ${s.name} is already defined")
        }

        // Get space needed in local table
        val size = getTypeSize(s.type)
        val type = generateTypeDescriptor(s.type)

        val local = ctx.localTable.createLocal(s.uniqueName, size)

        if (s.initialValue != null) {
            visitValue(ctx, s.initialValue!!)
            ctx.bytecodeInstructionGenerator.store(type, local)
        }
    }

    private fun visitIf(ctx: BytecodeGenerationContext, s: ShakeIf) {
        // Generate condition

        visitValue(ctx, s.condition)

        // If-else

        if (s.elseBody != null) {
            // If the if condition is false, jump to else
            val elseStart = ctx.bytecodeInstructionGenerator.jz()
            visitCode(ctx, s.body)

            // As the last instruction of the if-body, skip the else body
            val ifEnd = ctx.bytecodeInstructionGenerator.jmp()

            elseStart.set(ctx.bytecodeInstructionGenerator.pointer())
            visitCode(ctx, s.elseBody!!)
            ifEnd.set(ctx.bytecodeInstructionGenerator.pointer())
        }

        // If only

        else {
            val lateInit = ctx.bytecodeInstructionGenerator.jz()
            visitCode(ctx, s.body)
            lateInit.set(ctx.bytecodeInstructionGenerator.pointer())
        }
    }

    private fun visitFor(ctx: BytecodeGenerationContext, s: ShakeFor) {
        visitStatement(ctx, s.init)
        val loopStart = ctx.bytecodeInstructionGenerator.pointer()
        visitValue(ctx, s.condition)
        val loopEnd = ctx.bytecodeInstructionGenerator.jz()
        visitCode(ctx, s.body)
        visitStatement(ctx, s.update)
        ctx.bytecodeInstructionGenerator.jmp(loopStart)
        loopEnd.set(ctx.bytecodeInstructionGenerator.pointer())
    }

    private fun visitWhile(ctx: BytecodeGenerationContext, s: ShakeWhile) {
        val loopStart = ctx.bytecodeInstructionGenerator.pointer()
        visitValue(ctx, s.condition)
        val loopEnd = ctx.bytecodeInstructionGenerator.jz()
        visitCode(ctx, s.body)
        ctx.bytecodeInstructionGenerator.jmp(loopStart)
        loopEnd.set(ctx.bytecodeInstructionGenerator.pointer())
    }

    private fun visitDoWhile(ctx: BytecodeGenerationContext, s: ShakeDoWhile) {
        val loopStart = ctx.bytecodeInstructionGenerator.pointer()
        visitCode(ctx, s.body)
        visitValue(ctx, s.condition)
        ctx.bytecodeInstructionGenerator.jnz(loopStart)
    }

    private fun loadVariable(ctx: BytecodeGenerationContext, v: ShakeVariableDeclaration) {
        val local = ctx.localTable.getLocal(v.uniqueName)
        val type = generateTypeDescriptor(v.type)
        ctx.bytecodeInstructionGenerator.load(type, local)
    }

    private fun loadVariable(ctx: BytecodeGenerationContext, v: ShakeAssignable) {
        when (v) {
            is ShakeVariableDeclaration -> loadVariable(ctx, v)
            else -> TODO()
        }
    }

    private fun storeVariable(ctx: BytecodeGenerationContext, v: ShakeVariableDeclaration) {
        val local = ctx.localTable.getLocal(v.uniqueName)
        val type = generateTypeDescriptor(v.type)
        ctx.bytecodeInstructionGenerator.store(type, local)
    }

    private fun storeVariable(ctx: BytecodeGenerationContext, v: ShakeAssignable) {
        when (v) {
            is ShakeVariableDeclaration -> storeVariable(ctx, v)
            else -> TODO()
        }
    }

    fun generateProject(project: ShakeProject): List<StorageFormat> {
        val contexts = mutableListOf<GenerationContext>()
        project.subpackages.forEach {
            contexts.addAll(generatePackage(it))
        }
        return contexts.map {
            it.toStorageFormat()
        }
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
        ctx.run {
            val returnType = generateTypeDescriptor(method.returnType)
            val parameters = method.parameters.map { generateTypeDescriptor(it.type) }

            name = "${method.name}(${parameters.joinToString(",")})$returnType"
            isPublic = method.isPublic
            isStatic = method.isStatic
            isFinal = method.isFinal
            isStatic = method.isStatic
            isFinal = method.isFinal
            isNative = method.isNative
            isAbstract = method.isAbstract
            isSynchronized = method.isSynchronized
            isStrict = method.isStrict

            if (method.body != null) {
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
                            body,
                        )

                        // Return at the end of the method
                        ret()
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

    fun getTypeSize(type: ShakeType): Int {
        return when (type.kind) {
            ShakeType.Kind.PRIMITIVE -> {
                when ((type as ShakeType.Primitive).type) {
                    ShakeType.PrimitiveType.BYTE -> 1
                    ShakeType.PrimitiveType.SHORT -> 1
                    ShakeType.PrimitiveType.INT -> 1
                    ShakeType.PrimitiveType.LONG -> 2
                    ShakeType.PrimitiveType.UNSIGNED_BYTE -> 1
                    ShakeType.PrimitiveType.UNSIGNED_SHORT -> 1
                    ShakeType.PrimitiveType.UNSIGNED_INT -> 1
                    ShakeType.PrimitiveType.UNSIGNED_LONG -> 2
                    ShakeType.PrimitiveType.FLOAT -> 1
                    ShakeType.PrimitiveType.DOUBLE -> 2
                    ShakeType.PrimitiveType.BOOLEAN -> 1
                    ShakeType.PrimitiveType.CHAR -> 1
                    ShakeType.PrimitiveType.NULL -> 1
                    ShakeType.PrimitiveType.VOID -> 0
                    ShakeType.PrimitiveType.DYNAMIC -> 8
                }
            }

            ShakeType.Kind.ARRAY, ShakeType.Kind.OBJECT, ShakeType.Kind.LAMBDA -> 8
        }
    }

    class LocalTable(
        val locals: MutableMap<String, Int> = mutableMapOf(),
        var size: Int = 0,
    ) {

        fun containsLocal(name: String): Boolean {
            return locals.containsKey(name)
        }

        fun createLocal(name: String, size: Int): UShort {
            locals[name] = this.size
            this.size += size
            return locals[name]!!.toUShort()
        }

        fun getLocal(name: String): UShort {
            return (locals[name] ?: throw IllegalStateException("Local $name is not defined")).toUShort()
        }
    }

    class BytecodeGenerationContext(
        val gen: ShakeBytecodeGenerator,
        val bytecodeInstructionGenerator: PooledShakeBytecodeInstructionGenerator,
        val method: MethodGenerationContext,
        val localTable: LocalTable,
    )
}
