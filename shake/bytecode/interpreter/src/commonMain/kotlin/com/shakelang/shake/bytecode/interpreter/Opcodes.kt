package com.shakelang.shake.bytecode.interpreter

object Opcodes {

    val NOP: Byte = 0 // Syntax: NOP ; No operation

    // Push instructions
    // Hardcoded values directly from the bytecode
    // No need for unsigned or floating point values for push operation (as they have the same sizes)
    val BPUSH: Byte = 1 // Syntax: BPUSH u1 byte ; Push a byte onto the stack
    val SPUSH: Byte = 2 // Syntax: SPUSH u2 short ; Push a short onto the stack
    val IPUSH: Byte = 3 // Syntax: IPUSH u4 int ; Push an int onto the stack
    val LPUSH: Byte = 4 // Syntax: LPUSH u8 long ; Push a long onto the stack

    // Load instructions
    // No need for unsigned or floating point values for load operation (as they have the same sizes)
    val BLOAD: Byte = 5 // Syntax: BLOAD u2 variable ; Load a byte from a local variable onto the stack
    val SLOAD: Byte = 6 // Syntax: SLOAD u2 variable ; Load a short from a local variable onto the stack
    val ILOAD: Byte = 7 // Syntax: ILOAD u2 variable ; Load an int from a local variable onto the stack
    val LLOAD: Byte = 8 // Syntax: LLOAD u2 variable ; Load a long from a local v ariable onto the stack

    // Store instructions
    // No need for unsigned or floating point values for store operation (as they have the same sizes)
    val BSTORE: Byte = 9 // Syntax: BSTORE u2 variable ; Store a byte from the stack into a local variable
    val SSTORE: Byte = 10 // Syntax: SSTORE u2 variable ; Store a short from the stack into a local variable
    val ISTORE: Byte = 11 // Syntax: ISTORE u2 variable ; Store an int from the stack into a local variable
    val LSTORE: Byte = 12 // Syntax: LSTORE u2 variable ; Store a long from the stack into a local variable

    // Add instructions
    // Unsigned adding is not needed as it is the same as signed adding
    val BADD: Byte = 13 // Syntax: BADD ; Add two bytes
    val SADD: Byte = 14 // Syntax: SADD ; Add two shorts
    val IADD: Byte = 15 // Syntax: IADD ; Add two ints
    val LADD: Byte = 16 // Syntax: LADD ; Add two longs
    val FADD: Byte = 17 // Syntax: FADD ; Add two floats
    val DADD: Byte = 18 // Syntax: DADD ; Add two doubles

    // Subtract instructions
    // Unsigned subtracting is not needed as it is the same as signed subtracting
    val BSUB: Byte = 19 // Syntax: BSUB ; Subtract two bytes
    val SSUB: Byte = 20 // Syntax: SSUB ; Subtract two shorts
    val ISUB: Byte = 21 // Syntax: ISUB ; Subtract two ints
    val LSUB: Byte = 22 // Syntax: LSUB ; Subtract two longs
    val UBSUB: Byte = 23 // Syntax: UBSUB ; Subtract two unsigned bytes
    val USSUB: Byte = 24 // Syntax: USSUB ; Subtract two unsigned shorts
    val UISUB: Byte = 25 // Syntax: UISUB ; Subtract two unsigned ints
    val ULSUB: Byte = 26 // Syntax: ULSUB ; Subtract two unsigned longs
    val FSUB: Byte = 27 // Syntax: FSUB ; Subtract two floats
    val DSUB: Byte = 28 // Syntax: DSUB ; Subtract two doubles

    // Multiply instructions
    val BMUL: Byte = 30 // Syntax: BMUL ; Multiply two bytes
    val SMUL: Byte = 31 // Syntax: SMUL ; Multiply two shorts
    val IMUL: Byte = 32 // Syntax: IMUL ; Multiply two ints
    val LMUL: Byte = 33 // Syntax: LMUL ; Multiply two longs
    val UBMUL: Byte = 34 // Syntax: UBUL ; Multiply two unsigned bytes
    val USMUL: Byte = 35 // Syntax: USMUL ; Multiply two unsigned shorts
    val UIMUL: Byte = 36 // Syntax: UIMUL ; Multiply two unsigned ints
    val ULMUL: Byte = 37 // Syntax: ULMUL ; Multiply two unsigned longs
    val FMUL: Byte = 38 // Syntax: FMUL ; Multiply two floats
    val DMUL: Byte = 39 // Syntax: DMUL ; Multiply two doubles

    // Divide instructions
    val BDIV: Byte = 40 // Syntax: BDIV ; Divide two bytes
    val SDIV: Byte = 41 // Syntax: SDIV ; Divide two shorts
    val IDIV: Byte = 42 // Syntax: IDIV ; Divide two ints
    val LDIV: Byte = 43 // Syntax: LDIV ; Divide two longs
    val UBDIV: Byte = 44 // Syntax: UBDIV ; Divide two unsigned bytes
    val USDIV: Byte = 45 // Syntax: USDIV ; Divide two unsigned shorts
    val UIDIV: Byte = 46 // Syntax: UIDIV ; Divide two unsigned ints
    val ULDIV: Byte = 47 // Syntax: ULDIV ; Divide two unsigned longs
    val FDIV: Byte = 48 // Syntax: FDIV ; Divide two floats
    val DDIV: Byte = 49 // Syntax: DDIV ; Divide two doubles

    // Modulo instructions
    val BMOD: Byte = 50 // Syntax: BMOD ; Modulo two bytes
    val SMOD: Byte = 51 // Syntax: SMOD ; Modulo two shorts
    val IMOD: Byte = 52 // Syntax: IMOD ; Modulo two ints
    val LMOD: Byte = 53 // Syntax: LMOD ; Modulo two longs
    val UBMOD: Byte = 54 // Syntax: UBMOD ; Modulo two unsigned bytes
    val USMOD: Byte = 55 // Syntax: USMOD ; Modulo two unsigned shorts
    val UIMOD: Byte = 56 // Syntax: UIMOD ; Modulo two unsigned ints
    val ULMOD: Byte = 57 // Syntax: ULMOD ; Modulo two unsigned longs
    val FMOD: Byte = 58 // Syntax: FMOD ; Modulo two floats
    val DMOD: Byte = 59 // Syntax: DMOD ; Modulo two doubles

    // Bitwise instructions
    val BAND: Byte = 60 // Syntax: BAND ; Bitwise AND two bytes
    val SAND: Byte = 61 // Syntax: SAND ; Bitwise AND two shorts
    val IAND: Byte = 62 // Syntax: IAND ; Bitwise AND two ints
    val LAND: Byte = 63 // Syntax: LAND ; Bitwise AND two longs

    val BOR: Byte = 64 // Syntax: BOR ; Bitwise OR two bytes
    val SOR: Byte = 65 // Syntax: SOR ; Bitwise OR two shorts
    val IOR: Byte = 66 // Syntax: IOR ; Bitwise OR two ints
    val LOR: Byte = 67 // Syntax: LOR ; Bitwise OR two longs

    val BXOR: Byte = 68 // Syntax: BXOR ; Bitwise XOR two bytes
    val SXOR: Byte = 69 // Syntax: SXOR ; Bitwise XOR two shorts
    val IXOR: Byte = 70 // Syntax: IXOR ; Bitwise XOR two ints
    val LXOR: Byte = 71 // Syntax: LXOR ; Bitwise XOR two longs

    val BNOT: Byte = 72 // Syntax: BNOT ; Bitwise NOT a byte
    val SNOT: Byte = 73 // Syntax: SNOT ; Bitwise NOT a short
    val INOT: Byte = 74 // Syntax: INOT ; Bitwise NOT an int
    val LNOT: Byte = 75 // Syntax: LNOT ; Bitwise NOT a long

    val BSHL: Byte = 76 // Syntax: BSHL ; Bitwise shift left a byte
    val SSHL: Byte = 77 // Syntax: SSHL ; Bitwise shift left a short
    val ISHL: Byte = 78 // Syntax: ISHL ; Bitwise shift left an int
    val LSHL: Byte = 79 // Syntax: LSHL ; Bitwise shift left a long

    val BSHR: Byte = 80 // Syntax: BSHR ; Bitwise shift right a byte
    val SSHR: Byte = 81 // Syntax: SSHR ; Bitwise shift right a short
    val ISHR: Byte = 82 // Syntax: ISHR ; Bitwise shift right an int
    val LSHR: Byte = 83 // Syntax: LSHR ; Bitwise shift right a long

    val BSHRU: Byte = 84 // Syntax: BSHRU ; Bitwise shift right unsigned a byte
    val SSHRU: Byte = 85 // Syntax: SSHRU ; Bitwise shift right unsigned a short
    val ISHRU: Byte = 86 // Syntax: ISHRU ; Bitwise shift right unsigned an int
    val LSHRU: Byte = 87 // Syntax: LSHRU ; Bitwise shift right unsigned a long

    val PCAST: Byte = 88 // Syntax: PCAST ; Pop a value from the stack and cast it to a primitive type
}

object PCast {

    val BYTE: Byte = 0
    val SHORT: Byte = 1
    val INT: Byte = 2
    val LONG: Byte = 3
    val UBYTE: Byte = 4
    val USHORT: Byte = 5
    val UINT: Byte = 6
    val ULONG: Byte = 7
    val FLOAT: Byte = 8
    val DOUBLE: Byte = 9

}