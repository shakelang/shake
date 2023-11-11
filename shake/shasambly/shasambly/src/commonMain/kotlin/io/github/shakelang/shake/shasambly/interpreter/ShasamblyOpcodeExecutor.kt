@file:Suppress("nothing_to_inline")

package io.github.shakelang.shake.shasambly.interpreter

import com.shakelang.shake.util.primitives.bytes.*
import io.github.shakelang.shake.shasambly.interpreter.natives.nativeFunctions
import io.github.shakelang.shake.util.primitives.bytes.*
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.math.abs

abstract class ShasamblyOpcodeExecutor(
    memorySize: Int,
    bytes: ByteArray,
    position: Int
) : ShasamblyInterpretingBase(memorySize, bytes, position) {

    fun incr_variable_stack() {
        val size = read_short().toUShort().toInt()
        incrLocalStack(size)
        // println("Variable stack size updated to $variableAddress")
    }

    fun decr_variable_stack() {
        decrLocalStack()
        // println("Variable stack size updated to $variableAddress")
    }

    fun jump(address: Int) {
        // println("Jumping to ${address.toBytes().toHexString()}")
        if (address < 0 || address > memory.size) {
            throw Error(
                "Address 0x${
                address.toBytes().toHexString()
                } out of range"
            )
        }
        position = address
    }

    fun jump_static() {
        jump(read_int())
    }

    fun jump_dynamic() {
        jump(stack.popInt())
    }

    fun jump_if() {
        val addr = read_int()
        if (stack.popByte() != 0x00.toByte()) jump(addr)
    }

    fun invoke_native() {
        val native = read_short().toUShort().toInt()
        (
            nativeFunctions[native]
                ?: throw Error(
                    "Unknown native function 0x${
                    native.toBytes().toHexString()
                    } at position 0x${position.toBytes().toHexString()}"
                )
            )
            .execute.invoke(this)
    }

    fun glob_addr() {
        val variable = read_short().toUShort().toInt()
        if (variable >= variableStackSize) throw IllegalArgumentException("Could not get global address of local $variable (Local stack size is only $variableStackSize)")
        stack.pushInt(localStackPointer + variable)
    }

    fun b_get_local() {
        val variable = read_short().toUShort().toInt()
        stack.push(memory[localStackPointer + variable])
    }

    fun s_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer + variable
        (1 downTo 0).forEach { stack.push(memory[addr + it]) }
    }

    fun i_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer + variable
        (3 downTo 0).forEach { stack.push(memory[addr + it]) }
    }

    fun l_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer - variable
        (7 downTo 0).forEach { stack.push(memory[addr + it]) }
    }

    fun b_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer - variable
        memory[addr] = stack.pop()
    }

    fun s_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer - variable
        (0..1).forEach { memory[addr + it] = stack.pop() }
    }

    fun i_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer - variable
        (0..3).forEach { memory[addr + it] = stack.pop() }
    }

    fun l_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = localStackPointer - variable
        (0..7).forEach { memory[addr + it] = stack.pop() }
    }

    fun bpush() {
        stack.push(read_byte())
    }

    fun spush() {
        stack.pushShort(read_short())
    }

    fun ipush() {
        stack.pushInt(read_int())
    }

    fun lpush() {
        stack.pushLong(read_long())
    }

    fun badd() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.push((v0 + v1).toByte())
    }

    fun bsub() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.push((v0 - v1).toByte())
    }

    fun bmul() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.push((v0 * v1).toByte())
    }

    fun bdiv() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.push((v0 / v1).toByte())
    }

    fun bmod() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.push((v0 % v1).toByte())
    }

    fun sadd() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushShort((v0 + v1).toShort())
    }

    fun ssub() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushShort((v0 - v1).toShort())
    }

    fun smul() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushShort((v0 * v1).toShort())
    }

    fun sdiv() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushShort((v0 / v1).toShort())
    }

    fun smod() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushShort((v0 % v1).toShort())
    }

    fun iadd() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushInt(v0 + v1)
    }

    fun isub() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushInt(v0 - v1)
    }

    fun imul() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushInt(v0 * v1)
    }

    fun idiv() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushInt(v0 / v1)
    }

    fun imod() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushInt(v0 % v1)
    }

    fun ladd() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushLong(v0 + v1)
    }

    fun lsub() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushLong(v0 - v1)
    }

    fun lmul() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushLong(v0 * v1)
    }

    fun ldiv() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushLong(v0 / v1)
    }

    fun lmod() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushLong(v0 % v1)
    }

    fun fadd() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushFloat(v0 + v1)
    }

    fun fsub() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushFloat(v0 - v1)
    }

    fun fmul() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushFloat(v0 * v1)
    }

    fun fdiv() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushFloat(v0 / v1)
    }

    fun fmod() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushFloat(v0 % v1)
    }

    fun dadd() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushDouble(v0 + v1)
    }

    fun dsub() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushDouble(v0 - v1)
    }

    fun dmul() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushDouble(v0 * v1)
    }

    fun ddiv() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushDouble(v0 / v1)
    }

    fun dmod() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushDouble(v0 % v1)
    }

    fun beq() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.pushBoolean(v0 == v1)
    }

    fun seq() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushBoolean(v0 == v1)
    }

    fun ieq() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushBoolean(v0 == v1)
    }

    fun leq() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushBoolean(v0 == v1)
    }

    fun feq() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushBoolean(v0 == v1)
    }

    fun deq() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushBoolean(v0 == v1)
    }

    fun bbigger() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.pushBoolean(v0 > v1)
    }

    fun sbigger() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushBoolean(v0 > v1)
    }

    fun ibigger() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushBoolean(v0 > v1)
    }

    fun lbigger() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushBoolean(v0 > v1)
    }

    fun fbigger() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushBoolean(v0 > v1)
    }

    fun dbigger() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushBoolean(v0 > v1)
    }

    fun bsmaller() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.pushBoolean(v0 < v1)
    }

    fun ssmaller() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushBoolean(v0 < v1)
    }

    fun ismaller() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushBoolean(v0 < v1)
    }

    fun lsmaller() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushBoolean(v0 < v1)
    }

    fun fsmaller() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushBoolean(v0 < v1)
    }

    fun dsmaller() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushBoolean(v0 < v1)
    }

    fun bbiggereq() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.pushBoolean(v0 >= v1)
    }

    fun sbiggereq() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushBoolean(v0 >= v1)
    }

    fun ibiggereq() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushBoolean(v0 >= v1)
    }

    fun lbiggereq() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushBoolean(v0 >= v1)
    }

    fun fbiggereq() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushBoolean(v0 >= v1)
    }

    fun dbiggereq() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushBoolean(v0 >= v1)
    }

    fun bsmallereq() {
        val v1 = stack.pop()
        val v0 = stack.pop()
        stack.pushBoolean(v0 <= v1)
    }

    fun ssmallereq() {
        val v1 = stack.popShort()
        val v0 = stack.popShort()
        stack.pushBoolean(v0 <= v1)
    }

    fun ismallereq() {
        val v1 = stack.popInt()
        val v0 = stack.popInt()
        stack.pushBoolean(v0 <= v1)
    }

    fun lsmallereq() {
        val v1 = stack.popLong()
        val v0 = stack.popLong()
        stack.pushBoolean(v0 <= v1)
    }

    fun fsmallereq() {
        val v1 = stack.popFloat()
        val v0 = stack.popFloat()
        stack.pushBoolean(v0 <= v1)
    }

    fun dsmallereq() {
        val v1 = stack.popDouble()
        val v0 = stack.popDouble()
        stack.pushBoolean(v0 <= v1)
    }

    fun bnot() {
        stack.pushBoolean(stack.pop() == 0.toByte())
    }

    fun b_get_global() {
        val pos = read_int()
        stack.pushByte(memory[pos])
    }

    fun s_get_global() {
        val pos = read_int()
        stack.pushShort(memory.getShort(pos))
    }

    fun i_get_global() {
        val pos = read_int()
        stack.pushInt(memory.getInt(pos))
    }

    fun l_get_global() {
        val pos = read_int()
        stack.pushLong(memory.getLong(pos))
    }

    fun b_get_global_dynamic() {
        val pos = stack.popInt()
        stack.pushByte(memory.getByte(pos))
    }

    fun s_get_global_dynamic() {
        val pos = stack.popInt()
        stack.pushShort(memory.getShort(pos))
    }

    fun i_get_global_dynamic() {
        val pos = stack.popInt()
        stack.pushInt(memory.getInt(pos))
    }

    fun l_get_global_dynamic() {
        val pos = stack.popInt()
        stack.pushLong(memory.getLong(pos))
    }

    fun b_store_global() {
        val pos = read_int()
        val value = stack.pop()
        memory[pos] = value
    }

    fun s_store_global() {
        val pos = read_int()
        val value = stack.popShort()
        memory.setShort(pos, value)
    }

    fun i_store_global() {
        val pos = read_int()
        val value = stack.popInt()
        memory.setInt(pos, value)
    }

    fun l_store_global() {
        val pos = read_int()
        val value = stack.popLong()
        memory.setLong(pos, value)
    }

    fun b_store_global_dynamic() {
        val pos = stack.popInt()
        val value = stack.pop()
        memory[pos] = value
    }

    fun s_store_global_dynamic() {
        val pos = stack.popInt()
        val value = stack.popShort()
        memory.setShort(pos, value)
    }

    fun i_store_global_dynamic() {
        val pos = stack.popInt()
        val value = stack.popInt()
        memory.setInt(pos, value)
    }

    fun l_store_global_dynamic() {
        val pos = stack.popInt()
        val value = stack.popLong()
        memory.setLong(pos, value)
    }

    fun b_neg() {
        stack.push((-stack.pop()).toByte())
    }

    fun s_neg() {
        stack.pushShort((-stack.popShort()).toShort())
    }

    fun i_neg() {
        stack.pushInt(-stack.popInt())
    }

    fun l_neg() {
        stack.pushLong(-stack.popLong())
    }

    fun f_neg() {
        stack.pushFloat(-stack.popFloat())
    }

    fun d_neg() {
        stack.pushDouble(-stack.popDouble())
    }

    fun b_abs() {
        stack.push(abs(stack.pop().toInt()).toByte())
    }

    fun s_abs() {
        stack.pushShort(abs(stack.popShort().toInt()).toShort())
    }

    fun i_abs() {
        stack.pushInt(abs(stack.popInt()))
    }

    fun l_abs() {
        stack.pushLong(abs(stack.popLong()))
    }

    fun f_abs() {
        stack.pushFloat(abs(stack.popFloat()))
    }

    fun d_abs() {
        stack.pushDouble(abs(stack.popDouble()))
    }

    fun primitive_cast() {
        val cast = read_byte().toUByte()
        val from = cast / 16u
        val to = cast % 16u

        when (from) {
            PrimitiveIds.PRIMITIVE_BYTE -> {
                val v = stack.pop()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> {
                val v = stack.popByte().toUByte()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_SHORT -> {
                val v = stack.popShort()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> {
                val v = stack.popShort().toUShort()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_INT -> {
                val v = stack.popInt()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v)
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> {
                val v = stack.popInt().toUInt()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_LONG -> {
                val v = stack.popLong()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> {
                val v = stack.popLong()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_FLOAT -> {
                val v = stack.popFloat()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toInt().toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toInt().toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toInt().toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toInt().toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v)
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toInt().toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_DOUBLE -> {
                val v = stack.popDouble()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> stack.push(v.toInt().toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> stack.push(v.toInt().toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> stack.pushShort(v.toInt().toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> stack.pushShort(v.toInt().toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> stack.pushInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> stack.pushLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> stack.pushLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> stack.pushFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> stack.pushDouble(v)
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> stack.pushShort(v.toInt().toUByte().toShort())
                }
            }

            PrimitiveIds.PRIMITIVE_BOOLEAN -> {
                val v = stack.popByte()
                when (to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> throw Error("Cannot cast from boolean to byte")
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> throw Error("Cannot cast from boolean to unsigned byte")
                    PrimitiveIds.PRIMITIVE_SHORT -> throw Error("Cannot cast from boolean to short")
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> throw Error("Cannot cast from boolean to unsigned short")
                    PrimitiveIds.PRIMITIVE_INT -> throw Error("Cannot cast from boolean to int")
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> throw Error("Cannot cast from boolean to unsigned int")
                    PrimitiveIds.PRIMITIVE_LONG -> throw Error("Cannot cast from boolean to long")
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> throw Error("Cannot cast from boolean to unsigned long")
                    PrimitiveIds.PRIMITIVE_FLOAT -> throw Error("Cannot cast from boolean to float")
                    PrimitiveIds.PRIMITIVE_DOUBLE -> throw Error("Cannot cast from boolean to double")
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> stack.push(v)
                    PrimitiveIds.PRIMITIVE_CHAR -> throw Error("Cannot cast from boolean to char")
                }
            }
        }
    }

    fun b_shr() {
        val shr = stack.popByte().toUByte().toInt()
        val byte = stack.popByte()
        stack.push((byte.toInt() shr shr).toByte())
    }

    fun s_shr() {
        val shr = stack.popByte().toUByte().toInt()
        val short = stack.popShort()
        stack.pushShort((short.toInt() shr shr).toShort())
    }

    fun i_shr() {
        val shr = stack.popByte().toUByte().toInt()
        val int = stack.popInt()
        stack.pushInt(int shr shr)
    }

    fun l_shr() {
        val shr = stack.popByte().toUByte().toInt()
        val long = stack.popLong()
        stack.pushLong(long shr shr)
    }

    fun b_shl() {
        val shl = stack.popByte().toUByte().toInt()
        val byte = stack.popByte()
        stack.push((byte.toInt() shl shl).toByte())
    }

    fun s_shl() {
        val shl = stack.popByte().toUByte().toInt()
        val short = stack.popShort()
        stack.pushShort((short.toInt() shl shl).toShort())
    }

    fun i_shl() {
        val shl = stack.popByte().toUByte().toInt()
        val int = stack.popInt()
        stack.pushInt(int shl shl)
    }

    fun l_shl() {
        val shl = stack.popByte().toUByte().toInt()
        val long = stack.popLong()
        stack.pushLong(long shl shl)
    }

    fun b_and() {
        val v0 = stack.popByte()
        val v1 = stack.popByte()
        stack.push(v0 and v1)
    }

    fun s_and() {
        val v0 = stack.popShort()
        val v1 = stack.popShort()
        stack.pushShort(v0 and v1)
    }

    fun i_and() {
        val v0 = stack.popInt()
        val v1 = stack.popInt()
        stack.pushInt(v0 and v1)
    }

    fun l_and() {
        val v0 = stack.popLong()
        val v1 = stack.popLong()
        stack.pushLong(v0 and v1)
    }

    fun b_or() {
        val v0 = stack.popByte()
        val v1 = stack.popByte()
        stack.push(v0 or v1)
    }

    fun s_or() {
        val v0 = stack.popShort()
        val v1 = stack.popShort()
        stack.pushShort(v0 or v1)
    }

    fun i_or() {
        val v0 = stack.popInt()
        val v1 = stack.popInt()
        stack.pushInt(v0 or v1)
    }

    fun l_or() {
        val v0 = stack.popLong()
        val v1 = stack.popLong()
        stack.pushLong(v0 or v1)
    }

    fun b_xor() {
        val v0 = stack.popByte()
        val v1 = stack.popByte()
        stack.push(v0 xor v1)
    }

    fun s_xor() {
        val v0 = stack.popShort()
        val v1 = stack.popShort()
        stack.pushShort(v0 xor v1)
    }

    fun i_xor() {
        val v0 = stack.popInt()
        val v1 = stack.popInt()
        stack.pushInt(v0 xor v1)
    }

    fun l_xor() {
        val v0 = stack.popLong()
        val v1 = stack.popLong()
        stack.pushLong(v0 xor v1)
    }
}
