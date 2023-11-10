package io.github.shakelang.shake.js.native

import io.github.shakelang.shake.processor.program.types.ShakeClass
import io.github.shakelang.shake.processor.program.types.ShakeMethod

object JsNatives {

    val nativeClasses = listOf<NativeClass>(
        io.github.shakelang.shake.js.native.shake.lang.String()
        // io.github.shakelang.shake.js.native.shake.lang.Object(),
    )

    val nativeFunctions = listOf(
        io.github.shakelang.shake.js.native.shake.js.Js(),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$B\$plus(B)B"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$B\$minus(B)B"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$B\$times(B)B"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$B\$div(B)B"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$B\$mod(B)B"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$B\$pow(B)B"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$B\$plus(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$B\$minus(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$B\$times(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$B\$div(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$B\$mod(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$B\$pow(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$B\$plus(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$B\$minus(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$B\$times(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$B\$div(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$B\$mod(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$B\$pow(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$B\$plus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$B\$minus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$B\$times(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$B\$div(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$B\$mod(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$B\$pow(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$B\$plus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$B\$minus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$B\$times(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$B\$div(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$B\$mod(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$B\$pow(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$B\$plus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$B\$minus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$B\$times(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$B\$div(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$B\$mod(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$B\$pow(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$S\$plus(B)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$S\$minus(B)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$S\$times(B)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$S\$div(B)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$S\$mod(B)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$S\$pow(B)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$S\$plus(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$S\$minus(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$S\$times(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$S\$div(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$S\$mod(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$S\$pow(S)S"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$S\$plus(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$S\$minus(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$S\$times(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$S\$div(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$S\$mod(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$S\$pow(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$S\$plus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$S\$minus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$S\$times(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$S\$div(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$S\$mod(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$S\$pow(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$S\$plus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$S\$minus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$S\$times(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$S\$div(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$S\$mod(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$S\$pow(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$S\$plus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$S\$minus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$S\$times(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$S\$div(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$S\$mod(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$S\$pow(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$I\$plus(B)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$I\$minus(B)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$I\$times(B)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$I\$div(B)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$I\$mod(B)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$I\$pow(B)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$I\$plus(S)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$I\$minus(S)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$I\$times(S)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$I\$div(S)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$I\$mod(S)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$I\$pow(S)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$I\$plus(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$I\$minus(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$I\$times(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$I\$div(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$I\$mod(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$I\$pow(I)I"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$I\$plus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$I\$minus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$I\$times(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$I\$div(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$I\$mod(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$I\$pow(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$I\$plus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$I\$minus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$I\$times(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$I\$div(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$I\$mod(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$I\$pow(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$I\$plus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$I\$minus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$I\$times(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$I\$div(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$I\$mod(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$I\$pow(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$J\$plus(B)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$J\$minus(B)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$J\$times(B)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$J\$div(B)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$J\$mod(B)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$J\$pow(B)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$J\$plus(S)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$J\$minus(S)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$J\$times(S)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$J\$div(S)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$J\$mod(S)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$J\$pow(S)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$J\$plus(I)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$J\$minus(I)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$J\$times(I)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$J\$div(I)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$J\$mod(I)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$J\$pow(I)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$J\$plus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$J\$minus(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$J\$times(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$J\$div(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$J\$mod(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$J\$pow(J)J"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$J\$plus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$J\$minus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$J\$times(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$J\$div(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$J\$mod(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$J\$pow(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$J\$plus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$J\$minus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$J\$times(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$J\$div(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$J\$mod(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$J\$pow(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$F\$plus(B)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$F\$minus(B)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$F\$times(B)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$F\$div(B)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$F\$mod(B)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$F\$pow(B)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$F\$plus(S)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$F\$minus(S)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$F\$times(S)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$F\$div(S)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$F\$mod(S)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$F\$pow(S)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$F\$plus(I)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$F\$minus(I)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$F\$times(I)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$F\$div(I)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$F\$mod(I)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$F\$pow(I)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$F\$plus(J)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$F\$minus(J)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$F\$times(J)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$F\$div(J)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$F\$mod(J)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$F\$pow(J)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$F\$plus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$F\$minus(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$F\$times(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$F\$div(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$F\$mod(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$F\$pow(F)F"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$F\$plus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$F\$minus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$F\$times(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$F\$div(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$F\$mod(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$F\$pow(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$D\$plus(B)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$D\$minus(B)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$D\$times(B)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$D\$div(B)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$D\$mod(B)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$D\$pow(B)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$D\$plus(S)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$D\$minus(S)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$D\$times(S)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$D\$div(S)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$D\$mod(S)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$D\$pow(S)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$D\$plus(I)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$D\$minus(I)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$D\$times(I)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$D\$div(I)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$D\$mod(I)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$D\$pow(I)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$D\$plus(J)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$D\$minus(J)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$D\$times(J)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$D\$div(J)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$D\$mod(J)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$D\$pow(J)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$D\$plus(F)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$D\$minus(F)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$D\$times(F)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$D\$div(F)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$D\$mod(F)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$D\$pow(F)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Plus("shake.lang\$D\$plus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Minus("shake.lang\$D\$minus(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Times("shake.lang\$D\$times(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Div("shake.lang\$D\$div(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Mod("shake.lang\$D\$mod(D)D"),
        io.github.shakelang.shake.js.native.shake.lang.Number.Pow("shake.lang\$D\$pow(D)D")
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
