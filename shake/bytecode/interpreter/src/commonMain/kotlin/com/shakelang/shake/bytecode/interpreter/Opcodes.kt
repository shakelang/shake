package com.shakelang.shake.bytecode.interpreter

object Opcodes {

    /**
     * No instruction
     */
    const val NOP: Byte = 0 // Syntax: NOP ; No operation

    // Push instructions
    // Hardcoded values directly from the bytecode
    // No need for unsigned or floating point values for push operation (as they have the same sizes)

    /**
     * Push a byte onto the stack
     * @example Syntax: `BPUSH` <u1 byte>
     */
    const val BPUSH: Byte = 1 // Syntax: BPUSH u1 byte ; Push a byte onto the stack

    /**
     * Push a short onto the stack
     * @example Syntax: `SPUSH` <u2 short>
     */
    const val SPUSH: Byte = 2 // Syntax: SPUSH u2 short ; Push a short onto the stack

    /**
     * Push an int onto the stack
     * @example Syntax: `IPUSH` <u4 int>
     */
    const val IPUSH: Byte = 3 // Syntax: IPUSH u4 int ; Push an int onto the stack

    /**
     * Push a long onto the stack
     * @example Syntax: `LPUSH` <u8 long>
     */
    const val LPUSH: Byte = 4 // Syntax: LPUSH u8 long ; Push a long onto the stack


    // Load instructions
    // No need for unsigned or floating point values for load operation (as they have the same sizes)

    /**
     * Load a byte from a local variable onto the stack
     * @example Syntax: `BLOAD` <u2 variable>
     */
    const val BLOAD: Byte = 5 // Syntax: BLOAD u2 variable ; Load a byte from a local variable onto the stack

    /**
     * Load a short from a local variable onto the stack
     * @example Syntax: `SLOAD` <u2 variable>
     */
    const val SLOAD: Byte = 6 // Syntax: SLOAD u2 variable ; Load a short from a local variable onto the stack

    /**
     * Load an int from a local variable onto the stack
     * @example Syntax: `ILOAD` <u2 variable>
     */
    const val ILOAD: Byte = 7 // Syntax: ILOAD u2 variable ; Load an int from a local variable onto the stack

    /**
     * Load a long from a local variable onto the stack
     * @example Syntax: `LLOAD` <u2 variable>
     */
    const val LLOAD: Byte = 8 // Syntax: LLOAD u2 variable ; Load a long from a local v ariable onto the stack


    // Store instructions
    // No need for unsigned or floating point values for store operation (as they have the same sizes)

    /**
     * Store a byte from the stack into a local variable
     * @example Syntax: `BSTORE` <u2 variable>
     */
    const val BSTORE: Byte = 9 // Syntax: BSTORE u2 variable ; Store a byte from the stack into a local variable

    /**
     * Store a short from the stack into a local variable
     * @example Syntax: `SSTORE` <u2 variable>
     */
    const val SSTORE: Byte = 10 // Syntax: SSTORE u2 variable ; Store a short from the stack into a local variable

    /**
     * Store an int from the stack into a local variable
     * @example Syntax: `ISTORE` <u2 variable>
     */
    const val ISTORE: Byte = 11 // Syntax: ISTORE u2 variable ; Store an int from the stack into a local variable

    /**
     * Store a long from the stack into a local variable
     * @example Syntax: `LSTORE` <u2 variable>
     */
    const val LSTORE: Byte = 12 // Syntax: LSTORE u2 variable ; Store a long from the stack into a local variable

    // Add instructions
    // Unsigned adding is not needed as it is the same as signed adding
    /**
     * Add two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BADD`
     */
    const val BADD: Byte = 13 // Syntax: BADD ; Add two bytes

    /**
     * Add two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SADD`
     */
    const val SADD: Byte = 14 // Syntax: SADD ; Add two shorts

    /**
     * Add two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IADD`
     */
    const val IADD: Byte = 15 // Syntax: IADD ; Add two ints

    /**
     * Add two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LADD`
     */
    const val LADD: Byte = 16 // Syntax: LADD ; Add two longs

    /**
     * Add two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FADD`
     */
    const val FADD: Byte = 17 // Syntax: FADD ; Add two floats

    /**
     * Add two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DADD`
     */
    const val DADD: Byte = 18 // Syntax: DADD ; Add two doubles


    // Subtract instructions
    // Unsigned subtracting is not needed as it is the same as signed subtracting

    /**
     * Subtract two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BSUB`
     */
    const val BSUB: Byte = 19 // Syntax: BSUB ; Subtract two bytes

    /**
     * Subtract two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SSUB`
     */
    const val SSUB: Byte = 20 // Syntax: SSUB ; Subtract two shorts

    /**
     * Subtract two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `ISUB`
     */
    const val ISUB: Byte = 21 // Syntax: ISUB ; Subtract two ints

    /**
     * Subtract two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LSUB`
     */
    const val LSUB: Byte = 22 // Syntax: LSUB ; Subtract two longs

    /**
     * Subtract two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBSUB`
     */
    const val UBSUB: Byte = 23 // Syntax: UBSUB ; Subtract two unsigned bytes

    /**
     * Subtract two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USSUB`
     */
    const val USSUB: Byte = 24 // Syntax: USSUB ; Subtract two unsigned shorts

    /**
     * Subtract two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UISUB`
     */
    const val UISUB: Byte = 25 // Syntax: UISUB ; Subtract two unsigned ints

    /**
     * Subtract two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULSUB`
     */
    const val ULSUB: Byte = 26 // Syntax: ULSUB ; Subtract two unsigned longs

    /**
     * Subtract two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FSUB`
     */
    const val FSUB: Byte = 27 // Syntax: FSUB ; Subtract two floats

    /**
     * Subtract two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DSUB`
     */
    const val DSUB: Byte = 28 // Syntax: DSUB ; Subtract two doubles

    // Multiply instructions
    /**
     * Multiply two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BMUL`
     */
    const val BMUL: Byte = 30 // Syntax: BMUL ; Multiply two bytes

    /**
     * Multiply two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SMUL`
     */
    const val SMUL: Byte = 31 // Syntax: SMUL ; Multiply two shorts

    /**
     * Multiply two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IMUL`
     */
    const val IMUL: Byte = 32 // Syntax: IMUL ; Multiply two ints

    /**
     * Multiply two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LMUL`
     */
    const val LMUL: Byte = 33 // Syntax: LMUL ; Multiply two longs

    /**
     * Multiply two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBMUL`
     */
    const val UBMUL: Byte = 34 // Syntax: UBUL ; Multiply two unsigned bytes

    /**
     * Multiply two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USMUL`
     */
    const val USMUL: Byte = 35 // Syntax: USMUL ; Multiply two unsigned shorts

    /**
     * Multiply two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UIMUL`
     */
    const val UIMUL: Byte = 36 // Syntax: UIMUL ; Multiply two unsigned ints

    /**
     * Multiply two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULMUL`
     */
    const val ULMUL: Byte = 37 // Syntax: ULMUL ; Multiply two unsigned longs

    /**
     * Multiply two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FMUL`
     */
    const val FMUL: Byte = 38 // Syntax: FMUL ; Multiply two floats

    /**
     * Multiply two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DMUL`
     */
    const val DMUL: Byte = 39 // Syntax: DMUL ; Multiply two doubles

    // Divide instructions
    /**
     * Divide two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BDIV`
     */
    const val BDIV: Byte = 40 // Syntax: BDIV ; Divide two bytes

    /**
     * Divide two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SDIV`
     */
    const val SDIV: Byte = 41 // Syntax: SDIV ; Divide two shorts

    /**
     * Divide two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IDIV`
     */
    const val IDIV: Byte = 42 // Syntax: IDIV ; Divide two ints

    /**
     * Divide two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LDIV`
     */
    const val LDIV: Byte = 43 // Syntax: LDIV ; Divide two longs

    /**
     * Divide two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBDIV`
     */
    const val UBDIV: Byte = 44 // Syntax: UBDIV ; Divide two unsigned bytes

    /**
     * Divide two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USDIV`
     */
    const val USDIV: Byte = 45 // Syntax: USDIV ; Divide two unsigned shorts

    /**
     * Divide two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UIDIV`
     */
    const val UIDIV: Byte = 46 // Syntax: UIDIV ; Divide two unsigned ints

    /**
     * Divide two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULDIV`
     */
    const val ULDIV: Byte = 47 // Syntax: ULDIV ; Divide two unsigned longs

    /**
     * Divide two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FDIV`
     */
    const val FDIV: Byte = 48 // Syntax: FDIV ; Divide two floats

    /**
     * Divide two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DDIV`
     */
    const val DDIV: Byte = 49 // Syntax: DDIV ; Divide two doubles


    // Modulo instructions

    /**
     * Modulo two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BMOD`
     */
    const val BMOD: Byte = 50 // Syntax: BMOD ; Modulo two bytes

    /**
     * Modulo two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SMOD`
     */
    const val SMOD: Byte = 51 // Syntax: SMOD ; Modulo two shorts

    /**
     * Modulo two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IMOD`
     */
    const val IMOD: Byte = 52 // Syntax: IMOD ; Modulo two ints

    /**
     * Modulo two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LMOD`
     */
    const val LMOD: Byte = 53 // Syntax: LMOD ; Modulo two longs

    /**
     * Modulo two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBMOD`
     */
    const val UBMOD: Byte = 54 // Syntax: UBMOD ; Modulo two unsigned bytes

    /**
     * Modulo two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USMOD`
     */
    const val USMOD: Byte = 55 // Syntax: USMOD ; Modulo two unsigned shorts

    /**
     * Modulo two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UIMOD`
     */
    const val UIMOD: Byte = 56 // Syntax: UIMOD ; Modulo two unsigned ints

    /**
     * Modulo two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULMOD`
     */
    const val ULMOD: Byte = 57 // Syntax: ULMOD ; Modulo two unsigned longs

    /**
     * Modulo two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FMOD`
     */
    const val FMOD: Byte = 58 // Syntax: FMOD ; Modulo two floats

    /**
     * Modulo two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DMOD`
     */
    const val DMOD: Byte = 59 // Syntax: DMOD ; Modulo two doubles


    // Negate instructions

    /**
     * Negate the byte on top of the stack
     * @example Syntax: `BNEG`
     */
    const val BNEG: Byte = 60 // Syntax: BNEG ; Negate a byte

    /**
     * Negate the short on top of the stack
     * @example Syntax: `SNEG`
     */
    const val SNEG: Byte = 61 // Syntax: SNEG ; Negate a short

    /**
     * Negate the int on top of the stack
     * @example Syntax: `INEG`
     */
    const val INEG: Byte = 62 // Syntax: INEG ; Negate an int

    /**
     * Negate the long on top of the stack
     * @example Syntax: `LNEG`
     */
    const val LNEG: Byte = 63 // Syntax: LNEG ; Negate a long

    /**
     * Negate the float on top of the stack
     * @example Syntax: `FNEG`
     */
    const val FNEG: Byte = 64 // Syntax: FNEG ; Negate a float

    /**
     * Negate the double on top of the stack
     * @example Syntax: `DNEG`
     */
    const val DNEG: Byte = 65 // Syntax: DNEG ; Negate a double


    // Bitwise And instructions

    /**
     * Bitwise AND the two bytes on top of the stack
     * @example Syntax: `BAND`
     */
    const val BAND: Byte = 66 // Syntax: BAND ; Bitwise AND two bytes

    /**
     * Bitwise AND two shorts (the first 4 bytes)
     * @example Syntax: `SAND`
     */
    const val SAND: Byte = 67 // Syntax: SAND ; Bitwise AND two shorts

    /**
     * Bitwise AND two ints (the first 8 bytes)
     * @example Syntax: `IAND`
     */
    const val IAND: Byte = 68 // Syntax: IAND ; Bitwise AND two ints

    /**
     * Bitwise AND two longs (the first 16 bytes)
     * @example Syntax: `LAND`
     */
    const val LAND: Byte = 69 // Syntax: LAND ; Bitwise AND two longs


    // Bitwise OR instructions

    /**
     * Bitwise OR two bytes on top of the stack
     * @example Syntax: `BOR`
     */
    const val BOR: Byte = 70 // Syntax: BOR ; Bitwise OR two bytes

    /**
     * Bitwise OR two shorts (the first 4 bytes)
     * @example Syntax: `SOR`
     */
    const val SOR: Byte = 71 // Syntax: SOR ; Bitwise OR two shorts

    /**
     * Bitwise OR two ints (the first 8 bytes)
     * @example Syntax: `IOR`
     */
    const val IOR: Byte = 72 // Syntax: IOR ; Bitwise OR two ints

    /**
     * Bitwise OR two longs (the first 16 bytes)
     * @example Syntax: `LOR`
     */
    const val LOR: Byte = 73 // Syntax: LOR ; Bitwise OR two longs


    // Bitwise XOR instructions

    /**
     * Bitwise XOR two bytes on top of the stack
     * @example Syntax: `BXOR`
     */
    const val BXOR: Byte = 74 // Syntax: BXOR ; Bitwise XOR two bytes

    /**
     * Bitwise XOR two shorts (the first 4 bytes)
     * @example Syntax: `SXOR`
     */
    const val SXOR: Byte = 75 // Syntax: SXOR ; Bitwise XOR two shorts

    /**
     * Bitwise XOR two ints (the first 8 bytes)
     * @example Syntax: `IXOR`
     */
    const val IXOR: Byte = 76 // Syntax: IXOR ; Bitwise XOR two ints

    /**
     * Bitwise XOR two longs (the first 16 bytes)
     * @example Syntax: `LXOR`
     */
    const val LXOR: Byte = 77 // Syntax: LXOR ; Bitwise XOR two longs


    // Bitwise NOT

    /**
     * Bitwise NOT the top byte on the stack
     * @example Syntax `BNOT`
     */
    const val BNOT : Byte = 78

    /**
     * Bitwise NOT the top short on the stack
     * @example Syntax `SNOT`
     */
    const val SNOT : Byte = 79

    /**
     * Bitwise NOT the top int on the stack
     * @example Syntax `INOT`
     */
    const val INOT : Byte = 80

    /**
     * Bitwise NOT the top long on the stack
     * @example Syntax `LNOT`
     */
    const val LNOT : Byte = 81



    // Shift instructions

    /**
     * Bitwise shift left a byte (the top byte is the amount, the second byte is the value to shift)
     * @example Syntax: `BSHL`
     */
    const val BSHL: Byte = 82 // Syntax: BSHL ; Bitwise shift left a byte

    /**
     * Bitwise shift left a short (the top byte is the amount, the second short is the value to shift)
     * @example Syntax: `SSHL`
     */
    const val SSHL: Byte = 83 // Syntax: SSHL ; Bitwise shift left a short

    /**
     * Bitwise shift left an int (the top byte is the amount, the second int is the value to shift)
     * @example Syntax: `ISHL`
     */
    const val ISHL: Byte = 84 // Syntax: ISHL ; Bitwise shift left an int

    /**
     * Bitwise shift left a long (the top byte is the amount, the second long is the value to shift)
     * @example Syntax: `LSHL`
     */
    const val LSHL: Byte = 85 // Syntax: LSHL ; Bitwise shift left a long


    // Shift right instructions

    /**
     * Bitwise shift right a byte (the top byte is the amount, the second byte is the value to shift)
     * @example Syntax: `BSHR`
     */
    const val BSHR: Byte = 86 // Syntax: BSHR ; Bitwise shift right a byte

    /**
     * Bitwise shift right a short (the top byte is the amount, the second short is the value to shift)
     * @example Syntax: `SSHR`
     */
    const val SSHR: Byte = 87 // Syntax: SSHR ; Bitwise shift right a short

    /**
     * Bitwise shift right an int (the top byte is the amount, the second int is the value to shift)
     * @example Syntax: `ISHR`
     */
    const val ISHR: Byte = 88 // Syntax: ISHR ; Bitwise shift right an int

    /**
     * Bitwise shift right a long (the top byte is the amount, the second long is the value to shift)
     * @example Syntax: `LSHR`
     */
    const val LSHR: Byte = 89 // Syntax: LSHR ; Bitwise shift right a long


    // Shift right unsigned instructions

    /**
     * Bitwise shift right unsigned a byte (the top byte is the amount, the second byte is the value to shift)
     * @example Syntax: `BSHRU`
     */
    const val BSHRU: Byte = 90 // Syntax: BSHRU ; Bitwise shift right unsigned a byte

    /**
     * Bitwise shift right unsigned a short (the top byte is the amount, the second short is the value to shift)
     * @example Syntax: `SSHRU`
     */
    const val SSHRU: Byte = 91 // Syntax: SSHRU ; Bitwise shift right unsigned a short

    /**
     * Bitwise shift right unsigned an int (the top byte is the amount, the second int is the value to shift)
     * @example Syntax: `ISHRU`
     */
    const val ISHRU: Byte = 92 // Syntax: ISHRU ; Bitwise shift right unsigned an int

    /**
     * Bitwise shift right unsigned a long (the top byte is the amount, the second long is the value to shift)
     * @example Syntax: `LSHRU`
     */
    const val LSHRU: Byte = 93 // Syntax: LSHRU ; Bitwise shift right unsigned a long


    // Increment instructions

    /**
     * Increment a byte
     * @example Syntax: `BINC`
     */
    const val BINC: Byte = 94 // Syntax: BINC ; Increment a byte

    /**
     * Increment a short
     * @example Syntax: `SINC`
     */
    const val SINC: Byte = 95 // Syntax: SINC ; Increment a short

    /**
     * Increment an int
     * @example Syntax: `IINC`
     */
    const val IINC: Byte = 96 // Syntax: IINC ; Increment an int

    /**
     * Increment a long
     * @example Syntax: `LINC`
     */
    const val LINC: Byte = 97 // Syntax: LINC ; Increment a long

    /**
     * Increment a float
     * @example Syntax: `FINC`
     */
    const val FINC: Byte = 98 // Syntax: FINC ; Increment a float

    /**
     * Increment a double
     * @example Syntax: `DINC`
     */
    const val DINC: Byte = 99 // Syntax: DINC ; Increment a double


    // Decrement instructions

    /**
     * Decrement a byte
     * @example Syntax: `BDEC`
     */
    const val BDEC: Byte = 100 // Syntax: BDEC ; Decrement a byte

    /**
     * Decrement a short
     * @example Syntax: `SDEC`
     */
    const val SDEC: Byte = 101 // Syntax: SDEC ; Decrement a short

    /**
     * Decrement an int
     * @example Syntax: `IDEC`
     */
    const val IDEC: Byte = 102 // Syntax: IDEC ; Decrement an int

    /**
     * Decrement a long
     * @example Syntax: `LDEC`
     */
    const val LDEC: Byte = 103 // Syntax: LDEC ; Decrement a long

    /**
     * Decrement a float
     * @example Syntax: `FDEC`
     */
    const val FDEC: Byte = 104 // Syntax: FDEC ; Decrement a float

    /**
     * Decrement a double
     * @example Syntax: `DDEC`
     */
    const val DDEC: Byte = 105 // Syntax: DDEC ; Decrement a double


    // Compare instructions

    /**
     * Compare two bytes
     * @example Syntax: `BCMP`
     */
    const val BCMP: Byte = 106 // Syntax: BCMP ; Compare two bytes

    /**
     * Compare two shorts
     * @example Syntax: `SCMP`
     */
    const val SCMP: Byte = 107 // Syntax: SCMP ; Compare two shorts

    /**
     * Compare two ints
     * @example Syntax: `ICMP`
     */
    const val ICMP: Byte = 108 // Syntax: ICMP ; Compare two ints

    /**
     * Compare two longs
     * @example Syntax: `LCMP`
     */
    const val LCMP: Byte = 109 // Syntax: LCMP ; Compare two longs

    /**
     * Compare two floats
     * @example Syntax: `FCMP`
     */
    const val FCMP: Byte = 110 // Syntax: FCMP ; Compare two floats

    /**
     * Compare two doubles
     * @example Syntax: `DCMP`
     */
    const val DCMP: Byte = 111 // Syntax: DCMP ; Compare two doubles


    /**
     * Primitive cast operation. The top element of the stack will be cast.
     * @example Syntax `PCAST` <u1 cast-type>
     */
    const val PCAST: Byte = 112 // Syntax: PCAST ; Pop a value from the stack and cast it to a primitive type
}