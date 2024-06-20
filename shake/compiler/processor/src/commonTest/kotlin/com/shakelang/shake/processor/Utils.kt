package com.shakelang.shake.processor

import com.shakelang.shake.processor.program.map.InformationConverter
import com.shakelang.shake.processor.program.map.information.ProjectInformation
import com.shakelang.shake.shakelib.ShakeLib

val baseInfo = createBaseInfo()

fun createBaseInfo(): ProjectInformation {
    val processor = ShakePackageBasedProcessor()
    ShakeLib.forEachFile {
        processor.loadSynthetic(it.path, it.contentsAsString())
    }
    val project = processor.finish()
    return InformationConverter.toInformation(project)
}

/**
 * Create a processor with the basic apis
 */
fun createBaseProcessor(): ShakePackageBasedProcessor {
    val project = InformationConverter.recreate(baseInfo)
    return ShakePackageBasedProcessor(project)
}
