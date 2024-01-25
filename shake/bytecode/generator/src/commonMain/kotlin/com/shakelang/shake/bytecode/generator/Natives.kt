package com.shakelang.shake.bytecode.generator

import com.shakelang.shake.bytecode.interpreter.generator.PooledShakeBytecodeInstructionGenerator
import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.types.code.ShakeInvocation

object Natives {

    val natives = mutableMapOf<String, NativeHandler>()

    fun register(signature: String, handler: NativeHandler) {
        natives[signature] = handler
    }

    fun get(signature: String): NativeHandler? = natives[signature]

    fun register(signature: String, handler: PooledShakeBytecodeInstructionGenerator.(HandleContext) -> Unit) {
        register(
            signature,
            object : NativeHandler {
                override val signature: String = signature
                override fun handle(
                    ctx: ShakeBytecodeGenerator.BytecodeGenerationContext,
                    v: ShakeInvocation,
                    keepResultOnStack: Boolean,
                ) {
                    ctx.bytecodeInstructionGenerator.handler(
                        HandleContext(
                            ctx,
                            v,
                            keepResultOnStack,
                        ),
                    )
                }
            },
        )
    }

    init {
        val types = arrayOf(
            CreationShakeType.Primitives.UBYTE,
            CreationShakeType.Primitives.BYTE,
            CreationShakeType.Primitives.USHORT,
            CreationShakeType.Primitives.SHORT,
            CreationShakeType.Primitives.UINT,
            CreationShakeType.Primitives.INT,
            CreationShakeType.Primitives.ULONG,
            CreationShakeType.Primitives.LONG,
            CreationShakeType.Primitives.FLOAT,
            CreationShakeType.Primitives.DOUBLE,
        )

        fun biggerType(a: CreationShakeType.Primitive, b: CreationShakeType.Primitive): CreationShakeType.Primitive {
            val aIndex = types.indexOf(a)
            val bIndex = types.indexOf(b)
            return if (aIndex > bIndex) a else b
        }

        for (type0 in types) {

            val sType0 = ShakeBytecodeGenerator.generateTypeDescriptor(type0)

            for (type1 in types) {

                val sType1 = ShakeBytecodeGenerator.generateTypeDescriptor(type1)

                // Type0 is the first type and type1 is the second type
                // So for example plus(B, S) would be type0 = B and type1 = S
                // The short is on top of the stack and the byte is below the short

                val biggerType = biggerType(type0, type1)
                val sBiggerType = ShakeBytecodeGenerator.generateTypeDescriptor(biggerType)

                fun castBeforeCalc(it: HandleContext) {
                    if (it.v.arguments.size != 2) throw IllegalArgumentException("Native function must have 2 arguments")
                    // Calculate left and right
                    it.ctx.gen.visitValue(it.ctx, it.v.arguments[0], type0)

                    if (type0 != biggerType) it.ctx.bytecodeInstructionGenerator.pcast(sType0, sBiggerType)

                    it.ctx.gen.visitValue(it.ctx, it.v.arguments[1], type1)

                    if (type1 != biggerType) it.ctx.bytecodeInstructionGenerator.pcast(sType1, sBiggerType)
                }

                register("shake/lang/plus($sType0,$sType1)$sBiggerType") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    add(sBiggerType)
                }

                register("shake/lang/minus($sType0,$sType1)$sBiggerType") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    sub(sBiggerType)
                }

                register("shake/lang/times($sType0,$sType1)$sBiggerType") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    mul(sBiggerType)
                }

                register("shake/lang/div($sType0,$sType1)$sBiggerType") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    div(sBiggerType)
                }

                register("shake/lang/rem($sType0,$sType1)$sBiggerType") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    mod(sBiggerType)
                }
            }
        }
    }

    interface NativeHandler {
        val signature: String
        fun handle(
            ctx: ShakeBytecodeGenerator.BytecodeGenerationContext,
            v: ShakeInvocation,
            keepResultOnStack: Boolean,
        )
    }

    data class HandleContext(
        val ctx: ShakeBytecodeGenerator.BytecodeGenerationContext,
        val v: ShakeInvocation,
        val keepResultOnStack: Boolean,
    )
}
