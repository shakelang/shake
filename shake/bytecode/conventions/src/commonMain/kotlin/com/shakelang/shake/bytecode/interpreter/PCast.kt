package com.shakelang.shake.bytecode.interpreter

object PCast {

    const val BYTE: UByte = 0u
    const val SHORT: UByte = 1u
    const val INT: UByte = 2u
    const val LONG: UByte = 3u
    const val UBYTE: UByte = 4u
    const val USHORT: UByte = 5u
    const val UINT: UByte = 6u
    const val ULONG: UByte = 7u
    const val FLOAT: UByte = 8u
    const val DOUBLE: UByte = 9u

    /** @deprecated (No use in casting from byte to byte) */
    const val BYTE_TO_BYTE: UByte = 0x00u
    const val BYTE_TO_SHORT: UByte = 0x01u
    const val BYTE_TO_INT: UByte = 0x02u
    const val BYTE_TO_LONG: UByte = 0x03u
    const val BYTE_TO_UBYTE: UByte = 0x04u
    const val BYTE_TO_USHORT: UByte = 0x05u
    const val BYTE_TO_UINT: UByte = 0x06u
    const val BYTE_TO_ULONG: UByte = 0x07u
    const val BYTE_TO_FLOAT: UByte = 0x08u
    const val BYTE_TO_DOUBLE: UByte = 0x09u

    const val SHORT_TO_BYTE: UByte = 0x10u
    /** @deprecated (No use in casting from short to short)  */
    const val SHORT_TO_SHORT: UByte = 0x11u
    const val SHORT_TO_INT: UByte = 0x12u
    const val SHORT_TO_LONG: UByte = 0x13u
    const val SHORT_TO_UBYTE: UByte = 0x14u
    const val SHORT_TO_USHORT: UByte = 0x15u
    const val SHORT_TO_UINT: UByte = 0x16u
    const val SHORT_TO_ULONG: UByte = 0x17u
    const val SHORT_TO_FLOAT: UByte = 0x18u
    const val SHORT_TO_DOUBLE: UByte = 0x19u

    const val INT_TO_BYTE: UByte = 0x20u
    const val INT_TO_SHORT: UByte = 0x21u
    /** @deprecated (No use in casting from int to int)  */
    const val INT_TO_INT: UByte = 0x22u
    const val INT_TO_LONG: UByte = 0x23u
    const val INT_TO_UBYTE: UByte = 0x24u
    const val INT_TO_USHORT: UByte = 0x25u
    const val INT_TO_UINT: UByte = 0x26u
    const val INT_TO_ULONG: UByte = 0x27u
    const val INT_TO_FLOAT: UByte = 0x28u
    const val INT_TO_DOUBLE: UByte = 0x29u

    const val LONG_TO_BYTE: UByte = 0x30u
    const val LONG_TO_SHORT: UByte = 0x31u
    const val LONG_TO_INT: UByte = 0x32u
    /** @deprecated (No use in casting from long to long)  */
    const val LONG_TO_LONG: UByte = 0x33u
    const val LONG_TO_UBYTE: UByte = 0x34u
    const val LONG_TO_USHORT: UByte = 0x35u
    const val LONG_TO_UINT: UByte = 0x36u
    const val LONG_TO_ULONG: UByte = 0x37u
    const val LONG_TO_FLOAT: UByte = 0x38u
    const val LONG_TO_DOUBLE: UByte = 0x39u

    const val UBYTE_TO_BYTE: UByte = 0x40u
    const val UBYTE_TO_SHORT: UByte = 0x41u
    const val UBYTE_TO_INT: UByte = 0x42u
    const val UBYTE_TO_LONG: UByte = 0x43u
    /** @deprecated (No use in casting from unsigned byte to unsigned byte)  */
    const val UBYTE_TO_UBYTE: UByte = 0x44u
    const val UBYTE_TO_USHORT: UByte = 0x45u
    const val UBYTE_TO_UINT: UByte = 0x46u
    const val UBYTE_TO_ULONG: UByte = 0x47u
    const val UBYTE_TO_FLOAT: UByte = 0x48u
    const val UBYTE_TO_DOUBLE: UByte = 0x49u

    const val USHORT_TO_BYTE: UByte = 0x50u
    const val USHORT_TO_SHORT: UByte = 0x51u
    const val USHORT_TO_INT: UByte = 0x52u
    const val USHORT_TO_LONG: UByte = 0x53u
    const val USHORT_TO_UBYTE: UByte = 0x54u
    /** @deprecated (No use in casting from unsigned short to unsigned short)  */
    const val USHORT_TO_USHORT: UByte = 0x55u
    const val USHORT_TO_UINT: UByte = 0x56u
    const val USHORT_TO_ULONG: UByte = 0x57u
    const val USHORT_TO_FLOAT: UByte = 0x58u
    const val USHORT_TO_DOUBLE: UByte = 0x59u

    const val UINT_TO_BYTE: UByte = 0x60u
    const val UINT_TO_SHORT: UByte = 0x61u
    const val UINT_TO_INT: UByte = 0x62u
    const val UINT_TO_LONG: UByte = 0x63u
    const val UINT_TO_UBYTE: UByte = 0x64u
    const val UINT_TO_USHORT: UByte = 0x65u
    /** @deprecated (No use in casting from unsigned int to unsigned int)  */
    const val UINT_TO_UINT: UByte = 0x66u
    const val UINT_TO_ULONG: UByte = 0x67u
    const val UINT_TO_FLOAT: UByte = 0x68u
    const val UINT_TO_DOUBLE: UByte = 0x69u

    const val ULONG_TO_BYTE: UByte = 0x70u
    const val ULONG_TO_SHORT: UByte = 0x71u
    const val ULONG_TO_INT: UByte = 0x72u
    const val ULONG_TO_LONG: UByte = 0x73u
    const val ULONG_TO_UBYTE: UByte = 0x74u
    const val ULONG_TO_USHORT: UByte = 0x75u
    const val ULONG_TO_UINT: UByte = 0x76u
    /** @deprecated (No use in casting from unsigned long to unsigned long)  */
    const val ULONG_TO_ULONG: UByte = 0x77u
    const val ULONG_TO_FLOAT: UByte = 0x78u
    const val ULONG_TO_DOUBLE: UByte = 0x79u

    const val FLOAT_TO_BYTE: UByte = 0x80u
    const val FLOAT_TO_SHORT: UByte = 0x81u
    const val FLOAT_TO_INT: UByte = 0x82u
    const val FLOAT_TO_LONG: UByte = 0x83u
    const val FLOAT_TO_UBYTE: UByte = 0x84u
    const val FLOAT_TO_USHORT: UByte = 0x85u
    const val FLOAT_TO_UINT: UByte = 0x86u
    const val FLOAT_TO_ULONG: UByte = 0x87u
    /** @deprecated (No use in casting from float to float)  */
    const val FLOAT_TO_FLOAT: UByte = 0x88u
    const val FLOAT_TO_DOUBLE: UByte = 0x89u

    const val DOUBLE_TO_BYTE: UByte = 0x90u
    const val DOUBLE_TO_SHORT: UByte = 0x91u
    const val DOUBLE_TO_INT: UByte = 0x92u
    const val DOUBLE_TO_LONG: UByte = 0x93u
    const val DOUBLE_TO_UBYTE: UByte = 0x94u
    const val DOUBLE_TO_USHORT: UByte = 0x95u
    const val DOUBLE_TO_UINT: UByte = 0x96u
    const val DOUBLE_TO_ULONG: UByte = 0x97u
    const val DOUBLE_TO_FLOAT: UByte = 0x98u
    /** @deprecated (No use in casting from double to double)  */
    const val DOUBLE_TO_DOUBLE: UByte = 0x99u




}