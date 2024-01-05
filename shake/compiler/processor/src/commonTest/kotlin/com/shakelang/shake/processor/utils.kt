package com.shakelang.shake.processor

import com.shakelang.shake.stdlib.CoreFiles

/**
 * Create a processor with the basic apis
 */
fun createBaseProcessor(): ShakePackageBasedProcessor {
    val processor = ShakePackageBasedProcessor()
    processor.loadSynthetic("shake/lang/Object.shake", CoreFiles.OBJECT_SHAKE)
    processor.loadSynthetic("shake/lang/String.shake", CoreFiles.STRING_SHAKE)
    processor.loadSynthetic("shake/lang/Numbers.shake", CoreFiles.NUMBERS_SHAKE)
    return processor
}
