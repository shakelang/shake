package com.shakelang.shake.shasambly.interpreter

import com.shakelang.shake.util.parseutils.generateUInt

object PrimitiveIds {

    val PRIMITIVE_BYTE = generateUInt(true)
    val PRIMITIVE_UNSIGNED_BYTE = generateUInt()
    val PRIMITIVE_SHORT = generateUInt()
    val PRIMITIVE_UNSIGNED_SHORT = generateUInt()
    val PRIMITIVE_INT = generateUInt()
    val PRIMITIVE_UNSIGNED_INT = generateUInt()
    val PRIMITIVE_LONG = generateUInt()
    val PRIMITIVE_UNSIGNED_LONG = generateUInt()
    val PRIMITIVE_FLOAT = generateUInt()
    val PRIMITIVE_DOUBLE = generateUInt()
    val PRIMITIVE_BOOLEAN = generateUInt()
    val PRIMITIVE_CHAR = generateUInt()
}
