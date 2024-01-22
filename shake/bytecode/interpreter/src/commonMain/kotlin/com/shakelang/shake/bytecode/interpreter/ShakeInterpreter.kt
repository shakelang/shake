package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor
import com.shakelang.shake.bytecode.interpreter.heap.GarbageCollector
import com.shakelang.shake.bytecode.interpreter.heap.GlobalMemory
import com.shakelang.shake.bytecode.interpreter.heap.Malloc
import com.shakelang.shake.bytecode.interpreter.natives.ShakeInterpreterProcess
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeInterpreterClass
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeInterpreterClasspath
import com.shakelang.shake.bytecode.interpreter.wrapper.ShakeInterpreterMethod
import com.shakelang.util.primitives.bytes.*
import com.shakelang.util.primitives.calc.shl
import com.shakelang.util.primitives.calc.shr
import com.shakelang.util.primitives.calc.ushr
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor

interface ShakeCallStackElement {
    val returnData: ByteArray
    val finished: Boolean
    val stack: ByteStack
    val locals: ByteArray
    fun tick(times: Int): Int
    fun tick()
}

class ShakeInterpreter {

    val classPath = ShakeInterpreterClasspath.create(this)

    val process = ShakeInterpreterProcess()

    val callStack: List<ShakeCallStackElement> get() = _callStack
    private val _callStack = mutableListOf<ShakeCallStackElement>()
    val globalMemory = GlobalMemory()
    val malloc = Malloc(globalMemory)
    val garbageCollector = GarbageCollector(malloc, this)

    var latestReturnData: ByteArray = ByteArray(0)
        private set

    internal fun pushStack(element: ShakeCallStackElement) {
        _callStack.add(element)
    }

    fun tick(): Boolean {
        if (callStack.isEmpty()) return false
        callStack.last().tick()
        if (callStack.last().finished) popStack()
        return true
    }

    private fun popStack() {
        val last = _callStack.removeLast()
        if (callStack.isNotEmpty()) {
            val stack = callStack.last().stack
            stack.push(last.returnData)
        }

        latestReturnData = last.returnData
    }

    fun tick(times: Int): Int {
        for (i in 0 until times) {
            if (!tick()) return i
        }
        return times
    }

    fun run(limit: Int = -1): Int {
        var i = 0
        while (tick()) {
            i++
            if (limit != -1 && i >= limit) return i
        }
        return i
    }

    fun createCodeInterpreter(code: ByteArray, method: ShakeInterpreterMethod) = ShakeCodeInterpreter(code, method)
    fun createCodeInterpreter(method: ShakeInterpreterMethod): ShakeCallStackElement {
        if (method.isNative) {
            return process.natives[method.fullName] ?: throw NullPointerException("Native ${method.qualifiedName} not found")
        }
        return createCodeInterpreter(method.code, method)
    }

    fun putFunctionOnStack(
        method: ShakeInterpreterMethod,
        args: ByteArray = kotlin.byteArrayOf(),
    ): ShakeCallStackElement {
        val interpreter = createCodeInterpreter(method)
        // Put the arguments on the stack
        interpreter.stack.push(args)
        _callStack.add(interpreter)
        return interpreter
    }

    fun putFunctionOnStack(
        method: String,
        args: ByteArray,
    ): ShakeCallStackElement {
        return putFunctionOnStack(
            classPath.getMethod(method)
                ?: throw NullPointerException("Method $method not found"),
            args,
        )
    }

    inner class ShakeCodeInterpreter(
        val code: ByteArray,
        val method: ShakeInterpreterMethod,
    ) : ShakeCallStackElement {

        override val locals = ByteArray(method.maxLocals)
        override val returnData = ByteArray(method.returnType.byteSize)
        override val stack = ByteStack(method.maxStack)

        override var finished = false
            private set

        var pc = 0

        private fun readByte(): Byte {
            val byte = code[pc]
            pc++
            return byte
        }

        private fun readShort(): Short {
            val v = code.getShort(pc)
            pc += 2
            return v
        }

        private fun readInt(): Int {
            val v = code.getInt(pc)
            pc += 4
            return v
        }

        private fun readLong(): Long {
            val v = code.getLong(pc)
            pc += 8
            return v
        }

        private fun readUByte(): UByte {
            val v = code.getUnsignedByte(pc)
            pc++
            return v
        }

        private fun readUShort(): UShort {
            val v = code.getUnsignedShort(pc)
            pc += 2
            return v
        }

        private fun readUInt(): UInt {
            val v = code.getUnsignedInt(pc)
            pc += 4
            return v
        }

        private fun readULong(): ULong {
            val v = code.getUnsignedLong(pc)
            pc += 8
            return v
        }

        override fun tick(times: Int): Int {
            for (i in 0 until times) {
                if (finished) return i
                tick()
            }
            return times
        }

        override fun tick() {
            if (finished) return
            val opcode = readByte()
            when (opcode) {
                Opcodes.NOP -> {
                    /* do nothing */
                }

                // Pushing values to the stack
                Opcodes.BPUSH -> stack.push(readByte())
                Opcodes.SPUSH -> stack.push(readShort())
                Opcodes.IPUSH -> stack.push(readInt())
                Opcodes.LPUSH -> stack.push(readLong())

                // Load a value from local variables to the stack
                Opcodes.BLOAD -> stack.push(locals[readUShort().toInt()])
                Opcodes.SLOAD -> {
                    val pos = readUShort().toInt()
                    stack.push(locals[pos])
                    stack.push(locals[pos + 1])
                }

                Opcodes.ILOAD -> {
                    val pos = readUShort().toInt()
                    for (i in 0 until 4) stack.push(locals[pos + i])
                }

                Opcodes.LLOAD -> {
                    val pos = readUShort().toInt()
                    for (i in 0 until 8) stack.push(locals[pos + i])
                }

                // Store a value from the stack to local variables
                Opcodes.BSTORE -> locals[readUShort().toInt()] = stack.pop()
                Opcodes.SSTORE -> {
                    val pos = readUShort().toInt()
                    locals[pos + 1] = stack.pop()
                    locals[pos] = stack.pop()
                }

                Opcodes.ISTORE -> {
                    val pos = readUShort().toInt()
                    for (i in 3 downTo 0) locals[pos + i] = stack.pop()
                }

                Opcodes.LSTORE -> {
                    val pos = readUShort().toInt()
                    for (i in 7 downTo 0) locals[pos + i] = stack.pop()
                }

                // Arithmetic operations
                Opcodes.BADD -> stack.push(stack.pop() + stack.pop())
                Opcodes.SADD -> stack.push(stack.popShort() + stack.popShort())
                Opcodes.IADD -> stack.push(stack.popInt() + stack.popInt())
                Opcodes.LADD -> stack.push(stack.popLong() + stack.popLong())
                Opcodes.FADD -> stack.push(stack.popFloat() + stack.popFloat())
                Opcodes.DADD -> stack.push(stack.popDouble() + stack.popDouble())

                Opcodes.BSUB -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b - a)
                }

                Opcodes.SSUB -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b - a)
                }

                Opcodes.ISUB -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b - a)
                }

                Opcodes.LSUB -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b - a)
                }

                Opcodes.UBSUB -> {
                    val a = stack.popUByte()
                    val b = stack.popUByte()
                    stack.push((b - a).toByte())
                }

                Opcodes.USSUB -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b - a).toShort())
                }

                Opcodes.UISUB -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b - a).toInt())
                }

                Opcodes.ULSUB -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b - a).toLong())
                }

                Opcodes.FSUB -> {
                    val a = stack.popFloat()
                    val b = stack.popFloat()
                    stack.push(b - a)
                }

                Opcodes.DSUB -> {
                    val a = stack.popDouble()
                    val b = stack.popDouble()
                    stack.push(b - a)
                }

                Opcodes.BMUL -> stack.push(stack.pop() * stack.pop())
                Opcodes.SMUL -> stack.push(stack.popShort() * stack.popShort())
                Opcodes.IMUL -> stack.push(stack.popInt() * stack.popInt())
                Opcodes.LMUL -> stack.push(stack.popLong() * stack.popLong())
                Opcodes.FMUL -> stack.push(stack.popFloat() * stack.popFloat())
                Opcodes.DMUL -> stack.push(stack.popDouble() * stack.popDouble())
                Opcodes.UBMUL -> stack.push((stack.popUByte() * stack.popUByte()).toByte())
                Opcodes.USMUL -> stack.push((stack.popUShort() * stack.popUShort()).toShort())
                Opcodes.UIMUL -> stack.push((stack.popUInt() * stack.popUInt()).toInt())
                Opcodes.ULMUL -> stack.push((stack.popULong() * stack.popULong()).toLong())

                Opcodes.BDIV -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b / a)
                }

                Opcodes.SDIV -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b / a)
                }

                Opcodes.IDIV -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b / a)
                }

                Opcodes.LDIV -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b / a)
                }

                Opcodes.FDIV -> {
                    val a = stack.popFloat()
                    val b = stack.popFloat()
                    stack.push(b / a)
                }

                Opcodes.DDIV -> {
                    val a = stack.popDouble()
                    val b = stack.popDouble()
                    stack.push(b / a)
                }

                Opcodes.UBDIV -> {
                    val a = stack.popUByte()
                    val b = stack.popUByte()
                    stack.push((b / a).toByte())
                }

                Opcodes.USDIV -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b / a).toShort())
                }

                Opcodes.UIDIV -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b / a).toInt())
                }

                Opcodes.ULDIV -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b / a).toLong())
                }

                Opcodes.BMOD -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b % a)
                }

                Opcodes.SMOD -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b % a)
                }

                Opcodes.IMOD -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b % a)
                }

                Opcodes.LMOD -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b % a)
                }

                Opcodes.UBMOD -> {
                    val a = stack.popUByte()
                    val b = stack.popUByte()
                    stack.push((b % a).toByte())
                }

                Opcodes.USMOD -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b % a).toShort())
                }

                Opcodes.UIMOD -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b % a).toInt())
                }

                Opcodes.ULMOD -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b % a).toLong())
                }

                Opcodes.FMOD -> {
                    val a = stack.popFloat()
                    val b = stack.popFloat()
                    stack.push(b % a)
                }

                Opcodes.DMOD -> {
                    val a = stack.popDouble()
                    val b = stack.popDouble()
                    stack.push(b % a)
                }

                Opcodes.BNEG -> stack.push(stack.pop().inv())
                Opcodes.SNEG -> stack.push(stack.popShort().inv())
                Opcodes.INEG -> stack.push(stack.popInt().inv())
                Opcodes.LNEG -> stack.push(stack.popLong().inv())

                Opcodes.BAND -> stack.push(stack.pop() and stack.pop())
                Opcodes.SAND -> stack.push(stack.popShort() and stack.popShort())
                Opcodes.IAND -> stack.push(stack.popInt() and stack.popInt())
                Opcodes.LAND -> stack.push(stack.popLong() and stack.popLong())

                Opcodes.BOR -> stack.push(stack.pop() or stack.pop())
                Opcodes.SOR -> stack.push(stack.popShort() or stack.popShort())
                Opcodes.IOR -> stack.push(stack.popInt() or stack.popInt())
                Opcodes.LOR -> stack.push(stack.popLong() or stack.popLong())

                Opcodes.BXOR -> stack.push(stack.pop() xor stack.pop())
                Opcodes.SXOR -> stack.push(stack.popShort() xor stack.popShort())
                Opcodes.IXOR -> stack.push(stack.popInt() xor stack.popInt())
                Opcodes.LXOR -> stack.push(stack.popLong() xor stack.popLong())

                Opcodes.BNOT -> stack.push(stack.pop().inv())
                Opcodes.SNOT -> stack.push(stack.popShort().inv())
                Opcodes.INOT -> stack.push(stack.popInt().inv())
                Opcodes.LNOT -> stack.push(stack.popLong().inv())

                Opcodes.BSHL -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b shl a)
                }

                Opcodes.SSHL -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b shl a)
                }

                Opcodes.ISHL -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b shl a)
                }

                Opcodes.LSHL -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b shl a)
                }

                Opcodes.BSHR -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b shr a)
                }

                Opcodes.SSHR -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b shr a)
                }

                Opcodes.ISHR -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b shr a)
                }

                Opcodes.LSHR -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b shr a)
                }

                Opcodes.BSHRU -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push(b ushr a)
                }

                Opcodes.SSHRU -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push(b ushr a)
                }

                Opcodes.ISHRU -> {
                    val a = stack.popInt()
                    val b = stack.popInt()
                    stack.push(b ushr a)
                }

                Opcodes.LSHRU -> {
                    val a = stack.popLong()
                    val b = stack.popLong()
                    stack.push(b ushr a)
                }

                Opcodes.BINC -> stack.push((stack.pop() + 1).toByte())
                Opcodes.SINC -> stack.push((stack.popShort() + 1).toShort())
                Opcodes.IINC -> stack.push(stack.popInt() + 1)
                Opcodes.LINC -> stack.push(stack.popLong() + 1)
                Opcodes.FINC -> stack.push(stack.popFloat() + 1)
                Opcodes.DINC -> stack.push(stack.popDouble() + 1)

                Opcodes.BDEC -> stack.push((stack.pop() - 1).toByte())
                Opcodes.SDEC -> stack.push((stack.popShort() - 1).toShort())
                Opcodes.IDEC -> stack.push(stack.popInt() - 1)
                Opcodes.LDEC -> stack.push(stack.popLong() - 1)
                Opcodes.FDEC -> stack.push(stack.popFloat() - 1)
                Opcodes.DDEC -> stack.push(stack.popDouble() - 1)

                Opcodes.BCMP -> {
                    val b = stack.popUByte()
                    val a = stack.popUByte()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.SCMP -> {
                    val b = stack.popUShort()
                    val a = stack.popUShort()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.ICMP -> {
                    val b = stack.popUInt()
                    val a = stack.popUInt()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.LCMP -> {
                    val b = stack.popULong()
                    val a = stack.popULong()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.FCMP -> {
                    val b = stack.popFloat()
                    val a = stack.popFloat()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.DCMP -> {
                    val b = stack.popDouble()
                    val a = stack.popDouble()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.UBCMP -> {
                    val b = stack.popUByte()
                    val a = stack.popUByte()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.USCMP -> {
                    val b = stack.popUShort()
                    val a = stack.popUShort()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.UICMP -> {
                    val b = stack.popUInt()
                    val a = stack.popUInt()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.ULCMP -> {
                    val b = stack.popULong()
                    val a = stack.popULong()
                    stack.push(
                        if (a > b) {
                            0.toByte()
                        } else if (a == b) {
                            1.toByte()
                        } else {
                            2.toByte()
                        },
                    )
                }

                Opcodes.CLT -> {
                    val b = stack.popUByte()
                    if (b == 2u.toUByte()) stack.push(1.toByte()) else stack.push(0.toByte())
                }

                Opcodes.CLE -> {
                    val b = stack.popUByte()
                    if (b >= 1u.toUByte()) stack.push(1.toByte()) else stack.push(0.toByte())
                }

                Opcodes.CGT -> {
                    val b = stack.popUByte()
                    if (b == 0u.toUByte()) stack.push(1.toByte()) else stack.push(0.toByte())
                }

                Opcodes.CGE -> {
                    val b = stack.popUByte()
                    if (b <= 1u.toUByte()) stack.push(1.toByte()) else stack.push(0.toByte())
                }

                Opcodes.CEQ -> {
                    val b = stack.popUByte()
                    if (b == 1u.toUByte()) stack.push(1.toByte()) else stack.push(0.toByte())
                }

                Opcodes.CNE -> {
                    val b = stack.popUByte()
                    if (b != 1u.toUByte()) stack.push(1.toByte()) else stack.push(0.toByte())
                }

                Opcodes.JMP -> pc = readInt()
                Opcodes.JZ -> {
                    val pos = readInt()
                    if (stack.pop() == 0.toByte()) pc = pos
                }

                Opcodes.JNZ -> {
                    val pos = readInt()
                    if (stack.pop() != 0.toByte()) pc = pos
                }

                Opcodes.JE -> {
                    val pos = readInt()
                    if (stack.pop() == 1.toByte()) pc = pos
                }

                Opcodes.JNE -> {
                    val pos = readInt()
                    if (stack.pop() != 1.toByte()) pc = pos
                }

                Opcodes.JGE -> {
                    val pos = readInt()
                    if (stack.pop() <= 1.toByte()) pc = pos
                }

                Opcodes.JL -> {
                    val pos = readInt()
                    if (stack.pop() == 2.toByte()) pc = pos
                }

                Opcodes.JLE -> {
                    val pos = readInt()
                    if (stack.pop() >= 1.toByte()) pc = pos
                }

                Opcodes.RET -> {
                    finished = true
                }

                Opcodes.BRET -> {
                    returnData[0] = stack.pop()
                }

                Opcodes.SRET -> {
                    returnData[0] = stack.pop()
                    returnData[1] = stack.pop()
                }

                Opcodes.IRET -> {
                    for (i in 0 until 4) returnData[i] = stack.pop()
                }

                Opcodes.LRET -> {
                    for (i in 0 until 8) returnData[i] = stack.pop()
                }

                Opcodes.BPOP -> stack.pop()
                Opcodes.SPOP -> stack.pop(2)
                Opcodes.IPOP -> stack.pop(4)
                Opcodes.LPOP -> stack.pop(8)

                Opcodes.BDUP -> stack.push(stack.peek())
                Opcodes.SDUP -> {
                    val arr = ByteArray(2) {
                        stack.pop()
                    }

                    stack.push(arr)
                    stack.push(arr)
                }

                Opcodes.IDUP -> {
                    val arr = ByteArray(4) {
                        stack.pop()
                    }

                    stack.push(arr)
                    stack.push(arr)
                }

                Opcodes.LDUP -> {
                    val arr = ByteArray(8) {
                        stack.pop()
                    }

                    stack.push(arr)
                    stack.push(arr)
                }

                Opcodes.PCAST -> {
                    // The First 4 bits are the "from" type, the last 4 bits are the "to" type
                    // See CastUtil.kt
                    val type = readUByte()
                    CastUtil.performCast(stack, type)
                }

                Opcodes.INVOKE_STATIC -> {
                    val methodName = this.method.constantPool.getUtf8(readInt()).value
                    val method =
                        classPath.getMethod(methodName) ?: throw NullPointerException("Method $methodName not found")
                    if (!method.isStatic) throw IllegalStateException("Method $methodName is not static")
                    val argsSize = method.parameters.sumOf { it.byteSize }
                    val args = ByteArray(argsSize) {
                        stack.pop()
                    }

                    putFunctionOnStack(method, args)
                }

                Opcodes.INVOKE_VIRTUAL -> {
                    val methodName = this.method.constantPool.getUtf8(readInt()).value
                    val method =
                        classPath.getMethod(methodName) ?: throw NullPointerException("Method $methodName not found")
                    if (method.isStatic) throw IllegalStateException("Method $methodName is static")
                    val argsSize = method.parameters.sumOf { it.byteSize }
                    val args = ByteArray(argsSize + 8) {
                        stack.pop()
                    }
                    putFunctionOnStack(method, args)
                }

                Opcodes.LOAD_STATIC -> {
                    val fieldName = this.method.constantPool.getUtf8(readInt()).value
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (!field.isStatic) throw IllegalStateException("Field $fieldName is not static")
                    val value = field.getStaticValue(field.type.byteSize)
                    stack.push(value)
                }

                Opcodes.LOAD_VIRTUAL -> {
                    val fieldName = this.method.constantPool.getUtf8(readInt()).value
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (field.isStatic) throw IllegalStateException("Field $fieldName is static")
                    val obj = stack.popLong()
                    val value = field.getVirtualValue(obj, field.type.byteSize)
                    stack.push(value)
                }

                Opcodes.STORE_STATIC -> {
                    val fieldName = this.method.constantPool.getUtf8(readInt()).value
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (!field.isStatic) throw IllegalStateException("Field $fieldName is not static")
                    val value = stack.pop(field.type.byteSize)
                    field.setStaticValue(value)
                }

                Opcodes.STORE_VIRTUAL -> {
                    val fieldName = this.method.constantPool.getUtf8(readInt()).value
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (field.isStatic) throw IllegalStateException("Field $fieldName is static")
                    val obj = stack.popLong()
                    val value = stack.pop(field.type.byteSize)
                    field.setVirtualValue(obj, value)
                }

                Opcodes.NEW_ARR -> {
                    val utf8 = this.method.constantPool.getUtf8(readInt()).value
                    val type = TypeDescriptor.parse(utf8)
                    val size = stack.popInt()

                    val array = malloc.malloc(size.toLong() * type.byteSize + 4)
                    globalMemory.setInt(array, size)
                    for (i in 0 until size * type.byteSize)
                        globalMemory.setByte(array + 4 + i, 0)

                    stack.push(array)
                }

                Opcodes.BALOAD -> {
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    val value = globalMemory.getByte(array + 4 + index)
                    stack.push(value)
                }

                Opcodes.SALOAD -> {
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    val value = globalMemory.getShort(array + 4 + index * 2)
                    stack.push(value)
                }

                Opcodes.IALOAD -> {
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    val value = globalMemory.getInt(array + 4 + index * 4)
                    stack.push(value)
                }

                Opcodes.LALOAD -> {
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    val value = globalMemory.getLong(array + 4 + index * 8)
                    stack.push(value)
                }

                Opcodes.BASTORE -> {
                    val value = stack.pop()
                    println("value: ${value.toBytes().toHexString()}")
                    val index = stack.popInt()
                    println("index: $index")
                    val array = stack.popLong()
                    println("array: $array")
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    globalMemory.setByte(array + 4 + index, value)
                }

                Opcodes.SASTORE -> {
                    val value = stack.popShort()
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    globalMemory.setShort(array + 4 + index * 2, value)
                }

                Opcodes.IASTORE -> {
                    val value = stack.popInt()
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    globalMemory.setInt(array + 4 + index * 4, value)
                }

                Opcodes.LASTORE -> {
                    val value = stack.popLong()
                    val index = stack.popInt()
                    val array = stack.popLong()
                    val size = globalMemory.getInt(array)
                    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index $index out of bounds")
                    // TODO: Throw ShakeException
                    globalMemory.setLong(array + 4 + index * 8, value)
                }
            }
        }
    }
}

object ClassRegister {

    private val classes = mutableMapOf<String, ShakeInterpreterClass>()

    fun registerClass(name: String, clazz: ShakeInterpreterClass) {
        classes[name] = clazz
    }

    fun getClass(name: String): ShakeInterpreterClass? = classes[name]
}
