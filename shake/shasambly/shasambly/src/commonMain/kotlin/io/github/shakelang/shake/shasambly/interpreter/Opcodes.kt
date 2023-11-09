package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.shake.util.parseutils.bGenerateUByte

object Opcodes {

    val INCR_STACK: Byte =
        bGenerateUByte(true) // Syntax: INCR_STACK, u2 stack_size (Increase the variable stack, the new stack has stack_size bytes of space)
    val DECR_STACK: Byte = bGenerateUByte() // Syntax: DECR_STACK ; Removes the top stack level
    val JUMP_STATIC: Byte = bGenerateUByte() // Syntax: JUMP_STATIC, u4 position ; Jump to the given position
    val JUMP_DYNAMIC: Byte = bGenerateUByte() // Syntax: JUMP_DYNAMIC ; Jump to the top u4 element on the stack
    val JUMP_IF: Byte = bGenerateUByte() // Syntax: JUMP_IF, u4 position ; Jump if the top stack boolean is true
    val INVOKE_NATIVE: Byte =
        bGenerateUByte() // Syntax: INVOKE_NATIVE, u2 native [bytes] ; Invoke native function with the given id
    val GLOB_ADDR: Byte =
        bGenerateUByte() // Syntax: GLOB_ADDR, u2 position ; Puts the global address of a local variable on top of the stack

    val B_GET_LOCAL: Byte = bGenerateUByte() // Syntax: B_GET_LOCAL, u2 position ; Load a local byte onto the stack
    val S_GET_LOCAL: Byte = bGenerateUByte() // Syntax: S_GET_LOCAL, u2 position ; Load a local short onto the stack
    val I_GET_LOCAL: Byte = bGenerateUByte() // Syntax: I_GET_LOCAL, u2 position ; Load a local integer onto the stack
    val L_GET_LOCAL: Byte = bGenerateUByte() // Syntax: L_GET_LOCAL, u2 position ; Load a local long onto the stack

    val B_STORE_LOCAL: Byte =
        bGenerateUByte() // Syntax: B_STORE_LOCAL, u2 position ; Store the top byte from the stack into local
    val S_STORE_LOCAL: Byte =
        bGenerateUByte() // Syntax: S_STORE_LOCAL, u2 position ; Store the top short from the stack into local
    val I_STORE_LOCAL: Byte =
        bGenerateUByte() // Syntax: I_STORE_LOCAL, u2 position ; Store the top integer from the stack into local
    val L_STORE_LOCAL: Byte =
        bGenerateUByte() // Syntax: L_STORE_LOCAL, u2 position ; Store the top long from the stack into local

    val B_PUSH: Byte = bGenerateUByte() // Syntax: B_PUSH u1 ; Pushes a byte onto the stack
    val S_PUSH: Byte = bGenerateUByte() // Syntax: S_PUSH u2 ; Pushes a short onto the stack
    val I_PUSH: Byte = bGenerateUByte() // Syntax: I_PUSH u4 ; Pushes an integer onto the stack
    val L_PUSH: Byte = bGenerateUByte() // Syntax: L_PUSH u8 ; Pushes a long onto the stack

    val B_ADD: Byte = bGenerateUByte() // Syntax: B_ADD ; Adds the top two bytes and leaves the result on the stack
    val B_SUB: Byte = bGenerateUByte() // Syntax: B_SUB ; Subtracts the top two bytes and leaves the result on the stack
    val B_MUL: Byte =
        bGenerateUByte() // Syntax: B_MUL ; Multiplies the top two bytes and leaves the result on the stack
    val B_DIV: Byte = bGenerateUByte() // Syntax: B_DIV ; Divides the top two bytes and leaves the result on the stack
    val B_MOD: Byte =
        bGenerateUByte() // Syntax: B_MOD ; Calculates the modulo of the top two bytes and leaves the result on the stack

    val S_ADD: Byte = bGenerateUByte() // Syntax: S_ADD ; Adds the top two shorts and leaves the result on the stack
    val S_SUB: Byte =
        bGenerateUByte() // Syntax: S_SUB ; Subtracts the top two shorts and leaves the result on the stack
    val S_MUL: Byte =
        bGenerateUByte() // Syntax: S_MUL ; Multiplies the top two shorts and leaves the result on the stack
    val S_DIV: Byte = bGenerateUByte() // Syntax: S_DIV ; Divides the top two shorts and leaves the result on the stack
    val S_MOD: Byte =
        bGenerateUByte() // Syntax: S_MOD ; Calculates the modulo of the top two shorts and leaves the result on the stack

    val I_ADD: Byte = bGenerateUByte() // Syntax: I_ADD ; Adds the top two integers and leaves the result on the stack
    val I_SUB: Byte =
        bGenerateUByte() // Syntax: I_SUB ; Subtracts the top two integers and leaves the result on the stack
    val I_MUL: Byte =
        bGenerateUByte() // Syntax: I_MUL ; Multiplies the top two integers and leaves the result on the stack
    val I_DIV: Byte =
        bGenerateUByte() // Syntax: I_DIV ; Divides the top two integers and leaves the result on the stack
    val I_MOD: Byte =
        bGenerateUByte() // Syntax: I_MOD ; Calculates the modulo of the top two integers and leaves the result on the stack

    val L_ADD: Byte = bGenerateUByte() // Syntax: L_ADD ; Adds the top two longs and leaves the result on the stack
    val L_SUB: Byte = bGenerateUByte() // Syntax: L_SUB ; Subtracts the top two longs and leaves the result on the stack
    val L_MUL: Byte =
        bGenerateUByte() // Syntax: L_MUL ; Multiplies the top two longs and leaves the result on the stack
    val L_DIV: Byte = bGenerateUByte() // Syntax: L_DIV ; Divides the top two longs and leaves the result on the stack
    val L_MOD: Byte =
        bGenerateUByte() // Syntax: L_MOD ; Calculates the modulo of the top two longs and leaves the result on the stack

    val F_ADD: Byte = bGenerateUByte() // Syntax: F_ADD ; Adds the top two floats and leaves the result on the stack
    val F_SUB: Byte =
        bGenerateUByte() // Syntax: F_SUB ; Subtracts the top two floats and leaves the result on the stack
    val F_MUL: Byte =
        bGenerateUByte() // Syntax: F_MUL ; Multiplies the top two floats and leaves the result on the stack
    val F_DIV: Byte = bGenerateUByte() // Syntax: F_DIV ; Divides the top two floats and leaves the result on the stack
    val F_MOD: Byte =
        bGenerateUByte() // Syntax: F_MOD ; Calculates the modulo of the top two floats and leaves the result on the stack

    val D_ADD: Byte = bGenerateUByte() // Syntax: D_ADD ; Adds the top two doubles and leaves the result on the stack
    val D_SUB: Byte =
        bGenerateUByte() // Syntax: D_SUB ; Subtracts the top two doubles and leaves the result on the stack
    val D_MUL: Byte =
        bGenerateUByte() // Syntax: D_MUL ; Multiplies the top two doubles and leaves the result on the stack
    val D_DIV: Byte = bGenerateUByte() // Syntax: D_DIV ; Divides the top two doubles and leaves the result on the stack
    val D_MOD: Byte =
        bGenerateUByte() // Syntax: D_MOD ; Calculates the modulo of the top two doubles and leaves the result on the stack

    val B_EQ: Byte =
        bGenerateUByte() // Syntax: B_EQ ; Calculates a boolean if the top two bytes are similar to each other
    val S_EQ: Byte =
        bGenerateUByte() // Syntax: S_EQ ; Calculates a boolean if the top two shorts are similar to each other
    val I_EQ: Byte =
        bGenerateUByte() // Syntax: I_EQ ; Calculates a boolean if the top two integers are similar to each other
    val L_EQ: Byte =
        bGenerateUByte() // Syntax: L_EQ ; Calculates a boolean if the top two longs are similar to each other
    val F_EQ: Byte =
        bGenerateUByte() // Syntax: F_EQ ; Calculates a boolean if the top two floats are similar to each other
    val D_EQ: Byte =
        bGenerateUByte() // Syntax: D_EQ ; Calculates a boolean if the top two doubles are similar to each other

    val B_BIGGER: Byte =
        bGenerateUByte() // Syntax: B_BIGGER ; Calculates a boolean if the second but top byte is bigger than the top byte
    val S_BIGGER: Byte =
        bGenerateUByte() // Syntax: S_BIGGER ; Calculates a boolean if the second but top short is bigger than the top byte
    val I_BIGGER: Byte =
        bGenerateUByte() // Syntax: I_BIGGER ; Calculates a boolean if the second but top integer is bigger than the top byte
    val L_BIGGER: Byte =
        bGenerateUByte() // Syntax: L_BIGGER ; Calculates a boolean if the second but top long is bigger than the top byte
    val F_BIGGER: Byte =
        bGenerateUByte() // Syntax: F_BIGGER ; Calculates a boolean if the second but top float is bigger than the top byte
    val D_BIGGER: Byte =
        bGenerateUByte() // Syntax: D_BIGGER ; Calculates a boolean if the second but top double is bigger than the top byte

    val B_SMALLER: Byte =
        bGenerateUByte() // Syntax: B_SMALLER ; Calculates a boolean if the second but top byte is smaller than the top byte
    val S_SMALLER: Byte =
        bGenerateUByte() // Syntax: S_SMALLER ; Calculates a boolean if the second but top short is smaller than the top byte
    val I_SMALLER: Byte =
        bGenerateUByte() // Syntax: I_SMALLER ; Calculates a boolean if the second but top integer is smaller than the top byte
    val L_SMALLER: Byte =
        bGenerateUByte() // Syntax: L_SMALLER ; Calculates a boolean if the second but top long is smaller than the top byte
    val F_SMALLER: Byte =
        bGenerateUByte() // Syntax: F_SMALLER ; Calculates a boolean if the second but top float is smaller than the top byte
    val D_SMALLER: Byte =
        bGenerateUByte() // Syntax: D_SMALLER ; Calculates a boolean if the second but top double is smaller than the top byte

    val B_BIGGER_EQ: Byte =
        bGenerateUByte() // Syntax: B_BIGGER_EQ ; Calculates a boolean if the second but top byte is bigger or equal than the top byte
    val S_BIGGER_EQ: Byte =
        bGenerateUByte() // Syntax: S_BIGGER_EQ ; Calculates a boolean if the second but top short is bigger or equal than the top byte
    val I_BIGGER_EQ: Byte =
        bGenerateUByte() // Syntax: I_BIGGER_EQ ; Calculates a boolean if the second but top integer is bigger or equal than the top byte
    val L_BIGGER_EQ: Byte =
        bGenerateUByte() // Syntax: L_BIGGER_EQ ; Calculates a boolean if the second but top long is bigger or equal than the top byte
    val F_BIGGER_EQ: Byte =
        bGenerateUByte() // Syntax: F_BIGGER_EQ ; Calculates a boolean if the second but top float is bigger or equal than the top byte
    val D_BIGGER_EQ: Byte =
        bGenerateUByte() // Syntax: D_BIGGER_EQ ; Calculates a boolean if the second but top double is bigger or equal than the top byte

    val B_SMALLER_EQ: Byte =
        bGenerateUByte() // Syntax: B_SMALLER_EQ ; Calculates a boolean if the second but top byte is smaller or equal than the top byte
    val S_SMALLER_EQ: Byte =
        bGenerateUByte() // Syntax: S_SMALLER_EQ ; Calculates a boolean if the second but top short is smaller or equal than the top byte
    val I_SMALLER_EQ: Byte =
        bGenerateUByte() // Syntax: I_SMALLER_EQ ; Calculates a boolean if the second but top integer is smaller or equal than the top byte
    val L_SMALLER_EQ: Byte =
        bGenerateUByte() // Syntax: L_SMALLER_EQ ; Calculates a boolean if the second but top long is smaller or equal than the top byte
    val F_SMALLER_EQ: Byte =
        bGenerateUByte() // Syntax: F_SMALLER_EQ ; Calculates a boolean if the second but top float is smaller or equal than the top byte
    val D_SMALLER_EQ: Byte =
        bGenerateUByte() // Syntax: D_SMALLER_EQ ; Calculates a boolean if the second but top double is smaller or equal than the top byte

    val BOOL_NOT: Byte = bGenerateUByte() // Syntax: BOOL_NOT ; Put the opposite of the top boolean onto the stack

    val B_GET_GLOBAL: Byte =
        bGenerateUByte() // Syntax: B_GET_GLOBAL, u4 position ; Get a global byte at a given position
    val S_GET_GLOBAL: Byte =
        bGenerateUByte() // Syntax: S_GET_GLOBAL, u4 position ; Get a global short at a given position
    val I_GET_GLOBAL: Byte =
        bGenerateUByte() // Syntax: I_GET_GLOBAL, u4 position ; Get a global int at a given position
    val L_GET_GLOBAL: Byte =
        bGenerateUByte() // Syntax: L_GET_GLOBAL, u4 position ; Get a global long at a given position

    val B_GET_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: B_GET_GLOBAL ; Get a global byte at a given position (position is the top stack integer)
    val S_GET_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: S_GET_GLOBAL ; Get a global short at a given position (position is the top stack integer)
    val I_GET_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: I_GET_GLOBAL ; Get a global int at a given position (position is the top stack integer)
    val L_GET_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: L_GET_GLOBAL ; Get a global long at a given position (position is the top stack integer)

    val B_STORE_GLOBAL: Byte =
        bGenerateUByte() // Syntax: B_GET_GLOBAL, u4 position ; Get a global byte at a given position
    val S_STORE_GLOBAL: Byte =
        bGenerateUByte() // Syntax: S_GET_GLOBAL, u4 position ; Get a global short at a given position
    val I_STORE_GLOBAL: Byte =
        bGenerateUByte() // Syntax: I_GET_GLOBAL, u4 position ; Get a global int at a given position
    val L_STORE_GLOBAL: Byte =
        bGenerateUByte() // Syntax: L_GET_GLOBAL, u4 position ; Get a global long at a given position

    val B_STORE_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: B_GET_GLOBAL ; Get a global byte at a given position (position is the top stack integer)
    val S_STORE_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: S_GET_GLOBAL ; Get a global short at a given position (position is the top stack integer)
    val I_STORE_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: I_GET_GLOBAL ; Get a global int at a given position (position is the top stack integer)
    val L_STORE_GLOBAL_DYNAMIC: Byte =
        bGenerateUByte() // Syntax: L_GET_GLOBAL ; Get a global long at a given position (position is the top stack integer)

    val B_NEG: Byte = bGenerateUByte() // Syntax: B_NEG ; Negate the top byte
    val S_NEG: Byte = bGenerateUByte() // Syntax: S_NEG ; Negate the top short
    val I_NEG: Byte = bGenerateUByte() // Syntax: I_NEG ; Negate the top integer
    val L_NEG: Byte = bGenerateUByte() // Syntax: L_NEG ; Negate the top long
    val F_NEG: Byte = bGenerateUByte() // Syntax: F_NEG ; Negate the top float
    val D_NEG: Byte = bGenerateUByte() // Syntax: D_NEG ; Negate the top double

    val B_ABS: Byte = bGenerateUByte() // Syntax: B_ABS ; Absolute value of the top byte
    val S_ABS: Byte = bGenerateUByte() // Syntax: S_ABS ; Absolute value of the top short
    val I_ABS: Byte = bGenerateUByte() // Syntax: I_ABS ; Absolute value of the top integer
    val L_ABS: Byte = bGenerateUByte() // Syntax: L_ABS ; Absolute value of the top long
    val F_ABS: Byte = bGenerateUByte() // Syntax: F_ABS ; Absolute value of the top float
    val D_ABS: Byte = bGenerateUByte() // Syntax: D_ABS ; Absolute value of the top double

    val PRIMITIVE_CAST: Byte =
        bGenerateUByte() // Syntax: PRIMITIVE_CAST CAST ; Cast the top value on the stack 4 Bits for from type, 4 Bits for to type

    val B_SHR: Byte =
        bGenerateUByte() // Syntax B_SHR ; Shift byte bits to the right (takes the top byte as number of shifts)
    val S_SHR: Byte =
        bGenerateUByte() // Syntax S_SHR ; Shift short bits to the right (takes the top byte as number of shifts)
    val I_SHR: Byte =
        bGenerateUByte() // Syntax I_SHR ; Shift int bits to the right (takes the top byte as number of shifts)
    val L_SHR: Byte =
        bGenerateUByte() // Syntax L_SHR ; Shift long bits to the right (takes the top byte as number of shifts)

    val B_SHL: Byte =
        bGenerateUByte() // Syntax B_SHL ; Shift byte bits to the left (takes the top byte as number of shifts)
    val S_SHL: Byte =
        bGenerateUByte() // Syntax S_SHL ; Shift short bits to the left (takes the top byte as number of shifts)
    val I_SHL: Byte =
        bGenerateUByte() // Syntax I_SHL ; Shift int bits to the left (takes the top byte as number of shifts)
    val L_SHL: Byte =
        bGenerateUByte() // Syntax L_SHL ; Shift long bits to the left (takes the top byte as number of shifts)

    val B_AND: Byte = bGenerateUByte() // Syntax B_SHL ; Combines two byte's bits using and
    val S_AND: Byte = bGenerateUByte() // Syntax S_SHL ; Combines two short's bits using and
    val I_AND: Byte = bGenerateUByte() // Syntax I_SHL ; Combines two int's bits using and
    val L_AND: Byte = bGenerateUByte() // Syntax L_SHL ; Combines two long's bits using and

    val B_OR: Byte = bGenerateUByte() // Syntax B_SHL ; Combines two byte's bits using or
    val S_OR: Byte = bGenerateUByte() // Syntax S_SHL ; Combines two short's bits using or
    val I_OR: Byte = bGenerateUByte() // Syntax I_SHL ; Combines two int's bits using or
    val L_OR: Byte = bGenerateUByte() // Syntax L_SHL ; Combines two long's bits using or

    val B_XOR: Byte = bGenerateUByte() // Syntax B_SHL ; Combines two byte's bits using xor
    val S_XOR: Byte = bGenerateUByte() // Syntax S_SHL ; Combines two short's bits using xor
    val I_XOR: Byte = bGenerateUByte() // Syntax I_SHL ; Combines two int's bits using xor
    val L_XOR: Byte = bGenerateUByte() // Syntax L_SHL ; Combines two long's bits using xor

}