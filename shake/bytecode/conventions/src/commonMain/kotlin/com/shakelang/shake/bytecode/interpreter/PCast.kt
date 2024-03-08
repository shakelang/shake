package com.shakelang.shake.bytecode.interpreter

import com.shakelang.util.primitives.calc.shl
import kotlin.experimental.or
import kotlin.jvm.JvmName

/**
 * Constants for casting operations.
 * We cast constants on top of the stack.
 * The stack is a stack of bytes. A cast operation takes the top n bytes and
 * casts them to a different type.
 *
 * Syntax of the cast byte: We can write the cast byte as 0xXY in hexadecimal
 * where X is the type we cast from and Y is the type we cast to.
 * It will be interpreted as follows:
 * Read type X from the stack and cast it to type Y.
 * Write the result back to the stack.
 *
 * Cast operations will change the size of the stack (if the size of the
 * type we cast to is different from the size of the type we cast from).
 *
 * Get more information about the cast byte in the
 * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
 *
 * @since 0.1.0
 * @version 0.1.0
 */
object PCast {

    /**
     * PCast constant for a byte
     *
     * To cast from a byte:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_BYTE], [PCast.BYTE_TO_SHORT], [PCast.BYTE_TO_INT],
     * [PCast.BYTE_TO_LONG], [PCast.BYTE_TO_UBYTE], [PCast.BYTE_TO_USHORT],
     * [PCast.BYTE_TO_UINT], [PCast.BYTE_TO_ULONG], [PCast.BYTE_TO_FLOAT],
     * [PCast.BYTE_TO_DOUBLE]
     *
     * To cast from a byte:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.BYTE
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_BYTE], [PCast.SHORT_TO_BYTE], [PCast.INT_TO_BYTE],
     * [PCast.LONG_TO_BYTE], [PCast.UBYTE_TO_BYTE], [PCast.USHORT_TO_BYTE],
     * [PCast.UINT_TO_BYTE], [PCast.ULONG_TO_BYTE], [PCast.FLOAT_TO_BYTE],
     * [PCast.DOUBLE_TO_BYTE]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE: UByte = 0u

    /**
     * PCast constant for a shorts
     *
     * To cast from a shorts:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_SHORT], [PCast.SHORT_TO_SHORT], [PCast.SHORT_TO_INT],
     * [PCast.SHORT_TO_LONG], [PCast.SHORT_TO_UBYTE], [PCast.SHORT_TO_USHORT],
     * [PCast.SHORT_TO_UINT], [PCast.SHORT_TO_ULONG], [PCast.SHORT_TO_FLOAT],
     * [PCast.SHORT_TO_DOUBLE]
     *
     * To cast from a shorts:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.SHORT
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_SHORT], [PCast.SHORT_TO_SHORT], [PCast.INT_TO_SHORT],
     * [PCast.LONG_TO_SHORT], [PCast.UBYTE_TO_SHORT], [PCast.USHORT_TO_SHORT],
     * [PCast.UINT_TO_SHORT], [PCast.ULONG_TO_SHORT], [PCast.FLOAT_TO_SHORT],
     * [PCast.DOUBLE_TO_SHORT]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT: UByte = 1u

    /**
     * PCast constant for an int
     *
     * To cast from an int:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_INT], [PCast.SHORT_TO_INT], [PCast.INT_TO_INT],
     * [PCast.INT_TO_LONG], [PCast.INT_TO_UBYTE], [PCast.INT_TO_USHORT],
     * [PCast.INT_TO_UINT], [PCast.INT_TO_ULONG], [PCast.INT_TO_FLOAT],
     * [PCast.INT_TO_DOUBLE]
     *
     * To cast from an int:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.INT
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_INT], [PCast.SHORT_TO_INT], [PCast.INT_TO_INT],
     * [PCast.LONG_TO_INT], [PCast.UBYTE_TO_INT], [PCast.USHORT_TO_INT],
     * [PCast.UINT_TO_INT], [PCast.ULONG_TO_INT], [PCast.FLOAT_TO_INT],
     * [PCast.DOUBLE_TO_INT]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT: UByte = 2u

    /**
     * PCast constant for a long
     *
     * To cast from a long:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_LONG], [PCast.SHORT_TO_LONG], [PCast.INT_TO_LONG],
     * [PCast.LONG_TO_LONG], [PCast.LONG_TO_UBYTE], [PCast.LONG_TO_USHORT],
     * [PCast.LONG_TO_UINT], [PCast.LONG_TO_ULONG], [PCast.LONG_TO_FLOAT],
     * [PCast.LONG_TO_DOUBLE]
     *
     * To cast from a long:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.LONG
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_LONG], [PCast.SHORT_TO_LONG], [PCast.INT_TO_LONG],
     * [PCast.LONG_TO_LONG], [PCast.UBYTE_TO_LONG], [PCast.USHORT_TO_LONG],
     * [PCast.UINT_TO_LONG], [PCast.ULONG_TO_LONG], [PCast.FLOAT_TO_LONG],
     * [PCast.DOUBLE_TO_LONG]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG: UByte = 3u

    /**
     * PCast constant for an unsigned byte
     *
     * To cast from an unsigned byte:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_UBYTE], [PCast.SHORT_TO_UBYTE], [PCast.INT_TO_UBYTE],
     * [PCast.LONG_TO_UBYTE], [PCast.UBYTE_TO_UBYTE], [PCast.USHORT_TO_UBYTE],
     * [PCast.UINT_TO_UBYTE], [PCast.ULONG_TO_UBYTE], [PCast.FLOAT_TO_UBYTE],
     * [PCast.DOUBLE_TO_UBYTE]
     *
     * To cast from an unsigned byte:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.UBYTE
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_UBYTE], [PCast.SHORT_TO_UBYTE], [PCast.INT_TO_UBYTE],
     * [PCast.LONG_TO_UBYTE], [PCast.UBYTE_TO_UBYTE], [PCast.USHORT_TO_UBYTE],
     * [PCast.UINT_TO_UBYTE], [PCast.ULONG_TO_UBYTE], [PCast.FLOAT_TO_UBYTE],
     * [PCast.DOUBLE_TO_UBYTE]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE: UByte = 4u

    /**
     * PCast constant for an unsigned shorts
     *
     * To cast from an unsigned shorts:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_USHORT], [PCast.SHORT_TO_USHORT], [PCast.INT_TO_USHORT],
     * [PCast.LONG_TO_USHORT], [PCast.UBYTE_TO_USHORT], [PCast.USHORT_TO_USHORT],
     * [PCast.UINT_TO_USHORT], [PCast.ULONG_TO_USHORT], [PCast.FLOAT_TO_USHORT],
     * [PCast.DOUBLE_TO_USHORT]
     *
     * To cast from an unsigned shorts:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.USHORT
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_USHORT], [PCast.SHORT_TO_USHORT], [PCast.INT_TO_USHORT],
     * [PCast.LONG_TO_USHORT], [PCast.UBYTE_TO_USHORT], [PCast.USHORT_TO_USHORT],
     * [PCast.UINT_TO_USHORT], [PCast.ULONG_TO_USHORT], [PCast.FLOAT_TO_USHORT],
     * [PCast.DOUBLE_TO_USHORT]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT: UByte = 5u

    /**
     * PCast constant for an unsigned int
     *
     * To cast from an unsigned int:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_UINT], [PCast.SHORT_TO_UINT], [PCast.INT_TO_UINT],
     * [PCast.LONG_TO_UINT], [PCast.UBYTE_TO_UINT], [PCast.USHORT_TO_UINT],
     * [PCast.UINT_TO_UINT], [PCast.ULONG_TO_UINT], [PCast.FLOAT_TO_UINT],
     * [PCast.DOUBLE_TO_UINT]
     *
     * To cast from an unsigned int:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.UINT
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_UINT], [PCast.SHORT_TO_UINT], [PCast.INT_TO_UINT],
     * [PCast.LONG_TO_UINT], [PCast.UBYTE_TO_UINT], [PCast.USHORT_TO_UINT],
     * [PCast.UINT_TO_UINT], [PCast.ULONG_TO_UINT], [PCast.FLOAT_TO_UINT],
     * [PCast.DOUBLE_TO_UINT]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT: UByte = 6u

    /**
     * PCast constant for an unsigned long
     *
     * To cast from an unsigned long:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_ULONG], [PCast.SHORT_TO_ULONG], [PCast.INT_TO_ULONG],
     * [PCast.LONG_TO_ULONG], [PCast.UBYTE_TO_ULONG], [PCast.USHORT_TO_ULONG],
     * [PCast.UINT_TO_ULONG], [PCast.ULONG_TO_ULONG], [PCast.FLOAT_TO_ULONG],
     * [PCast.DOUBLE_TO_ULONG]
     *
     * To cast from an unsigned long:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.ULONG
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_ULONG], [PCast.SHORT_TO_ULONG], [PCast.INT_TO_ULONG],
     * [PCast.LONG_TO_ULONG], [PCast.UBYTE_TO_ULONG], [PCast.USHORT_TO_ULONG],
     * [PCast.UINT_TO_ULONG], [PCast.ULONG_TO_ULONG], [PCast.FLOAT_TO_ULONG],
     * [PCast.DOUBLE_TO_ULONG]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG: UByte = 7u

    /**
     * PCast constant for a float
     *
     * To cast from a float:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_FLOAT], [PCast.SHORT_TO_FLOAT], [PCast.INT_TO_FLOAT],
     * [PCast.LONG_TO_FLOAT], [PCast.UBYTE_TO_FLOAT], [PCast.USHORT_TO_FLOAT],
     * [PCast.UINT_TO_FLOAT], [PCast.ULONG_TO_FLOAT], [PCast.FLOAT_TO_FLOAT],
     * [PCast.DOUBLE_TO_FLOAT]
     *
     * To cast from a float:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.FLOAT
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_FLOAT], [PCast.SHORT_TO_FLOAT], [PCast.INT_TO_FLOAT],
     * [PCast.LONG_TO_FLOAT], [PCast.UBYTE_TO_FLOAT], [PCast.USHORT_TO_FLOAT],
     * [PCast.UINT_TO_FLOAT], [PCast.ULONG_TO_FLOAT], [PCast.FLOAT_TO_FLOAT],
     * [PCast.DOUBLE_TO_FLOAT]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT: UByte = 8u

    /**
     * PCast constant for a doubles
     *
     * To cast from a doubles:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or [to]
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_DOUBLE], [PCast.SHORT_TO_DOUBLE], [PCast.INT_TO_DOUBLE],
     * [PCast.LONG_TO_DOUBLE], [PCast.UBYTE_TO_DOUBLE], [PCast.USHORT_TO_DOUBLE],
     * [PCast.UINT_TO_DOUBLE], [PCast.ULONG_TO_DOUBLE], [PCast.FLOAT_TO_DOUBLE],
     * [PCast.DOUBLE_TO_DOUBLE]
     *
     * To cast from a doubles:
     * ```kotlin
     * val castByte = ([from] shl 4) or PCast.DOUBLE
     * ```
     *
     * You can also use the cast constants:
     * [PCast.BYTE_TO_DOUBLE], [PCast.SHORT_TO_DOUBLE], [PCast.INT_TO_DOUBLE],
     * [PCast.LONG_TO_DOUBLE], [PCast.UBYTE_TO_DOUBLE], [PCast.USHORT_TO_DOUBLE],
     * [PCast.UINT_TO_DOUBLE], [PCast.ULONG_TO_DOUBLE], [PCast.FLOAT_TO_DOUBLE],
     * [PCast.DOUBLE_TO_DOUBLE]
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE: UByte = 9u

    /**
     * Cast from a byte to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#-84-pcast-0xa0)
     *
     * @deprecated No use in casting from byte to byte. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_BYTE: UByte = 0x00u

    /**
     * Cast from a byte to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions#84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_SHORT: UByte = 0x01u

    /**
     * Cast from a byte to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instructions84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_INT: UByte = 0x02u

    /**
     * Cast from a byte to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_LONG: UByte = 0x03u

    /**
     * Cast from a byte to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytecode/instruction84-pcast-0xa0)
     *
     * @deprecated Byte and UByte are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_UBYTE: UByte = 0x04u

    /**
     * Cast from a byte to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_USHORT: UByte = 0x05u

    /**
     * Cast from a byte to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_UINT: UByte = 0x06u

    /**
     * Cast from a byte to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_ULONG: UByte = 0x07u

    /**
     * Cast from a byte to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_FLOAT: UByte = 0x08u

    /**
     * Cast from a byte to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.BYTE shl 4) or PCast.DOUBLE
     * ```
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val BYTE_TO_DOUBLE: UByte = 0x09u

    /**
     * Cast from a shorts to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_BYTE: UByte = 0x10u

    /**
     * Cast from a shorts to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from shorts to shorts. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_SHORT: UByte = 0x11u

    /**
     * Cast from a shorts to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_INT: UByte = 0x12u

    /**
     * Cast from a shorts to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_LONG: UByte = 0x13u

    /**
     * Cast from a shorts to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_UBYTE: UByte = 0x14u

    /**
     * Cast from a shorts to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Short and UShort are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_USHORT: UByte = 0x15u

    /**
     * Cast from a shorts to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.UINT
     * ```
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_UINT: UByte = 0x16u

    /**
     * Cast from a shorts to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.ULONG
     * ```
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_ULONG: UByte = 0x17u

    /**
     * Cast from a shorts to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_FLOAT: UByte = 0x18u

    /**
     * Cast from a shorts to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.SHORT shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val SHORT_TO_DOUBLE: UByte = 0x19u

    /**
     * Cast from an int to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_BYTE: UByte = 0x20u

    /**
     * Cast from an int to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_SHORT: UByte = 0x21u

    /**
     * Cast from an int to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from int to int. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_INT: UByte = 0x22u

    /**
     * Cast from an int to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_LONG: UByte = 0x23u

    /**
     * Cast from an int to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_UBYTE: UByte = 0x24u

    /**
     * Cast from an int to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_USHORT: UByte = 0x25u

    /**
     * Cast from an int to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Int and UInt are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_UINT: UByte = 0x26u

    /**
     * Cast from an int to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_ULONG: UByte = 0x27u

    /**
     * Cast from an int to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_FLOAT: UByte = 0x28u

    /**
     * Cast from an int to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.INT shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val INT_TO_DOUBLE: UByte = 0x29u

    /**
     * Cast from a long to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_BYTE: UByte = 0x30u

    /**
     * Cast from a long to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_SHORT: UByte = 0x31u

    /**
     * Cast from a long to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_INT: UByte = 0x32u

    /**
     * Cast from a long to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from long to long. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_LONG: UByte = 0x33u

    /**
     * Cast from a long to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_UBYTE: UByte = 0x34u

    /**
     * Cast from a long to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_USHORT: UByte = 0x35u

    /**
     * Cast from a long to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_UINT: UByte = 0x36u

    /**
     * Cast from a long to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Long and ULong are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_ULONG: UByte = 0x37u

    /**
     * Cast from a long to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_FLOAT: UByte = 0x38u

    /**
     * Cast from a long to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.LONG shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val LONG_TO_DOUBLE: UByte = 0x39u

    /**
     * Cast from an unsigned byte to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Byte and UByte are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_BYTE: UByte = 0x40u

    /**
     * Cast from an unsigned byte to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_SHORT: UByte = 0x41u

    /**
     * Cast from an unsigned byte to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_INT: UByte = 0x42u

    /**
     * Cast from an unsigned byte to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_LONG: UByte = 0x43u

    /**
     * Cast from an unsigned byte to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from unsigned byte to unsigned byte. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_UBYTE: UByte = 0x44u

    /**
     * Cast from an unsigned byte to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_USHORT: UByte = 0x45u

    /**
     * Cast from an unsigned byte to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_UINT: UByte = 0x46u

    /**
     * Cast from an unsigned byte to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_ULONG: UByte = 0x47u

    /**
     * Cast from an unsigned byte to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_FLOAT: UByte = 0x48u

    /**
     * Cast from an unsigned byte to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UBYTE shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UBYTE_TO_DOUBLE: UByte = 0x49u

    /**
     * Cast from an unsigned shorts to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_BYTE: UByte = 0x50u

    /**
     * Cast from an unsigned shorts to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Short and UShort are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_SHORT: UByte = 0x51u

    /**
     * Cast from an unsigned shorts to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_INT: UByte = 0x52u

    /**
     * Cast from an unsigned shorts to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_LONG: UByte = 0x53u

    /**
     * Cast from an unsigned shorts to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_UBYTE: UByte = 0x54u

    /**
     * Cast from an unsigned shorts to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from unsigned shorts to unsigned shorts. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_USHORT: UByte = 0x55u

    /**
     * Cast from an unsigned shorts to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_UINT: UByte = 0x56u

    /**
     * Cast from an unsigned shorts to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_ULONG: UByte = 0x57u

    /**
     * Cast from an unsigned shorts to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_FLOAT: UByte = 0x58u

    /**
     * Cast from an unsigned shorts to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.USHORT shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val USHORT_TO_DOUBLE: UByte = 0x59u

    /**
     * Cast from an unsigned int to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_BYTE: UByte = 0x60u

    /**
     * Cast from an unsigned int to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_SHORT: UByte = 0x61u

    /**
     * Cast from an unsigned int to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Int and UInt are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_INT: UByte = 0x62u

    /**
     * Cast from an unsigned int to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_LONG: UByte = 0x63u

    /**
     * Cast from an unsigned int to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_UBYTE: UByte = 0x64u

    /**
     * Cast from an unsigned int to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_USHORT: UByte = 0x65u

    /**
     * Cast from an unsigned int to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.UINT
     * ```
     *
     * @deprecated No use in casting from unsigned int to unsigned int. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_UINT: UByte = 0x66u

    /**
     * Cast from an unsigned int to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_ULONG: UByte = 0x67u

    /**
     * Cast from an unsigned int to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_FLOAT: UByte = 0x68u

    /**
     * Cast from an unsigned int to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.UINT shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val UINT_TO_DOUBLE: UByte = 0x69u

    /**
     * Cast from an unsigned long to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_BYTE: UByte = 0x70u

    /**
     * Cast from an unsigned long to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_SHORT: UByte = 0x71u

    /**
     * Cast from an unsigned long to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_INT: UByte = 0x72u

    /**
     * Cast from an unsigned long to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated Long and ULong are two different interpretations of the same value.
     *             For this reason, this operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_LONG: UByte = 0x73u

    /**
     * Cast from an unsigned long to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_UBYTE: UByte = 0x74u

    /**
     * Cast from an unsigned long to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_USHORT: UByte = 0x75u

    /**
     * Cast from an unsigned long to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_UINT: UByte = 0x76u

    /**
     * Cast from an unsigned long to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from unsigned long to unsigned long. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_ULONG: UByte = 0x77u

    /**
     * Cast from an unsigned long to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_FLOAT: UByte = 0x78u

    /**
     * Cast from an unsigned long to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.ULONG shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val ULONG_TO_DOUBLE: UByte = 0x79u

    /**
     * Cast from a float to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_BYTE: UByte = 0x80u

    /**
     * Cast from a float to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_SHORT: UByte = 0x81u

    /**
     * Cast from a float to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_INT: UByte = 0x82u

    /**
     * Cast from a float to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_LONG: UByte = 0x83u

    /**
     * Cast from a float to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_UBYTE: UByte = 0x84u

    /**
     * Cast from a float to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_USHORT: UByte = 0x85u

    /**
     * Cast from a float to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_UINT: UByte = 0x86u

    /**
     * Cast from a float to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_ULONG: UByte = 0x87u

    /**
     * Cast from a float to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from float to float. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_FLOAT: UByte = 0x88u

    /**
     * Cast from a float to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.FLOAT shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val FLOAT_TO_DOUBLE: UByte = 0x89u

    /**
     * Cast from a doubles to a byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.BYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_BYTE: UByte = 0x90u

    /**
     * Cast from a doubles to a shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.SHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_SHORT: UByte = 0x91u

    /**
     * Cast from a doubles to an int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.INT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_INT: UByte = 0x92u

    /**
     * Cast from a doubles to a long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.LONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_LONG: UByte = 0x93u

    /**
     * Cast from a doubles to an unsigned byte
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.UBYTE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_UBYTE: UByte = 0x94u

    /**
     * Cast from a doubles to an unsigned shorts
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.USHORT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_USHORT: UByte = 0x95u

    /**
     * Cast from a doubles to an unsigned int
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.UINT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_UINT: UByte = 0x96u

    /**
     * Cast from a doubles to an unsigned long
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.ULONG
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_ULONG: UByte = 0x97u

    /**
     * Cast from a doubles to a float
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.FLOAT
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_FLOAT: UByte = 0x98u

    /**
     * Cast from a doubles to a doubles
     *
     * Shortcut for:
     * ```kotlin
     * val castByte = (PCast.DOUBLE shl 4) or PCast.DOUBLE
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @deprecated No use in casting from doubles to doubles. This operation will do nothing.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    const val DOUBLE_TO_DOUBLE: UByte = 0x99u

    /**
     * Invoke is a shortcut for:
     *
     * ```kotlin
     * val castByte = ([from] shl 4) or [to]
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    operator fun invoke(from: UByte, to: UByte): UByte {
        return (from shl 4) or to
    }

    /**
     * Invoke is a shortcut for:
     *
     * ```kotlin
     * val castByte = ([from] shl 4) or [to]
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    @JvmName("invokeByte")
    operator fun invoke(from: Byte, to: Byte): UByte {
        return ((from shl 4) or to).toUByte()
    }

    /**
     * Create shortcut for:
     *
     * ```kotlin
     * val castByte = ([from] shl 4) or [to]
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun create(from: UByte, to: UByte): UByte {
        return (from shl 4) or to
    }

    /**
     * Create shortcut for:
     *
     * ```kotlin
     * val castByte = ([from] shl 4) or [to]
     * ```
     *
     * Get more information about the cast byte in the
     * [PCast Bytecode Specification](https://spec.shakelang.com/bytcode/instruction84-pcast-0xa0)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    @JvmName("createByte")
    fun create(from: Byte, to: Byte): UByte {
        return ((from shl 4) or to).toUByte()
    }
}
