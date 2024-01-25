package com.shakelang.shake.bytecode.interpreter

object Opcodes {

    /**
     * No instruction
     *
     * Value: `0x00`
     */
    const val NOP: Byte = 0x00

    // Push instructions
    // Hardcoded values directly from the bytecode
    // No need for unsigned or floating point values for push operation (as they have the same sizes)

    /**
     * Push a byte onto the stack
     *
     * Value: `0x01`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bpush)
     *
     * @example Syntax: `BPUSH` <u1 byte>
     */
    const val BPUSH: Byte = 0x01

    /**
     * Push a short onto the stack
     *
     * Value: `0x02`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-spush)
     *
     * @example Syntax: `SPUSH` <u2 short>
     */
    const val SPUSH: Byte = 0x02

    /**
     * Push an int onto the stack
     *
     * Value: `0x03`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ipush)
     *
     * @example Syntax: `IPUSH` <u4 int>
     */
    const val IPUSH: Byte = 0x03

    /**
     * Push a long onto the stack
     *
     * Value: `0x04`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lpush)
     *
     * @example Syntax: `LPUSH` <u8 long>
     */
    const val LPUSH: Byte = 0x04

    // Load instructions
    // No need for unsigned or floating point values for load operation (as they have the same sizes)

    /**
     * Load a byte from a local variable onto the stack
     *
     * Value: `0x05`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bload)
     *
     * @example Syntax: `BLOAD` <u2 variable>
     */
    const val BLOAD: Byte = 0x05

    /**
     * Load a short from a local variable onto the stack
     *
     * Value: `0x06`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sload)
     *
     * @example Syntax: `SLOAD` <u2 variable>
     */
    const val SLOAD: Byte = 0x06

    /**
     * Load an int from a local variable onto the stack
     *
     * Value: `0x07`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iload)
     *
     * @example Syntax: `ILOAD` <u2 variable>
     */
    const val ILOAD: Byte = 0x07

    /**
     * Load a long from a local variable onto the stack
     *
     * Value: `0x08`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lload)
     *
     * @example Syntax: `LLOAD` <u2 variable>
     */
    const val LLOAD: Byte = 0x08

    // Store instructions
    // No need for unsigned or floating point values for store operation (as they have the same sizes)

    /**
     * Store a byte from the stack into a local variable
     *
     * Value: `0x09`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bstore)
     *
     * @example Syntax: `BSTORE` <u2 variable>
     */
    const val BSTORE: Byte = 0x09

    /**
     * Store a short from the stack into a local variable
     *
     * Value: `0x0A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sstore)
     *
     * @example Syntax: `SSTORE` <u2 variable>
     */
    const val SSTORE: Byte = 0x0A

    /**
     * Store an int from the stack into a local variable
     *
     * Value: `0x0B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-istore)
     *
     * @example Syntax: `ISTORE` <u2 variable>
     */
    const val ISTORE: Byte = 0x0B

    /**
     * Store a long from the stack into a local variable
     *
     * Value: `0x0C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lstore)
     *
     * @example Syntax: `LSTORE` <u2 variable>
     */
    const val LSTORE: Byte = 0x0C

    // Add instructions
    // Unsigned adding is not needed as it is the same as signed adding

    /**
     * Add two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x10`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-badd)
     *
     * @example Syntax: `BADD`
     */
    const val BADD: Byte = 0x10

    /**
     * Add two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x11`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sadd)
     *
     * @example Syntax: `SADD`
     */
    const val SADD: Byte = 0x11

    /**
     * Add two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x12`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iadd)
     *
     * @example Syntax: `IADD`
     */
    const val IADD: Byte = 0x12

    /**
     * Add two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x13`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ladd)
     *
     * @example Syntax: `LADD`
     */
    const val LADD: Byte = 0x13

    /**
     * Add two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x14`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fadd)
     *
     * @example Syntax: `FADD`
     */
    const val FADD: Byte = 0x14

    /**
     * Add two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x15`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dadd)
     *
     * @example Syntax: `DADD`
     */
    const val DADD: Byte = 0x15

    /**
     * Subtract two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x16`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bsub)
     *
     * @example Syntax: `BSUB`
     */
    const val BSUB: Byte = 0x16

    /**
     * Subtract two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x17`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ssub)
     *
     * @example Syntax: `SSUB`
     */
    const val SSUB: Byte = 0x17

    /**
     * Subtract two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x18`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-isub)
     *
     * @example Syntax: `ISUB`
     */
    const val ISUB: Byte = 0x18

    /**
     * Subtract two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x19`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lsub)
     *
     * @example Syntax: `LSUB`
     */
    const val LSUB: Byte = 0x19

    /**
     * Subtract two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x1A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubsub)
     *
     * @example Syntax: `UBSUB`
     */
    const val UBSUB: Byte = 0x1A

    /**
     * Subtract two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x1B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ussub)
     *
     * @example Syntax: `USSUB`
     */
    const val USSUB: Byte = 0x1B

    /**
     * Subtract two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x1C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uisub)
     *
     * @example Syntax: `UISUB`
     */
    const val UISUB: Byte = 0x1C

    /**
     * Subtract two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x1D`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulsub)
     *
     * @example Syntax: `ULSUB`
     */
    const val ULSUB: Byte = 0x1D

    /**
     * Subtract two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x1E`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fsub)
     *
     * @example Syntax: `FSUB`
     */
    const val FSUB: Byte = 0x1E

    /**
     * Subtract two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x1F`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dsub)
     *
     * @example Syntax: `DSUB`
     */
    const val DSUB: Byte = 0x1F

    // Multiply instructions

    /**
     * Multiply two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x20`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bmul)
     *
     * @example Syntax: `BMUL`
     */
    const val BMUL: Byte = 0x20

    /**
     * Multiply two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x21`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-smul)
     *
     * @example Syntax: `SMUL`
     */
    const val SMUL: Byte = 0x21

    /**
     * Multiply two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x22`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-imul)
     *
     * @example Syntax: `IMUL`
     */
    const val IMUL: Byte = 0x22

    /**
     * Multiply two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x23`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lmul)
     *
     * @example Syntax: `LMUL`
     */
    const val LMUL: Byte = 0x23

    /**
     * Multiply two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x24`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubmul)
     *
     * @example Syntax: `UBMUL`
     */
    const val UBMUL: Byte = 0x24

    /**
     * Multiply two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x25`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-usmul)
     *
     * @example Syntax: `USMUL`
     */
    const val USMUL: Byte = 0x25

    /**
     * Multiply two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x26`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uimul)
     *
     * @example Syntax: `UIMUL`
     */
    const val UIMUL: Byte = 0x26

    /**
     * Multiply two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x27`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulumul)
     *
     * @example Syntax: `ULMUL`
     */
    const val ULMUL: Byte = 0x27

    /**
     * Multiply two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x28`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fmul)
     *
     * @example Syntax: `FMUL`
     */
    const val FMUL: Byte = 0x28

    /**
     * Multiply two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x29`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dmul)
     *
     * @example Syntax: `DMUL`
     */
    const val DMUL: Byte = 0x29

    // Divide instructions

    /**
     * Divide two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x2A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bdiv)
     *
     * @example Syntax: `BDIV`
     */
    const val BDIV: Byte = 0x2A

    /**
     * Divide two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x2B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sdiv)
     *
     * @example Syntax: `SDIV`
     */
    const val SDIV: Byte = 0x2B

    /**
     * Divide two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x2C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-idiv)
     *
     * @example Syntax: `IDIV`
     */
    const val IDIV: Byte = 0x2C

    /**
     * Divide two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x2D`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ldiv)
     *
     * @example Syntax: `LDIV`
     */
    const val LDIV: Byte = 0x2D

    /**
     * Divide two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x2E`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubdiv)
     *
     * @example Syntax: `UBDIV`
     */
    const val UBDIV: Byte = 0x2E

    /**
     * Divide two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x2F`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-usdiv)
     *
     * @example Syntax: `USDIV`
     */
    const val USDIV: Byte = 0x2F

    /**
     * Divide two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x30`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uidiv)
     *
     * @example Syntax: `UIDIV`
     */
    const val UIDIV: Byte = 0x30

    /**
     * Divide two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x31`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uldiv)
     *
     * @example Syntax: `ULDIV`
     */
    const val ULDIV: Byte = 0x31

    /**
     * Divide two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x32`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fdiv)
     *
     * @example Syntax: `FDIV`
     */
    const val FDIV: Byte = 0x32

    /**
     * Divide two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x33`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ddiv)
     *
     * @example Syntax: `DDIV`
     */
    const val DDIV: Byte = 0x33

    // Modulo instructions

    /**
     * Modulo two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x34`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bmod)
     *
     * @example Syntax: `BMOD`
     */
    const val BMOD: Byte = 0x34

    /**
     * Modulo two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x35`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-smod)
     *
     * @example Syntax: `SMOD`
     */
    const val SMOD: Byte = 0x35

    /**
     * Modulo two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x36`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-imod)
     *
     * @example Syntax: `IMOD`
     */
    const val IMOD: Byte = 0x36

    /**
     * Modulo two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x37`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lmod)
     *
     * @example Syntax: `LMOD`
     */
    const val LMOD: Byte = 0x37

    /**
     * Modulo two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x38`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubmod)
     *
     * @example Syntax: `UBMOD`
     */
    const val UBMOD: Byte = 0x38

    /**
     * Modulo two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x39`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-usmod)
     *
     * @example Syntax: `USMOD`
     */
    const val USMOD: Byte = 0x39

    /**
     * Modulo two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x3A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uimod)
     *
     * @example Syntax: `UIMOD`
     */
    const val UIMOD: Byte = 0x3A

    /**
     * Modulo two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x3B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulmod)
     *
     * @example Syntax: `ULMOD`
     */
    const val ULMOD: Byte = 0x3B

    /**
     * Modulo two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x3C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fmod)
     *
     * @example Syntax: `FMOD`
     */
    const val FMOD: Byte = 0x3C

    /**
     * Modulo two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x3D`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dmod)
     *
     * @example Syntax: `DMOD`
     */
    const val DMOD: Byte = 0x3D

    // Negate instructions

    /**
     * Negate the byte on top of the stack
     *
     * Value: `0x3E`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bneg)
     *
     * @example Syntax: `BNEG`
     */
    const val BNEG: Byte = 0x3E

    /**
     * Negate the short on top of the stack
     *
     * Value: `0x3F`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sneg)
     *
     * @example Syntax: `SNEG`
     */
    const val SNEG: Byte = 0x3F

    /**
     * Negate the int on top of the stack
     *
     * Value: `0x40`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ineg)
     *
     * @example Syntax: `INEG`
     */
    const val INEG: Byte = 0x40

    /**
     * Negate the long on top of the stack
     *
     * Value: `0x41`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ineg)
     *
     * @example Syntax: `LNEG`
     */
    const val LNEG: Byte = 0x41

    /**
     * Negate the float on top of the stack
     *
     * Value: `0x42`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fneg)
     *
     * @example Syntax: `FNEG`
     */
    const val FNEG: Byte = 0x42

    /**
     * Negate the double on top of the stack
     *
     * Value: `0x43`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dneg)
     *
     * @example Syntax: `DNEG`
     */
    const val DNEG: Byte = 0x43

    // Increment instructions

    /**
     * Increment a byte
     *
     * Value: `0x44`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-binc)
     *
     * @example Syntax: `BINC`
     */
    const val BINC: Byte = 0x44

    /**
     * Increment a short
     *
     * Value: `0x45`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sinc)
     *
     * @example Syntax: `SINC`
     */
    const val SINC: Byte = 0x45

    /**
     * Increment an int
     *
     * Value: `0x46`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iinc)
     *
     * @example Syntax: `IINC`
     */
    const val IINC: Byte = 0x46

    /**
     * Increment a long
     *
     * Value: `0x47`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-linc)
     *
     * @example Syntax: `LINC`
     */
    const val LINC: Byte = 0x47

    /**
     * Increment a float
     *
     * Value: `0x48`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-finc)
     *
     * @example Syntax: `FINC`
     */
    const val FINC: Byte = 0x48

    /**
     * Increment a double
     *
     * Value: `0x49`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dinc)
     *
     * @example Syntax: `DINC`
     */
    const val DINC: Byte = 0x49

    // Decrement instructions

    /**
     * Decrement a byte
     *
     * Value: `0x4A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bdec)
     *
     * @example Syntax: `BDEC`
     */
    const val BDEC: Byte = 0x4A

    /**
     * Decrement a short
     *
     * Value: `0x4B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sdec)
     *
     * @example Syntax: `SDEC`
     */
    const val SDEC: Byte = 0x4B

    /**
     * Decrement an int
     *
     * Value: `0x4C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-idec)
     *
     * @example Syntax: `IDEC`
     */
    const val IDEC: Byte = 0x4C

    /**
     * Decrement a long
     *
     * Value: `0x4D`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ldec)
     *
     * @example Syntax: `LDEC`
     */
    const val LDEC: Byte = 0x4D

    /**
     * Decrement a float
     *
     * Value: `0x4E`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fdec)
     *
     * @example Syntax: `FDEC`
     */
    const val FDEC: Byte = 0x4E

    /**
     * Decrement a double
     *
     * Value: `0x4F`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ddec)
     *
     * @example Syntax: `DDEC`
     */
    const val DDEC: Byte = 0x4F

    // Bitwise And instructions

    /**
     * Bitwise AND the two bytes on top of the stack
     *
     * Value: `0x50`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-band)
     *
     * @example Syntax: `BAND`
     */
    const val BAND: Byte = 0x50

    /**
     * Bitwise AND two shorts (the first 4 bytes)
     *
     * Value: `0x51`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sand)
     *
     * @example Syntax: `SAND`
     */
    const val SAND: Byte = 0x51

    /**
     * Bitwise AND two ints (the first 8 bytes)
     *
     * Value: `0x52`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iand)
     *
     * @example Syntax: `IAND`
     */
    const val IAND: Byte = 0x52

    /**
     * Bitwise AND two longs (the first 16 bytes)
     *
     * Value: `0x53`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-land)
     *
     * @example Syntax: `LAND`
     */
    const val LAND: Byte = 0x53

    // Bitwise OR instructions

    /**
     * Bitwise OR two bytes on top of the stack
     *
     * Value: `0x54`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bor)
     *
     * @example Syntax: `BOR`
     */
    const val BOR: Byte = 0x54

    /**
     * Bitwise OR two shorts (the first 4 bytes)
     *
     * Value: `0x55`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sor)
     *
     * @example Syntax: `SOR`
     */
    const val SOR: Byte = 0x55

    /**
     * Bitwise OR two ints (the first 8 bytes)
     *
     * Value: `0x56`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ior)
     *
     * @example Syntax: `IOR`
     */
    const val IOR: Byte = 0x56

    /**
     * Bitwise OR two longs (the first 16 bytes)
     *
     * Value: `0x57`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lor)
     *
     * @example Syntax: `LOR`
     */
    const val LOR: Byte = 0x57

    // Bitwise XOR instructions

    /**
     * Bitwise XOR two bytes on top of the stack
     *
     * Value: `0x58`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bxor)
     *
     * @example Syntax: `BXOR`
     */
    const val BXOR: Byte = 0x58

    /**
     * Bitwise XOR two shorts (the first 4 bytes)
     *
     * Value: `0x59`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sxor)
     *
     * @example Syntax: `SXOR`
     */
    const val SXOR: Byte = 0x59

    /**
     * Bitwise XOR two ints (the first 8 bytes)
     *
     * Value: `0x5A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ixor)
     *
     * @example Syntax: `IXOR`
     */
    const val IXOR: Byte = 0x5A

    /**
     * Bitwise XOR two longs (the first 16 bytes)
     *
     * Value: `0x5B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lxor)
     *
     * @example Syntax: `LXOR`
     */
    const val LXOR: Byte = 0x5B

    // Bitwise NOT

    /**
     * Bitwise NOT the top byte on the stack
     *
     * Value: `0x5C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bnot)
     *
     * @example Syntax `BNOT`
     */
    const val BNOT: Byte = 0x5C

    /**
     * Bitwise NOT the top short on the stack
     *
     * Value: `0x5D`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-snot)
     *
     * @example Syntax `SNOT`
     */
    const val SNOT: Byte = 0x5D

    /**
     * Bitwise NOT the top int on the stack
     *
     * Value: `0x5E`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-inot)
     *
     * @example Syntax `INOT`
     */
    const val INOT: Byte = 0x5E

    /**
     * Bitwise NOT the top long on the stack
     *
     * Value: `0x5F`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lnot)
     *
     * @example Syntax `LNOT`
     */
    const val LNOT: Byte = 0x5F

    // Shift instructions

    /**
     * Bitwise shift left a byte (the top byte is the amount, the second byte is the value to shift)
     *
     * Value: `0x60`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bshl)
     *
     * @example Syntax: `BSHL`
     */
    const val BSHL: Byte = 0x60

    /**
     * Bitwise shift left a short (the top byte is the amount, the second short is the value to shift)
     *
     * Value: `0x61`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sshl)
     *
     * @example Syntax: `SSHL`
     */
    const val SSHL: Byte = 0x61

    /**
     * Bitwise shift left an int (the top byte is the amount, the second int is the value to shift)
     *
     * Value: `0x62`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ishl)
     *
     * @example Syntax: `ISHL`
     */
    const val ISHL: Byte = 0x62

    /**
     * Bitwise shift left a long (the top byte is the amount, the second long is the value to shift)
     *
     * Value: `0x63`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lshl)
     *
     * @example Syntax: `LSHL`
     */
    const val LSHL: Byte = 0x63

    // Shift right instructions

    /**
     * Bitwise shift right a byte (the top byte is the amount, the second byte is the value to shift)
     *
     * Value: `0x64`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bshr)
     *
     * @example Syntax: `BSHR`
     */
    const val BSHR: Byte = 0x64

    /**
     * Bitwise shift right a short (the top byte is the amount, the second short is the value to shift)
     *
     * Value: `0x65`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sshr)
     *
     * @example Syntax: `SSHR`
     */
    const val SSHR: Byte = 0x65

    /**
     * Bitwise shift right an int (the top byte is the amount, the second int is the value to shift)
     *
     * Value: `0x66`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ishr)
     *
     * @example Syntax: `ISHR`
     */
    const val ISHR: Byte = 0x66

    /**
     * Bitwise shift right a long (the top byte is the amount, the second long is the value to shift)
     *
     * Value: `0x67`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lshr)
     *
     * @example Syntax: `LSHR`
     */
    const val LSHR: Byte = 0x67

    // Shift right unsigned instructions

    /**
     * Bitwise shift right unsigned a byte (the top byte is the amount, the second byte is the value to shift)
     *
     * Value: `0x68`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bshru)
     *
     * @example Syntax: `BSHRU`
     */
    const val BSHRU: Byte = 0x68

    /**
     * Bitwise shift right unsigned a short (the top byte is the amount, the second short is the value to shift)
     *
     * Value: `0x69`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sshr)
     *
     * @example Syntax: `SSHRU`
     */
    const val SSHRU: Byte = 0x69

    /**
     * Bitwise shift right unsigned an int (the top byte is the amount, the second int is the value to shift)
     *
     * Value: `0x6A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ishru)
     *
     * @example Syntax: `ISHRU`
     */
    const val ISHRU: Byte = 0x6A

    /**
     * Bitwise shift right unsigned a long (the top byte is the amount, the second long is the value to shift)
     *
     * Value: `0x6B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lshru)
     *
     * @example Syntax: `LSHRU`
     */
    const val LSHRU: Byte = 0x6B

    // Compare instructions

    /**
     * Compare two bytes
     *
     * Place the result on the stack (as a byte):
     * 0 (00000000) if the first value is less than the second
     * 1 (00000001) if the first value is equal to the second
     * 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x70`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bcmp)
     *
     * @example Syntax: `BCMP`
     */
    const val BCMP: Byte = 0x70

    /**
     * Compare two shorts
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x71`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-scmp)
     *
     * @example Syntax: `SCMP`
     */
    const val SCMP: Byte = 0x71

    /**
     * Compare two ints
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x72`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-icmp)
     *
     * @example Syntax: `ICMP`
     */
    const val ICMP: Byte = 0x72

    /**
     * Compare two longs
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x73`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lcmp)
     *
     * @example Syntax: `LCMP`
     */
    const val LCMP: Byte = 0x73

    /**
     * Compare two floats
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x74`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-fcmp)
     *
     * @example Syntax: `FCMP`
     */
    const val FCMP: Byte = 0x74

    /**
     * Compare two doubles
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x75`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-dcmp)
     *
     * @example Syntax: `DCMP`
     */
    const val DCMP: Byte = 0x75

    /**
     * Compare two unsigned bytes
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x76`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ubcmp)
     *
     * @example Syntax: `UBCMP`
     */
    const val UBCMP: Byte = 0x76

    /**
     * Compare two unsigned shorts
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x77`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uscmp)
     *
     * @example Syntax: `USCMP`
     */
    const val USCMP: Byte = 0x77

    /**
     * Compare two unsigned ints
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x78`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-uicmp)
     *
     * @example Syntax: `UICMP`
     */
    const val UICMP: Byte = 0x78

    /**
     * Compare two unsigned longs
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * Value: `0x79`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ulcmp)
     *
     * @example Syntax: `ULCMP`
     */
    const val ULCMP: Byte = 0x79

    /**
     * Compare result is less than
     *
     * Value: `0x7A`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-clt)
     *
     * @example Syntax: `CLT`
     */
    const val CLT: Byte = 0x7A

    /**
     * Compare result is less than or equal
     *
     * Value: `0x7B`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-cle)
     *
     * @example Syntax: `CLE`
     */
    const val CLE: Byte = 0x7B

    /**
     * Compare result is equal
     *
     * Value: `0x7C`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ceq)
     *
     * @example Syntax: `CEQ`
     */
    const val CEQ: Byte = 0x7C

    /**
     * Compare result is not equal
     *
     * Value: `0x7D`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-cne)
     *
     * @example Syntax: `CNE`
     */
    const val CNE: Byte = 0x7D

    /**
     * Compare result is greater than or equal
     *
     * Value: `0x7E`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-cge)
     *
     * @example Syntax: `CGE`
     */
    const val CGE: Byte = 0x7E

    /**
     * Compare result is greater than
     *
     * Value: `0x7F`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-cgt)
     *
     * @example Syntax: `CGT`
     */
    const val CGT: Byte = 0x7F

    // Jump instructions

    /**
     * Jump to an address
     *
     * Value: `0x80`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jmp)
     *
     * @example Syntax: `JMP` <u4 address>
     */
    const val JMP: Byte = -128

    /**
     * Jump to an address if there is a zero on top of the stack
     * (takes the top byte value and jumps if it is 0)
     *
     * Value: `0x81`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jz)
     *
     * @example Syntax: `JZ` <u4 address>
     */
    const val JZ: Byte = -127

    /**
     * Jump to an address if there is not a zero on top of the stack
     * (takes the top byte value and jumps if it is 1)
     *
     * Value: `0x82`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jnz)
     *
     * @example Syntax: `JNZ` <u4 address>
     */
    const val JNZ: Byte = -126

    /**
     * Jump to an address if the top two values are equal
     * (takes the top byte value and jumps if it is 1)
     *
     * Value: `0x83`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-je)
     *
     * @example Syntax: `JE` <u4 address>
     */
    const val JE: Byte = -125

    /**
     * Jump to an address if the top two values are not equal
     * (takes the top byte value and jumps if it is not 1)
     *
     * Value: `0x84`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jne)
     *
     * @example Syntax: `JNE` <u4 address>
     */
    const val JNE: Byte = -124

    /**
     * Jump to an address if the top two values are greater than
     * (takes the top byte value and jumps if it is 0)
     *
     * Value: `0x81`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jg)
     *
     * @deprecated Use [JNZ] instead, it does the same thing
     *
     * @example Syntax: `JG` <u4 address>
     */
    const val JG: Byte = -127

    /**
     * Jump to an address if the top two values are greater than or equal
     * (takes the top byte value and jumps if it is 0 or 1)
     *
     * Value: `0x85`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jge)
     *
     * @example Syntax: `JGE` <u4 address>
     */
    const val JGE: Byte = -123

    /**
     * Jump to an address if the top two values are less than
     * (takes the top byte value and jumps if it is 2)
     *
     * Value: `0x86`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jl)
     *
     * @example Syntax: `JL` <u4 address>
     */
    const val JL: Byte = -122

    /**
     * Jump to an address if the top two values are less than or equal
     * (takes the top byte value and jumps if it is 1 or 2)
     *
     * Value: `0x87`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-jle)
     *
     * @example Syntax: `JLE` <u4 address>
     */
    const val JLE: Byte = -121

    /**
     * Return from a function
     *
     * Value: `0x90`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ret)
     *
     * @example Syntax: `RET`
     */
    const val RET: Byte = -112

    /**
     * Store a byte as return value
     *
     * Value: `0x91`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bret)
     *
     * @example Syntax: `BRET`
     */
    const val BRET: Byte = -111

    /**
     * Store a short as return value
     *
     * Value: `0x92`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sret)
     *
     * @example Syntax: `SRET`
     */
    const val SRET: Byte = -110

    /**
     * Store an int as return value
     *
     * Value: `0x93`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iret)
     *
     * @example Syntax: `IRET`
     */
    const val IRET: Byte = -109

    /**
     * Store a long as return value
     *
     * Value: `0x94`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lret)
     *
     * @example Syntax: `LRET`
     */
    const val LRET: Byte = -108

    /**
     * Pop the top element of the stack
     *
     * Value: `0xA1`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bpop)
     *
     * @example Syntax: `POP`
     */
    const val BPOP: Byte = -95

    /**
     * Pop the top 2 elements of the stack
     *
     * Value: `0xA2`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-spop)
     *
     * @example Syntax: `SPOP`
     */
    const val SPOP: Byte = -94

    /**
     * Pop the top 4 elements of the stack
     *
     * Value: `0xA3`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ipop)
     *
     * @example Syntax: `IPOP`
     */
    const val IPOP: Byte = -93

    /**
     * Pop the top 8 elements of the stack
     *
     * Value: `0xA4`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lpop)
     *
     * @example Syntax: `POP8`
     */
    const val LPOP: Byte = -92

    /**
     * Duplicate the top element of the stack
     *
     * Value: `0xA5`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bdup)
     *
     * @example Syntax: `BDUP`
     */
    const val BDUP: Byte = -91

    /**
     * Duplicate the top 2 elements of the stack
     *
     * Value: `0xA6`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sdup)
     *
     * @example Syntax: `SDUP`
     */
    const val SDUP: Byte = -90

    /**
     * Duplicate the top 4 elements of the stack
     *
     * Value: `0xA7`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-idup)
     *
     * @example Syntax: `IDUP`
     */
    const val IDUP: Byte = -89

    /**
     * Duplicate the top 8 elements of the stack
     *
     * Value: `0xA8`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-ldup)
     *
     * @example Syntax: `LDUP`
     */
    const val LDUP: Byte = -88

    /**
     * Primitive cast operation. The top element of the stack will be cast.
     *
     * Value: `0xA9`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-pcast)
     *
     * @example Syntax `PCAST` <u1 cast-type>
     */
    const val PCAST: Byte = -87

    /**
     * Invoke-Static instruction
     *
     * Value: `0xB0`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-invoke-static)
     *
     * @example Syntax: `INVOKE_STATIC` <u4 address>
     */
    const val INVOKE_STATIC: Byte = -80

    /**
     * Invoke instance instruction
     *
     * Value: `0xB1`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-invoke-virtual)
     *
     * @example Syntax: `INVOKE_VIRTUAL` <u4 address>
     */
    const val INVOKE_VIRTUAL: Byte = -79

    /**
     * Load static field instruction
     *
     * Value: `0xB2`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-load-static)
     *
     * @example Syntax: `LOAD_STATIC` <u4 address>
     */
    const val LOAD_STATIC: Byte = -78

    /**
     * Load instance field instruction
     *
     * Value: `0xB3`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-load-virtual)
     *
     * @example Syntax: `LOAD_VIRTUAL` <u4 address>
     */
    const val LOAD_VIRTUAL: Byte = -77

    /**
     * Load a byte from an array
     *
     * Value: `0xB4`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-baload)
     *
     * @example Syntax: `BALOAD`
     */
    const val BALOAD: Byte = -76

    /**
     * Load a short from an array
     *
     * Value: `0xB5`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-saload)
     *
     * @example Syntax: `SALOAD`
     */
    const val SALOAD: Byte = -75

    /**
     * Load an int from an array
     *
     * Value: `0xB6`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iaload)
     *
     * @example Syntax: `IALOAD`
     */
    const val IALOAD: Byte = -74

    /**
     * Load a long from an array
     *
     * Value: `0xB7`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-laload)
     *
     * @example Syntax: `LALOAD`
     */
    const val LALOAD: Byte = -73

    /**
     * Store a field in a class
     *
     * Value: `0xB8`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-store-static)
     *
     * @example Syntax: `STORE_STATIC` <u4 address>
     */
    const val STORE_STATIC: Byte = -72

    /**
     * Store a field in an instance
     *
     * Value: `0xB9`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-store-virtual)
     *
     * @example Syntax: `STORE_VIRTUAL` <u4 address>
     */
    const val STORE_VIRTUAL: Byte = -71

    /**
     * Store a byte in an array
     *
     * Value: `0xBA`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-bastore)
     *
     * @example Syntax: `BASTORE`
     */
    const val BASTORE: Byte = -70

    /**
     * Store a short in an array
     *
     * Value: `0xBB`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-sastore)
     *
     * @example Syntax: `SASTORE`
     */
    const val SASTORE: Byte = -69

    /**
     * Store an int in an array
     *
     * Value: `0xBC`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-iastore)
     *
     * @example Syntax: `IASTORE`
     */
    const val IASTORE: Byte = -68

    /**
     * Store a long in an array
     *
     * Value: `0xBD`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-lastore)
     *
     * @example Syntax: `LASTORE`
     */
    const val LASTORE: Byte = -67

    /**
     * Construct a new object from a class
     *
     * Value: `0xC0`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-new-obj)
     *
     * @example Syntax: `NEW_OBJ` <u4 address>
     */
    const val NEW_OBJ: Byte = -64

    /**
     * Construct a new array
     *
     * Value: `0xC1`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-new-arr)
     *
     * @example Syntax: `NEW_ARR` <u1 type>
     */
    const val NEW_ARR: Byte = -63

    /**
     * Throw an exception
     *
     * Value: `0xC2`
     *
     * [Specification](https://spec.shakelang.com/bytecode/instructions#instr-throw)
     *
     * @example Syntax: `THROW`
     */
    const val THROW: Byte = -62
}
