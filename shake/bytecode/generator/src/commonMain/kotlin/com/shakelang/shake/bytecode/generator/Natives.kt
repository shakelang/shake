package com.shakelang.shake.bytecode.generator

import com.shakelang.shake.bytecode.interpreter.PCast
import com.shakelang.shake.bytecode.interpreter.generator.PooledShakeBytecodeInstructionGenerator
import com.shakelang.shake.processor.program.types.code.ShakeInvocation

object Natives {

    val natives = mutableMapOf<String, NativeHandler>()

    fun register(signature: String, handler: NativeHandler) {
        natives[signature] = handler
    }

    fun get(signature: String): NativeHandler? = natives[signature]

    fun registerNoArgs(signature: String, handler: PooledShakeBytecodeInstructionGenerator.(HandleContext) -> Unit) {
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
        val sizes = arrayOf("b", "B", "s", "S", "i", "I", "j", "J", "F", "D")
        val types = listOf(
            "B" to PCast.BYTE,
            "S" to PCast.SHORT,
            "I" to PCast.INT,
            "J" to PCast.LONG,
            "F" to PCast.FLOAT,
            "D" to PCast.DOUBLE,
            "b" to PCast.BYTE,
            "s" to PCast.SHORT,
            "i" to PCast.INT,
            "j" to PCast.LONG,
        )

        fun biggerType(a: String, b: String): String {
            val aIndex = sizes.indexOf(a)
            val bIndex = sizes.indexOf(b)
            return if (aIndex > bIndex) a else b
        }

        for (type0 in types) {
            for (type1 in types) {

                // Type0 is the first type and type1 is the second type
                // So for example plus(B, S) would be type0 = B and type1 = S
                // The short is on top of the stack and the byte is below the short

                val biggerType = biggerType(type0.first, type1.first)

                fun PooledShakeBytecodeInstructionGenerator.castBeforeCalc(it: HandleContext) {
                    if (it.v.arguments.size != 2) throw IllegalArgumentException("Native function must have 2 arguments")

                    // Calculate left and right
                    it.ctx.gen.visitValue(it.ctx, it.v.arguments[0])

                    if (type0.first != biggerType) {
                        // If type1 is bigger than type0, we need to cast type0 to type1
                        pcast(type0.second, type1.second)
                    }

                    it.ctx.gen.visitValue(it.ctx, it.v.arguments[1])

                    if (type1.first != biggerType) {
                        // If type0 is bigger than type1, we need to cast type1 to type0
                        pcast(type1.second, type0.second)
                    }
                }

                registerNoArgs("shake/lang/plus(${type0.first},${type1.first})$biggerType") {
                    this.castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    add(biggerType)
                }

                registerNoArgs("shake/lang/minus(${type0.first},${type1.first})$biggerType") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    sub(biggerType)
                }

                registerNoArgs("shake/lang/multiply(${type0.first},${type1.first})$biggerType}") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    mul(biggerType)
                }

                registerNoArgs("shake/lang/divide(${type0.first},${type1.first})$biggerType}") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    div(biggerType)
                }

                registerNoArgs("shake/lang/modulo(${type0.first},${type1.first})$biggerType}") {
                    castBeforeCalc(it)

                    // Now we can add the two values on the stack
                    // We use the bigger type (because we just casted the
                    // smaller types to the bigger type)
                    mod(biggerType)
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
