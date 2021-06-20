package com.github.shakelang.shake.interpreter.values

import com.github.shakelang.shake.interpreter.values.BooleanValue.Companion.from

actual fun createInterpreterValueOf(value: Any?): InterpreterValue {
    if (value == null || value == undefined || value is Unit) return NullValue.NULL
    if (value is Byte) return IntegerValue(value.toInt())
    if (value is Short) return IntegerValue(value.toByte().toInt())
    if (value is Int) return IntegerValue((value as Int?)!!)
    if (value is Long) return IntegerValue(value.toInt())
    if (value is Float) return DoubleValue(value.toDouble())
    if (value is Double) return DoubleValue((value as Double?)!!)
    if (value is Boolean) return from((value as Boolean?)!!)
    if (value is Char) return CharacterValue((value as Char?)!!)
    //if (value is KClass<*>) return Java.JavaClass((value as KClass<*>?)!!)
    /*return if (value is String) StringValue((value as String?)!!) else Java.JavaValue<Any?>(
        value::class,
        value
    )*/
    console.log(value)
    TODO("Other objects")
}