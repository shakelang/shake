package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor
import com.shakelang.shake.bytecode.interpreter.heap.GarbageCollector
import com.shakelang.shake.bytecode.interpreter.heap.GlobalMemory
import com.shakelang.shake.bytecode.interpreter.heap.Malloc
import com.shakelang.shake.bytecode.interpreter.natives.ShakeInterpreterProcess
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

    val process = ShakeInterpreterProcess(this)

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
            return process.natives[method.fullName]?.reset() ?: throw NullPointerException("Native ${method.qualifiedName} not found")
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

        private fun readUtf8() = method.constantPool.getUtf8(readInt())

        private fun readUtf8Value() = readUtf8().value

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
                //
                // Tick Header
                //
                // This is the main logic of the interpreter, the bytecode
                // interpreter.
                //
                // This method is called once per tick and executes the
                // opcode at the current position of the program counter.
                // For this reason this method needs to be as fast as possible
                // and should not contain any unnecessary code.
                // We use a when statement to execute the opcode.
                // The main reason is that when statements create a jump table
                // which is very fast in comparison to if statements.
                // This when block can up to 256 cases (0x00 to 0xFF) and
                // an if statement would be need 256 checks in the worst case.
                //

                // The NOP opcode does nothing
                // It will just be skipped
                // https://spec.shakelang.com/bytecode/instructions#instr-nop
                Opcodes.NOP -> {
                    /* do nothing */
                }

                // ///////////////////////////////////////
                // The next 4 opcodes are used to push constants to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bpush
                // https://spec.shakelang.com/bytecode/instructions#instr-spush
                // https://spec.shakelang.com/bytecode/instructions#instr-ipush
                // https://spec.shakelang.com/bytecode/instructions#instr-lpush
                Opcodes.BPUSH -> stack.push(readByte())
                Opcodes.SPUSH -> stack.push(readShort())
                Opcodes.IPUSH -> stack.push(readInt())
                Opcodes.LPUSH -> stack.push(readLong())

                // ///////////////////////////////////////
                // The next 4 opcodes are used to load values from local variables
                // https://spec.shakelang.com/bytecode/instructions#instr-bload
                // https://spec.shakelang.com/bytecode/instructions#instr-sload
                // https://spec.shakelang.com/bytecode/instructions#instr-iload
                // https://spec.shakelang.com/bytecode/instructions#instr-lload

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
                // https://spec.shakelang.com/bytecode/instructions#instr-bstore
                // https://spec.shakelang.com/bytecode/instructions#instr-sstore
                // https://spec.shakelang.com/bytecode/instructions#instr-istore
                // https://spec.shakelang.com/bytecode/instructions#instr-lstore

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

                // ///////////////////////////////////////
                // The Next section contains all the arithmetic add operations

                // Add two values from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-badd
                // https://spec.shakelang.com/bytecode/instructions#instr-sadd
                // https://spec.shakelang.com/bytecode/instructions#instr-iadd
                // https://spec.shakelang.com/bytecode/instructions#instr-ladd
                // https://spec.shakelang.com/bytecode/instructions#instr-fadd
                // https://spec.shakelang.com/bytecode/instructions#instr-dadd
                Opcodes.BADD -> stack.push((stack.pop() + stack.pop()).toByte())
                Opcodes.SADD -> stack.push((stack.popShort() + stack.popShort()).toShort())
                Opcodes.IADD -> stack.push(stack.popInt() + stack.popInt())
                Opcodes.LADD -> stack.push(stack.popLong() + stack.popLong())
                Opcodes.FADD -> stack.push(stack.popFloat() + stack.popFloat())
                Opcodes.DADD -> stack.push(stack.popDouble() + stack.popDouble())

                // Subtract two values from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bsub
                // https://spec.shakelang.com/bytecode/instructions#instr-ssub
                // https://spec.shakelang.com/bytecode/instructions#instr-isub
                // https://spec.shakelang.com/bytecode/instructions#instr-lsub
                // https://spec.shakelang.com/bytecode/instructions#instr-fsub
                // https://spec.shakelang.com/bytecode/instructions#instr-dsub
                // https://spec.shakelang.com/bytecode/instructions#instr-ubsub
                // https://spec.shakelang.com/bytecode/instructions#instr-ussub
                // https://spec.shakelang.com/bytecode/instructions#instr-uisub
                // https://spec.shakelang.com/bytecode/instructions#instr-ulsub
                Opcodes.BSUB -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push((b - a).toByte())
                }

                Opcodes.SSUB -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push((b - a).toShort())
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
                    stack.push((b - a).toUByte())
                }

                Opcodes.USSUB -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b - a).toUShort())
                }

                Opcodes.UISUB -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push((b - a).toUInt())
                }

                Opcodes.ULSUB -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push((b - a).toULong())
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

                // Multiply two values from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bmul
                // https://spec.shakelang.com/bytecode/instructions#instr-smul
                // https://spec.shakelang.com/bytecode/instructions#instr-imul
                // https://spec.shakelang.com/bytecode/instructions#instr-lmul
                // https://spec.shakelang.com/bytecode/instructions#instr-fmul
                // https://spec.shakelang.com/bytecode/instructions#instr-dmul
                // https://spec.shakelang.com/bytecode/instructions#instr-ubmul
                // https://spec.shakelang.com/bytecode/instructions#instr-usmul
                // https://spec.shakelang.com/bytecode/instructions#instr-uimul
                // https://spec.shakelang.com/bytecode/instructions#instr-ulmul
                Opcodes.BMUL -> stack.push((stack.pop() * stack.pop()).toByte())
                Opcodes.SMUL -> stack.push((stack.popShort() * stack.popShort()).toShort())
                Opcodes.IMUL -> stack.push(stack.popInt() * stack.popInt())
                Opcodes.LMUL -> stack.push(stack.popLong() * stack.popLong())
                Opcodes.FMUL -> stack.push(stack.popFloat() * stack.popFloat())
                Opcodes.DMUL -> stack.push(stack.popDouble() * stack.popDouble())
                Opcodes.UBMUL -> stack.push((stack.popUByte() * stack.popUByte()).toUByte())
                Opcodes.USMUL -> stack.push((stack.popUShort() * stack.popUShort()).toUShort())
                Opcodes.UIMUL -> stack.push((stack.popUInt() * stack.popUInt()))
                Opcodes.ULMUL -> stack.push((stack.popULong() * stack.popULong()))

                // Divide two values from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bdiv
                // https://spec.shakelang.com/bytecode/instructions#instr-sdiv
                // https://spec.shakelang.com/bytecode/instructions#instr-idiv
                // https://spec.shakelang.com/bytecode/instructions#instr-ldiv
                // https://spec.shakelang.com/bytecode/instructions#instr-fdiv
                // https://spec.shakelang.com/bytecode/instructions#instr-ddiv
                // https://spec.shakelang.com/bytecode/instructions#instr-ubdiv
                // https://spec.shakelang.com/bytecode/instructions#instr-usdiv
                // https://spec.shakelang.com/bytecode/instructions#instr-uidiv
                // https://spec.shakelang.com/bytecode/instructions#instr-uldiv
                Opcodes.BDIV -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push((b / a).toByte())
                }

                Opcodes.SDIV -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push((b / a).toShort())
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
                    stack.push((b / a).toUByte())
                }

                Opcodes.USDIV -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b / a).toUShort())
                }

                Opcodes.UIDIV -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push(b / a)
                }

                Opcodes.ULDIV -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push(b / a)
                }

                // Modulo two values from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bmod
                // https://spec.shakelang.com/bytecode/instructions#instr-smod
                // https://spec.shakelang.com/bytecode/instructions#instr-imod
                // https://spec.shakelang.com/bytecode/instructions#instr-lmod
                // https://spec.shakelang.com/bytecode/instructions#instr-fmod
                // https://spec.shakelang.com/bytecode/instructions#instr-dmod
                // https://spec.shakelang.com/bytecode/instructions#instr-ubmod
                // https://spec.shakelang.com/bytecode/instructions#instr-usmod
                // https://spec.shakelang.com/bytecode/instructions#instr-uimod
                // https://spec.shakelang.com/bytecode/instructions#instr-ulmod
                Opcodes.BMOD -> {
                    val a = stack.pop()
                    val b = stack.pop()
                    stack.push((b % a).toByte())
                }

                Opcodes.SMOD -> {
                    val a = stack.popShort()
                    val b = stack.popShort()
                    stack.push((b % a).toShort())
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
                    stack.push((b % a).toUByte())
                }

                Opcodes.USMOD -> {
                    val a = stack.popUShort()
                    val b = stack.popUShort()
                    stack.push((b % a).toUShort())
                }

                Opcodes.UIMOD -> {
                    val a = stack.popUInt()
                    val b = stack.popUInt()
                    stack.push(b % a)
                }

                Opcodes.ULMOD -> {
                    val a = stack.popULong()
                    val b = stack.popULong()
                    stack.push(b % a)
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

                // Negate a value from the stack and push the result to the stack
                // These only exist for signed values as unsigned values can't be negative
                // https://spec.shakelang.com/bytecode/instructions#instr-bneg
                // https://spec.shakelang.com/bytecode/instructions#instr-sneg
                // https://spec.shakelang.com/bytecode/instructions#instr-ineg
                // https://spec.shakelang.com/bytecode/instructions#instr-lneg
                // https://spec.shakelang.com/bytecode/instructions#instr-fneg
                // https://spec.shakelang.com/bytecode/instructions#instr-dneg
                Opcodes.BNEG -> stack.push((-stack.pop()).toUByte())
                Opcodes.SNEG -> stack.push((-stack.popShort()).toUShort())
                Opcodes.INEG -> stack.push(-stack.popInt())
                Opcodes.LNEG -> stack.push(-stack.popLong())
                Opcodes.FNEG -> stack.push(-stack.popFloat())
                Opcodes.DNEG -> stack.push(-stack.popDouble())

                // Bitwise AND two values from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-band
                // https://spec.shakelang.com/bytecode/instructions#instr-sand
                // https://spec.shakelang.com/bytecode/instructions#instr-iand
                // https://spec.shakelang.com/bytecode/instructions#instr-land
                Opcodes.BAND -> stack.push(stack.pop() and stack.pop())
                Opcodes.SAND -> stack.push(stack.popShort() and stack.popShort())
                Opcodes.IAND -> stack.push(stack.popInt() and stack.popInt())
                Opcodes.LAND -> stack.push(stack.popLong() and stack.popLong())

                // Bitwise OR two values from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-bor
                // https://spec.shakelang.com/bytecode/instructions#instr-sor
                // https://spec.shakelang.com/bytecode/instructions#instr-ior
                // https://spec.shakelang.com/bytecode/instructions#instr-lor
                Opcodes.BOR -> stack.push(stack.pop() or stack.pop())
                Opcodes.SOR -> stack.push(stack.popShort() or stack.popShort())
                Opcodes.IOR -> stack.push(stack.popInt() or stack.popInt())
                Opcodes.LOR -> stack.push(stack.popLong() or stack.popLong())

                // Bitwise XOR two values from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-bxor
                // https://spec.shakelang.com/bytecode/instructions#instr-sxor
                // https://spec.shakelang.com/bytecode/instructions#instr-ixor
                // https://spec.shakelang.com/bytecode/instructions#instr-lxor
                Opcodes.BXOR -> stack.push(stack.pop() xor stack.pop())
                Opcodes.SXOR -> stack.push(stack.popShort() xor stack.popShort())
                Opcodes.IXOR -> stack.push(stack.popInt() xor stack.popInt())
                Opcodes.LXOR -> stack.push(stack.popLong() xor stack.popLong())

                // Bitwise NOT a value from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-bnot
                // https://spec.shakelang.com/bytecode/instructions#instr-snot
                // https://spec.shakelang.com/bytecode/instructions#instr-inot
                // https://spec.shakelang.com/bytecode/instructions#instr-lnot
                Opcodes.BNOT -> stack.push(stack.pop().inv())
                Opcodes.SNOT -> stack.push(stack.popShort().inv())
                Opcodes.INOT -> stack.push(stack.popInt().inv())
                Opcodes.LNOT -> stack.push(stack.popLong().inv())

                // Shift a value from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-bshl
                // https://spec.shakelang.com/bytecode/instructions#instr-sshl
                // https://spec.shakelang.com/bytecode/instructions#instr-ishl
                // https://spec.shakelang.com/bytecode/instructions#instr-lshl
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

                // Shift a value from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-bshr
                // https://spec.shakelang.com/bytecode/instructions#instr-sshr
                // https://spec.shakelang.com/bytecode/instructions#instr-ishr
                // https://spec.shakelang.com/bytecode/instructions#instr-lshr
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

                // Shift a value from the stack and push the result to the stack
                // We refer to byte, short, int and long, but the same applies to unsigned
                // values, booleans, chars, floats and doubles, so a byte means any 8-bit
                // value
                // https://spec.shakelang.com/bytecode/instructions#instr-bushr
                // https://spec.shakelang.com/bytecode/instructions#instr-sushr
                // https://spec.shakelang.com/bytecode/instructions#instr-iushr
                // https://spec.shakelang.com/bytecode/instructions#instr-lushr
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

                // Increment a value from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-binc
                // https://spec.shakelang.com/bytecode/instructions#instr-sinc
                // https://spec.shakelang.com/bytecode/instructions#instr-iinc
                // https://spec.shakelang.com/bytecode/instructions#instr-linc
                // https://spec.shakelang.com/bytecode/instructions#instr-finc
                // https://spec.shakelang.com/bytecode/instructions#instr-dinc
                Opcodes.BINC -> stack.push((stack.pop() + 1).toByte())
                Opcodes.SINC -> stack.push((stack.popShort() + 1).toShort())
                Opcodes.IINC -> stack.push(stack.popInt() + 1)
                Opcodes.LINC -> stack.push(stack.popLong() + 1)
                Opcodes.FINC -> stack.push(stack.popFloat() + 1)
                Opcodes.DINC -> stack.push(stack.popDouble() + 1)

                // Decrement a value from the stack and push the result to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bdec
                // https://spec.shakelang.com/bytecode/instructions#instr-sdec
                // https://spec.shakelang.com/bytecode/instructions#instr-idec
                // https://spec.shakelang.com/bytecode/instructions#instr-ldec
                // https://spec.shakelang.com/bytecode/instructions#instr-fdec
                // https://spec.shakelang.com/bytecode/instructions#instr-ddec
                Opcodes.BDEC -> stack.push((stack.pop() - 1).toByte())
                Opcodes.SDEC -> stack.push((stack.popShort() - 1).toShort())
                Opcodes.IDEC -> stack.push(stack.popInt() - 1)
                Opcodes.LDEC -> stack.push(stack.popLong() - 1)
                Opcodes.FDEC -> stack.push(stack.popFloat() - 1)
                Opcodes.DDEC -> stack.push(stack.popDouble() - 1)

                // Compare two values from the stack and push the result to the stack
                // Comparison results:
                // 0: a > b
                // 1: a == b
                // 2: a < b
                // b is the top value on the stack, a is the value below b
                // https://spec.shakelang.com/bytecode/instructions#instr-bcmp
                // https://spec.shakelang.com/bytecode/instructions#instr-scmp
                // https://spec.shakelang.com/bytecode/instructions#instr-icmp
                // https://spec.shakelang.com/bytecode/instructions#instr-lcmp
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

                // Convert the comparison result to a boolean
                // Comparison results:
                // 0: a < b
                // 1: a >= b
                // b is the top value on the stack, a is the value below b
                // https://spec.shakelang.com/bytecode/instructions#instr-bclt
                // https://spec.shakelang.com/bytecode/instructions#instr-sclt
                // https://spec.shakelang.com/bytecode/instructions#instr-iclt
                // https://spec.shakelang.com/bytecode/instructions#instr-lclt
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

                // Jump (unconditionally) to a position in the code
                // https://spec.shakelang.com/bytecode/instructions#instr-jmp
                Opcodes.JMP -> pc = readInt()

                // Jump to a position in the code if the top value on the stack is 0
                // https://spec.shakelang.com/bytecode/instructions#instr-jz
                Opcodes.JZ -> {
                    val pos = readInt()
                    if (stack.pop() == 0.toByte()) pc = pos
                }

                // Jump to a position in the code if the top value on the stack is not 0
                // https://spec.shakelang.com/bytecode/instructions#instr-jnz
                Opcodes.JNZ -> {
                    val pos = readInt()
                    if (stack.pop() != 0.toByte()) pc = pos
                }

                // Jump to a position in the code if the top value on the stack is 1
                // https://spec.shakelang.com/bytecode/instructions#instr-je
                Opcodes.JE -> {
                    val pos = readInt()
                    if (stack.pop() == 1.toByte()) pc = pos
                }

                // Jump to a position in the code if the top value on the stack is not 1
                // https://spec.shakelang.com/bytecode/instructions#instr-jne
                Opcodes.JNE -> {
                    val pos = readInt()
                    if (stack.pop() != 1.toByte()) pc = pos
                }

                // Jump to a position in the code if the top value on the stack is 2
                // https://spec.shakelang.com/bytecode/instructions#instr-jl
                Opcodes.JGE -> {
                    val pos = readInt()
                    if (stack.pop() <= 1.toByte()) pc = pos
                }

                // Jump to a position in the code if the top value on the stack is not 2
                // https://spec.shakelang.com/bytecode/instructions#instr-jl
                Opcodes.JL -> {
                    val pos = readInt()
                    if (stack.pop() == 2.toByte()) pc = pos
                }

                // Jump to a position in the code if the top value on the stack is not 2
                // https://spec.shakelang.com/bytecode/instructions#instr-jnl
                Opcodes.JLE -> {
                    val pos = readInt()
                    if (stack.pop() >= 1.toByte()) pc = pos
                }

                // Return from a function
                // https://spec.shakelang.com/bytecode/instructions#instr-ret
                Opcodes.RET -> {
                    finished = true
                }

                // Set the return value of a function.
                // Does NOT return from the function
                // https://spec.shakelang.com/bytecode/instructions#instr-bret
                // https://spec.shakelang.com/bytecode/instructions#instr-sret
                // https://spec.shakelang.com/bytecode/instructions#instr-iret
                // https://spec.shakelang.com/bytecode/instructions#instr-lret
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

                // Pop a value from the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bpop
                // https://spec.shakelang.com/bytecode/instructions#instr-spop
                // https://spec.shakelang.com/bytecode/instructions#instr-ipop
                // https://spec.shakelang.com/bytecode/instructions#instr-lpop
                Opcodes.BPOP -> stack.pop()
                Opcodes.SPOP -> stack.pop(2)
                Opcodes.IPOP -> stack.pop(4)
                Opcodes.LPOP -> stack.pop(8)

                // Duplicate a value on the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-bdup
                // https://spec.shakelang.com/bytecode/instructions#instr-sdup
                // https://spec.shakelang.com/bytecode/instructions#instr-idup
                // https://spec.shakelang.com/bytecode/instructions#instr-ldup
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

                // Cast a value on the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-pcast
                Opcodes.PCAST -> {
                    // The First 4 bits are the "from" type, the last 4 bits are the "to" type
                    // See CastUtil.kt
                    val type = readUByte()
                    CastUtil.performCast(stack, type)
                }

                // Invoke a static function
                // https://spec.shakelang.com/bytecode/instructions#instr-invoke-static
                Opcodes.INVOKE_STATIC -> {
                    val methodName = this.readUtf8Value()
                    val method =
                        classPath.getMethod(methodName) ?: throw NullPointerException("Method $methodName not found")
                    if (!method.isStatic) throw IllegalStateException("Method $methodName is not static")
                    val argsSize = method.parameters.sumOf { it.byteSize }
                    val args = ByteArray(argsSize) {
                        stack.pop()
                    }

                    putFunctionOnStack(method, args)
                }

                // Invoke a virtual (instance) function
                // https://spec.shakelang.com/bytecode/instructions#instr-invoke-virtual
                Opcodes.INVOKE_VIRTUAL -> {
                    val methodName = this.readUtf8Value()
                    val method =
                        classPath.getMethod(methodName) ?: throw NullPointerException("Method $methodName not found")
                    if (method.isStatic) throw IllegalStateException("Method $methodName is static")
                    val argsSize = method.parameters.sumOf { it.byteSize }
                    val args = ByteArray(argsSize + 8) {
                        stack.pop()
                    }
                    putFunctionOnStack(method, args)
                }

                // Load a static field
                // https://spec.shakelang.com/bytecode/instructions#instr-load-static
                Opcodes.LOAD_STATIC -> {
                    val fieldName = this.readUtf8Value()
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (!field.isStatic) throw IllegalStateException("Field $fieldName is not static")
                    val value = field.getStaticValue(field.type.byteSize)
                    stack.push(value)
                }

                // Load a virtual (instance) field
                // https://spec.shakelang.com/bytecode/instructions#instr-load-virtual
                Opcodes.LOAD_VIRTUAL -> {
                    val fieldName = this.readUtf8Value()
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (field.isStatic) throw IllegalStateException("Field $fieldName is static")
                    val obj = stack.popLong()
                    val value = field.getVirtualValue(obj, field.type.byteSize)
                    stack.push(value)
                }

                // Store a value in a static field
                // https://spec.shakelang.com/bytecode/instructions#instr-store-static
                Opcodes.STORE_STATIC -> {
                    val fieldName = this.readUtf8Value()
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (!field.isStatic) throw IllegalStateException("Field $fieldName is not static")
                    val value = stack.pop(field.type.byteSize)
                    field.setStaticValue(value)
                }

                // Store a value in a virtual (instance) field
                // https://spec.shakelang.com/bytecode/instructions#instr-store-virtual
                Opcodes.STORE_VIRTUAL -> {
                    val fieldName = this.readUtf8Value()
                    val field = classPath.getField(fieldName)
                        ?: throw NullPointerException("Field $fieldName not found")
                    if (field.isStatic) throw IllegalStateException("Field $fieldName is static")
                    val obj = stack.popLong()
                    val value = stack.pop(field.type.byteSize)
                    field.setVirtualValue(obj, value)
                }

                // Instantiate a new array and push its address to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-new-arr
                Opcodes.NEW_ARR -> {
                    val utf8 = this.readUtf8Value()
                    val type = TypeDescriptor.parse(utf8)
                    val size = stack.popInt()

                    val array = malloc.malloc(size.toLong() * type.byteSize + 4)
                    globalMemory.setInt(array, size)
                    for (i in 0 until size * type.byteSize)
                        globalMemory.setByte(array + 4 + i, 0)

                    stack.push(array)
                }

                // Load a value from an array and push it to the stack
                // https://spec.shakelang.com/bytecode/instructions#instr-baload
                // https://spec.shakelang.com/bytecode/instructions#instr-saload
                // https://spec.shakelang.com/bytecode/instructions#instr-iaload
                // https://spec.shakelang.com/bytecode/instructions#instr-laload
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

                // Store a value in an array
                // https://spec.shakelang.com/bytecode/instructions#instr-bastore
                // https://spec.shakelang.com/bytecode/instructions#instr-sastore
                // https://spec.shakelang.com/bytecode/instructions#instr-iastore
                // https://spec.shakelang.com/bytecode/instructions#instr-lastore
                Opcodes.BASTORE -> {
                    val value = stack.pop()
                    val index = stack.popInt()
                    val array = stack.popLong()
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

                // Create a new Object
                // https://spec.shakelang.com/bytecode/instructions#instr-new-obj
                Opcodes.NEW_OBJ -> {
                    // Read the constructor
                    val constructorName = this.readUtf8Value()
                    val constructor =
                        classPath.getMethod(constructorName) ?: throw NullPointerException("Constructor \"$constructorName\" not found")
                    if (!constructor.isConstructor) throw IllegalStateException("Method $constructorName is not a constructor")

                    val clazz = constructor.clazz ?: throw NullPointerException("Class not found")

                    // Create the object in the heap
                    val obj = clazz.createInstanceInMemory()

                    // Read the arguments
                    val argsSize = constructor.parameters.sumOf { it.byteSize }
                    val args = ByteArray(argsSize) {
                        stack.pop()
                    }
                    putFunctionOnStack(constructor, args + obj.toBytes())

                    // push the object to the stack
                    stack.push(obj)
                }
            }
        }
    }
}
