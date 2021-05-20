package com.github.nsc.de.shake.util;

import java.lang.reflect.Array;
import java.util.function.Function;

public class ArrayUtil {

    @SuppressWarnings("unchecked")
    public static <S,T> T[] map(S[] array, T[] target, Function<S, T> fun) {
        if(target.length < array.length) target = (T[]) Array.newInstance(target.getClass().getComponentType(), array.length);
        for(int i = 0 ; i < array.length; i++) target[i] = fun.apply(array[i]);
        return target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] map(byte[] array, T[] target, Function<Byte, T> fun) {
        if(target.length < array.length) target = (T[]) Array.newInstance(target.getClass().getComponentType(), array.length);
        for(int i = 0 ; i < array.length; i++) target[i] = fun.apply(array[i]);
        return target;
    }
}
