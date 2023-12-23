package com.shakelang.shake.bytecode.generator

import com.shakelang.shake.bytecode.interpreter.PCast
import com.shakelang.shake.bytecode.interpreter.generator.GenerationContext
import com.shakelang.shake.bytecode.interpreter.generator.PooledShakeBytecodeInstructionGenerator
import com.shakelang.shake.processor.program.types.code.ShakeInvocation

object Natives {

    val natives = mutableMapOf<String, NativeHandler>()

    fun register(signature: String, handler: NativeHandler) {
        natives[signature] = handler
    }

    fun register(signature: String, handler: PooledShakeBytecodeInstructionGenerator.(ShakeInvocation, GenerationContext, Boolean) -> Unit) {
        register(signature, object : NativeHandler {
            override val signature: String = signature
            override fun handle(
                gen: PooledShakeBytecodeInstructionGenerator,
                v: ShakeInvocation,
                ctx: GenerationContext,
                keepResultOnStack: Boolean
            ) {
                gen.handler(v, ctx, keepResultOnStack)
            }
        })
    }

    fun registerNoArgs(signature: String, handler: PooledShakeBytecodeInstructionGenerator.() -> Unit) {
        register(signature, object : NativeHandler {
            override val signature: String = signature
            override fun handle(
                gen: PooledShakeBytecodeInstructionGenerator,
                v: ShakeInvocation,
                ctx: GenerationContext,
                keepResultOnStack: Boolean
            ) {
                gen.handler()
            }
        })
    }

    init {
        registerNoArgs("shake.lang\$B\$plus(B)B") {
            badd()
        }
        registerNoArgs("shake.lang\$B\$plus(S)S") {
            sstore(0u)
            pcast(PCast.BYTE, PCast.SHORT)
            sload(0u)
            sadd()
        }
        registerNoArgs("shake.lang\$B\$plus(I)I") {
            istore(0u)
            pcast(PCast.BYTE, PCast.INT)
            iload(0u)
            iadd()
        }
        registerNoArgs("shake.lang\$B\$plus(J)J") {
            lstore(0u)
            pcast(PCast.BYTE, PCast.LONG)
            lload(0u)
            ladd()
        }

    }

    interface NativeHandler {
        val signature: String
        fun handle(gen: PooledShakeBytecodeInstructionGenerator, v: ShakeInvocation, ctx: GenerationContext, keepResultOnStack: Boolean)
    }
}