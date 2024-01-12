package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.shake.bytecode.interpreter.ByteStack
import com.shakelang.shake.bytecode.interpreter.ShakeCallStackElement

class ShakeBytecodeNative(

    val descriptor: String,
    val function: ShakeBytecodeNative.() -> Unit,

) : ShakeCallStackElement {

    override var returnData: ByteArray = ByteArray(0)
        private set
    override var finished: Boolean = false
        private set

    override val stack = ByteStack()
    override val locals: ByteArray
        get() = ByteArray(0)

    fun returnData(data: ByteArray) {
        returnData = data
        finished = true
    }

    private fun run() {
        function()
        finished = true
    }

    override fun tick() {
        if (finished) return
        run()
    }

    override fun tick(times: Int): Int {
        if (finished) return 0
        run()
        return 1
    }
}

fun native(descriptor: String, function: ShakeBytecodeNative.() -> Unit) =
    ShakeBytecodeNative(descriptor, function)
