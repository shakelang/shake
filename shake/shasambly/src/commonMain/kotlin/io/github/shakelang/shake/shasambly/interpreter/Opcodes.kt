package io.github.shakelang.shake.shasambly.interpreter

object Opcodes {

    const val INCR_STACK: Byte = 0x01 // Syntax: INCR_STACK, u2 stack_size (Increase the variable stack, the new stack has stack_size bytes of space)
    const val DECR_STACK: Byte = 0x02 // Syntax: DECR_STACK ; Removes the top stack level
    const val JUMP_STATIC: Byte = 0x03 // Syntax: JUMP_STATIC, u4 position ; Jump to the given position
    const val JUMP_DYNAMIC: Byte = 0x04 // Syntax: JUMP_DYNAMIC ; Jump to the top u4 element on the stack
    const val JUMP_IF: Byte = 0x05 // Syntax: JUMP_IF, u4 position ; Jump if the top stack boolean is true
    const val INVOKE_NATIVE: Byte = 0x06 // Syntax: INVOKE_NATIVE, u2 native [bytes] ; Invoke native function with the given id
    const val GLOB_ADDR: Byte = 0x07 // Syntax: GLOB_ADDR, u2 position ; Puts the global address of a local variable on top of the stack

    const val B_GET_LOCAL: Byte = 0x10 // Syntax: B_GET_LOCAL, u2 position ; Load a local byte onto the stack
    const val S_GET_LOCAL: Byte = 0x11 // Syntax: S_GET_LOCAL, u2 position ; Load a local short onto the stack
    const val I_GET_LOCAL: Byte = 0x12 // Syntax: I_GET_LOCAL, u2 position ; Load a local integer onto the stack
    const val L_GET_LOCAL: Byte = 0x13 // Syntax: L_GET_LOCAL, u2 position ; Load a local long onto the stack

    const val B_STORE_LOCAL: Byte = 0x14 // Syntax: B_STORE_LOCAL, u2 position ; Store the top byte from the stack into local
    const val S_STORE_LOCAL: Byte = 0x15 // Syntax: S_STORE_LOCAL, u2 position ; Store the top short from the stack into local
    const val I_STORE_LOCAL: Byte = 0x16 // Syntax: I_STORE_LOCAL, u2 position ; Store the top integer from the stack into local
    const val L_STORE_LOCAL: Byte = 0x17 // Syntax: L_STORE_LOCAL, u2 position ; Store the top long from the stack into local

    const val B_PUSH: Byte = 0x18 // Syntax: B_PUSH u1 ; Pushes a byte onto the stack
    const val S_PUSH: Byte = 0x19 // Syntax: S_PUSH u2 ; Pushes a short onto the stack
    const val I_PUSH: Byte = 0x1a // Syntax: I_PUSH u4 ; Pushes an integer onto the stack
    const val L_PUSH: Byte = 0x1b // Syntax: L_PUSH u8 ; Pushes a long onto the stack

    const val B_ADD: Byte = 0x1c // Syntax: B_ADD ; Adds the top two bytes and leaves the result on the stack
    const val B_SUB: Byte = 0x1d // Syntax: B_SUB ; Subtracts the top two bytes and leaves the result on the stack
    const val B_MUL: Byte = 0x1e // Syntax: B_MUL ; Multiplies the top two bytes and leaves the result on the stack
    const val B_DIV: Byte = 0x1f // Syntax: B_DIV ; Divides the top two bytes and leaves the result on the stack
    const val B_MOD: Byte = 0x20 // Syntax: B_MOD ; Calculates the modulo of the top two bytes and leaves the result on the stack

    const val S_ADD: Byte = 0x21 // Syntax: S_ADD ; Adds the top two shorts and leaves the result on the stack
    const val S_SUB: Byte = 0x22 // Syntax: S_SUB ; Subtracts the top two shorts and leaves the result on the stack
    const val S_MUL: Byte = 0x23 // Syntax: S_MUL ; Multiplies the top two shorts and leaves the result on the stack
    const val S_DIV: Byte = 0x24 // Syntax: S_DIV ; Divides the top two shorts and leaves the result on the stack
    const val S_MOD: Byte = 0x25 // Syntax: S_MOD ; Calculates the modulo of the top two shorts and leaves the result on the stack

    const val I_ADD: Byte = 0x26 // Syntax: I_ADD ; Adds the top two integers and leaves the result on the stack
    const val I_SUB: Byte = 0x27 // Syntax: I_SUB ; Subtracts the top two integers and leaves the result on the stack
    const val I_MUL: Byte = 0x28 // Syntax: I_MUL ; Multiplies the top two integers and leaves the result on the stack
    const val I_DIV: Byte = 0x29 // Syntax: I_DIV ; Divides the top two integers and leaves the result on the stack
    const val I_MOD: Byte = 0x2a // Syntax: I_MOD ; Calculates the modulo of the top two integers and leaves the result on the stack

    const val L_ADD: Byte = 0x2b // Syntax: L_ADD ; Adds the top two longs and leaves the result on the stack
    const val L_SUB: Byte = 0x2c // Syntax: L_SUB ; Subtracts the top two longs and leaves the result on the stack
    const val L_MUL: Byte = 0x2d // Syntax: L_MUL ; Multiplies the top two longs and leaves the result on the stack
    const val L_DIV: Byte = 0x2e // Syntax: L_DIV ; Divides the top two longs and leaves the result on the stack
    const val L_MOD: Byte = 0x2f // Syntax: L_MOD ; Calculates the modulo of the top two longs and leaves the result on the stack

    const val F_ADD: Byte = 0x30 // Syntax: F_ADD ; Adds the top two floats and leaves the result on the stack
    const val F_SUB: Byte = 0x31 // Syntax: F_SUB ; Subtracts the top two floats and leaves the result on the stack
    const val F_MUL: Byte = 0x32 // Syntax: F_MUL ; Multiplies the top two floats and leaves the result on the stack
    const val F_DIV: Byte = 0x33 // Syntax: F_DIV ; Divides the top two floats and leaves the result on the stack
    const val F_MOD: Byte = 0x34 // Syntax: F_MOD ; Calculates the modulo of the top two floats and leaves the result on the stack

    const val D_ADD: Byte = 0x35 // Syntax: D_ADD ; Adds the top two doubles and leaves the result on the stack
    const val D_SUB: Byte = 0x36 // Syntax: D_SUB ; Subtracts the top two doubles and leaves the result on the stack
    const val D_MUL: Byte = 0x37 // Syntax: D_MUL ; Multiplies the top two doubles and leaves the result on the stack
    const val D_DIV: Byte = 0x38 // Syntax: D_DIV ; Divides the top two doubles and leaves the result on the stack
    const val D_MOD: Byte = 0x39 // Syntax: D_MOD ; Calculates the modulo of the top two doubles and leaves the result on the stack

    const val B_EQ: Byte = 0x03a // Syntax: B_EQ ; Calculates a boolean if the top two bytes are similar to each other
    const val S_EQ: Byte = 0x03b // Syntax: S_EQ ; Calculates a boolean if the top two shorts are similar to each other
    const val I_EQ: Byte = 0x03c // Syntax: I_EQ ; Calculates a boolean if the top two integers are similar to each other
    const val L_EQ: Byte = 0x03d // Syntax: L_EQ ; Calculates a boolean if the top two longs are similar to each other
    const val F_EQ: Byte = 0x03e // Syntax: F_EQ ; Calculates a boolean if the top two floats are similar to each other
    const val D_EQ: Byte = 0x03f // Syntax: D_EQ ; Calculates a boolean if the top two doubles are similar to each other

    const val B_BIGGER: Byte = 0x040 // Syntax: B_BIGGER ; Calculates a boolean if the second but top byte is bigger than the top byte
    const val S_BIGGER: Byte = 0x041 // Syntax: S_BIGGER ; Calculates a boolean if the second but top short is bigger than the top byte
    const val I_BIGGER: Byte = 0x042 // Syntax: I_BIGGER ; Calculates a boolean if the second but top integer is bigger than the top byte
    const val L_BIGGER: Byte = 0x043 // Syntax: L_BIGGER ; Calculates a boolean if the second but top long is bigger than the top byte
    const val F_BIGGER: Byte = 0x044 // Syntax: F_BIGGER ; Calculates a boolean if the second but top float is bigger than the top byte
    const val D_BIGGER: Byte = 0x045 // Syntax: D_BIGGER ; Calculates a boolean if the second but top double is bigger than the top byte

    const val B_SMALLER: Byte = 0x046 // Syntax: B_SMALLER ; Calculates a boolean if the second but top byte is smaller than the top byte
    const val S_SMALLER: Byte = 0x047 // Syntax: S_SMALLER ; Calculates a boolean if the second but top short is smaller than the top byte
    const val I_SMALLER: Byte = 0x048 // Syntax: I_SMALLER ; Calculates a boolean if the second but top integer is smaller than the top byte
    const val L_SMALLER: Byte = 0x049 // Syntax: L_SMALLER ; Calculates a boolean if the second but top long is smaller than the top byte
    const val F_SMALLER: Byte = 0x04a // Syntax: F_SMALLER ; Calculates a boolean if the second but top float is smaller than the top byte
    const val D_SMALLER: Byte = 0x04b // Syntax: D_SMALLER ; Calculates a boolean if the second but top double is smaller than the top byte

    const val B_BIGGER_EQ: Byte = 0x04c // Syntax: B_BIGGER_EQ ; Calculates a boolean if the second but top byte is bigger or equal than the top byte
    const val S_BIGGER_EQ: Byte = 0x04d // Syntax: S_BIGGER_EQ ; Calculates a boolean if the second but top short is bigger or equal than the top byte
    const val I_BIGGER_EQ: Byte = 0x04e // Syntax: I_BIGGER_EQ ; Calculates a boolean if the second but top integer is bigger or equal than the top byte
    const val L_BIGGER_EQ: Byte = 0x04f // Syntax: L_BIGGER_EQ ; Calculates a boolean if the second but top long is bigger or equal than the top byte
    const val F_BIGGER_EQ: Byte = 0x050 // Syntax: F_BIGGER_EQ ; Calculates a boolean if the second but top float is bigger or equal than the top byte
    const val D_BIGGER_EQ: Byte = 0x051 // Syntax: D_BIGGER_EQ ; Calculates a boolean if the second but top double is bigger or equal than the top byte

    const val B_SMALLER_EQ: Byte = 0x052 // Syntax: B_SMALLER_EQ ; Calculates a boolean if the second but top byte is smaller or equal than the top byte
    const val S_SMALLER_EQ: Byte = 0x053 // Syntax: S_SMALLER_EQ ; Calculates a boolean if the second but top short is smaller or equal than the top byte
    const val I_SMALLER_EQ: Byte = 0x054 // Syntax: I_SMALLER_EQ ; Calculates a boolean if the second but top integer is smaller or equal than the top byte
    const val L_SMALLER_EQ: Byte = 0x055 // Syntax: L_SMALLER_EQ ; Calculates a boolean if the second but top long is smaller or equal than the top byte
    const val F_SMALLER_EQ: Byte = 0x056 // Syntax: F_SMALLER_EQ ; Calculates a boolean if the second but top float is smaller or equal than the top byte
    const val D_SMALLER_EQ: Byte = 0x057 // Syntax: D_SMALLER_EQ ; Calculates a boolean if the second but top double is smaller or equal than the top byte

    const val BOOL_NOT: Byte = 0x058 // Syntax: BOOL_NOT ; Put the opposite of the top boolean onto the stack

    const val B_GET_GLOBAL: Byte = 0x059 // Syntax: B_GET_GLOBAL, u4 position ; Get a global byte at a given position
    const val S_GET_GLOBAL: Byte = 0x05a // Syntax: S_GET_GLOBAL, u4 position ; Get a global short at a given position
    const val I_GET_GLOBAL: Byte = 0x05b // Syntax: I_GET_GLOBAL, u4 position ; Get a global int at a given position
    const val L_GET_GLOBAL: Byte = 0x05c // Syntax: L_GET_GLOBAL, u4 position ; Get a global long at a given position

    const val B_GET_GLOBAL_DYNAMIC: Byte = 0x05d // Syntax: B_GET_GLOBAL ; Get a global byte at a given position (position is the top stack integer)
    const val S_GET_GLOBAL_DYNAMIC: Byte = 0x05e // Syntax: S_GET_GLOBAL ; Get a global short at a given position (position is the top stack integer)
    const val I_GET_GLOBAL_DYNAMIC: Byte = 0x05f // Syntax: I_GET_GLOBAL ; Get a global int at a given position (position is the top stack integer)
    const val L_GET_GLOBAL_DYNAMIC: Byte = 0x060 // Syntax: L_GET_GLOBAL ; Get a global long at a given position (position is the top stack integer)

    const val B_STORE_GLOBAL: Byte = 0x061 // Syntax: B_GET_GLOBAL, u4 position ; Get a global byte at a given position
    const val S_STORE_GLOBAL: Byte = 0x062 // Syntax: S_GET_GLOBAL, u4 position ; Get a global short at a given position
    const val I_STORE_GLOBAL: Byte = 0x063 // Syntax: I_GET_GLOBAL, u4 position ; Get a global int at a given position
    const val L_STORE_GLOBAL: Byte = 0x064 // Syntax: L_GET_GLOBAL, u4 position ; Get a global long at a given position

    const val B_STORE_GLOBAL_DYNAMIC: Byte = 0x065 // Syntax: B_GET_GLOBAL ; Get a global byte at a given position (position is the top stack integer)
    const val S_STORE_GLOBAL_DYNAMIC: Byte = 0x066 // Syntax: S_GET_GLOBAL ; Get a global short at a given position (position is the top stack integer)
    const val I_STORE_GLOBAL_DYNAMIC: Byte = 0x067 // Syntax: I_GET_GLOBAL ; Get a global int at a given position (position is the top stack integer)
    const val L_STORE_GLOBAL_DYNAMIC: Byte = 0x068 // Syntax: L_GET_GLOBAL ; Get a global long at a given position (position is the top stack integer)

    const val B_NEG: Byte = 0x069 // Syntax: B_NEG ; Negate the top byte
    const val S_NEG: Byte = 0x06a // Syntax: S_NEG ; Negate the top short
    const val I_NEG: Byte = 0x06b // Syntax: I_NEG ; Negate the top integer
    const val L_NEG: Byte = 0x06c // Syntax: L_NEG ; Negate the top long
    const val F_NEG: Byte = 0x06d // Syntax: F_NEG ; Negate the top float
    const val D_NEG: Byte = 0x06e // Syntax: D_NEG ; Negate the top double

    const val B_ABS: Byte = 0x06f // Syntax: B_ABS ; Absolute value of the top byte
    const val S_ABS: Byte = 0x070 // Syntax: S_ABS ; Absolute value of the top short
    const val I_ABS: Byte = 0x071 // Syntax: I_ABS ; Absolute value of the top integer
    const val L_ABS: Byte = 0x072 // Syntax: L_ABS ; Absolute value of the top long
    const val F_ABS: Byte = 0x073 // Syntax: F_ABS ; Absolute value of the top float
    const val D_ABS: Byte = 0x074 // Syntax: D_ABS ; Absolute value of the top double

    const val PRIMITIVE_CAST: Byte = 0x075 // Syntax: PRIMITIVE_CAST CAST ; Cast the top value on the stack 4 Bits for from type, 4 Bits for to type

}