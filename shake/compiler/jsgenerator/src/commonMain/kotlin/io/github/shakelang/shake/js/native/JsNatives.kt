package io.github.shakelang.shake.js.native

import io.github.shakelang.shake.processor.program.types.ShakeClass
import io.github.shakelang.shake.processor.program.types.ShakeMethod

object JsNatives {

    val nativeClasses = listOf<NativeClass>(
        io.github.shakelang.shake.js.native.shake.lang.String(),
    )

    val nativeFunctions = listOf<NativeFunction>()
    val nativeFields = listOf<NativeField>()

    fun getNativeClass(qualifiedName: String): NativeClass? {
        return nativeClasses.firstOrNull { it.qualifiedName == qualifiedName }
    }

    fun getNativeClass(clazz: ShakeClass): NativeClass {
        if(!clazz.isNative) {
            throw IllegalArgumentException("Class is not native")
        }
        return getNativeClass(clazz.qualifiedName) ?: throw IllegalArgumentException("No matching native class found")
    }

    fun getNativeFunction(fn: ShakeMethod): NativeFunction {
        if(!fn.isNative) {
            throw IllegalArgumentException("Function is not native")
        }
        if(fn.clazz != null) {
            return getNativeClass(fn.clazz!!)
                .functions
                .firstOrNull { it.signature == fn.name }
                    ?: throw IllegalArgumentException("No matching native function found")
        }
        return nativeFunctions.firstOrNull { it.signature == fn.qualifiedName }
            ?: throw IllegalArgumentException("No matching native function found")
    }

    fun getNativeField(field: ShakeMethod): NativeField {
        if(!field.isNative) {
            throw IllegalArgumentException("Field is not native")
        }
        if(field.clazz != null) {
            return getNativeClass(field.clazz!!)
                .fields
                .firstOrNull { it.signature == field.name }
                    ?: throw IllegalArgumentException("No matching native field found")
        }
        return nativeFields.firstOrNull { it.signature == field.qualifiedName }
            ?: throw IllegalArgumentException("No matching native field found")
    }




}