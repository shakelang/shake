package com.shakelang.shake.stdlib

import com.shakelang.shake.shakelib.ShakeLib
import dev.icerock.moko.resources.compose.readTextAsState

object CoreFiles {
    val PRINT_SHAKE = ShakeLib.files.Print.readTextAsState().value ?: throw NullPointerException("Print.shake is null")
    val STRING_SHAKE = ShakeLib.files.String.readTextAsState().value ?: throw NullPointerException("String.shake is null")
    val OBJECT_SHAKE = ShakeLib.files.Object.readTextAsState().value ?: throw NullPointerException("Object.shake is null")
    val NUMBERS_SHAKE = ShakeLib.files.Numbers.readTextAsState().value ?: throw NullPointerException("Numbers.shake is null")
}
