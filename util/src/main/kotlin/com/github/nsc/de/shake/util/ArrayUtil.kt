package com.github.nsc.de.shake.util

import java.util.function.Function

object ArrayUtil {

    @JvmStatic
    fun <S, T> map(array: Array<S>, target: Array<T>, f: Function<S, T>): Array<T> {
        var target = target
        if (target.size < array.size) target =
            java.lang.reflect.Array.newInstance(target.javaClass.componentType, array.size) as Array<T>
        for (i in array.indices) target[i] = f.apply(array[i])
        return target
    }

    @JvmStatic
    fun <T> map(array: ByteArray, target: Array<T>, f: Function<Byte?, T>): Array<T> {
        var target = target
        if (target.size < array.size) target =
            java.lang.reflect.Array.newInstance(target.javaClass.componentType, array.size) as Array<T>
        for (i in array.indices) target[i] = f.apply(array[i])
        return target
    }
    
}