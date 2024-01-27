package com.shakelang.shake.stdlib

import com.shakelang.shake.shakelib.ShakeLib
import dev.icerock.moko.resources.compose.readTextAsState

object CoreFiles {
    val PRINT_SHAKE = ShakeLib.files.Print.readTextAsState().value
    val STRING_SHAKE = ShakeLib.files.String.readTextAsState().value
    val OBJECT_SHAKE = ShakeLib.files.Object.readTextAsState().value
    val NUMBERS_SHAKE = ShakeLib.files.Numbers.readTextAsState().value
}
