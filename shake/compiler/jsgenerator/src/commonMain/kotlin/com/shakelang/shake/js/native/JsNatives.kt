package com.shakelang.shake.js.native

import com.shakelang.shake.js.native.shake.js.Js
import com.shakelang.shake.js.native.shake.lang.Number
import com.shakelang.shake.processor.program.types.ShakeClass
import com.shakelang.shake.processor.program.types.ShakeMethod

object JsNatives {

    val nativeClasses = listOf<NativeClass>(
        com.shakelang.shake.js.native.shake.lang.String()
        // com.shakelang.shake.js.native.shake.lang.Object(),
    )

    val nativeFunctions = listOf(
        Js(),
        Number.Plus("shake.lang\$B\$plus(B)B"),
        Number.Minus("shake.lang\$B\$minus(B)B"),
        Number.Times("shake.lang\$B\$times(B)B"),
        Number.Div("shake.lang\$B\$div(B)B"),
        Number.Mod("shake.lang\$B\$mod(B)B"),
        Number.Pow("shake.lang\$B\$pow(B)B"),
        Number.Plus("shake.lang\$B\$plus(S)S"),
        Number.Minus("shake.lang\$B\$minus(S)S"),
        Number.Times("shake.lang\$B\$times(S)S"),
        Number.Div("shake.lang\$B\$div(S)S"),
        Number.Mod("shake.lang\$B\$mod(S)S"),
        Number.Pow("shake.lang\$B\$pow(S)S"),
        Number.Plus("shake.lang\$B\$plus(I)I"),
        Number.Minus("shake.lang\$B\$minus(I)I"),
        Number.Times("shake.lang\$B\$times(I)I"),
        Number.Div("shake.lang\$B\$div(I)I"),
        Number.Mod("shake.lang\$B\$mod(I)I"),
        Number.Pow("shake.lang\$B\$pow(I)I"),
        Number.Plus("shake.lang\$B\$plus(J)J"),
        Number.Minus("shake.lang\$B\$minus(J)J"),
        Number.Times("shake.lang\$B\$times(J)J"),
        Number.Div("shake.lang\$B\$div(J)J"),
        Number.Mod("shake.lang\$B\$mod(J)J"),
        Number.Pow("shake.lang\$B\$pow(J)J"),
        Number.Plus("shake.lang\$B\$plus(F)F"),
        Number.Minus("shake.lang\$B\$minus(F)F"),
        Number.Times("shake.lang\$B\$times(F)F"),
        Number.Div("shake.lang\$B\$div(F)F"),
        Number.Mod("shake.lang\$B\$mod(F)F"),
        Number.Pow("shake.lang\$B\$pow(F)F"),
        Number.Plus("shake.lang\$B\$plus(D)D"),
        Number.Minus("shake.lang\$B\$minus(D)D"),
        Number.Times("shake.lang\$B\$times(D)D"),
        Number.Div("shake.lang\$B\$div(D)D"),
        Number.Mod("shake.lang\$B\$mod(D)D"),
        Number.Pow("shake.lang\$B\$pow(D)D"),
        Number.Plus("shake.lang\$S\$plus(B)S"),
        Number.Minus("shake.lang\$S\$minus(B)S"),
        Number.Times("shake.lang\$S\$times(B)S"),
        Number.Div("shake.lang\$S\$div(B)S"),
        Number.Mod("shake.lang\$S\$mod(B)S"),
        Number.Pow("shake.lang\$S\$pow(B)S"),
        Number.Plus("shake.lang\$S\$plus(S)S"),
        Number.Minus("shake.lang\$S\$minus(S)S"),
        Number.Times("shake.lang\$S\$times(S)S"),
        Number.Div("shake.lang\$S\$div(S)S"),
        Number.Mod("shake.lang\$S\$mod(S)S"),
        Number.Pow("shake.lang\$S\$pow(S)S"),
        Number.Plus("shake.lang\$S\$plus(I)I"),
        Number.Minus("shake.lang\$S\$minus(I)I"),
        Number.Times("shake.lang\$S\$times(I)I"),
        Number.Div("shake.lang\$S\$div(I)I"),
        Number.Mod("shake.lang\$S\$mod(I)I"),
        Number.Pow("shake.lang\$S\$pow(I)I"),
        Number.Plus("shake.lang\$S\$plus(J)J"),
        Number.Minus("shake.lang\$S\$minus(J)J"),
        Number.Times("shake.lang\$S\$times(J)J"),
        Number.Div("shake.lang\$S\$div(J)J"),
        Number.Mod("shake.lang\$S\$mod(J)J"),
        Number.Pow("shake.lang\$S\$pow(J)J"),
        Number.Plus("shake.lang\$S\$plus(F)F"),
        Number.Minus("shake.lang\$S\$minus(F)F"),
        Number.Times("shake.lang\$S\$times(F)F"),
        Number.Div("shake.lang\$S\$div(F)F"),
        Number.Mod("shake.lang\$S\$mod(F)F"),
        Number.Pow("shake.lang\$S\$pow(F)F"),
        Number.Plus("shake.lang\$S\$plus(D)D"),
        Number.Minus("shake.lang\$S\$minus(D)D"),
        Number.Times("shake.lang\$S\$times(D)D"),
        Number.Div("shake.lang\$S\$div(D)D"),
        Number.Mod("shake.lang\$S\$mod(D)D"),
        Number.Pow("shake.lang\$S\$pow(D)D"),
        Number.Plus("shake.lang\$I\$plus(B)I"),
        Number.Minus("shake.lang\$I\$minus(B)I"),
        Number.Times("shake.lang\$I\$times(B)I"),
        Number.Div("shake.lang\$I\$div(B)I"),
        Number.Mod("shake.lang\$I\$mod(B)I"),
        Number.Pow("shake.lang\$I\$pow(B)I"),
        Number.Plus("shake.lang\$I\$plus(S)I"),
        Number.Minus("shake.lang\$I\$minus(S)I"),
        Number.Times("shake.lang\$I\$times(S)I"),
        Number.Div("shake.lang\$I\$div(S)I"),
        Number.Mod("shake.lang\$I\$mod(S)I"),
        Number.Pow("shake.lang\$I\$pow(S)I"),
        Number.Plus("shake.lang\$I\$plus(I)I"),
        Number.Minus("shake.lang\$I\$minus(I)I"),
        Number.Times("shake.lang\$I\$times(I)I"),
        Number.Div("shake.lang\$I\$div(I)I"),
        Number.Mod("shake.lang\$I\$mod(I)I"),
        Number.Pow("shake.lang\$I\$pow(I)I"),
        Number.Plus("shake.lang\$I\$plus(J)J"),
        Number.Minus("shake.lang\$I\$minus(J)J"),
        Number.Times("shake.lang\$I\$times(J)J"),
        Number.Div("shake.lang\$I\$div(J)J"),
        Number.Mod("shake.lang\$I\$mod(J)J"),
        Number.Pow("shake.lang\$I\$pow(J)J"),
        Number.Plus("shake.lang\$I\$plus(F)F"),
        Number.Minus("shake.lang\$I\$minus(F)F"),
        Number.Times("shake.lang\$I\$times(F)F"),
        Number.Div("shake.lang\$I\$div(F)F"),
        Number.Mod("shake.lang\$I\$mod(F)F"),
        Number.Pow("shake.lang\$I\$pow(F)F"),
        Number.Plus("shake.lang\$I\$plus(D)D"),
        Number.Minus("shake.lang\$I\$minus(D)D"),
        Number.Times("shake.lang\$I\$times(D)D"),
        Number.Div("shake.lang\$I\$div(D)D"),
        Number.Mod("shake.lang\$I\$mod(D)D"),
        Number.Pow("shake.lang\$I\$pow(D)D"),
        Number.Plus("shake.lang\$J\$plus(B)J"),
        Number.Minus("shake.lang\$J\$minus(B)J"),
        Number.Times("shake.lang\$J\$times(B)J"),
        Number.Div("shake.lang\$J\$div(B)J"),
        Number.Mod("shake.lang\$J\$mod(B)J"),
        Number.Pow("shake.lang\$J\$pow(B)J"),
        Number.Plus("shake.lang\$J\$plus(S)J"),
        Number.Minus("shake.lang\$J\$minus(S)J"),
        Number.Times("shake.lang\$J\$times(S)J"),
        Number.Div("shake.lang\$J\$div(S)J"),
        Number.Mod("shake.lang\$J\$mod(S)J"),
        Number.Pow("shake.lang\$J\$pow(S)J"),
        Number.Plus("shake.lang\$J\$plus(I)J"),
        Number.Minus("shake.lang\$J\$minus(I)J"),
        Number.Times("shake.lang\$J\$times(I)J"),
        Number.Div("shake.lang\$J\$div(I)J"),
        Number.Mod("shake.lang\$J\$mod(I)J"),
        Number.Pow("shake.lang\$J\$pow(I)J"),
        Number.Plus("shake.lang\$J\$plus(J)J"),
        Number.Minus("shake.lang\$J\$minus(J)J"),
        Number.Times("shake.lang\$J\$times(J)J"),
        Number.Div("shake.lang\$J\$div(J)J"),
        Number.Mod("shake.lang\$J\$mod(J)J"),
        Number.Pow("shake.lang\$J\$pow(J)J"),
        Number.Plus("shake.lang\$J\$plus(F)F"),
        Number.Minus("shake.lang\$J\$minus(F)F"),
        Number.Times("shake.lang\$J\$times(F)F"),
        Number.Div("shake.lang\$J\$div(F)F"),
        Number.Mod("shake.lang\$J\$mod(F)F"),
        Number.Pow("shake.lang\$J\$pow(F)F"),
        Number.Plus("shake.lang\$J\$plus(D)D"),
        Number.Minus("shake.lang\$J\$minus(D)D"),
        Number.Times("shake.lang\$J\$times(D)D"),
        Number.Div("shake.lang\$J\$div(D)D"),
        Number.Mod("shake.lang\$J\$mod(D)D"),
        Number.Pow("shake.lang\$J\$pow(D)D"),
        Number.Plus("shake.lang\$F\$plus(B)F"),
        Number.Minus("shake.lang\$F\$minus(B)F"),
        Number.Times("shake.lang\$F\$times(B)F"),
        Number.Div("shake.lang\$F\$div(B)F"),
        Number.Mod("shake.lang\$F\$mod(B)F"),
        Number.Pow("shake.lang\$F\$pow(B)F"),
        Number.Plus("shake.lang\$F\$plus(S)F"),
        Number.Minus("shake.lang\$F\$minus(S)F"),
        Number.Times("shake.lang\$F\$times(S)F"),
        Number.Div("shake.lang\$F\$div(S)F"),
        Number.Mod("shake.lang\$F\$mod(S)F"),
        Number.Pow("shake.lang\$F\$pow(S)F"),
        Number.Plus("shake.lang\$F\$plus(I)F"),
        Number.Minus("shake.lang\$F\$minus(I)F"),
        Number.Times("shake.lang\$F\$times(I)F"),
        Number.Div("shake.lang\$F\$div(I)F"),
        Number.Mod("shake.lang\$F\$mod(I)F"),
        Number.Pow("shake.lang\$F\$pow(I)F"),
        Number.Plus("shake.lang\$F\$plus(J)F"),
        Number.Minus("shake.lang\$F\$minus(J)F"),
        Number.Times("shake.lang\$F\$times(J)F"),
        Number.Div("shake.lang\$F\$div(J)F"),
        Number.Mod("shake.lang\$F\$mod(J)F"),
        Number.Pow("shake.lang\$F\$pow(J)F"),
        Number.Plus("shake.lang\$F\$plus(F)F"),
        Number.Minus("shake.lang\$F\$minus(F)F"),
        Number.Times("shake.lang\$F\$times(F)F"),
        Number.Div("shake.lang\$F\$div(F)F"),
        Number.Mod("shake.lang\$F\$mod(F)F"),
        Number.Pow("shake.lang\$F\$pow(F)F"),
        Number.Plus("shake.lang\$F\$plus(D)D"),
        Number.Minus("shake.lang\$F\$minus(D)D"),
        Number.Times("shake.lang\$F\$times(D)D"),
        Number.Div("shake.lang\$F\$div(D)D"),
        Number.Mod("shake.lang\$F\$mod(D)D"),
        Number.Pow("shake.lang\$F\$pow(D)D"),
        Number.Plus("shake.lang\$D\$plus(B)D"),
        Number.Minus("shake.lang\$D\$minus(B)D"),
        Number.Times("shake.lang\$D\$times(B)D"),
        Number.Div("shake.lang\$D\$div(B)D"),
        Number.Mod("shake.lang\$D\$mod(B)D"),
        Number.Pow("shake.lang\$D\$pow(B)D"),
        Number.Plus("shake.lang\$D\$plus(S)D"),
        Number.Minus("shake.lang\$D\$minus(S)D"),
        Number.Times("shake.lang\$D\$times(S)D"),
        Number.Div("shake.lang\$D\$div(S)D"),
        Number.Mod("shake.lang\$D\$mod(S)D"),
        Number.Pow("shake.lang\$D\$pow(S)D"),
        Number.Plus("shake.lang\$D\$plus(I)D"),
        Number.Minus("shake.lang\$D\$minus(I)D"),
        Number.Times("shake.lang\$D\$times(I)D"),
        Number.Div("shake.lang\$D\$div(I)D"),
        Number.Mod("shake.lang\$D\$mod(I)D"),
        Number.Pow("shake.lang\$D\$pow(I)D"),
        Number.Plus("shake.lang\$D\$plus(J)D"),
        Number.Minus("shake.lang\$D\$minus(J)D"),
        Number.Times("shake.lang\$D\$times(J)D"),
        Number.Div("shake.lang\$D\$div(J)D"),
        Number.Mod("shake.lang\$D\$mod(J)D"),
        Number.Pow("shake.lang\$D\$pow(J)D"),
        Number.Plus("shake.lang\$D\$plus(F)D"),
        Number.Minus("shake.lang\$D\$minus(F)D"),
        Number.Times("shake.lang\$D\$times(F)D"),
        Number.Div("shake.lang\$D\$div(F)D"),
        Number.Mod("shake.lang\$D\$mod(F)D"),
        Number.Pow("shake.lang\$D\$pow(F)D"),
        Number.Plus("shake.lang\$D\$plus(D)D"),
        Number.Minus("shake.lang\$D\$minus(D)D"),
        Number.Times("shake.lang\$D\$times(D)D"),
        Number.Div("shake.lang\$D\$div(D)D"),
        Number.Mod("shake.lang\$D\$mod(D)D"),
        Number.Pow("shake.lang\$D\$pow(D)D")
    )
    val nativeFields = listOf<NativeField>()

    fun getNativeClass(qualifiedName: String): NativeClass? {
        return nativeClasses.firstOrNull { it.qualifiedName == qualifiedName }
    }

    fun getNativeClass(clazz: ShakeClass): NativeClass {
        if (!clazz.isNative) {
            throw IllegalArgumentException("Class is not native")
        }
        return getNativeClass(clazz.qualifiedName) ?: throw IllegalArgumentException("No matching native class found")
    }

    fun getNativeFunction(fn: ShakeMethod): NativeFunction {
        if (!fn.isNative) {
            throw IllegalArgumentException("Function is not native")
        }
        if (fn.clazz != null) {
            return getNativeClass(fn.clazz!!)
                .functions
                .firstOrNull {
                    it.signature == fn.signature
                }
                ?: throw IllegalArgumentException("No matching native function found ${fn.qualifiedSignature}")
        }
        return nativeFunctions.firstOrNull {
            it.signature == fn.qualifiedSignature
        }
            ?: throw IllegalArgumentException("No matching native function found for ${fn.qualifiedSignature}")
    }

    fun getNativeField(field: ShakeMethod): NativeField {
        if (!field.isNative) {
            throw IllegalArgumentException("Field is not native")
        }
        if (field.clazz != null) {
            return getNativeClass(field.clazz!!)
                .fields
                .firstOrNull { it.signature == field.name }
                ?: throw IllegalArgumentException("No matching native field found")
        }
        return nativeFields.firstOrNull { it.signature == field.qualifiedName }
            ?: throw IllegalArgumentException("No matching native field found")
    }
}
