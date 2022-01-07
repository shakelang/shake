@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*

class ShasamblyInterpreter(
    memorySize: Int,
    bytes: ByteArray,
    position: Int
): ShasamblyOpcodeExecutor(memorySize, bytes, position) {

    val byteMap = createByteMap()

    fun finished(): Boolean = this.position == 0

    fun tick() {
        val pos = position
        val next = memory[position++]
        //println("Executing byte at position 0x${(position-1).toBytes().toHexString()}")
        try {
            (byteMap[next.toUByte().toInt()] ?: throw NoSuchElementException("Wrong opcode")).invoke()
        } catch (e: Throwable) {
            throw Error("Could not execute byte 0x${next.toBytes().toHexString()} " +
                    "at position 0x${(pos - 5).toBytes().toHexString()} (${pos-5})", e)
        }
    }

    fun tick(times: Int) {
        for (i in 1..times) {
            tick()
            if(finished()) break
        }
    }

    fun finish() {
        while(!finished()) tick()
    }

    private fun createByteMap(): Array<(() -> Unit)?> {
        val map = arrayOfNulls<() -> Unit>(256)
        map[Opcodes.INCR_STACK.toInt()] = { this.incr_variable_stack() }
        map[Opcodes.DECR_STACK.toInt()] = { this.decr_variable_stack() }
        map[Opcodes.JUMP_STATIC.toInt()] = { this.jump_static() }
        map[Opcodes.JUMP_DYNAMIC.toInt()] = { this.jump_dynamic() }
        map[Opcodes.JUMP_IF.toInt()] = { this.jump_if() }
        map[Opcodes.INVOKE_NATIVE.toInt()] = { this.invoke_native() }
        map[Opcodes.GLOB_ADDR.toInt()] = { this.glob_addr() }

        map[Opcodes.B_GET_LOCAL.toInt()] = { this.b_get_local() }
        map[Opcodes.S_GET_LOCAL.toInt()] = { this.s_get_local() }
        map[Opcodes.I_GET_LOCAL.toInt()] = { this.i_get_local() }
        map[Opcodes.L_GET_LOCAL.toInt()] = { this.l_get_local() }

        map[Opcodes.B_STORE_LOCAL.toInt()] = { this.b_store_local() }
        map[Opcodes.S_STORE_LOCAL.toInt()] = { this.s_store_local() }
        map[Opcodes.I_STORE_LOCAL.toInt()] = { this.i_store_local() }
        map[Opcodes.L_STORE_LOCAL.toInt()] = { this.l_store_local() }

        map[Opcodes.B_ADD.toInt()] = { this.badd() }
        map[Opcodes.B_SUB.toInt()] = { this.bsub() }
        map[Opcodes.B_MUL.toInt()] = { this.bmul() }
        map[Opcodes.B_DIV.toInt()] = { this.bdiv() }
        map[Opcodes.B_MOD.toInt()] = { this.bmod() }

        map[Opcodes.S_ADD.toInt()] = { this.sadd() }
        map[Opcodes.S_SUB.toInt()] = { this.ssub() }
        map[Opcodes.S_MUL.toInt()] = { this.smul() }
        map[Opcodes.S_DIV.toInt()] = { this.sdiv() }
        map[Opcodes.S_MOD.toInt()] = { this.smod() }

        map[Opcodes.I_ADD.toInt()] = { this.iadd() }
        map[Opcodes.I_SUB.toInt()] = { this.isub() }
        map[Opcodes.I_MUL.toInt()] = { this.imul() }
        map[Opcodes.I_DIV.toInt()] = { this.idiv() }
        map[Opcodes.I_MOD.toInt()] = { this.imod() }

        map[Opcodes.L_ADD.toInt()] = { this.ladd() }
        map[Opcodes.L_SUB.toInt()] = { this.lsub() }
        map[Opcodes.L_MUL.toInt()] = { this.lmul() }
        map[Opcodes.L_DIV.toInt()] = { this.ldiv() }
        map[Opcodes.L_MOD.toInt()] = { this.lmod() }

        map[Opcodes.F_ADD.toInt()] = { this.fadd() }
        map[Opcodes.F_SUB.toInt()] = { this.fsub() }
        map[Opcodes.F_MUL.toInt()] = { this.fmul() }
        map[Opcodes.F_DIV.toInt()] = { this.fdiv() }
        map[Opcodes.F_MOD.toInt()] = { this.fmod() }

        map[Opcodes.D_ADD.toInt()] = { this.dadd() }
        map[Opcodes.D_SUB.toInt()] = { this.dsub() }
        map[Opcodes.D_MUL.toInt()] = { this.dmul() }
        map[Opcodes.D_DIV.toInt()] = { this.ddiv() }
        map[Opcodes.D_MOD.toInt()] = { this.dmod() }

        map[Opcodes.B_PUSH.toInt()] = { this.bpush() }
        map[Opcodes.S_PUSH.toInt()] = { this.spush() }
        map[Opcodes.I_PUSH.toInt()] = { this.ipush() }
        map[Opcodes.L_PUSH.toInt()] = { this.lpush() }

        map[Opcodes.B_EQ.toInt()] = { this.beq() }
        map[Opcodes.S_EQ.toInt()] = { this.seq() }
        map[Opcodes.I_EQ.toInt()] = { this.ieq() }
        map[Opcodes.L_EQ.toInt()] = { this.leq() }
        map[Opcodes.F_EQ.toInt()] = { this.feq() }
        map[Opcodes.D_EQ.toInt()] = { this.deq() }

        map[Opcodes.B_BIGGER.toInt()] = { this.bbigger() }
        map[Opcodes.S_BIGGER.toInt()] = { this.sbigger() }
        map[Opcodes.I_BIGGER.toInt()] = { this.ibigger() }
        map[Opcodes.L_BIGGER.toInt()] = { this.lbigger() }
        map[Opcodes.F_BIGGER.toInt()] = { this.fbigger() }
        map[Opcodes.D_BIGGER.toInt()] = { this.dbigger() }

        map[Opcodes.B_SMALLER.toInt()] = { this.bsmaller() }
        map[Opcodes.S_SMALLER.toInt()] = { this.ssmaller() }
        map[Opcodes.I_SMALLER.toInt()] = { this.ismaller() }
        map[Opcodes.L_SMALLER.toInt()] = { this.lsmaller() }
        map[Opcodes.F_SMALLER.toInt()] = { this.fsmaller() }
        map[Opcodes.D_SMALLER.toInt()] = { this.dsmaller() }

        map[Opcodes.B_BIGGER_EQ.toInt()] = { this.bbiggereq() }
        map[Opcodes.S_BIGGER_EQ.toInt()] = { this.sbiggereq() }
        map[Opcodes.I_BIGGER_EQ.toInt()] = { this.ibiggereq() }
        map[Opcodes.L_BIGGER_EQ.toInt()] = { this.lbiggereq() }
        map[Opcodes.F_BIGGER_EQ.toInt()] = { this.fbiggereq() }
        map[Opcodes.D_BIGGER_EQ.toInt()] = { this.dbiggereq() }

        map[Opcodes.B_SMALLER_EQ.toInt()] = { this.bsmallereq() }
        map[Opcodes.S_SMALLER_EQ.toInt()] = { this.ssmallereq() }
        map[Opcodes.I_SMALLER_EQ.toInt()] = { this.ismallereq() }
        map[Opcodes.L_SMALLER_EQ.toInt()] = { this.lsmallereq() }
        map[Opcodes.F_SMALLER_EQ.toInt()] = { this.fsmallereq() }
        map[Opcodes.D_SMALLER_EQ.toInt()] = { this.dsmallereq() }

        map[Opcodes.BOOL_NOT.toInt()] = { this.bnot() }

        map[Opcodes.B_GET_GLOBAL.toInt()] = { this.b_get_global() }
        map[Opcodes.S_GET_GLOBAL.toInt()] = { this.s_get_global() }
        map[Opcodes.I_GET_GLOBAL.toInt()] = { this.i_get_global() }
        map[Opcodes.L_GET_GLOBAL.toInt()] = { this.l_get_global() }

        map[Opcodes.B_GET_GLOBAL_DYNAMIC.toInt()] = { this.b_get_global_dynamic() }
        map[Opcodes.S_GET_GLOBAL_DYNAMIC.toInt()] = { this.s_get_global_dynamic() }
        map[Opcodes.I_GET_GLOBAL_DYNAMIC.toInt()] = { this.i_get_global_dynamic() }
        map[Opcodes.L_GET_GLOBAL_DYNAMIC.toInt()] = { this.l_get_global_dynamic() }

        map[Opcodes.B_STORE_GLOBAL.toInt()] = { this.b_store_global() }
        map[Opcodes.S_STORE_GLOBAL.toInt()] = { this.s_store_global() }
        map[Opcodes.I_STORE_GLOBAL.toInt()] = { this.i_store_global() }
        map[Opcodes.L_STORE_GLOBAL.toInt()] = { this.l_store_global() }

        map[Opcodes.B_STORE_GLOBAL_DYNAMIC.toInt()] = { this.b_store_global_dynamic() }
        map[Opcodes.S_STORE_GLOBAL_DYNAMIC.toInt()] = { this.s_store_global_dynamic() }
        map[Opcodes.I_STORE_GLOBAL_DYNAMIC.toInt()] = { this.i_store_global_dynamic() }
        map[Opcodes.L_STORE_GLOBAL_DYNAMIC.toInt()] = { this.l_store_global_dynamic() }

        map[Opcodes.B_NEG.toInt()] = { this.b_neg() }
        map[Opcodes.S_NEG.toInt()] = { this.s_neg() }
        map[Opcodes.I_NEG.toInt()] = { this.i_neg() }
        map[Opcodes.L_NEG.toInt()] = { this.l_neg() }
        map[Opcodes.F_NEG.toInt()] = { this.f_neg() }
        map[Opcodes.D_NEG.toInt()] = { this.d_neg() }

        map[Opcodes.B_ABS.toInt()] = { this.b_abs() }
        map[Opcodes.S_ABS.toInt()] = { this.s_abs() }
        map[Opcodes.I_ABS.toInt()] = { this.i_abs() }
        map[Opcodes.L_ABS.toInt()] = { this.l_abs() }
        map[Opcodes.F_ABS.toInt()] = { this.f_abs() }
        map[Opcodes.D_ABS.toInt()] = { this.d_abs() }

        map[Opcodes.PRIMITIVE_CAST.toInt()] = { this.primitive_cast() }

        return map
    }

    override fun toString(): String {
        return "ShasablyInterpreter{" +
                "position=$position," +
                "memory=${memory.toHexString()}," +
                "stack=${stack.toHexString()}}"
    }

}

