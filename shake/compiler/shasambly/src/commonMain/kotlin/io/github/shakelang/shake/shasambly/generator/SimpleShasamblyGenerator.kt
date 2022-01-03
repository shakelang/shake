@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.generator

typealias SimpleShasamblyGeneratorFunction = SimpleShasamblyGenerator.() -> Unit

class SimpleShasamblyGenerator(generator: SimpleShasamblyGeneratorFunction): ShasamblyGenerator(mutableListOf()) {

    init {
        generator.invoke(this)
    }

    fun opcode(vararg opcodes: ShasamblyOpcode) {
        this.addAll(opcodes)
    }

    fun incrStack(size: Int) = opcode(ShasamblyOpcodeIncrStack(size))
    fun decrStack() = opcode(ShasamblyOpcodeDecrStack())
    fun jumpStatic(target: Int) = opcode(ShasamblyOpcodeJumpStatic(target))
    fun jumpStaticTo(target: Int) = opcode(ShasamblyOpcodeJumpStaticToIndex(target))
    fun jumpDynamic() = opcode(ShasamblyOpcodeJumpDynamic())
    fun jumpIf(target: Int) = opcode(ShasamblyOpcodeJumpIf(target))
    fun jumpIfTo(target: Int) = opcode(ShasamblyOpcodeJumpIfToIndex(target))
    fun invokeNative(address: Short, bytes: ByteArray = byteArrayOf()) = opcode(ShasamblyOpcodeInvokeNative(address, bytes))

    fun getLocalByte(address: Int) = opcode(ShasamblyOpcodeBGetLocal(address))
    fun getLocalShort(address: Int) = opcode(ShasamblyOpcodeSGetLocal(address))
    fun getLocalInt(address: Int) = opcode(ShasamblyOpcodeIGetLocal(address))
    fun getLocalLong(address: Int) = opcode(ShasamblyOpcodeLGetLocal(address))
    fun getLocalFloat(address: Int) = getLocalInt(address)
    fun getLocalDouble(address: Int) = getLocalLong(address)
    fun getLocalBoolean(address: Int) = getLocalByte(address)

    fun b_get_local(address: Int) = getLocalByte(address)
    fun s_get_local(address: Int) = getLocalShort(address)
    fun i_get_local(address: Int) = getLocalInt(address)
    fun l_get_local(address: Int) = getLocalLong(address)
    fun f_get_local(address: Int) = getLocalFloat(address)
    fun d_get_local(address: Int) = getLocalDouble(address)

    fun storeLocalByte(address: Int) = opcode(ShasamblyOpcodeBStoreLocal(address))
    fun storeLocalShort(address: Int) = opcode(ShasamblyOpcodeSStoreLocal(address))
    fun storeLocalInt(address: Int) = opcode(ShasamblyOpcodeIStoreLocal(address))
    fun storeLocalLong(address: Int) = opcode(ShasamblyOpcodeLStoreLocal(address))
    fun storeLocalFloat(address: Int) = storeLocalInt(address)
    fun storeLocalDouble(address: Int) = storeLocalLong(address)
    fun storeLocalBoolean(address: Int) = storeLocalByte(address)

    fun b_store_local(address: Int) = storeLocalByte(address)
    fun s_store_local(address: Int) = storeLocalShort(address)
    fun i_store_local(address: Int) = storeLocalInt(address)
    fun l_store_local(address: Int) = storeLocalLong(address)
    fun f_store_local(address: Int) = storeLocalFloat(address)
    fun d_store_local(address: Int) = storeLocalDouble(address)

    fun pushByte(byte: Byte) = opcode(ShasamblyOpcodeBPush(byte))
    fun pushShort(short: Short) = opcode(ShasamblyOpcodeSPush(short))
    fun pushInt(int: Int) = opcode(ShasamblyOpcodeIPush(int))
    fun pushLong(long: Long) = opcode(ShasamblyOpcodeLPush(long))
    fun pushFloat(float: Float) = opcode(ShasamblyOpcodeIPush(float))
    fun pushDouble(double: Double) = opcode(ShasamblyOpcodeLPush(double))
    fun pushBoolean(boolean: Boolean) = opcode(ShasamblyOpcodeBPush(boolean))

    fun bpush(byte: Byte) = pushByte(byte)
    fun spush(short: Short) = pushShort(short)
    fun ipush(int: Int) = pushInt(int)
    fun lpush(long: Long) = pushLong(long)
    fun fpush(float: Float) = pushFloat(float)
    fun dpush(double: Double) = pushDouble(double)
    fun bpush(boolean: Boolean) = pushBoolean(boolean)

    fun bpush(vararg bytes: Byte) = pushBytes(*bytes)
    fun spush(vararg shorts: Short) = pushShorts(*shorts)
    fun ipush(vararg ints: Int) = pushInts(*ints)
    fun lpush(vararg longs: Long) = pushLongs(*longs)
    fun fpush(vararg floats: Float) = pushFloats(*floats)
    fun dpush(vararg doubles: Double) = pushDoubles(*doubles)
    fun bpush(vararg booleans: Boolean) = pushBooleans(*booleans)

    fun pushBytes(vararg bytes: Byte) = bytes.forEach { pushByte(it) }
    fun pushShorts(vararg shorts: Short) = shorts.forEach { pushShort(it) }
    fun pushInts(vararg ints: Int) = ints.forEach { pushInt(it) }
    fun pushLongs(vararg longs: Long) = longs.forEach { pushLong(it) }
    fun pushFloats(vararg floats: Float) = floats.forEach { pushFloat(it) }
    fun pushDoubles(vararg doubles: Double) = doubles.forEach { pushDouble(it) }
    fun pushBooleans(vararg booleans: Boolean) = booleans.forEach { pushBoolean(it) }

    fun badd() = opcode(ShasamblyOpcodeBAdd())
    fun bsub() = opcode(ShasamblyOpcodeBSub())
    fun bmul() = opcode(ShasamblyOpcodeBMul())
    fun bdiv() = opcode(ShasamblyOpcodeBDiv())
    fun bmod() = opcode(ShasamblyOpcodeBMod())

    fun sadd() = opcode(ShasamblyOpcodeSAdd())
    fun ssub() = opcode(ShasamblyOpcodeSSub())
    fun smul() = opcode(ShasamblyOpcodeSMul())
    fun sdiv() = opcode(ShasamblyOpcodeSDiv())
    fun smod() = opcode(ShasamblyOpcodeSMod())

    fun iadd() = opcode(ShasamblyOpcodeIAdd())
    fun isub() = opcode(ShasamblyOpcodeISub())
    fun imul() = opcode(ShasamblyOpcodeIMul())
    fun idiv() = opcode(ShasamblyOpcodeIDiv())
    fun imod() = opcode(ShasamblyOpcodeIMod())

    fun ladd() = opcode(ShasamblyOpcodeLAdd())
    fun lsub() = opcode(ShasamblyOpcodeLSub())
    fun lmul() = opcode(ShasamblyOpcodeLMul())
    fun ldiv() = opcode(ShasamblyOpcodeLDiv())
    fun lmod() = opcode(ShasamblyOpcodeLMod())

    fun fadd() = opcode(ShasamblyOpcodeFAdd())
    fun fsub() = opcode(ShasamblyOpcodeFSub())
    fun fmul() = opcode(ShasamblyOpcodeFMul())
    fun fdiv() = opcode(ShasamblyOpcodeFDiv())
    fun fmod() = opcode(ShasamblyOpcodeFMod())

    fun dadd() = opcode(ShasamblyOpcodeDAdd())
    fun dsub() = opcode(ShasamblyOpcodeDSub())
    fun dmul() = opcode(ShasamblyOpcodeDMul())
    fun ddiv() = opcode(ShasamblyOpcodeDDiv())
    fun dmod() = opcode(ShasamblyOpcodeDMod())

    fun beq() = opcode(ShasamblyOpcodeBEq())
    fun seq() = opcode(ShasamblyOpcodeSEq())
    fun ieq() = opcode(ShasamblyOpcodeIEq())
    fun leq() = opcode(ShasamblyOpcodeLEq())
    fun feq() = opcode(ShasamblyOpcodeFEq())
    fun deq() = opcode(ShasamblyOpcodeDEq())

    fun bbigger() = opcode(ShasamblyOpcodeBBigger())
    fun sbigger() = opcode(ShasamblyOpcodeSBigger())
    fun ibigger() = opcode(ShasamblyOpcodeIBigger())
    fun lbigger() = opcode(ShasamblyOpcodeLBigger())
    fun fbigger() = opcode(ShasamblyOpcodeFBigger())
    fun dbigger() = opcode(ShasamblyOpcodeDBigger())

    fun bsmaller() = opcode(ShasamblyOpcodeBSmaller())
    fun ssmaller() = opcode(ShasamblyOpcodeSSmaller())
    fun ismaller() = opcode(ShasamblyOpcodeISmaller())
    fun lsmaller() = opcode(ShasamblyOpcodeLSmaller())
    fun fsmaller() = opcode(ShasamblyOpcodeFSmaller())
    fun dsmaller() = opcode(ShasamblyOpcodeDSmaller())

    fun bbiggereq() = opcode(ShasamblyOpcodeBBiggerEq())
    fun sbiggereq() = opcode(ShasamblyOpcodeSBiggerEq())
    fun ibiggereq() = opcode(ShasamblyOpcodeIBiggerEq())
    fun lbiggereq() = opcode(ShasamblyOpcodeLBiggerEq())
    fun fbiggereq() = opcode(ShasamblyOpcodeFBiggerEq())
    fun dbiggereq() = opcode(ShasamblyOpcodeDBiggerEq())

    fun bsmallereq() = opcode(ShasamblyOpcodeBSmallerEq())
    fun ssmallereq() = opcode(ShasamblyOpcodeSSmallerEq())
    fun ismallereq() = opcode(ShasamblyOpcodeISmallerEq())
    fun lsmallereq() = opcode(ShasamblyOpcodeLSmallerEq())
    fun fsmallereq() = opcode(ShasamblyOpcodeFSmallerEq())
    fun dsmallereq() = opcode(ShasamblyOpcodeDSmallerEq())


    fun byteAdd() = badd()
    fun byteSub() = bsub()
    fun byteMul() = bmul()
    fun byteDiv() = bdiv()
    fun byteMod() = bmod()

    fun shortAdd() = sadd()
    fun shortSub() = ssub()
    fun shortMul() = smul()
    fun shortDiv() = sdiv()
    fun shortMod() = smod()

    fun intAdd() = iadd()
    fun intSub() = isub()
    fun intMul() = imul()
    fun intDiv() = idiv()
    fun intMod() = imod()

    fun longAdd() = ladd()
    fun longSub() = lsub()
    fun longMul() = lmul()
    fun longDiv() = ldiv()
    fun longMod() = lmod()

    fun floatAdd() = fadd()
    fun floatSub() = fsub()
    fun floatMul() = fmul()
    fun floatDiv() = fdiv()
    fun floatMod() = fmod()

    fun doubleAdd() = dadd()
    fun doubleSub() = dsub()
    fun doubleMul() = dmul()
    fun doubleDiv() = ddiv()
    fun doubleMod() = dmod()

    fun byteEq() = beq()
    fun shortEq() = seq()
    fun intEq() = ieq()
    fun longEq() = leq()
    fun floatEq() = feq()
    fun doubleEq() = deq()

    fun byteEquals() = beq()
    fun shortEquals() = seq()
    fun intEquals() = ieq()
    fun longEquals() = leq()
    fun floatEquals() = feq()
    fun doubleEquals() = deq()

    fun byteBigger() = bbigger()
    fun shortBigger() = sbigger()
    fun intBigger() = ibigger()
    fun longBigger() = lbigger()
    fun floatBigger() = fbigger()
    fun doubleBigger() = dbigger()

    fun byteSmaller() = bsmaller()
    fun shortSmaller() = ssmaller()
    fun intSmaller() = ismaller()
    fun longSmaller() = lsmaller()
    fun floatSmaller() = fsmaller()
    fun doubleSmaller() = dsmaller()

    fun byteBiggerEq() = bbiggereq()
    fun shortBiggerEq() = sbiggereq()
    fun intBiggerEq() = ibiggereq()
    fun longBiggerEq() = lbiggereq()
    fun floatBiggerEq() = fbiggereq()
    fun doubleBiggerEq() = dbiggereq()

    fun byteBiggerEquals() = bbiggereq()
    fun shortBiggerEquals() = sbiggereq()
    fun intBiggerEquals() = ibiggereq()
    fun longBiggerEquals() = lbiggereq()
    fun floatBiggerEquals() = fbiggereq()
    fun doubleBiggerEquals() = dbiggereq()

    fun byteSmallerEq() = bsmallereq()
    fun shortSmallerEq() = ssmallereq()
    fun intSmallerEq() = ismallereq()
    fun longSmallerEq() = lsmallereq()
    fun floatSmallerEq() = fsmallereq()
    fun doubleSmallerEq() = dsmallereq()

    fun byteSmallerEquals() = bsmallereq()
    fun shortSmallerEquals() = ssmallereq()
    fun intSmallerEquals() = ismallereq()
    fun longSmallerEquals() = lsmallereq()
    fun floatSmallerEquals() = fsmallereq()
    fun doubleSmallerEquals() = dsmallereq()

}

fun shasambly(f: SimpleShasamblyGeneratorFunction) = SimpleShasamblyGenerator(f).generate()