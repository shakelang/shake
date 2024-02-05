package com.shakelang.shake.processor

import com.shakelang.shake.shakelib.ShakeLib

/**
 * Create a processor with the basic apis
 */
fun createBaseProcessor(): ShakePackageBasedProcessor {
    val processor = ShakePackageBasedProcessor()
    ShakeLib.forEachFile {
        processor.loadSynthetic(it.path, it.contentsAsString())
    }
    return processor
}
