package com.shakelang.shake.bytecode.interpreter

object Opcodes {

    /**
     * No instruction
     */
    const val NOP: Byte = 0

    // Push instructions
    // Hardcoded values directly from the bytecode
    // No need for unsigned or floating point values for push operation (as they have the same sizes)

    /**
     * Push a byte onto the stack
     * @example Syntax: `BPUSH` <u1 byte>
     * @see - https://shakelang.com/specification/bytecode/instructions#-111-bpush
     */
    const val BPUSH: Byte = 1

    /**
     * Push a short onto the stack
     * @example Syntax: `SPUSH` <u2 short>
     * @see - https://shakelang.com/specification/bytecode/instructions#-112-spush
     */
    const val SPUSH: Byte = 2

    /**
     * Push an int onto the stack
     * @example Syntax: `IPUSH` <u4 int>
     * @see - https://shakelang.com/specification/bytecode/instructions#-113-ipush
     */
    const val IPUSH: Byte = 3

    /**
     * Push a long onto the stack
     * @example Syntax: `LPUSH` <u8 long>
     * @see - https://shakelang.com/specification/bytecode/instructions#-114-lpush
     */
    const val LPUSH: Byte = 4

    // Load instructions
    // No need for unsigned or floating point values for load operation (as they have the same sizes)

    /**
     * Load a byte from a local variable onto the stack
     * @example Syntax: `BLOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-121-bload
     */
    const val BLOAD: Byte = 5

    /**
     * Load a short from a local variable onto the stack
     * @example Syntax: `SLOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-122-sload
     */
    const val SLOAD: Byte = 6

    /**
     * Load an int from a local variable onto the stack
     * @example Syntax: `ILOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-123-iload
     */
    const val ILOAD: Byte = 7

    /**
     * Load a long from a local variable onto the stack
     * @example Syntax: `LLOAD` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-124-lload
     */
    const val LLOAD: Byte = 8

    // Store instructions
    // No need for unsigned or floating point values for store operation (as they have the same sizes)

    /**
     * Store a byte from the stack into a local variable
     * @example Syntax: `BSTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-131-bstore
     */
    const val BSTORE: Byte = 9

    /**
     * Store a short from the stack into a local variable
     * @example Syntax: `SSTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-132-sstore
     */
    const val SSTORE: Byte = 10

    /**
     * Store an int from the stack into a local variable
     * @example Syntax: `ISTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-133-istore
     */
    const val ISTORE: Byte = 11

    /**
     * Store a long from the stack into a local variable
     * @example Syntax: `LSTORE` <u2 variable>
     * @see - https://shakelang.com/specification/bytecode/instructions#-134-lstore
     */
    const val LSTORE: Byte = 12

    // Add instructions
    // Unsigned adding is not needed as it is the same as signed adding

    /**
     * Add two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-211-badd
     */
    const val BADD: Byte = 13

    /**
     * Add two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-212-sadd
     */
    const val SADD: Byte = 14

    /**
     * Add two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-213-iadd
     */
    const val IADD: Byte = 15

    /**
     * Add two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-214-ladd
     */
    const val LADD: Byte = 16

    /**
     * Add two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-215-fadd
     */
    const val FADD: Byte = 17

    /**
     * Add two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DADD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-216-dadd
     */
    const val DADD: Byte = 18

    // Subtract instructions
    // Unsigned subtracting is not needed as it is the same as signed subtracting

    /**
     * Subtract two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-221-bsub
     */
    const val BSUB: Byte = 19

    /**
     * Subtract two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-222-ssub
     */
    const val SSUB: Byte = 20

    /**
     * Subtract two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `ISUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-223-isub
     */
    const val ISUB: Byte = 21

    /**
     * Subtract two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-224-lsub
     */
    const val LSUB: Byte = 22

    /**
     * Subtract two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-227-ubsub
     */
    const val UBSUB: Byte = 23

    /**
     * Subtract two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-228-ussub
     */
    const val USSUB: Byte = 24

    /**
     * Subtract two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UISUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-229-uisub
     */
    const val UISUB: Byte = 25

    /**
     * Subtract two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2210-ulsub
     */
    const val ULSUB: Byte = 26

    /**
     * Subtract two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-225-fsub
     */
    const val FSUB: Byte = 27

    /**
     * Subtract two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DSUB`
     * @see - https://shakelang.com/specification/bytecode/instructions#-226-dsub
     */
    const val DSUB: Byte = 28

    // Multiply instructions
    /**
     * Multiply two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-231-bmul
     */
    const val BMUL: Byte = 29

    /**
     * Multiply two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-232-smul
     */
    const val SMUL: Byte = 30

    /**
     * Multiply two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-233-imul
     */
    const val IMUL: Byte = 31

    /**
     * Multiply two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-234-lmul
     */
    const val LMUL: Byte = 32

    /**
     * Multiply two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-237-ubmul
     */
    const val UBMUL: Byte = 33

    /**
     * Multiply two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-238-usmul
     */
    const val USMUL: Byte = 34

    /**
     * Multiply two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UIMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-239-uimul
     */
    const val UIMUL: Byte = 35

    /**
     * Multiply two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2310-ulmul
     */
    const val ULMUL: Byte = 36

    /**
     * Multiply two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-235-fmul
     */
    const val FMUL: Byte = 37

    /**
     * Multiply two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DMUL`
     * @see - https://shakelang.com/specification/bytecode/instructions#-236-dmul
     */
    const val DMUL: Byte = 38

    // Divide instructions
    /**
     * Divide two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-241-bdiv
     */
    const val BDIV: Byte = 39

    /**
     * Divide two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-242-sdiv
     */
    const val SDIV: Byte = 40

    /**
     * Divide two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-243-idiv
     */
    const val IDIV: Byte = 41

    /**
     * Divide two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-244-ldiv
     */
    const val LDIV: Byte = 42

    /**
     * Divide two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-247-ubdiv
     */
    const val UBDIV: Byte = 43

    /**
     * Divide two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-248-usdiv
     */
    const val USDIV: Byte = 44

    /**
     * Divide two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UIDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-249-uidiv
     */
    const val UIDIV: Byte = 45

    /**
     * Divide two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2410-uldiv
     */
    const val ULDIV: Byte = 46

    /**
     * Divide two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-245-fdiv
     */
    const val FDIV: Byte = 47

    /**
     * Divide two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DDIV`
     * @see - https://shakelang.com/specification/bytecode/instructions#-246-ddiv
     */
    const val DDIV: Byte = 48

    // Modulo instructions

    /**
     * Modulo two bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `BMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-251-bmod
     */
    const val BMOD: Byte = 49

    /**
     * Modulo two shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `SMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-252-smod
     */
    const val SMOD: Byte = 50

    /**
     * Modulo two ints on top of the stack (the first 8 bytes)
     * @example Syntax: `IMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-253-imod
     */
    const val IMOD: Byte = 51

    /**
     * Modulo two longs on top of the stack (the first 16 bytes)
     * @example Syntax: `LMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-254-lmod
     */
    const val LMOD: Byte = 52

    /**
     * Modulo two unsigned bytes on top of the stack (the first 2 bytes)
     * @example Syntax: `UBMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-257-ubmod
     */
    const val UBMOD: Byte = 53

    /**
     * Modulo two unsigned shorts on top of the stack (the first 4 bytes)
     * @example Syntax: `USMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-258-usmod
     */
    const val USMOD: Byte = 54

    /**
     * Modulo two unsigned ints on top of the stack (the first 8 bytes)
     * @example Syntax: `UIMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-259-uimod
     */
    const val UIMOD: Byte = 55

    /**
     * Modulo two unsigned longs on top of the stack (the first 16 bytes)
     * @example Syntax: `ULMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-2510-ulmod
     */
    const val ULMOD: Byte = 56

    /**
     * Modulo two floats on top of the stack (the first 8 bytes)
     * @example Syntax: `FMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-255-fmod
     */
    const val FMOD: Byte = 57

    /**
     * Modulo two doubles on top of the stack (the first 16 bytes)
     * @example Syntax: `DMOD`
     * @see - https://shakelang.com/specification/bytecode/instructions#-256-dmod
     */
    const val DMOD: Byte = 58

    // Negate instructions

    /**
     * Negate the byte on top of the stack
     * @example Syntax: `BNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-261-bneg
     */
    const val BNEG: Byte = 59

    /**
     * Negate the short on top of the stack
     * @example Syntax: `SNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-262-sneg
     */
    const val SNEG: Byte = 60

    /**
     * Negate the int on top of the stack
     * @example Syntax: `INEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-263-ineg
     */
    const val INEG: Byte = 61

    /**
     * Negate the long on top of the stack
     * @example Syntax: `LNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-264-ineg
     */
    const val LNEG: Byte = 62

    /**
     * Negate the float on top of the stack
     * @example Syntax: `FNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-265-fneg
     */
    const val FNEG: Byte = 63

    /**
     * Negate the double on top of the stack
     * @example Syntax: `DNEG`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-266-dneg
     */
    const val DNEG: Byte = 64

    // Bitwise And instructions

    /**
     * Bitwise AND the two bytes on top of the stack
     * @example Syntax: `BAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-311-band
     */
    const val BAND: Byte = 65

    /**
     * Bitwise AND two shorts (the first 4 bytes)
     * @example Syntax: `SAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-312-sand
     */
    const val SAND: Byte = 66

    /**
     * Bitwise AND two ints (the first 8 bytes)
     * @example Syntax: `IAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-313-iand
     */
    const val IAND: Byte = 67

    /**
     * Bitwise AND two longs (the first 16 bytes)
     * @example Syntax: `LAND`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-314-land
     */
    const val LAND: Byte = 68

    // Bitwise OR instructions

    /**
     * Bitwise OR two bytes on top of the stack
     * @example Syntax: `BOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-321-bor
     */
    const val BOR: Byte = 69

    /**
     * Bitwise OR two shorts (the first 4 bytes)
     * @example Syntax: `SOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-322-sor
     */
    const val SOR: Byte = 70

    /**
     * Bitwise OR two ints (the first 8 bytes)
     * @example Syntax: `IOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-323-ior
     */
    const val IOR: Byte = 71

    /**
     * Bitwise OR two longs (the first 16 bytes)
     * @example Syntax: `LOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-324-lor
     */
    const val LOR: Byte = 72

    // Bitwise XOR instructions

    /**
     * Bitwise XOR two bytes on top of the stack
     * @example Syntax: `BXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-331-bxor
     */
    const val BXOR: Byte = 73

    /**
     * Bitwise XOR two shorts (the first 4 bytes)
     * @example Syntax: `SXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-332-sxor
     */
    const val SXOR: Byte = 74

    /**
     * Bitwise XOR two ints (the first 8 bytes)
     * @example Syntax: `IXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-333-ixor
     */
    const val IXOR: Byte = 75

    /**
     * Bitwise XOR two longs (the first 16 bytes)
     * @example Syntax: `LXOR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-334-lxor
     */
    const val LXOR: Byte = 76

    // Bitwise NOT

    /**
     * Bitwise NOT the top byte on the stack
     * @example Syntax `BNOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-341-bnot
     */
    const val BNOT: Byte = 77

    /**
     * Bitwise NOT the top short on the stack
     * @example Syntax `SNOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-342-snot
     */
    const val SNOT: Byte = 78

    /**
     * Bitwise NOT the top int on the stack
     * @example Syntax `INOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-343-inot
     */
    const val INOT: Byte = 79

    /**
     * Bitwise NOT the top long on the stack
     * @example Syntax `LNOT`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-344-inot
     */
    const val LNOT: Byte = 80

    // Shift instructions

    /**
     * Bitwise shift left a byte (the top byte is the amount, the second byte is the value to shift)
     * @example Syntax: `BSHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-351-bshl
     */
    const val BSHL: Byte = 81

    /**
     * Bitwise shift left a short (the top byte is the amount, the second short is the value to shift)
     * @example Syntax: `SSHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-352-sshl
     */
    const val SSHL: Byte = 82

    /**
     * Bitwise shift left an int (the top byte is the amount, the second int is the value to shift)
     * @example Syntax: `ISHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-353-ishl
     */
    const val ISHL: Byte = 83

    /**
     * Bitwise shift left a long (the top byte is the amount, the second long is the value to shift)
     * @example Syntax: `LSHL`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-354-lshl
     */
    const val LSHL: Byte = 84

    // Shift right instructions

    /**
     * Bitwise shift right a byte (the top byte is the amount, the second byte is the value to shift)
     * @example Syntax: `BSHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-361-bshr
     */
    const val BSHR: Byte = 85

    /**
     * Bitwise shift right a short (the top byte is the amount, the second short is the value to shift)
     * @example Syntax: `SSHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-362-sshr
     */
    const val SSHR: Byte = 86

    /**
     * Bitwise shift right an int (the top byte is the amount, the second int is the value to shift)
     * @example Syntax: `ISHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-363-ishr
     */
    const val ISHR: Byte = 87

    /**
     * Bitwise shift right a long (the top byte is the amount, the second long is the value to shift)
     * @example Syntax: `LSHR`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-364-lshr
     */
    const val LSHR: Byte = 88

    // Shift right unsigned instructions

    /**
     * Bitwise shift right unsigned a byte (the top byte is the amount, the second byte is the value to shift)
     * @example Syntax: `BSHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-371-bshru
     */
    const val BSHRU: Byte = 89

    /**
     * Bitwise shift right unsigned a short (the top byte is the amount, the second short is the value to shift)
     * @example Syntax: `SSHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-372-sshr
     */
    const val SSHRU: Byte = 90

    /**
     * Bitwise shift right unsigned an int (the top byte is the amount, the second int is the value to shift)
     * @example Syntax: `ISHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-373-ishr
     */
    const val ISHRU: Byte = 91

    /**
     * Bitwise shift right unsigned a long (the top byte is the amount, the second long is the value to shift)
     * @example Syntax: `LSHRU`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-374-lshr
     */
    const val LSHRU: Byte = 92

    // Increment instructions

    /**
     * Increment a byte
     * @example Syntax: `BINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-271-binc
     */
    const val BINC: Byte = 93

    /**
     * Increment a short
     * @example Syntax: `SINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-272-sinc
     */
    const val SINC: Byte = 94

    /**
     * Increment an int
     * @example Syntax: `IINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-273-iinc
     */
    const val IINC: Byte = 95

    /**
     * Increment a long
     * @example Syntax: `LINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-274-linc
     */
    const val LINC: Byte = 96

    /**
     * Increment a float
     * @example Syntax: `FINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-275-finc
     */
    const val FINC: Byte = 97

    /**
     * Increment a double
     * @example Syntax: `DINC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-276-dinc
     */
    const val DINC: Byte = 98

    // Decrement instructions

    /**
     * Decrement a byte
     * @example Syntax: `BDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-281-bdec
     */
    const val BDEC: Byte = 99

    /**
     * Decrement a short
     * @example Syntax: `SDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-282-sdec
     */
    const val SDEC: Byte = 100

    /**
     * Decrement an int
     * @example Syntax: `IDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-283-idec
     */
    const val IDEC: Byte = 101

    /**
     * Decrement a long
     * @example Syntax: `LDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-284-ldec
     */
    const val LDEC: Byte = 102

    /**
     * Decrement a float
     * @example Syntax: `FDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-285-fdec
     */
    const val FDEC: Byte = 103

    /**
     * Decrement a double
     * @example Syntax: `DDEC`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-286-ddec
     */
    const val DDEC: Byte = 104

    // Compare instructions

    /**
     * Compare two bytes
     *
     * Place the result on the stack (as a byte):
     * 0 (00000000) if the first value is less than the second
     * 1 (00000001) if the first value is equal to the second
     * 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `BCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-411-bcmp
     */
    const val BCMP: Byte = 105

    /**
     * Compare two shorts
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `SCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-412-scmp
     */
    const val SCMP: Byte = 106

    /**
     * Compare two ints
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `ICMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-413-icmp
     */
    const val ICMP: Byte = 107

    /**
     * Compare two longs
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `LCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-414-lcmp
     */
    const val LCMP: Byte = 108

    /**
     * Compare two floats
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `FCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-415-fcmp
     */
    const val FCMP: Byte = 109

    /**
     * Compare two doubles
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `DCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-416-dcmp
     */
    const val DCMP: Byte = 110

    /**
     * Compare two unsigned bytes
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `UBCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-417-ubcmp
     */
    const val UBCMP: Byte = 111

    /**
     * Compare two unsigned shorts
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `USCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-418-uscmp
     */
    const val USCMP: Byte = 112

    /**
     * Compare two unsigned ints
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `UICMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-419-uicmp
     */
    const val UICMP: Byte = 113

    /**
     * Compare two unsigned longs
     *
     * Place the result on the stack (as a byte):
     * - 0 (00000000) if the first value is less than the second
     * - 1 (00000001) if the first value is equal to the second
     * - 2 (00000010) if the first value is greater than the second
     *
     * @example Syntax: `ULCMP`
     * @see - https://shakelang.com/specification/bytecode/instructions/#-4110-ulcmp
     */
    const val ULCMP: Byte = 114

    // Jump instructions

    /**
     * Jump to an address
     * @example Syntax: `JMP` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions/#-511-jmp
     */
    const val JMP: Byte = 115

    /**
     * Jump to an address if the top two values are equal
     * (takes the top byte value and jumps if it is 1)
     * @example Syntax: `JE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions/#-514-je
     */
    const val JE: Byte = 116

    /**
     * Jump to an address if the top two values are not equal
     * (takes the top byte value and jumps if it is not 1)
     * @example Syntax: `JNE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions/#-515-jne
     */
    const val JNE: Byte = 117

    /**
     * Jump to an address if the top two values are less than
     * (takes the top byte value and jumps if it is 2)
     * @example Syntax: `JL` <u4 address>
     * @deprecated Use [JZ] instead, it does the same thing
     * @see - https://shakelang.com/specification/bytecode/instructions#-518-jl
     */
    const val JL: Byte = 118

    /**
     * Jump to an address if the top two values are greater than
     * (takes the top byte value and jumps if it is 0)
     * @example Syntax: `JGT` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-516-jg
     */
    const val JG: Byte = 119

    /**
     * Jump to an address if the top two values are less than or equal
     * (takes the top byte value and jumps if it is 1 or 2)
     * @example Syntax: `JLE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-519-jle
     */
    const val JLE: Byte = 120

    /**
     * Jump to an address if the top two values are greater than or equal
     * (takes the top byte value and jumps if it is 0 or 1)
     * @example Syntax: `JGE` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-517-jge
     */
    const val JGE: Byte = 121

    /**
     * Jump to an address if there is a zero on top of the stack
     * (takes the top byte value and jumps if it is 0)
     * @example Syntax: `JZ` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-512-jz
     */
    const val JZ: Byte = 122

    /**
     * Jump to an address if there is not a zero on top of the stack
     * (takes the top byte value and jumps if it is 1)
     * @example Syntax: `JNZ` <u4 address>
     * @see - https://shakelang.com/specification/bytecode/instructions#-513-jnz
     */
    const val JNZ: Byte = 123

    /**
     * Return from a function
     * @example Syntax: `RET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-521-ret
     */
    const val RET: Byte = 124

    /**
     * Store a byte as return value
     * @example Syntax: `BRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-531-bret
     */
    const val BRET: Byte = 125

    /**
     * Store a short as return value
     * @example Syntax: `SRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-532-sret
     */
    const val SRET: Byte = 126

    /**
     * Store an int as return value
     * @example Syntax: `IRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-533-iret
     */
    const val IRET: Byte = 127

    /**
     * Store a long as return value
     * @example Syntax: `LRET`
     * @see - https://shakelang.com/specification/bytecode/instructions#-534-lret
     */
    const val LRET: Byte = -128

    /**
     * Primitive cast operation. The top element of the stack will be cast.
     * @example Syntax `PCAST` <u1 cast-type>
     * @see - https://shakelang.com/specification/bytecode/instructions#-531-pcast
     */
    const val PCAST: Byte = -127

}
