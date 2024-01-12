package com.shakelang.shake.bytecode.interpreter.natives

val natives: List<ShakeBytecodeNative> = listOf(

    native("shake/lang/print(B)V") {
        val value = stack.pop()
        print(value)
    },

    native("shake/lang/print(b)V") {
        val value = stack.popUByte()
        print(value)
    },

    native("shake/lang/print(S)V") {
        val value = stack.popShort()
        print(value)
    },

    native("shake/lang/print(s)V") {
        val value = stack.popUShort()
        print(value)
    },

    native("shake/lang/print(I)V") {
        val value = stack.popInt()
        print(value)
    },

    native("shake/lang/print(i)V") {
        val value = stack.popUInt()
        print(value)
    },

    native("shake/lang/print(J)V") {
        val value = stack.popLong()
        print(value)
    },

    native("shake/lang/print(j)V") {
        val value = stack.popULong()
        print(value)
    },

    native("shake/lang/print(F)V") {
        val value = stack.pop()
        print(value)
    },

    native("shake/lang/print(D)V") {
        val value = stack.pop()
        print(value)
    },

    native("shake/lang/print(Z)V") {
        val value = stack.pop()
        print(value != 0.toByte())
    },

    native("shake/lang/print(C)V") {
        val value = stack.popShort().toInt().toChar()
        print(value)
    },

    native("shake/lang/println()V") {
        println()
    },

    native("shake/lang/println(B)V") {
        val value = stack.popUByte()
        println(value)
    },

    native("shake/lang/println(b)V") {
        val value = stack.popUByte()
        println(value)
    },

    native("shake/lang/println(S)V") {
        val value = stack.popShort()
        println(value)
    },

    native("shake/lang/println(s)V") {
        val value = stack.popUShort()
        println(value)
    },

    native("shake/lang/println(I)V") {
        val value = stack.popInt()
        println(value)
    },

    native("shake/lang/println(i)V") {
        val value = stack.popUInt()
        println(value)
    },

    native("shake/lang/println(J)V") {
        val value = stack.popLong()
        println(value)
    },

    native("shake/lang/println(j)V") {
        val value = stack.popULong()
        println(value)
    },

    native("shake/lang/println(F)V") {
        val value = stack.pop()
        println(value)
    },

    native("shake/lang/println(D)V") {
        val value = stack.pop()
        println(value)
    },

    native("shake/lang/println(Z)V") {
        val value = stack.pop()
        println(value != 0.toByte())
    },

    native("shake/lang/println(C)V") {
        val value = stack.popShort().toInt().toChar()
        println(value)
    },
)
