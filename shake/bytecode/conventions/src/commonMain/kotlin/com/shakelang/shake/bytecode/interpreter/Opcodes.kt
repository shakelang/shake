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
     * @example Syntax: `BPUSH` <u1 byte>
     * @see - https://shakelang.com/specification/bytecode/instructions#-111-bpush
     */
    const val BPUSH: Byte = 0x01

    /**
     * Push a short onto the stack
     *
     * Value: `0x02`
     *
     * @example Syntax: `SPUSH` <u2 short>
     * @see - https://shakelang.com/specification/bytecode/instructions#-112-spush
     */
    const val SPUSH: Byte = 0x02

    /**
     * Push an int onto the stack
     *
     * Value: `0x03`
     *
     * @example Syntax: `IPUSH` <u4 int>
     * @see - https://shakelang.com/specification/bytecode/instructions#-113-ipush
     */
    const val IPUSH: Byte = 0x03

    /**
     * Push a long onto the stack
     *
     * Value: `0x04`
     *
     * @example Syntax: `LPUSH` <u8 long>
     * @see - https://shakelang.com/specification/bytecode/instructions#-114-lpush
     */
    const val LPUSH: Byte = 0x04

    // Load instructions
    // No need for unsigned or floating point values for load operation (as they have the same sizes)

    /**
     * Load a byte from a local variable onto the stack
     *
     * Value: `0x05`
     *
     * @example Syntax: `BLOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-121-bload
     */
    const val BLOAD: Byte = 0x05

    /**
     * Load a short from a local variable onto the stack
     *
     * Value: `0x06`
     *
     * @example Syntax: `SLOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-122-sload
     */
    const val SLOAD: Byte = 0x06

    /**
     * Load an int from a local variable onto the stack
     *
     * Value: `0x07`
     *
     * @example Syntax: `ILOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-123-iload
     */
    const val ILOAD: Byte = 0x07

    /**
     * Load a long from a local variable onto the stack
     *
     * Value: `0x08`
     *
     * @example Syntax: `LLOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-124-lload
     */
    const val LLOAD: Byte = 0x08

    // Store instructions
    // No need for unsigned or floating point values for store operation (as they have the same sizes)

    /**
     * Store a byte from the stack into a local variable
     *
     * Value: `0x09`
     *
     * @example Syntax: `BSTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-131-bstore
     */
    const val BSTORE: Byte = 0x09

    /**
     * Store a short from the stack into a local variable
     *
     * Value: `0x0A`
     *
     * @example Syntax: `SSTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-132-sstore
     */
    const val SSTORE: Byte = 0x0A

    /**
     * Store an int from the stack into a local variable
     *
     * Value: `0x0B`
     *
     * @example Syntax: `ISTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-133-istore
     */
    const val ISTORE: Byte = 0x0B

    /**
     * Store a long from the stack into a local variable
     *
     * Value: `0x0C`
     *
     * @example Syntax: `LSTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-134-lstore
     */
    const val LSTORE: Byte = 0x0C

    // Add instructions
    // Unsigned adding is not needed as it is the same as signed adding

    /**
     * Add two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x10`
     *
     * @example Syntax: `BADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-211-badd
     */
    const val BADD: Byte = 0x10

    /**
     * Add two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x11`
     *
     * @example Syntax: `SADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-212-sadd
     */
    const val SADD: Byte = 0x11

    /**
     * Add two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x12`
     *
     * @example Syntax: `IADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-213-iadd
     */
    const val IADD: Byte = 0x12

    /**
     * Add two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x13`
     *
     * @example Syntax: `LADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-214-ladd
     */
    const val LADD: Byte = 0x13

    /**
     * Add two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x14`
     *
     * @example Syntax: `FADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-215-fadd
     */
    const val FADD: Byte = 0x14

    /**
     * Add two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x15`
     *
     * @example Syntax: `DADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-216-dadd
     */
    const val DADD: Byte = 0x15

    // Subtract instructions
    // Unsigned subtracting is not needed as it is the same as signed subtracting

    /**
     * Subtract two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x16`
     *
     * @example Syntax: `BSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-221-bsub
     */
    const val BSUB: Byte = 0x16

    /**
     * Subtract two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x17`
     *
     * @example Syntax: `SSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-222-ssub
     */
    const val SSUB: Byte = 0x17

    /**
     * Subtract two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x18`
     *
     * @example Syntax: `ISUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-223-isub
     */
    const val ISUB: Byte = 0x18

    /**
     * Subtract two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x19`
     *
     * @example Syntax: `LSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-224-lsub
     */
    const val LSUB: Byte = 0x19

    /**
     * Subtract two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x1A`
     *
     * @example Syntax: `UBSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-227-ubsub
     */
    const val UBSUB: Byte = 0x1A

    /**
     * Subtract two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x1B`
     *
     * @example Syntax: `USSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-228-ussub
     */
    const val USSUB: Byte = 0x1B

    /**
     * Subtract two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x1C`
     *
     * @example Syntax: `UISUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-229-uisub
     */
    const val UISUB: Byte = 0x1C

    /**
     * Subtract two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x1D`
     *
     * @example Syntax: `ULSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2210-ulsub
     */
    const val ULSUB: Byte = 0x1D

    /**
     * Subtract two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x1E`
     *
     * @example Syntax: `FSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-225-fsub
     */
    const val FSUB: Byte = 0x1E

    /**
     * Subtract two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x1F`
     *
     * @example Syntax: `DSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-226-dsub
     */
    const val DSUB: Byte = 0x1F

    // Multiply instructions

    /**
     * Multiply two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x20`
     *
     * @example Syntax: `BMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-231-bmul
     */
    const val BMUL: Byte = 0x20

    /**
     * Multiply two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x21`
     *
     * @example Syntax: `SMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-232-smul
     */
    const val SMUL: Byte = 0x21

    /**
     * Multiply two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x22`
     *
     * @example Syntax: `IMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-233-imul
     */
    const val IMUL: Byte = 0x22

    /**
     * Multiply two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x23`
     *
     * @example Syntax: `LMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-234-lmul
     */
    const val LMUL: Byte = 0x23

    /**
     * Multiply two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x24`
     *
     * @example Syntax: `UBMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-237-ubmul
     */
    const val UBMUL: Byte = 0x24

    /**
     * Multiply two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x25`
     *
     * @example Syntax: `USMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-238-usmul
     */
    const val USMUL: Byte = 0x25

    /**
     * Multiply two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x26`
     *
     * @example Syntax: `UIMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-239-uimul
     */
    const val UIMUL: Byte = 0x26

    /**
     * Multiply two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x27`
     *
     * @example Syntax: `ULMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2310-ulmul
     */
    const val ULMUL: Byte = 0x27

    /**
     * Multiply two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x28`
     *
     * @example Syntax: `FMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-235-fmul
     */
    const val FMUL: Byte = 0x28

    /**
     * Multiply two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x29`
     *
     * @example Syntax: `DMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-236-dmul
     */
    const val DMUL: Byte = 0x29


    // Divide instructions

    /**
     * Divide two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x2A`
     *
     * @example Syntax: `BDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-241-bdiv
     */
    const val BDIV: Byte = 0x2A

    /**
     * Divide two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x2B`
     *
     * @example Syntax: `SDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-242-sdiv
     */
    const val SDIV: Byte = 0x2B

    /**
     * Divide two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x2C`
     *
     * @example Syntax: `IDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-243-idiv
     */
    const val IDIV: Byte = 0x2C

    /**
     * Divide two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x2D`
     *
     * @example Syntax: `LDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-244-ldiv
     */
    const val LDIV: Byte = 0x2D

    /**
     * Divide two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x2E`
     *
     * @example Syntax: `UBDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-247-ubdiv
     */
    const val UBDIV: Byte = 0x2E

    /**
     * Divide two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x2F`
     *
     * @example Syntax: `USDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-248-usdiv
     */
    const val USDIV: Byte = 0x2F

    /**
     * Divide two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x30`
     *
     * @example Syntax: `UIDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-249-uidiv
     */
    const val UIDIV: Byte = 0x30

    /**
     * Divide two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x31`
     *
     * @example Syntax: `ULDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2410-uldiv
     */
    const val ULDIV: Byte = 0x31

    /**
     * Divide two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x32`
     *
     * @example Syntax: `FDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-245-fdiv
     */
    const val FDIV: Byte = 0x32

    /**
     * Divide two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x33`
     *
     * @example Syntax: `DDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-246-ddiv
     */
    const val DDIV: Byte = 0x33

    // Modulo instructions

    /**
     * Modulo two bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x34`
     *
     * @example Syntax: `BMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-251-bmod
     */
    const val BMOD: Byte = 0x34

    /**
     * Modulo two shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x35`
     *
     * @example Syntax: `SMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-252-smod
     */
    const val SMOD: Byte = 0x35

    /**
     * Modulo two ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x36`
     *
     * @example Syntax: `IMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-253-imod
     */
    const val IMOD: Byte = 0x36

    /**
     * Modulo two longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x37`
     *
     * @example Syntax: `LMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-254-lmod
     */
    const val LMOD: Byte = 0x37

    /**
     * Modulo two unsigned bytes on top of the stack (the first 2 bytes)
     *
     * Value: `0x38`
     *
     * @example Syntax: `UBMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-257-ubmod
     */
    const val UBMOD: Byte = 0x38

    /**
     * Modulo two unsigned shorts on top of the stack (the first 4 bytes)
     *
     * Value: `0x39`
     *
     * @example Syntax: `USMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-258-usmod
     */
    const val USMOD: Byte = 0x39

    /**
     * Modulo two unsigned ints on top of the stack (the first 8 bytes)
     *
     * Value: `0x3A`
     *
     * @example Syntax: `UIMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-259-uimod
     */
    const val UIMOD: Byte = 0x3A

    /**
     * Modulo two unsigned longs on top of the stack (the first 16 bytes)
     *
     * Value: `0x3B`
     *
     * @example Syntax: `ULMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2510-ulmod
     */
    const val ULMOD: Byte = 0x3B

    /**
     * Modulo two floats on top of the stack (the first 8 bytes)
     *
     * Value: `0x3C`
     *
     * @example Syntax: `FMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-255-fmod
     */
    const val FMOD: Byte = 0x3C

    /**
     * Modulo two doubles on top of the stack (the first 16 bytes)
     *
     * Value: `0x3D`
     *
     * @example Syntax: `DMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-256-dmod
     */
    const val DMOD: Byte = 0x3D

    // Negate instructions

    /**
     * Negate the byte on top of the stack
     *
     * Value: `0x3E`
     *
     * @example Syntax: `BNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-261-bneg
     */
    const val BNEG: Byte = 0x3E

    /**
     * Negate the short on top of the stack
     *
     * Value: `0x3F`
     *
     * @example Syntax: `SNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-262-sneg
     */
    const val SNEG: Byte = 0x3F

    /**
     * Negate the int on top of the stack
     *
     * Value: `0x40`
     *
     * @example Syntax: `INEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-263-ineg
     */
    const val INEG: Byte = 0x40

    /**
     * Negate the long on top of the stack
     *
     * Value: `0x41`
     *
     * @example Syntax: `LNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-264-ineg
     */
    const val LNEG: Byte = 0x41

    /**
     * Negate the float on top of the stack
     *
     * Value: `0x42`
     *
     * @example Syntax: `FNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-265-fneg
     */
    const val FNEG: Byte = 0x42

    /**
     * Negate the double on top of the stack
     *
     * Value: `0x43`
     *
     * @example Syntax: `DNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-266-dneg
     */
    const val DNEG: Byte = 0x43

    // Increment instructions

    /**
     * Increment a byte
     *
     * Value: `0x44`
     *
     * @example Syntax: `BINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-271-binc
     */
    const val BINC: Byte = 0x44

    /**
     * Increment a short
     *
     * Value: `0x45`
     *
     * @example Syntax: `SINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-272-sinc
     */
    const val SINC: Byte = 0x45

    /**
     * Increment an int
     *
     * Value: `0x46`
     *
     * @example Syntax: `IINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-273-iinc
     */
    const val IINC: Byte = 0x46

    /**
     * Increment a long
     *
     * Value: `0x47`
     *
     * @example Syntax: `LINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-274-linc
     */
    const val LINC: Byte = 0x47

    /**
     * Increment a float
     *
     * Value: `0x48`
     *
     * @example Syntax: `FINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-275-finc
     */
    const val FINC: Byte = 0x48

    /**
     * Increment a double
     *
     * Value: `0x49`
     *
     * @example Syntax: `DINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-276-dinc
     */
    const val DINC: Byte = 0x49

    // Decrement instructions

    /**
     * Decrement a byte
     *
     * Value: `0x4A`
     *
     * @example Syntax: `BDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-281-bdec
     */
    const val BDEC: Byte = 0x4A

    /**
     * Decrement a short
     *
     * Value: `0x4B`
     *
     * @example Syntax: `SDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-282-sdec
     */
    const val SDEC: Byte = 0x4B

    /**
     * Decrement an int
     *
     * Value: `0x4C`
     *
     * @example Syntax: `IDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-283-idec
     */
    const val IDEC: Byte = 0x4C

    /**
     * Decrement a long
     *
     * Value: `0x4D`
     *
     * @example Syntax: `LDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-284-ldec
     */
    const val LDEC: Byte = 0x4D

    /**
     * Decrement a float
     *
     * Value: `0x4E`
     *
     * @example Syntax: `FDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-285-fdec
     */
    const val FDEC: Byte = 0x4E

    /**
     * Decrement a double
     *
     * Value: `0x4F`
     *
     * @example Syntax: `DDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-286-ddec
     */
    const val DDEC: Byte = 0x4F

    // Bitwise And instructions

    /**
     * Bitwise AND the two bytes on top of the stack
     *
     * Value: `0x50`
     *
     * @example Syntax: `BAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-311-band
     */
    const val BAND: Byte = 0x50

    /**
     * Bitwise AND two shorts (the first 4 bytes)
     *
     * Value: `0x51`
     *
     * @example Syntax: `SAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-312-sand
     */
    const val SAND: Byte = 0x51

    /**
     * Bitwise AND two ints (the first 8 bytes)
     *
     * Value: `0x52`
     *
     * @example Syntax: `IAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-313-iand
     */
    const val IAND: Byte = 0x52

    /**
     * Bitwise AND two longs (the first 16 bytes)
     *
     * Value: `0x53`
     *
     * @example Syntax: `LAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-314-land
     */
    const val LAND: Byte = 0x53

    // Bitwise OR instructions

    /**
     * Bitwise OR two bytes on top of the stack
     *
     * Value: `0x54`
     *
     * @example Syntax: `BOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-321-bor
     */
    const val BOR: Byte = 0x54

    /**
     * Bitwise OR two shorts (the first 4 bytes)
     *
     * Value: `0x55`
     *
     * @example Syntax: `SOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-322-sor
     */
    const val SOR: Byte = 0x55

    /**
     * Bitwise OR two ints (the first 8 bytes)
     *
     * Value: `0x56`
     *
     * @example Syntax: `IOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-323-ior
     */
    const val IOR: Byte = 0x56

    /**
     * Bitwise OR two longs (the first 16 bytes)
     *
     * Value: `0x57`
     *
     * @example Syntax: `LOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-324-lor
     */
    const val LOR: Byte = 0x57

    // Bitwise XOR instructions

    /**
     * Bitwise XOR two bytes on top of the stack
     *
     * Value: `0x58`
     *
     * @example Syntax: `BXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-331-bxor
     */
    const val BXOR: Byte = 0x58

    /**
     * Bitwise XOR two shorts (the first 4 bytes)
     *
     * Value: `0x59`
     *
     * @example Syntax: `SXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-332-sxor
     */
    const val SXOR: Byte = 0x59

    /**
     * Bitwise XOR two ints (the first 8 bytes)
     *
     * Value: `0x5A`
     *
     * @example Syntax: `IXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-333-ixor
     */
    const val IXOR: Byte = 0x5A

    /**
     * Bitwise XOR two longs (the first 16 bytes)
     *
     * Value: `0x5B`
     *
     * @example Syntax: `LXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-334-lxor
     */
    const val LXOR: Byte = 0x5B

    // Bitwise NOT

    /**
     * Bitwise NOT the top byte on the stack
     *
     * Value: `0x5C`
     *
     * @example Syntax `BNOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-341-bnot
     */
    const val BNOT: Byte = 0x5C

    /**
     * Bitwise NOT the top short on the stack
     *
     * Value: `0x5D`
     *
     * @example Syntax `SNOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-342-snot
     */
    const val SNOT: Byte = 0x5D

    /**
     * Bitwise NOT the top int on the stack
     *
     * Value: `0x5E`
     *
     * @example Syntax `INOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-343-inot
     */
    const val INOT: Byte = 0x5E

    /**
     * Bitwise NOT the top long on the stack
     *
     * Value: `0x5F`
     *
     * @example Syntax `LNOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-344-inot
     */
    const val LNOT: Byte = 0x5F

    // Shift instructions

    /**
     * Bitwise shift left a byte (the top byte is the amount, the second byte is the value to shift)
     *
     * Value: `0x60`
     *
     * @example Syntax: `BSHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-351-bshl
     */
    const val BSHL: Byte = 0x60

    /**
     * Bitwise shift left a short (the top byte is the amount, the second short is the value to shift)
     *
     * Value: `0x61`
     *
     * @example Syntax: `SSHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-352-sshl
     */
    const val SSHL: Byte = 0x61

    /**
     * Bitwise shift left an int (the top byte is the amount, the second int is the value to shift)
     *
     * Value: `0x62`
     *
     * @example Syntax: `ISHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-353-ishl
     */
    const val ISHL: Byte = 0x62

    /**
     * Bitwise shift left a long (the top byte is the amount, the second long is the value to shift)
     *
     * Value: `0x63`
     *
     * @example Syntax: `LSHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-354-lshl
     */
    const val LSHL: Byte = 0x63

    // Shift right instructions

    /**
     * Bitwise shift right a byte (the top byte is the amount, the second byte is the value to shift)
     *
     * Value: `0x64`
     *
     * @example Syntax: `BSHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-361-bshr
     */
    const val BSHR: Byte = 0x64

    /**
     * Bitwise shift right a short (the top byte is the amount, the second short is the value to shift)
     *
     * Value: `0x65`
     *
     * @example Syntax: `SSHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-362-sshr
     */
    const val SSHR: Byte = 0x65

    /**
     * Bitwise shift right an int (the top byte is the amount, the second int is the value to shift)
     *
     * Value: `0x66`
     *
     * @example Syntax: `ISHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-363-ishr
     */
    const val ISHR: Byte = 0x66

    /**
     * Bitwise shift right a long (the top byte is the amount, the second long is the value to shift)
     *
     * Value: `0x67`
     *
     * @example Syntax: `LSHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-364-lshr
     */
    const val LSHR: Byte = 0x67

    // Shift right unsigned instructions

    /**
     * Bitwise shift right unsigned a byte (the top byte is the amount, the second byte is the value to shift)
     *
     * Value: `0x68`
     *
     * @example Syntax: `BSHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-371-bshru
     */
    const val BSHRU: Byte = 0x68

    /**
     * Bitwise shift right unsigned a short (the top byte is the amount, the second short is the value to shift)
     *
     * Value: `0x69`
     *
     * @example Syntax: `SSHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-372-sshr
     */
    const val SSHRU: Byte = 0x69

    /**
     * Bitwise shift right unsigned an int (the top byte is the amount, the second int is the value to shift)
     *
     * Value: `0x6A`
     *
     * @example Syntax: `ISHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-373-ishr
     */
    const val ISHRU: Byte = 0x6A

    /**
     * Bitwise shift right unsigned a long (the top byte is the amount, the second long is the value to shift)
     *
     * Value: `0x6B`
     *
     * @example Syntax: `LSHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-374-lshr
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
     * @example Syntax: `BCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-411-bcmp
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
     * @example Syntax: `SCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-412-scmp
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
     * @example Syntax: `ICMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-413-icmp
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
     * @example Syntax: `LCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-414-lcmp
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
     * @example Syntax: `FCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-415-fcmp
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
     * @example Syntax: `DCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-416-dcmp
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
     * @example Syntax: `UBCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-417-ubcmp
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
     * @example Syntax: `USCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-418-uscmp
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
     * @example Syntax: `UICMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-419-uicmp
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
     * @example Syntax: `ULCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-4110-ulcmp
     */
    const val ULCMP: Byte = 0x79

    // Jump instructions

    /**
     * Jump to an address
     *
     * Value: `0x80`
     *
     * @example Syntax: `JMP` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions/#-511-jmp
     */
    const val JMP: Byte = -128

    /**
     * Jump to an address if the top two values are equal
     * (takes the top byte value and jumps if it is 1)
     *
     * Value: `0x81`
     *
     * @example Syntax: `JE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions/#-514-je
     */
    const val JE: Byte = -127

    /**
     * Jump to an address if the top two values are not equal
     * (takes the top byte value and jumps if it is not 1)
     *
     * Value: `0x82`
     *
     * @example Syntax: `JNE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions/#-515-jne
     */
    const val JNE: Byte = -126

    /**
     * Jump to an address if the top two values are less than
     * (takes the top byte value and jumps if it is 2)
     *
     * Value: `0x83`
     *
     * @example Syntax: `JL` <u4 address>
     * @deprecated Use [JZ] instead, it does the same thing
     * @see - https://shakelang.com/specification/bytecode/instructions#-518-jl
     */
    const val JL: Byte = -125

    /**
     * Jump to an address if the top two values are greater than
     * (takes the top byte value and jumps if it is 0)
     *
     * Value: `0x84`
     *
     * @example Syntax: `JGT` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-516-jg
     */
    const val JG: Byte = -124

    /**
     * Jump to an address if the top two values are less than or equal
     * (takes the top byte value and jumps if it is 1 or 2)
     *
     * Value: `0x85`
     *
     * @example Syntax: `JLE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-519-jle
     */
    const val JLE: Byte = -123

    /**
     * Jump to an address if the top two values are greater than or equal
     * (takes the top byte value and jumps if it is 0 or 1)
     *
     * Value: `0x86`
     *
     * @example Syntax: `JGE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-517-jge
     */
    const val JGE: Byte = -122

    /**
     * Jump to an address if there is a zero on top of the stack
     * (takes the top byte value and jumps if it is 0)
     *
     * Value: `0x87`
     *
     * @example Syntax: `JZ` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-512-jz
     */
    const val JZ: Byte = -121

    /**
     * Jump to an address if there is not a zero on top of the stack
     * (takes the top byte value and jumps if it is 1)
     *
     * Value: `0x88`
     *
     * @example Syntax: `JNZ` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-513-jnz
     */
    const val JNZ: Byte = -120

    /**
     * Return from a function
     *
     * Value: `0x90`
     *
     * @example Syntax: `RET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-521-ret
     */
    const val RET: Byte = -112

    /**
     * Store a byte as return value
     *
     * Value: `0x91`
     *
     * @example Syntax: `BRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-531-bret
     */
    const val BRET: Byte = -111

    /**
     * Store a short as return value
     *
     * Value: `0x92`
     *
     * @example Syntax: `SRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-532-sret
     */
    const val SRET: Byte = -110

    /**
     * Store an int as return value
     *
     * Value: `0x93`
     *
     * @example Syntax: `IRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-533-iret
     */
    const val IRET: Byte = -109

    /**
     * Store a long as return value
     *
     * Value: `0x94`
     *
     * @example Syntax: `LRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-534-lret
     */
    const val LRET: Byte = -108

    /**
     * Primitive cast operation. The top element of the stack will be cast.
     *
     * Value: `0xA0`
     *
     * @example Syntax `PCAST` <u1 cast-type>
     * @see - https://shakelang.com/specification/bytecode/instructions#-531-pcast
     */
    const val PCAST: Byte = -96

    /**
     * Pop the top element of the stack
     *
     * Value: `0xA1`
     *
     * @example Syntax: `POP`
     * @see - https://shakelang.com/specification/bytecode/instructions#-541-pop
     */
    const val POP: Byte = -95

    /**
     * Pop the top 2 elements of the stack
     *
     * Value: `0xA2`
     *
     * @example Syntax: `POP2`
     * @see - https://shakelang.com/specification/bytecode/instructions#-542-pop2
     */
    const val SPOP: Byte = -94

    /**
     * Pop the top 4 elements of the stack
     *
     * Value: `0xA3`
     *
     * @example Syntax: `POP4`
     * @see - https://shakelang.com/specification/bytecode/instructions#-543-pop4
     */
    const val IPOP: Byte = -93

    /**
     * Pop the top 8 elements of the stack
     *
     * Value: `0xA4`
     *
     * @example Syntax: `POP8`
     * @see - https://shakelang.com/specification/bytecode/instructions#-544-pop8
     */
    const val LPOP: Byte = -92

    /**
     * Duplicate the top element of the stack
     *
     * Value: `0xA5`
     *
     * @example Syntax: `DUP`
     * @see - https://shakelang.com/specification/bytecode/instructions#-551-dup
     */
    const val DUP: Byte = -91

    /**
     * Duplicate the top 2 elements of the stack
     *
     * Value: `0xA6`
     *
     * @example Syntax: `DUP2`
     * @see - https://shakelang.com/specification/bytecode/instructions#-552-dup2
     */
    const val SDUP: Byte = -90

    /**
     * Duplicate the top 4 elements of the stack
     *
     * Value: `0xA7`
     *
     * @example Syntax: `DUP4`
     * @see - https://shakelang.com/specification/bytecode/instructions#-553-dup4
     */
    const val IDUP: Byte = -89

    /**
     * Duplicate the top 8 elements of the stack
     *
     * Value: `0xA8`
     *
     * @example Syntax: `DUP8`
     * @see - https://shakelang.com/specification/bytecode/instructions#-554-dup8
     */
    const val LDUP: Byte = -88

}
