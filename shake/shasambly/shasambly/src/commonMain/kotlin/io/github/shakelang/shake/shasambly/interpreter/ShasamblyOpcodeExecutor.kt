@file:Suppress("nothing_to_inline")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.shake.shasambly.interpreter.natives.nativeFunctions
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.math.abs

abstract class ShasamblyOpcodeExecutor (
    memorySize: Int,
    bytes: ByteArray,
    position: Int
) : ShasamblyInterpretingBase (memorySize, bytes, position) {


    fun incr_variable_stack() {
        variableStackSize = read_short().toUShort().toInt()
        variableAddress += variableStackSize
        variableStackSizes.add(variableStackSize)
        //println("Variable stack size updated to $variableAddress")
    }

    fun decr_variable_stack() {
        variableAddress -= variableStackSizes.removeLast()
        //println("Variable stack size updated to $variableAddress")
    }

    fun jump(address: Int) {
        //println("Jumping to ${address.toBytes().toHexString()}")
        if(address < 0 || address > memory.size) throw Error("Address 0x${address.toBytes().toHexString()} out of range")
        position = address
    }

    fun jump_static() {
        jump(read_int())
    }

    fun jump_dynamic() {
        jump(removeLastInt())
    }

    fun jump_if() {
        val addr = read_int()
        if(removeLastByte() != 0x00.toByte()) jump(addr)
    }

    fun invoke_native() {
        val native = read_short().toUShort().toInt()
        (nativeFunctions[native]
            ?: throw Error("Unknown native function 0x${native.toBytes().toHexString()} at position 0x${position.toBytes().toHexString()}"))
            .execute.invoke(this)
    }

    fun glob_addr() {
        val variable = read_short().toUShort().toInt()
        if(variable >= variableStackSize) throw IllegalArgumentException("Could not get global address of local $variable (Local stack size is only $variableStackSize)")
        addInt(variableAddress - variable)
    }

    fun b_get_local() {
        val variable = read_short().toUShort().toInt()
        sadd(memory[variableAddress - variable])
    }

    fun s_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        sadd(memory[addr])
        sadd(memory[addr - 1])
    }

    fun i_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        sadd(memory[addr])
        sadd(memory[addr - 1])
        sadd(memory[addr - 2])
        sadd(memory[addr - 3])
    }

    fun l_get_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        sadd(memory[addr])
        sadd(memory[addr - 1])
        sadd(memory[addr - 2])
        sadd(memory[addr - 3])
        sadd(memory[addr - 4])
        sadd(memory[addr - 5])
        sadd(memory[addr - 6])
        sadd(memory[addr - 7])
    }

    fun b_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr] = sRemoveLast()
    }

    fun s_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 1] = sRemoveLast()
        memory[addr] = sRemoveLast()
    }

    fun i_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 3] = sRemoveLast()
        memory[addr - 2] = sRemoveLast()
        memory[addr - 1] = sRemoveLast()
        memory[addr] = sRemoveLast()
    }

    fun l_store_local() {
        val variable = read_short().toUShort().toInt()
        val addr = variableAddress - variable
        memory[addr - 7] = sRemoveLast()
        memory[addr - 6] = sRemoveLast()
        memory[addr - 5] = sRemoveLast()
        memory[addr - 4] = sRemoveLast()
        memory[addr - 3] = sRemoveLast()
        memory[addr - 2] = sRemoveLast()
        memory[addr - 1] = sRemoveLast()
        memory[addr] = sRemoveLast()
    }

    fun bpush() {
        sadd(read_byte())
    }

    fun spush() {
        addShort(read_short())
    }

    fun ipush() {
        addInt(read_int())
    }

    fun lpush() {
        addLong(read_long())
    }

    fun badd() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 + v1).toByte())
    }

    fun bsub() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 - v1).toByte())
    }

    fun bmul() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 * v1).toByte())
    }

    fun bdiv() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 / v1).toByte())
    }

    fun bmod() {
        val v1 = sRemoveLast()
        val v0 = sRemoveLast()
        sadd((v0 % v1).toByte())
    }

    fun sadd() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 + v1).toShort())
    }

    fun ssub() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 - v1).toShort())
    }

    fun smul() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 * v1).toShort())
    }

    fun sdiv() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 / v1).toShort())
    }

    fun smod() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addShort((v0 % v1).toShort())
    }

    fun iadd() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 + v1)
    }

    fun isub() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 - v1)
    }

    fun imul() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 * v1)
    }

    fun idiv() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 / v1)
    }

    fun imod() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addInt(v0 % v1)
    }

    fun ladd() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 + v1)
    }

    fun lsub() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 - v1)
    }

    fun lmul() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 * v1)
    }

    fun ldiv() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 / v1)
    }

    fun lmod() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addLong(v0 % v1)
    }

    fun fadd() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 + v1)
    }

    fun fsub() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 - v1)
    }

    fun fmul() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 * v1)
    }

    fun fdiv() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 / v1)
    }

    fun fmod() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addFloat(v0 % v1)
    }

    fun dadd() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 + v1)
    }

    fun dsub() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 - v1)
    }

    fun dmul() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 * v1)
    }

    fun ddiv() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 / v1)
    }

    fun dmod() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addDouble(v0 % v1)
    }

    fun beq() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 == v1)
    }

    fun seq() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 == v1)
    }

    fun ieq() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 == v1)
    }

    fun leq() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 == v1)
    }

    fun feq() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 == v1)
    }

    fun deq() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 == v1)
    }

    fun bbigger() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 > v1)
    }

    fun sbigger() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 > v1)
    }

    fun ibigger() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 > v1)
    }

    fun lbigger() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 > v1)
    }

    fun fbigger() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 > v1)
    }

    fun dbigger() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 > v1)
    }

    fun bsmaller() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 < v1)
    }

    fun ssmaller() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 < v1)
    }

    fun ismaller() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 < v1)
    }

    fun lsmaller() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 < v1)
    }

    fun fsmaller() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 < v1)
    }

    fun dsmaller() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 < v1)
    }

    fun bbiggereq() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 >= v1)
    }

    fun sbiggereq() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 >= v1)
    }

    fun ibiggereq() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 >= v1)
    }

    fun lbiggereq() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 >= v1)
    }

    fun fbiggereq() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 >= v1)
    }

    fun dbiggereq() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 >= v1)
    }

    fun bsmallereq() {
        val v1 = removeLastByte()
        val v0 = removeLastByte()
        addBoolean(v0 <= v1)
    }

    fun ssmallereq() {
        val v1 = removeLastShort()
        val v0 = removeLastShort()
        addBoolean(v0 <= v1)
    }

    fun ismallereq() {
        val v1 = removeLastInt()
        val v0 = removeLastInt()
        addBoolean(v0 <= v1)
    }

    fun lsmallereq() {
        val v1 = removeLastLong()
        val v0 = removeLastLong()
        addBoolean(v0 <= v1)
    }

    fun fsmallereq() {
        val v1 = removeLastFloat()
        val v0 = removeLastFloat()
        addBoolean(v0 <= v1)
    }

    fun dsmallereq() {
        val v1 = removeLastDouble()
        val v0 = removeLastDouble()
        addBoolean(v0 <= v1)
    }

    fun bnot() {
        addBoolean(removeLastByte() == 0.toByte())
    }

    fun b_get_global() {
        val pos = read_int()
        addByte(memory[pos])
    }

    fun s_get_global() {
        val pos = read_int()
        addShort(memory.getShort(pos - 1))
    }

    fun i_get_global() {
        val pos = read_int()
        addInt(memory.getInt(pos - 3))
    }

    fun l_get_global() {
        val pos = read_int()
        addLong(memory.getLong(pos - 7))
    }

    fun b_get_global_dynamic() {
        val pos = removeLastInt()
        addByte(memory.getByte(pos))
    }

    fun s_get_global_dynamic() {
        val pos = removeLastInt()
        addShort(memory.getShort(pos - 1))
    }

    fun i_get_global_dynamic() {
        val pos = removeLastInt()
        addInt(memory.getInt(pos - 3))
    }

    fun l_get_global_dynamic() {
        val pos = removeLastInt()
        addLong(memory.getLong(pos - 7))
    }

    fun b_store_global() {
        val pos = read_int()
        val value = removeLastByte()
        memory[pos] = value
    }

    fun s_store_global() {
        val pos = read_int()
        val value = removeLastShort()
        memory.setShort(pos - 1, value)
    }

    fun i_store_global() {
        val pos = read_int()
        val value = removeLastInt()
        memory.setInt(pos - 3, value)
    }

    fun l_store_global() {
        val pos = read_int()
        val value = removeLastLong()
        memory.setLong(pos - 7, value)
    }

    fun b_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastByte()
        memory[pos] = value
    }

    fun s_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastShort()
        memory.setShort(pos - 1, value)
    }

    fun i_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastInt()
        memory.setInt(pos - 3, value)
    }

    fun l_store_global_dynamic() {
        val pos = removeLastInt()
        val value = removeLastLong()
        memory.setLong(pos - 7, value)
    }

    fun b_neg() {
        addByte((-removeLastByte()).toByte())
    }

    fun s_neg() {
        addShort((-removeLastShort()).toShort())
    }

    fun i_neg() {
        addInt(-removeLastInt())
    }

    fun l_neg() {
        addLong(-removeLastLong())
    }

    fun f_neg() {
        addFloat(-removeLastFloat())
    }

    fun d_neg() {
        addDouble(-removeLastDouble())
    }

    fun b_abs() {
        addByte(abs(removeLastByte().toInt()).toByte())
    }

    fun s_abs() {
        addShort(abs(removeLastShort().toInt()).toShort())
    }

    fun i_abs() {
        addInt(abs(removeLastInt()))
    }

    fun l_abs() {
        addLong(abs(removeLastLong()))
    }

    fun f_abs() {
        addFloat(abs(removeLastFloat()))
    }

    fun d_abs() {
        addDouble(abs(removeLastDouble()))
    }
    fun primitive_cast() {
        val cast = read_byte().toUByte()
        val from = cast / 16u
        val to = cast % 16u

        when (from) {
            PrimitiveIds.PRIMITIVE_BYTE -> {
                val v = removeLastByte()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> {
                val v = removeLastByte().toUByte()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_SHORT -> {
                val v = removeLastShort()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> {
                val v = removeLastShort().toUShort()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_INT -> {
                val v = removeLastInt()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v)
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> {
                val v = removeLastInt().toUInt()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_LONG -> {
                val v = removeLastLong()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> {
                val v = removeLastLong()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v)
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_FLOAT -> {
                val v = removeLastFloat()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toInt().toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toInt().toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toInt().toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toInt().toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v)
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v.toDouble())
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toInt().toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_DOUBLE -> {
                val v = removeLastDouble()
                when(to) {
                    PrimitiveIds.PRIMITIVE_BYTE -> addByte(v.toInt().toByte())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_BYTE -> addByte(v.toInt().toUByte().toByte())
                    PrimitiveIds.PRIMITIVE_SHORT -> addShort(v.toInt().toShort())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_SHORT -> addShort(v.toInt().toUShort().toShort())
                    PrimitiveIds.PRIMITIVE_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_INT -> addInt(v.toInt())
                    PrimitiveIds.PRIMITIVE_LONG -> addLong(v.toLong())
                    PrimitiveIds.PRIMITIVE_UNSIGNED_LONG -> addLong(v.toULong().toLong())
                    PrimitiveIds.PRIMITIVE_FLOAT -> addFloat(v.toFloat())
                    PrimitiveIds.PRIMITIVE_DOUBLE -> addDouble(v)
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> throw Error("Cannot cast from unsigned byte to boolean")
                    PrimitiveIds.PRIMITIVE_CHAR -> addShort(v.toInt().toUByte().toShort())
                }
            }
            PrimitiveIds.PRIMITIVE_BOOLEAN -> {
                val v = removeLastByte()
                when(to) {
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
                    PrimitiveIds.PRIMITIVE_BOOLEAN -> addByte(v)
                    PrimitiveIds.PRIMITIVE_CHAR -> throw Error("Cannot cast from boolean to char")
                }
            }
        }
    }
    fun b_shr() {
        val shr = removeLastByte().toUByte().toInt()
        val byte = removeLastByte()
        addByte((byte.toInt() shr shr).toByte())
    }

    fun s_shr() {
        val shr = removeLastByte().toUByte().toInt()
        val short = removeLastShort()
        addShort((short.toInt() shr shr).toShort())
    }

    fun i_shr() {
        val shr = removeLastByte().toUByte().toInt()
        val int = removeLastInt()
        addInt(int shr shr)
    }

    fun l_shr() {
        val shr = removeLastByte().toUByte().toInt()
        val long = removeLastLong()
        addLong(long shr shr)
    }

    fun b_shl() {
        val shl = removeLastByte().toUByte().toInt()
        val byte = removeLastByte()
        addByte((byte.toInt() shl shl).toByte())
    }

    fun s_shl() {
        val shl = removeLastByte().toUByte().toInt()
        val short = removeLastShort()
        addShort((short.toInt() shl shl).toShort())
    }

    fun i_shl() {
        val shl = removeLastByte().toUByte().toInt()
        val int = removeLastInt()
        addInt(int shl shl)
    }

    fun l_shl() {
        val shl = removeLastByte().toUByte().toInt()
        val long = removeLastLong()
        addLong(long shl shl)
    }

    fun b_and() {
        val v0 = removeLastByte()
        val v1 = removeLastByte()
        addByte(v0 and v1)
    }

    fun s_and() {
        val v0 = removeLastShort()
        val v1 = removeLastShort()
        addShort(v0 and v1)
    }

    fun i_and() {
        val v0 = removeLastInt()
        val v1 = removeLastInt()
        addInt(v0 and v1)
    }

    fun l_and() {
        val v0 = removeLastLong()
        val v1 = removeLastLong()
        addLong(v0 and v1)
    }

    fun b_or() {
        val v0 = removeLastByte()
        val v1 = removeLastByte()
        addByte(v0 or  v1)
    }

    fun s_or() {
        val v0 = removeLastShort()
        val v1 = removeLastShort()
        addShort(v0 or v1)
    }

    fun i_or() {
        val v0 = removeLastInt()
        val v1 = removeLastInt()
        addInt(v0 or v1)
    }

    fun l_or() {
        val v0 = removeLastLong()
        val v1 = removeLastLong()
        addLong(v0 or v1)
    }

    fun b_xor() {
        val v0 = removeLastByte()
        val v1 = removeLastByte()
        addByte(v0 xor  v1)
    }

    fun s_xor() {
        val v0 = removeLastShort()
        val v1 = removeLastShort()
        addShort(v0 xor v1)
    }

    fun i_xor() {
        val v0 = removeLastInt()
        val v1 = removeLastInt()
        addInt(v0 xor v1)
    }

    fun l_xor() {
        val v0 = removeLastLong()
        val v1 = removeLastLong()
        addLong(v0 xor v1)
    }
}