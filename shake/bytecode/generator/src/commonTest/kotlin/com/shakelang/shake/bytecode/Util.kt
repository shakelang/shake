package com.shakelang.shake.bytecode

import com.shakelang.shake.processor.ShakePackageBasedProcessor
import com.shakelang.shake.stdlib.CoreFiles

fun createBaseProcessor(): ShakePackageBasedProcessor {
    val processor = ShakePackageBasedProcessor()
    processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
    processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
    processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
    processor.loadSynthetic("shake/lang/Print.shake", CoreFiles.PRINT_SHAKE)

    return processor
}
